package ru.practicum.ewm.privateApi.service.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.base.dao.EventRepository;
import ru.practicum.ewm.base.dao.UserRepository;
import ru.practicum.ewm.base.dto.*;
import ru.practicum.ewm.base.dto.event.*;
import ru.practicum.ewm.base.exception.NotFoundException;
import ru.practicum.ewm.base.mapper.EventMapper;
import ru.practicum.ewm.base.model.Event;
import ru.practicum.ewm.base.util.page.MyPageRequest;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class PrivateEventsServiceImpl implements PrivateEventsService {

    private final EventRepository eventRepository;

    private final UserRepository userRepository;

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

    }

    @Override
    public EventFullDto create(Long userId, NewEventDto eventDto) {
        return null;
    }

    @Override
    public EventFullDto update(Long userId, Integer eventId, UpdateEventUserRequest eventDto) {
        return null;
    }

    @Override
    public EventRequestStatusUpdateResult updateRequestStatus(Long userId, Integer eventId, EventRequestStatusUpdateRequest request) {
        return null;
    }
}
