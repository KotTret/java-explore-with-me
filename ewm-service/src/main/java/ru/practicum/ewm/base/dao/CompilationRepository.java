package ru.practicum.ewm.base.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.ewm.base.model.Compilation;
import ru.practicum.ewm.base.util.page.MyPageRequest;

import java.util.List;
import java.util.stream.Collectors;

public interface CompilationRepository extends JpaRepository<Compilation, Long> {
    List<Compilation> findAllByPinned(Boolean pinned, MyPageRequest pageable);
}
