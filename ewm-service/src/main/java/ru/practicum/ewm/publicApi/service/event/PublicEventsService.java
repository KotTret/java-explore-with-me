package ru.practicum.ewm.publicApi.service.event;

import ru.practicum.ewm.base.dto.EventFullDto;
import ru.practicum.ewm.base.dto.EventShortDto;
import ru.practicum.ewm.publicApi.dto.RequestParamForEvent;

import java.util.List;

public interface PublicEventsService {

    List<EventShortDto> getAll(RequestParamForEvent param);

    EventFullDto get(Long id);
}
