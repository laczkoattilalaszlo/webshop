package com.laczkoattilalaszlo.webshop.model;

import java.util.UUID;

public class User {

    // Field(s)
    private UUID id;
    private String email;
    private String password;
    private String name;
    private String phone;

    // Getter(s) and Setter(s)
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
