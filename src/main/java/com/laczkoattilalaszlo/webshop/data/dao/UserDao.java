package com.laczkoattilalaszlo.webshop.data.dao;

import com.laczkoattilalaszlo.webshop.model.User;

import java.util.UUID;

public interface UserDao {

    void addUser(String email, String password);

    void removeUser(UUID userId);

    User getUser(UUID userId);

    void updateUser(User user);

    UUID getUserIdByEmailAndPassword(String email, String hashedPassword);

    void addSessionTokenToUser(String sessionToken, UUID userId);

    UUID getUserIdBySessionToken(String sessionToken);

}
