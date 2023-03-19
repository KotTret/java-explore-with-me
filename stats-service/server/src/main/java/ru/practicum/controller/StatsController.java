package ru.practicum.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.dto.EndpointHitDto;
import ru.practicum.dto.ViewStatsDto;
import ru.practicum.exception.BadRequestException;
import ru.practicum.service.StatsService;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequiredArgsConstructor
@Slf4j
public class StatsController {

    private final StatsService service;


    @GetMapping("/stats")
    public ResponseEntity<List<ViewStatsDto>> get(@RequestParam @NotBlank
                                                  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime start,
                                                  @RequestParam @NotBlank
                                                  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime end,
                                                  @RequestParam List<String> uris,
                                                  @RequestParam(defaultValue = "false") Boolean unique) {

        validateParamForGetMapping(start, end);
        log.info("Получен запрос GET /stats");
        return new ResponseEntity<>(service.get(start, end, uris, unique), HttpStatus.OK);
    }


    @PostMapping("/hit")
    public ResponseEntity<EndpointHitDto> save(@RequestBody EndpointHitDto dto) {
        log.info("Получен запрос POST /hit");
        return new ResponseEntity<>(service.save(dto), HttpStatus.CREATED);
    }

    private void validateParamForGetMapping(LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate.isAfter(endDate) || startDate.equals(endDate)) {
            throw new BadRequestException("start date is not  before end date");
        }
    }
}
