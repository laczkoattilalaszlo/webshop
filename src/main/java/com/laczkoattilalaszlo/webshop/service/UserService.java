package com.laczkoattilalaszlo.webshop.service;

import com.laczkoattilalaszlo.webshop.data.dao.UserDao;

import java.util.UUID;

public class UserService {

    // Field(s)
    private UserDao userDao;

    // Constructor(s)
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    // Method(s)
    public void addUser(String email, String password) {
        userDao.addUser(email, password);
    }

    public void removeUser(UUID userId) {
        userDao.removeUser(userId);
    }

}
