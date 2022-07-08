package com.laczkoattilalaszlo.webshop.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

public class BankCard {

    // Field(s)
    private String name;
    private String cardNumber;
    private LocalDate expirationDate;
    private String securityCode;
    private UUID userId;

    // Getter(s) and Setter(s)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

}
