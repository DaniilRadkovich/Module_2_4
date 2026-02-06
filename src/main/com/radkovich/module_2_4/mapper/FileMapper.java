package com.radkovich.module_2_4.mapper;

import com.radkovich.module_2_4.dto.response.FileResponseDto;
import com.radkovich.module_2_4.model.FileEntity;

import java.util.List;
import java.util.stream.Collectors;

public class FileMapper {

    private FileMapper() {
    }

    public static FileResponseDto toDto(FileEntity file) {
        if (file == null) return null;

        return new FileResponseDto(
                file.getId(),
                file.getName(),
                file.getFilePath()
        );
    }

    public static List<FileResponseDto> toDtoList(List<FileEntity> files) {
        return files.stream()
                .map(FileMapper::toDto)
                .collect(Collectors.toList());
    }
}
