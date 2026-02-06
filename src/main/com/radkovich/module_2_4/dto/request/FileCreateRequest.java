package com.radkovich.module_2_4.dto.request;

public class FileCreateRequest {

    private String name;
    private String filePath;

    public FileCreateRequest() {
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
