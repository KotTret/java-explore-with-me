package ru.practicum.ewm.publicApi.service.event;

import org.springframework.stereotype.Service;
import ru.practicum.ewm.base.dto.EventFullDto;
import ru.practicum.ewm.base.dto.EventShortDto;
import ru.practicum.ewm.publicApi.dto.RequestParamForEvent;

import java.util.List;

@Service
public class PublicEventsServiceImpl implements PublicEventsService{

    @Override
    public List<EventShortDto> getAll(RequestParamForEvent param) {
        return null;
    }

    @Override
    public EventFullDto get(Integer id) {
        return null;
    }
}
