package com.laczkoattilalaszlo.webshop.service;

import com.laczkoattilalaszlo.webshop.data.SecurityUtility;
import com.laczkoattilalaszlo.webshop.data.dao.UserDao;
import com.laczkoattilalaszlo.webshop.model.User;

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
        String hashedPassword = SecurityUtility.hashPassword(password);
        userDao.addUser(email, hashedPassword);
    }

    public void removeUser(String sessionToken) {
        UUID userId = userDao.getUserIdBySessionToken(sessionToken);

        userDao.removeUser(userId);
    }

    public User getUser(UUID userId) {
        return userDao.getUser(userId);
    }

    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    public String authenticateUser(String email, String password) {
        String hashedPassword = SecurityUtility.hashPassword(password);
        UUID userId = userDao.getUserIdByEmailAndPassword(email, hashedPassword);
        if (userId != null) {
            String sessionToken = SecurityUtility.generateRandomHashToken();
            userDao.addSessionTokenToUser(sessionToken, userId);
            return sessionToken;
        } else {
            return null;
        }
    }

    public UUID getUserIdBySessionToken(String sessionToken){
        return userDao.getUserIdBySessionToken(sessionToken);
    }

}
