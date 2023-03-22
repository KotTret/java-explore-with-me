package ru.practicum.ewm.adminApi.service.category;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.base.dao.CategoriesRepository;
import ru.practicum.ewm.base.dao.EventRepository;
import ru.practicum.ewm.base.dto.CategoryDto;
import ru.practicum.ewm.base.dto.NewCategoryDto;
import ru.practicum.ewm.base.exception.ConditionsNotMetException;
import ru.practicum.ewm.base.exception.ConflictException;
import ru.practicum.ewm.base.exception.NotFoundException;
import ru.practicum.ewm.base.mapper.CategoryMapper;
import ru.practicum.ewm.base.model.Category;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class AdminCategoriesServiceImpl implements AdminCategoriesService {

    private final CategoriesRepository categoriesRepository;

    private final EventRepository eventRepository;

    @Transactional
    @Override
    public CategoryDto create(NewCategoryDto dto) {
        Category category = CategoryMapper.toEntity(dto);
        try {
            category = categoriesRepository.save(category);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException(e.getMessage(), e);
        }
        log.info("Add category: {}", category.getName());
        return CategoryMapper.toDto(category);
    }

    @Transactional
    @Override
    public void delete(Long catId) {
        if (eventRepository.existsByCategory(get(catId))) {
            throw new ConditionsNotMetException("The category is not empty");
        } else {
            log.info("Deleted category with id = {}", catId);
            categoriesRepository.deleteById(catId);
        }
    }

    @Transactional
    @Override
    public CategoryDto update(NewCategoryDto dto, Long catId) {
        return null;
    }

    private Category get(Long id) {
        final Category category = categoriesRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Category not found with id = %s", id)));
        log.info("Get category: {}", category.getName());
        return category;
    }

    private boolean existsById(Long id) {
        if (!eventRepository.existsById(id)) {
            throw new NotFoundException(String.format("Category not found with id = %s", id));
        }
        return true;
    }
}
