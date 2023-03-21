package ru.practicum.ewm.privateApi.service.event;

import org.springframework.stereotype.Service;
import ru.practicum.ewm.base.dto.*;

import java.util.List;

@Service
public class PrivateEventsServiceImpl implements PrivateEventsService{

    @Override
    public List<EventShortDto> getAll(Long userId, Integer from, Integer size) {
        return null;
    }

    @Override
    public EventFullDto get(Long userId, Integer eventId) {
        return null;
    }

    @Override
    public List<ParticipationRequestDto> getRequests(Long userId, Integer eventId) {
        return null;
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
