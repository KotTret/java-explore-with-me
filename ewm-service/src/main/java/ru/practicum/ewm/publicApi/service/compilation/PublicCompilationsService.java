package ru.practicum.ewm.publicApi.service.compilation;

import ru.practicum.ewm.base.dto.CompilationDto;

import java.util.List;

public interface PublicCompilationsService {
    List<CompilationDto> getAll(Boolean pinned, int from, int size);

    CompilationDto get(Long comId);
}
