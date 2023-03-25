package ru.practicum.ewm.base.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.ewm.base.enums.State;
import ru.practicum.ewm.base.model.Category;
import ru.practicum.ewm.base.model.Event;
import ru.practicum.ewm.base.util.page.MyPageRequest;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public interface EventRepository extends JpaRepository<Event, Long> {


    boolean existsByCategory(Category category);

    Optional<Event> findFirstByCategory(Category category);

    List<Event> findAllByIdIn(Set<Long> eventsId);

    List<Event> findAllByInitiator_IdInAndStateInAndCategory_IdInAndEventDateBetween(List<Long> users,
                                                                                     List<State> states,
                                                                                     List<Long> categories,
                                                                                     String rangeStart,
                                                                                     String rangeEnd,
                                                                                     MyPageRequest pageable);
}
