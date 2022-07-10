package com.laczkoattilalaszlo.webshop.data.dao;

import com.laczkoattilalaszlo.webshop.data.dto.AddressDto;
import com.laczkoattilalaszlo.webshop.data.dto.OrderPaymentDto;
import com.laczkoattilalaszlo.webshop.data.dto.ProductInOrderCartDto;
import com.laczkoattilalaszlo.webshop.data.dto.UserDto;

import java.util.List;
import java.util.UUID;

public interface OrderDao {

    void createActiveOrder(UUID userId);

    UUID getActiveOrderId(UUID userId);

    List<ProductInOrderCartDto> getOrderCart(UUID orderId);

    UserDto getOrderContact(UUID orderId);

    AddressDto getOrderShippingAddress(UUID orderId);

    AddressDto getOrderBillingAddress(UUID orderId);

    List<OrderPaymentDto> getOrderPayments(UUID orderId);

}
