package ru.practicum.ewm.publicApi.service.category;

import org.springframework.stereotype.Service;
import ru.practicum.ewm.base.dto.CategoryDto;
import ru.practicum.ewm.publicApi.service.category.PublicCategoriesService;

import java.util.List;

@Service
public class PublicCategoriesServiceImpl implements PublicCategoriesService {
    @Override
    public List<CategoryDto> getAll(int from, int size) {
        return null;
    }

    @Override
    public CategoryDto get(Long catId) {
        return null;
    }
}
