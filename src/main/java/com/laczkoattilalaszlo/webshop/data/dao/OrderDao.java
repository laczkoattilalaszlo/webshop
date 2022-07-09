package com.laczkoattilalaszlo.webshop.data.dao;

import com.laczkoattilalaszlo.webshop.data.dto.AddressDto;
import com.laczkoattilalaszlo.webshop.data.dto.OrderDto;
import com.laczkoattilalaszlo.webshop.data.dto.ProductInOrderCartDto;
import com.laczkoattilalaszlo.webshop.data.dto.UserDto;

import java.util.List;
import java.util.UUID;

public interface OrderDao {

    void createNewInProgressOrder(UUID userId);

    UUID getOrderIdOfInProgressOrder(UUID userId);

    OrderDto getOrder(UUID orderId);

    UserDto getOrderContact(UUID orderContactId);

    AddressDto getOrderShippingAddress(UUID orderShippingAddressId);

    AddressDto getOrderBillingAddress(UUID orderBillingAddressId);

    List<ProductInOrderCartDto> getOrderCart(UUID orderId);

}
