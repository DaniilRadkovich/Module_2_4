package com.radkovich.module_2_4.service.impl;

import com.radkovich.module_2_4.model.FileEntity;

import java.util.List;

public interface FileService {

    FileEntity createFile(String name, String filePath);

    FileEntity getFileById(Integer id);

    List<FileEntity> getAllFiles();

    FileEntity updateFile(Integer id, String name, String filePath);

    void deleteFile(Integer id);
}
