package ru.practicum.ewm.privateApi.service.event;

import ru.practicum.ewm.base.dto.*;

import java.util.List;

public interface PrivateEventsService {
    List<EventShortDto> getAll(Long userId, Integer from, Integer size);

    EventFullDto get(Long userId, Integer eventId);

    List<ParticipationRequestDto> getRequests(Long userId, Integer eventId);

    EventFullDto create(Long userId, NewEventDto eventDto);

    EventFullDto update(Long userId, Integer eventId, UpdateEventUserRequest eventDto);

    EventRequestStatusUpdateResult updateRequestStatus(Long userId, Integer eventId, EventRequestStatusUpdateRequest request);
}
