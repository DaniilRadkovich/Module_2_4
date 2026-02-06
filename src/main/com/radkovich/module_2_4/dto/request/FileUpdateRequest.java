package com.radkovich.module_2_4.dto.request;

public class FileUpdateRequest {

    private String name;
    private String filePath;

    public FileUpdateRequest() {
    }

    public String getName() {
        return name;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
