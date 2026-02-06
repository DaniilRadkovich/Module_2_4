package com.radkovich.module_2_4.dto.response;

public class EventResponseDto {

    private final Integer id;
    private final Integer userId;
    private final Integer fileId;

    public EventResponseDto(Integer id, Integer userId, Integer fileId) {
        this.id = id;
        this.userId = userId;
        this.fileId = fileId;
    }

    public Integer getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getFileId() {
        return fileId;
    }
}
