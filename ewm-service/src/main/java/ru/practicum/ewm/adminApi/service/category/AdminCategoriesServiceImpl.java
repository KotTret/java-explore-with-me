package ru.practicum.ewm.adminApi.service.category;

import org.springframework.stereotype.Service;
import ru.practicum.ewm.base.dto.CategoryDto;
import ru.practicum.ewm.base.dto.NewCategoryDto;

@Service
public class AdminCategoriesServiceImpl implements AdminCategoriesService{

    @Override
    public CategoryDto create(NewCategoryDto dto) {
        return null;
    }

    @Override
    public void delete(Integer catId) {

    }

    @Override
    public CategoryDto update(NewCategoryDto dto, Integer catId) {
        return null;
    }
}
