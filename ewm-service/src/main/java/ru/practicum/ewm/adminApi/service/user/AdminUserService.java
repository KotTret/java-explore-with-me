package ru.practicum.ewm.adminApi.service.user;

import ru.practicum.ewm.base.dto.NewUserRequest;
import ru.practicum.ewm.base.dto.UserDto;

import java.util.List;

public interface AdminUserService {
    List<UserDto> getAll(List<Long> ids, Integer from, Integer size);

    UserDto save(NewUserRequest newUserRequest);

    void delete(Long userId);
}
