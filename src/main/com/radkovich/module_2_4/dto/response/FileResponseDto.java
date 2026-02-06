package com.radkovich.module_2_4.dto.response;

public class FileResponseDto {

    private final Integer id;
    private final String name;
    private final String filePath;

    public FileResponseDto(Integer id, String name, String filePath) {
        this.id = id;
        this.name = name;
        this.filePath = filePath;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFilePath() {
        return filePath;
    }
}
