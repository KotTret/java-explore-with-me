package ru.practicum.ewm.adminApi.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.adminApi.service.user.AdminUserService;
import ru.practicum.ewm.base.dto.user.NewUserRequest;
import ru.practicum.ewm.base.dto.user.UserDto;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/admin/users")
public class AdminUsersController {

    private final AdminUserService service;

    @GetMapping()
    public ResponseEntity<List<UserDto>> getAll(@RequestParam(required = false) List<Long> ids,
                                                @RequestParam(defaultValue = "0") Integer from,
                                                @RequestParam(defaultValue = "10") Integer size) {
        log.info("Получен запрос GET /admin/users");
        return new ResponseEntity<>(service.getAll(ids, from, size), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UserDto> save(@RequestBody @Valid NewUserRequest newUserRequest) {
        log.info("Получен запрос POST /admin/users c новым пользователем: {}", newUserRequest.getEmail());
        return new ResponseEntity<>(service.save(newUserRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> delete(@PathVariable Long userId) {
        log.info("Получен запрос DELETE /admin/users/{}", userId);
        service.delete(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
