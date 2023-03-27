package ru.practicum.ewm.adminApi.service.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.adminApi.dto.RequestParamForEvent;
import ru.practicum.ewm.base.dao.EventRepository;
import ru.practicum.ewm.base.dto.event.EventFullDto;
import ru.practicum.ewm.base.dto.UpdateEventAdminRequest;
import ru.practicum.ewm.base.exception.NotFoundException;
import ru.practicum.ewm.base.mapper.EventMapper;
import ru.practicum.ewm.base.model.Event;
import ru.practicum.ewm.base.util.UtilMergeProperty;
import ru.practicum.ewm.base.util.page.MyPageRequest;

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
        Event eventUpdate = EventMapper.toEntity(dto);
        Event eventTarget = get(eventId);
        UtilMergeProperty.copyProperties(eventUpdate, eventTarget);
        eventRepository.flush();
        log.info("Update event: {}", eventTarget.getTitle());
        return EventMapper.toEventFullDto(eventTarget);
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
}
