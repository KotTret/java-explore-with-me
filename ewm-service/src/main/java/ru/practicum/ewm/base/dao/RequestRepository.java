package ru.practicum.ewm.base.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.ewm.base.dto.ParticipationRequestDto;
import ru.practicum.ewm.base.model.Request;

import java.util.List;
import java.util.Optional;

public interface RequestRepository extends JpaRepository<Request, Long> {

    List<Request> findAllByEventId(Long eventId);

    List<Request> findAllByIdIn(List<Long> requestIds);

    List<ParticipationRequestDto> findAllByRequesterId(Long userId);

    boolean existsByRequesterIdAndEventId(Long userId, Long eventId);

    Optional<Request> findByIdAndRequesterId(Long requestId, Long userId);
}
