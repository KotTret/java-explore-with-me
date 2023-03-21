package ru.practicum.ewm.adminApi.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.base.dto.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/admin/categories")
public class AdminCategoriesController {

    public final AdminCategoriesService service;

    @PostMapping
    public ResponseEntity<List<EventFullDto>> create(@RequestBody @Valid NewCategoryDto dto) {
        log.info("Получен запрос POST /admin/categories c новой категорией: {}", dto.getName());
        return new ResponseEntity<>(service.create(dto), HttpStatus.OK);
    }

    @DeleteMapping("/{catId}")
    public ResponseEntity<Void> delete(@PathVariable Integer catId) {
        log.info("Получен запрос DELETE /admin/categories/{}", catId);
        service.delete(catId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{catId}")
    public ResponseEntity<CategoryDto> update(@RequestBody @Valid NewCategoryDto dto,
                                              @PathVariable Integer catId) {
        log.info("Получен запрос PATCH /admin/categories/{} на изменение категориии: {}", catId, dto.getName());
        return new ResponseEntity(service.update(dto, catId), HttpStatus.OK);
    }
}
