package ru.practicum.ewm.adminApi.service.user;

import org.springframework.stereotype.Service;
import ru.practicum.ewm.base.dto.NewUserRequest;
import ru.practicum.ewm.base.dto.UserDto;

import java.util.List;

@Service
public class AdminUserServiceImpl implements AdminUserService{
    @Override
    public List<UserDto> getAll(List<Long> ids, Integer from, Integer size) {
        return null;
    }

    @Override
    public UserDto save(NewUserRequest newUserRequest) {
        return null;
    }

    @Override
    public void delete(Long userId) {

    }
}
