package com.radkovich.module_2_4.dto.response;

public class UserResponseDto {

    private final Integer id;
    private final String name;

    public UserResponseDto(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
