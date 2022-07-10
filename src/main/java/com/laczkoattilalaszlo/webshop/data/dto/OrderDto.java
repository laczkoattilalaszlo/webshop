package com.laczkoattilalaszlo.webshop.data.dto;

import java.util.List;

public class OrderDto {

    // Field(s)
    private List<ProductInOrderCartDto> orderCart;
    private UserDto orderContact;
    private AddressDto orderShippingAddress;
    private AddressDto orderBillingAddress;
    private List<OrderPaymentDto> orderPayments;

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

    public List<OrderPaymentDto> getOrderPayments() {
        return orderPayments;
    }

    public void setOrderPayments(List<OrderPaymentDto> orderPayments) {
        this.orderPayments = orderPayments;
    }

}
