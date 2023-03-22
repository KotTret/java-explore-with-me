package ru.practicum.ewm.base.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.ewm.base.model.Category;
import ru.practicum.ewm.base.model.Event;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {


    boolean existsByCategory(Category category);

    Optional<Event> findFirstByCategory(Category category);
}
