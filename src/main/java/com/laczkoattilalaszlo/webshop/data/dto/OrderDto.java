package com.laczkoattilalaszlo.webshop.data.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class OrderDto {

    // Field(s)
    private UUID orderContact;
    private UUID orderShippingAddress;
    private UUID orderBillingAddress;
    private String successfulTransactionCode;
    private LocalDate date;

    // Getter(s) and Setter(s)
    public UUID getOrderContact() {
        return orderContact;
    }

    public void setOrderContact(UUID orderContact) {
        this.orderContact = orderContact;
    }

    public UUID getOrderShippingAddress() {
        return orderShippingAddress;
    }

    public void setOrderShippingAddress(UUID orderShippingAddress) {
        this.orderShippingAddress = orderShippingAddress;
    }

    public UUID getOrderBillingAddress() {
        return orderBillingAddress;
    }

    public void setOrderBillingAddress(UUID orderBillingAddress) {
        this.orderBillingAddress = orderBillingAddress;
    }

    public String getSuccessfulTransactionCode() {
        return successfulTransactionCode;
    }

    public void setSuccessfulTransactionCode(String successfulTransactionCode) {
        this.successfulTransactionCode = successfulTransactionCode;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
