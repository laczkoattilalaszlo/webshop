package com.laczkoattilalaszlo.webshop.data.dao;

import com.laczkoattilalaszlo.webshop.data.dto.*;

import java.util.List;
import java.util.UUID;

public interface OrderDao {

    void createActiveOrder(UUID userId);

    UUID getActiveOrderId(UUID userId);

    List<PaidOrderDto> getPaidOrdersByUserId(UUID userId);

    List<ProductInOrderCartDto> getOrderCart(UUID orderId);

    void addOrderCart(UUID orderId, List<ProductInOrderCartDto> orderCart);

    void deleteOrderCart(UUID orderId);

    UserDto getOrderContact(UUID orderId);

    void addOrderContact(UUID orderId, UserDto orderContact);

    void deleteOrderContact(UUID orderId);

    AddressDto getOrderAddress(String tableName, UUID orderId);

    void addOrderAddress(String tableName, UUID orderId, AddressDto orderAddress);

    void deleteOrderAddress(String tableName, UUID orderId);

    List<OrderPaymentDto> getOrderPayments(UUID orderId);

}
