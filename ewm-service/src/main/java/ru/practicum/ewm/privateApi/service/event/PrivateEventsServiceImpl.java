package ru.practicum.ewm.privateApi.service.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import ru.practicum.ewm.base.dao.CategoriesRepository;
import ru.practicum.ewm.base.dao.EventRepository;
import ru.practicum.ewm.base.dao.RequestRepository;
import ru.practicum.ewm.base.dao.UserRepository;
import ru.practicum.ewm.base.dto.*;
import ru.practicum.ewm.base.dto.event.*;
import ru.practicum.ewm.base.enums.State;
import ru.practicum.ewm.base.enums.Status;
import ru.practicum.ewm.base.exception.ConflictException;
import ru.practicum.ewm.base.exception.NotFoundException;
import ru.practicum.ewm.base.mapper.CompilationMapper;
import ru.practicum.ewm.base.mapper.EventMapper;
import ru.practicum.ewm.base.mapper.RequestMapper;
import ru.practicum.ewm.base.model.Compilation;
import ru.practicum.ewm.base.model.Event;
import ru.practicum.ewm.base.model.Request;
import ru.practicum.ewm.base.util.UtilMergeProperty;
import ru.practicum.ewm.base.util.page.MyPageRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class PrivateEventsServiceImpl implements PrivateEventsService {

    private final EventRepository eventRepository;

    private final UserRepository userRepository;

    private final RequestRepository requestRepository;

    private final CategoriesRepository categoriesRepository;

    @Override
    public List<EventShortDto> getAll(Long userId, Integer from, Integer size) {
        MyPageRequest pageRequest = new MyPageRequest(from, size,
                Sort.by(Sort.Direction.ASC, "event_id"));
        List<EventShortDto> eventShorts = EventMapper.toEventShortDtoList(eventRepository.findAll(pageRequest).toList());
        log.info("Get events list size: {}", eventShorts.size());
        return eventShorts;
    }

    @Override
    public EventFullDto get(Long userId, Long eventId) {
        Event event = eventRepository.findByIdAndInitiatorId(eventId, userId)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Event not found with id = %s and userId = %s", eventId, userId)));
        log.info("Get event: {}", event.getId());
        return EventMapper.toEventFullDto(event);
    }

    @Override
    public List<ParticipationRequestDto> getRequests(Long userId, Long eventId) {
        if (!eventRepository.existsByIdAndInitiatorId(eventId, userId)) {
            throw new NotFoundException(
                    String.format("Event not found with id = %s and userId = %s", eventId, userId));
        }
        return RequestMapper.toDtoList(requestRepository.findAllByEventId(eventId));
    }

    @Transactional
    @Override
    public EventFullDto create(Long userId, NewEventDto eventDto) {
        checkEventDate(eventDto.getEventDate());
        Event event = EventMapper.toEntity(eventDto);
        event.setCategory(categoriesRepository.findById(eventDto.getCategory())
                .orElseThrow(() -> new NotFoundException(String.format("Category with id=%d was not found",
                        eventDto.getCategory()))));
        event.setConfirmedRequests(0L);
        event.setInitiator(userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(String.format("User with id=%d was not found", userId))));
        event.setViews(0L);
        try {
            event = eventRepository.save(event);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException(e.getMessage(), e);
        }
        log.info("Add event: {}", event.getTitle());
        return EventMapper.toEventFullDto(event);
    }

    @Transactional
    @Override
    public EventFullDto update(Long userId, Long eventId, UpdateEventUserRequest eventDto) {
        checkEventDate(eventDto.getEventDate());
        Event eventUpdate = EventMapper.toEntity(eventDto);
        eventUpdate.setCategory(categoriesRepository.findById(eventDto.getCategory())
                .orElseThrow(() -> new NotFoundException(String.format("Category with id=%d was not found",
                        eventDto.getCategory()))));
        Event eventTarget = eventRepository.findByIdAndInitiatorId(eventId, userId)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Event not found with id = %s and userId = %s", eventId, userId)));
        if (eventTarget.getState().equals(State.PUBLISHED)) {
            throw new ConflictException("Event must not be published");
        }
        eventTarget.setState(State.valueOf(eventDto.getStateAction()));
        UtilMergeProperty.copyProperties(eventUpdate, eventTarget);
        eventRepository.flush();
        log.info("Update event: {}", eventTarget.getTitle());
        return EventMapper.toEventFullDto(eventTarget);
    }

    @Transactional
    @Override
    public EventRequestStatusUpdateResult updateRequestStatus(Long userId, Long eventId, EventRequestStatusUpdateRequest request) {
        List<ParticipationRequestDto> confirmedRequests = List.of();
        List<ParticipationRequestDto> rejectedRequests = List.of();

        List<Long> requestIds = request.getRequestIds();
        List<Request> requests = requestRepository.findAllByIdIn(requestIds);

        Status status = Status.valueOf(request.getStatus());

        if (status.equals(Status.REJECTED)) {
            requests.forEach(r -> request.setStatus(Status.REJECTED.toString()));
            requestRepository.flush();
            rejectedRequests = requests.stream()
                    .map(RequestMapper::toParticipationRequestDto)
                    .collect(Collectors.toList());
            return new EventRequestStatusUpdateResult(confirmedRequests, rejectedRequests);
        }

        Event event = eventRepository.findByIdAndInitiatorId(eventId, userId)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Event not found with id = %s and userId = %s", eventId, userId)));
        Long participantLimit = event.getParticipantLimit();
        Long approvedRequests = event.getConfirmedRequests();
        Long availableParticipants = participantLimit - approvedRequests;
        Long potentialParticipants = (long) requestIds.size();

        if (participantLimit > 0 && participantLimit.equals(approvedRequests)) {
            throw new ConflictException(String.format("Event with id=%d has reached participant limit", eventId));
        }

        if (status.equals(Status.CONFIRMED)) {
            if (participantLimit.equals(0L) || (potentialParticipants <= availableParticipants && !event.getRequestModeration())) {
                requests.forEach(r -> request.setStatus(Status.CONFIRMED.toString()));
                requestRepository.flush();
                confirmedRequests = requests.stream()
                        .map(RequestMapper::toParticipationRequestDto)
                        .collect(Collectors.toList());
                event.setConfirmedRequests(approvedRequests + potentialParticipants);
            } else {
                confirmedRequests = requests.stream()
                        .limit(availableParticipants)
                        .peek(r -> request.setStatus(Status.CONFIRMED.toString()))
                        .map(requestRepository::save)
                        .map(RequestMapper::toParticipationRequestDto)
                        .collect(Collectors.toList());
                rejectedRequests = requests.stream()
                        .skip(availableParticipants)
                        .peek(r -> request.setStatus(Status.REJECTED.toString()))
                        .map(requestRepository::save)
                        .map(RequestMapper::toParticipationRequestDto)
                        .collect(Collectors.toList());
                event.setConfirmedRequests(participantLimit);
            }
        }
        eventRepository.save(event);
        return new EventRequestStatusUpdateResult(confirmedRequests, rejectedRequests);
    }

    private void checkEventDate(LocalDateTime eventDate) {
        if (eventDate.isBefore(LocalDateTime.now().plusHours(2))) {
            throw new ConflictException("Field: eventDate. Error: the date and time for which the event is scheduled" +
                    " cannot be earlier than two hours from the current moment. Value: " + eventDate);
        }
    }

}
