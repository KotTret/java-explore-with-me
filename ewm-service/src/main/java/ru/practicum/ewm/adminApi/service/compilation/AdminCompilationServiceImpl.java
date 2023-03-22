package ru.practicum.ewm.adminApi.service.compilation;

import org.springframework.stereotype.Service;
import ru.practicum.ewm.base.dto.CompilationDto;
import ru.practicum.ewm.base.dto.NewCompilationDto;
import ru.practicum.ewm.base.dto.UpdateCompilationRequest;

@Service
public class AdminCompilationServiceImpl implements AdminCompilationService{

    @Override
    public CompilationDto save(NewCompilationDto newCompilationDto) {
        return null;
    }

    @Override
    public void delete(Long compId) {

    }

    @Override
    public CompilationDto update(Long compId, UpdateCompilationRequest updateCompilationRequest) {
        return null;
    }
}
