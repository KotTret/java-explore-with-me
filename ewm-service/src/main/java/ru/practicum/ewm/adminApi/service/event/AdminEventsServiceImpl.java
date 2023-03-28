package ru.practicum.ewm.adminApi.service.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.adminApi.dto.RequestParamForEvent;
import ru.practicum.ewm.base.dao.EventRepository;
import ru.practicum.ewm.base.dto.event.EventFullDto;
import ru.practicum.ewm.base.dto.event.UpdateEventAdminRequest;
import ru.practicum.ewm.base.enums.State;
import ru.practicum.ewm.base.exception.ConflictException;
import ru.practicum.ewm.base.exception.NotFoundException;
import ru.practicum.ewm.base.mapper.CompilationMapper;
import ru.practicum.ewm.base.mapper.EventMapper;
import ru.practicum.ewm.base.model.Compilation;
import ru.practicum.ewm.base.model.Event;
import ru.practicum.ewm.base.model.Location;
import ru.practicum.ewm.base.util.UtilMergeProperty;
import ru.practicum.ewm.base.util.page.MyPageRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class AdminEventsServiceImpl implements AdminEventsService {

    private final EventRepository eventRepository;

    @Transactional
    @Override
    public EventFullDto update(Long eventId, UpdateEventAdminRequest dto) {
        if (dto.getEventDate() != null) {
            checkEventDate(dto.getEventDate());
        }
       //Event eventUpdate = EventMapper.toEntity(dto);
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new NotFoundException(String.format("Event not found with id = %s", eventId)));




      //  Event eventTarget = get(eventId);
        if (event.getState().equals(State.PUBLISHED)) {
            throw new ConflictException("Cannot publish the event because it's not in the right state: PUBLISHED");
        } else if (event.getState().equals(State.CANCELED)) {
            throw new ConflictException("Cannot publish the event because it's not in the right state: CANCELED");
        }
        BeanUtils.copyProperties(dto, event,  "annotation", "category", "description", "eventDate",
                "location","paid","participantLimit","requestModeration","stateAction","title");

        try {
            eventRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException(e.getMessage(), e);
        }
        log.info("Update event: {}", event.getTitle());
        return EventMapper.toEventFullDto(event);
    }

    @Override
    public List<EventFullDto> getAll(RequestParamForEvent param) {
        MyPageRequest pageable = new MyPageRequest(param.getFrom(), param.getSize(),
                Sort.by(Sort.Direction.ASC, "id"));
        List<Event> events = eventRepository.findEventsByParams(
                param.getUsers(), param.getStates(), param.getCategories(), param.getRangeStart(),
                param.getRangeEnd(), pageable);
        return events.stream()
                .map(EventMapper::toEventFullDto)
                .collect(Collectors.toList());
    }

    private Event get(Long id) {
        final Event event = eventRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Event not found with id = %s", id)));
        log.info("Get event: {}", event.getTitle());
        return event;
    }

    private void checkEventDate(LocalDateTime eventDate) {
        if (eventDate.isBefore(LocalDateTime.now().plusHours(2))) {
            throw new ConflictException("Field: eventDate. Error: the date and time for which the event is scheduled" +
                    " cannot be earlier than two hours from the current moment. Value: " + eventDate);
        }
    }
}
