package com.laczkoattilalaszlo.webshop.service;

import com.laczkoattilalaszlo.webshop.data.SecurityUtility;
import com.laczkoattilalaszlo.webshop.data.dao.UserDao;
import com.laczkoattilalaszlo.webshop.data.dto.UserDto;

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

    public void removeUser(UUID userId) {
        userDao.removeUser(userId);
    }

    public UserDto getUser(UUID userId) {
        return userDao.getUser(userId);
    }

    public UUID getUserIdByEmail(String email) {
        return userDao.getUserIdByEmail(email);
    }

    public UUID getUserIdBySessionToken(String sessionToken) {
        return userDao.getUserIdBySessionToken(sessionToken);
    }

    public void updateUser(UserDto userDto, UUID userId) {
        userDao.updateUser(userDto, userId);
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

    public String getCurrentPassword(UUID userId) {
        return userDao.getCurrentPassword(userId);
    }

    public void updatePassword(String hashedNewPassword, UUID userId) {
        userDao.updatePassword(hashedNewPassword, userId);
    }

}
