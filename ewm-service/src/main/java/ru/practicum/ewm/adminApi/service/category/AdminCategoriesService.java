package ru.practicum.ewm.adminApi.service.category;

import ru.practicum.ewm.base.dto.CategoryDto;
import ru.practicum.ewm.base.dto.NewCategoryDto;

public interface AdminCategoriesService {
    CategoryDto create(NewCategoryDto dto);

    void delete(Integer catId);

    CategoryDto update(NewCategoryDto dto, Integer catId);
}
