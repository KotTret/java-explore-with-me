package ru.practicum.ewm.publicApi.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.base.dto.CategoryDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/categories")
public class PublicCategoriesController {

    public final  PublicCategoriesService categoriesService;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAll(@RequestParam(defaultValue = "0") int from,
                                                    @RequestParam(defaultValue = "10") int size) {
        log.info("Получен запрос GET /categories c параметрами: from = {}, size = {}", from, size);
        return new ResponseEntity<>(categoriesService.getAll(from, size), HttpStatus.OK);
    }

    @GetMapping("/{catId}")
    public ResponseEntity<CategoryDto> get(@PathVariable Integer catId) {
        log.info("Получен запрос GET /categories/{}", catId);
        return new ResponseEntity<>(categoriesService.get(catId));
    }
}