package ru.practicum.ewm.adminApi.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.base.dto.CompilationDto;
import ru.practicum.ewm.base.dto.NewCompilationDto;
import ru.practicum.ewm.base.dto.UpdateCompilationRequest;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/admin/compilations")
public class AdminCompilationsController {
    private final AdminCompilationService service;

    @PostMapping()
    public ResponseEntity<CompilationDto> save(@RequestBody @Valid NewCompilationDto newCompilationDto) {
        log.info("Получен запрос POST /admin/compilations c новой подборкой: {}", newCompilationDto.getTitle());
        return new ResponseEntity<>(service.save(newCompilationDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{compId}")
    public ResponseEntity<Void> delete(@PathVariable Integer compId) {
        log.info("Получен запрос DELETE /admin/compilations/{}", compId);
        service.delete(compId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{compId}")
    public ResponseEntity<CompilationDto> update(@PathVariable Integer compId,
                                                 @RequestBody UpdateCompilationRequest updateCompilationRequest) {
        log.info("Получен запрос PATCH /admin/compilations/{} на изменение подборки.", compId);
        return new ResponseEntity(service.update(compId, updateCompilationRequest), HttpStatus.OK);
    }
}
