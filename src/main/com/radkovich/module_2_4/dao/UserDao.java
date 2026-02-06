package com.radkovich.module_2_4.dao;

import com.radkovich.module_2_4.model.User;

import java.util.List;

public interface UserDao {

    void save(User user);

    User findById(Integer id);

    List<User> findAll();

    void update(User user);

    void delete(User user);
}
