package com.laczkoattilalaszlo.webshop.data.dto;

import java.time.LocalDate;
import java.util.List;

public class OrderExtendedDeepDto {

    // Field(s)
    private List<ProductInOrderCartDto> orderCart;
    private UserDto orderContact;
    private AddressDto orderShippingAddress;
    private AddressDto orderBillingAddress;
    private String transactionCode;
    private LocalDate date;

    // Getter(s) and Setter(s)


    public List<ProductInOrderCartDto> getOrderCart() {
        return orderCart;
    }

    public void setOrderCart(List<ProductInOrderCartDto> orderCart) {
        this.orderCart = orderCart;
    }

    public UserDto getOrderContact() {
        return orderContact;
    }

    public void setOrderContact(UserDto orderContact) {
        this.orderContact = orderContact;
    }

    public AddressDto getOrderShippingAddress() {
        return orderShippingAddress;
    }

    public void setOrderShippingAddress(AddressDto orderShippingAddress) {
        this.orderShippingAddress = orderShippingAddress;
    }

    public AddressDto getOrderBillingAddress() {
        return orderBillingAddress;
    }

    public void setOrderBillingAddress(AddressDto orderBillingAddress) {
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
