package com.laczkoattilalaszlo.webshop.data.dao;

import com.laczkoattilalaszlo.webshop.data.dto.UserDto;

import java.util.UUID;

public interface UserDao {

    void addUser(String email, String password);

    void removeUser(UUID userId);

    UserDto getUser(UUID userId);

    void updateUser(UserDto userDto, UUID userId);

    UUID getUserIdByEmailAndPassword(String email, String hashedPassword);

    void addSessionTokenToUser(String sessionToken, UUID userId);

    UUID getUserIdBySessionToken(String sessionToken);

    String getCurrentPassword(UUID userId);

    void updatePassword(String newPassword, UUID userId);

}
