package ru.practicum.ewm.adminApi.service.event;

import ru.practicum.ewm.adminApi.dto.RequestParamForEvent;
import ru.practicum.ewm.base.dto.EventFullDto;
import ru.practicum.ewm.base.dto.UpdateEventAdminRequest;

import java.util.List;

public interface AdminEventsService {
    EventFullDto update(Integer eventId, UpdateEventAdminRequest updateEvent);

    List<EventFullDto> getAll(RequestParamForEvent param);
}
