package com.radkovich.module_2_4.service;

import com.radkovich.module_2_4.dao.UserDao;
import com.radkovich.module_2_4.dao.impl.UserDaoImpl;
import com.radkovich.module_2_4.model.User;

import java.util.List;

public class UserServiceImpl implements com.radkovich.module_2_4.service.impl.UserService {

    private final UserDao userDao = new UserDaoImpl();

    @Override
    public User createUser(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty!");
        }

        User user = new User(name);
        userDao.save(user);
        return user;
    }

    @Override
    public User getUserById(Integer id) {
        User user = userDao.findById(id);

        if (user == null) {
            throw new IllegalArgumentException("User with ID: " + id + " not found!");
        }

        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @Override
    public User updateUser(Integer id, String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name for update cannot be null or empty!");
        }

        User user = getUserById(id);
        user.setName(name);
        userDao.update(user);
        return user;
    }

    @Override
    public void deleteUser(Integer id) {
        User user = getUserById(id);
        userDao.delete(user);
    }
}
