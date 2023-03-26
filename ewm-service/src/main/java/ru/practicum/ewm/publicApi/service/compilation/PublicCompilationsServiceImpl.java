package ru.practicum.ewm.publicApi.service.compilation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.base.dao.CompilationRepository;
import ru.practicum.ewm.base.dto.Compilation.CompilationDto;
import ru.practicum.ewm.base.exception.NotFoundException;
import ru.practicum.ewm.base.mapper.CompilationMapper;
import ru.practicum.ewm.base.model.Compilation;
import ru.practicum.ewm.base.util.page.MyPageRequest;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class PublicCompilationsServiceImpl implements PublicCompilationsService {
    private final CompilationRepository compilationRepository;

    @Override
    public List<CompilationDto> getAll(Boolean pinned, int from, int size) {
        MyPageRequest pageable = new MyPageRequest(from, size,
                Sort.by(Sort.Direction.ASC, "compilation_id"));
        List<Compilation> compilations = compilationRepository.findAllByPinned(pinned, pageable);
        log.info("Get list compilations with pinned = {}:", pinned.toString());
        return CompilationMapper.toDtoList(compilations);
    }

    @Override
    public CompilationDto get(Long comId) {
        final Compilation compilation = compilationRepository.findById(comId)
                .orElseThrow(() -> new NotFoundException(String.format("Compilation not found with id = %s", comId)));
        log.info("Get compilation: {}", compilation.getTitle());
        return CompilationMapper.toDto(compilation);
    }
}
