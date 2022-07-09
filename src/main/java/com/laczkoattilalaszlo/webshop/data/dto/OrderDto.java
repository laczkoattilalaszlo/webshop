package com.laczkoattilalaszlo.webshop.data.dto;

import java.time.LocalDate;
import java.util.UUID;

public class OrderDto {

    // Field(s)
    private UUID orderContact;
    private UUID orderShippingAddress;
    private UUID orderBillingAddress;
    private String transactionCode;
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

    public String getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
