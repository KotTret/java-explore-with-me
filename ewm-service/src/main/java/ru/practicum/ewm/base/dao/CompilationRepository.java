package ru.practicum.ewm.base.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.ewm.base.model.Compilation;

public interface CompilationRepository extends JpaRepository<Compilation, Long> {
}
