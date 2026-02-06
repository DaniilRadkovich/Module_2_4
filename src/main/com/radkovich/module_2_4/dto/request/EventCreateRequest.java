package com.radkovich.module_2_4.dto.request;

public class EventCreateRequest {

    private Integer userId;
    private Integer fileId;

    public EventCreateRequest() {
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }
}
