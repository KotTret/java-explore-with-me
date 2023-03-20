package ru.practicum.ewm.publicApi.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.base.dto.EventFullDto;
import ru.practicum.ewm.base.dto.EventShortDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/events")
public class PublicEventsController {

    public final  PublicEventsService eventsService;

    @GetMapping
    public ResponseEntity<List<EventShortDto>> getAll(@RequestParam(required = false) String text,
                                                      @RequestParam(required = false) List<Integer> categories,
                                                      @RequestParam(required = false) Boolean paid,
                                                      @RequestParam(required = false) String rangeStart,
                                                      @RequestParam(required = false) String rangeEnd,
                                                      @RequestParam(defaultValue = "false") Boolean onlyAvailable,
                                                      @RequestParam(required = false) String sort,
                                                      @RequestParam(defaultValue = "0") int from,
                                                      @RequestParam(defaultValue = "10") int size,
                                                      HttpServletRequest request) {
        log.info("Получен запрос GET /events c параметрами: text = {}, categories = {}, paid = {}, rangeStart = {}, " +
                "rangeEnd = {}, onlyAvailable = {}, sort = {}, from = {}, size = {}", text, categories.toArray(), paid,
                rangeStart, rangeEnd, onlyAvailable, sort, from, size);
        return new ResponseEntity<>(eventsService.getAll(text, categories, paid, rangeStart, rangeEnd,
                onlyAvailable, sort, from, size, request), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventFullDto> get(@PathVariable Integer id) {
        log.info("Получен запрос GET /events/{}", id);
        return new ResponseEntity<>(eventsService.get(id), HttpStatus.OK);
    }
}
