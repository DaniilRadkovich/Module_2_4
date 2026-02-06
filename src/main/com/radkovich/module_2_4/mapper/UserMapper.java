package com.radkovich.module_2_4.mapper;

import com.radkovich.module_2_4.dto.response.UserResponseDto;
import com.radkovich.module_2_4.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    private UserMapper() {
    }

    public static UserResponseDto toDto(User user) {
        if (user == null) return null;

        return new UserResponseDto(
                user.getId(),
                user.getName()
        );
    }

    public static List<UserResponseDto> toDtoList(List<User> users) {
        return users.stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }
}
