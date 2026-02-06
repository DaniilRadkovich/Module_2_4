package com.radkovich.module_2_4.dao;

import com.radkovich.module_2_4.model.FileEntity;

import java.util.List;

public interface FileDao {

    void save(FileEntity fileEntity);

    FileEntity findById(Integer id);

    List<FileEntity> findAll();

    void update(FileEntity fileEntity);

    void delete(FileEntity fileEntity);

}
