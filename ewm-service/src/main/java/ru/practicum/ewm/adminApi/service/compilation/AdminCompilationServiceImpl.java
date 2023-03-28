package ru.practicum.ewm.adminApi.service.compilation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.base.dao.CompilationRepository;
import ru.practicum.ewm.base.dao.EventRepository;
import ru.practicum.ewm.base.dto.Compilation.CompilationDto;
import ru.practicum.ewm.base.dto.Compilation.NewCompilationDto;
import ru.practicum.ewm.base.dto.Compilation.UpdateCompilationRequest;
import ru.practicum.ewm.base.exception.ConditionsNotMetException;
import ru.practicum.ewm.base.exception.ConflictException;
import ru.practicum.ewm.base.exception.NotFoundException;
import ru.practicum.ewm.base.mapper.CategoryMapper;
import ru.practicum.ewm.base.mapper.CompilationMapper;
import ru.practicum.ewm.base.model.Category;
import ru.practicum.ewm.base.model.Compilation;
import ru.practicum.ewm.base.model.Event;
import ru.practicum.ewm.base.util.UtilMergeProperty;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class AdminCompilationServiceImpl implements AdminCompilationService {

    private final CompilationRepository compilationRepository;
    private final EventRepository eventRepository;

    @Transactional
    @Override
    public CompilationDto save(NewCompilationDto newCompilationDto) {
        Compilation compilation = CompilationMapper.toEntity(newCompilationDto);
        compilation.setEvents(findEvents(newCompilationDto.getEvents()));
        try {
            compilation = compilationRepository.save(compilation);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException(e.getMessage(), e);
        }
        log.info("Add compilation: {}", compilation.getTitle());
        return CompilationMapper.toDto(compilation);
    }

    @Transactional
    @Override
    public void delete(Long compId) {
        if (compilationRepository.existsById(compId)) {
            log.info("Deleted compilation with id = {}", compId);
            compilationRepository.deleteById(compId);
        }
    }

    @Transactional
    @Override
    public CompilationDto update(Long compId, UpdateCompilationRequest dto) {
        Compilation compilationTarget = compilationRepository.findById(compId)
                .orElseThrow(() -> new NotFoundException(String.format("Compilation not found with id = %s", compId)));

        BeanUtils.copyProperties(dto, compilationTarget,  "events", "pinned", "title");

        try {
            compilationRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException(e.getMessage(), e);
        }

        log.info("Update category: {}", compilationTarget.getTitle());
        return CompilationMapper.toDto(compilationTarget);
    }

    private Compilation get(Long id) {
        final Compilation compilation = compilationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Compilation not found with id = %s", id)));
        log.info("Get compilation: {}", compilation.getTitle());
        return compilation;
    }

    private List<Event> findEvents(Set<Long> eventsId) {
        if (eventsId == null) {
            return List.of();
        }
        return eventRepository.findAllByIdIn(eventsId);
    }
}
