package com.radkovich.module_2_4.service.impl;

import com.radkovich.module_2_4.model.User;

import java.util.List;

public interface UserService {

    User createUser(String name);

    User getUserById(Integer id);

    List<User> getAllUsers();

    User updateUser(Integer id, String name);

    void deleteUser(Integer id);
}
