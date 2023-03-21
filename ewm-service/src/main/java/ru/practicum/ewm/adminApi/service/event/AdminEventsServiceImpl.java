package ru.practicum.ewm.adminApi.service.event;

import org.springframework.stereotype.Service;
import ru.practicum.ewm.adminApi.dto.RequestParamForEvent;
import ru.practicum.ewm.base.dto.EventFullDto;
import ru.practicum.ewm.base.dto.UpdateEventAdminRequest;

import java.util.List;

@Service
public class AdminEventsServiceImpl implements AdminEventsService{

    @Override
    public EventFullDto update(Integer eventId, UpdateEventAdminRequest updateEvent) {
        return null;
    }

    @Override
    public List<EventFullDto> getAll(RequestParamForEvent param) {
        return null;
    }
}
