package com.radkovich.module_2_4.service;

import com.radkovich.module_2_4.dao.FileDao;
import com.radkovich.module_2_4.dao.impl.FileDaoImpl;
import com.radkovich.module_2_4.model.FileEntity;

import java.util.List;

public class FileServiceImpl implements com.radkovich.module_2_4.service.impl.FileService {

    private final FileDao fileDao = new FileDaoImpl();

    @Override
    public FileEntity createFile(String name, String filePath) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty!");
        }

        if (filePath == null || filePath.isEmpty()) {
            throw new IllegalArgumentException("File path cannot be null or empty!");
        }

        FileEntity file = new FileEntity(name, filePath);
        fileDao.save(file);
        return file;
    }

    @Override
    public FileEntity getFileById(Integer id) {
        FileEntity file = fileDao.findById(id);

        if (file == null) {
            throw new IllegalArgumentException("File with ID: " + id + " not found!");
        }

        return file;
    }

    @Override
    public List<FileEntity> getAllFiles() {
        return fileDao.findAll();
    }

    @Override
    public FileEntity updateFile(Integer id, String name, String filePath) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty!");
        }

        if (filePath == null || filePath.isEmpty()) {
            throw new IllegalArgumentException("File path cannot be null or empty!");
        }

        FileEntity file = fileDao.findById(id);

        if (file == null) {
            throw new IllegalArgumentException("File with ID: " + id + " not found!");
        }

        file.setName(name);
        file.setFilePath(filePath);
        fileDao.update(file);
        return file;
    }

    @Override
    public void deleteFile(Integer id) {
        FileEntity file = fileDao.findById(id);
        fileDao.delete(file);
    }
}
