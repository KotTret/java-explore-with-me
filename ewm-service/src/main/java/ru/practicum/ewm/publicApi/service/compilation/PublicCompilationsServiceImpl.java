package ru.practicum.ewm.publicApi.service.compilation;

import org.springframework.stereotype.Service;
import ru.practicum.ewm.base.dto.CompilationDto;

import java.util.List;

@Service
public class PublicCompilationsServiceImpl implements PublicCompilationsService{
    @Override
    public List<CompilationDto> getAll(Boolean pinned, int from, int size) {
        return null;
    }

    @Override
    public CompilationDto get(Integer comId) {
        return null;
    }
}
