package com.laczkoattilalaszlo.webshop.data.dto;

import java.util.Date;
import java.util.UUID;

public class BankCardDto {

    // Field(s)
    private String name;
    private String cardNumber;
    private Date expirationDate;
    private String securityCode;

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

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

}
