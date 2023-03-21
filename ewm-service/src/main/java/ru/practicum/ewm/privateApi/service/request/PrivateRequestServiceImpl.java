package ru.practicum.ewm.privateApi.service.request;

import org.springframework.stereotype.Service;
import ru.practicum.ewm.base.dto.ParticipationRequestDto;

import java.util.List;

@Service
public class PrivateRequestServiceImpl implements PrivateRequestService{

    @Override
    public List<ParticipationRequestDto> getRequests(Long userId) {
        return null;
    }

    @Override
    public ParticipationRequestDto create(Long userId, Integer eventId) {
        return null;
    }

    @Override
    public ParticipationRequestDto update(Long userId, Integer requestsId) {
        return null;
    }
}
