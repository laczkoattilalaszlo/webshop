package com.laczkoattilalaszlo.webshop.data.dto;

public class UserPasswordDto {

    // Field(s)
    private String currentPassword;
    private String newPassword;

    // Getter(s) and Setter(s)
    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

}
