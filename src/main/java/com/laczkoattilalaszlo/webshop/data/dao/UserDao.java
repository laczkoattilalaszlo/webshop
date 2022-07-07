package com.laczkoattilalaszlo.webshop.data.dao;

import java.util.UUID;

public interface UserDao {

    void addUser(String email, String password);

    void removeUser(UUID userId);

}
