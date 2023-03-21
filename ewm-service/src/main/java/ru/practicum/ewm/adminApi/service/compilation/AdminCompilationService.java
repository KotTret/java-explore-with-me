package ru.practicum.ewm.adminApi.service.compilation;

import ru.practicum.ewm.base.dto.CompilationDto;
import ru.practicum.ewm.base.dto.NewCompilationDto;
import ru.practicum.ewm.base.dto.UpdateCompilationRequest;

public interface AdminCompilationService {
    CompilationDto save(NewCompilationDto newCompilationDto);

    void delete(Integer compId);

    CompilationDto update(Integer compId, UpdateCompilationRequest updateCompilationRequest);
}
