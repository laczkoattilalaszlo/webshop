package com.laczkoattilalaszlo.webshop.service;

import com.laczkoattilalaszlo.webshop.data.dao.OrderDao;
import com.laczkoattilalaszlo.webshop.data.dto.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class OrderService {

    // Field(s)
    private OrderDao orderDao;

    // Constructor(s)
    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    // Method(s)
    public void createOrder(UUID userId) {
        orderDao.createOrder(userId);
    }

    public OrderExtendedDeepDto getOrder(UUID userId) {
        OrderExtendedDeepDto deepExtendedOrder = new OrderExtendedDeepDto();

        // Get order cart
        UUID orderId = orderDao.getOrderIdOfOrderInProgress(userId);
        List<ProductInOrderCartDto> orderCart = orderDao.getOrderCart(orderId);

        deepExtendedOrder.setOrderCart(orderCart);

        // Get order successful transaction code and order date
        OrderDto order = orderDao.getOrder(orderId);
        String orderSuccessfulTransactionCode = order.getSuccessfulTransactionCode();
        LocalDate orderDate = order.getDate();

        deepExtendedOrder.setSuccessfulTransactionCode(orderSuccessfulTransactionCode);
        deepExtendedOrder.setDate(orderDate);

        // Get order contact
        UUID orderContactId = order.getOrderContact();
        UserDto orderAddress = orderDao.getOrderContact(orderContactId);

        deepExtendedOrder.setOrderContact(orderAddress);

        // Get order shipping address
        UUID orderShippingAddressId = order.getOrderShippingAddress();
        AddressDto orderShippingAddress = orderDao.getOrderShippingAddress(orderShippingAddressId);

        deepExtendedOrder.setOrderShippingAddress(orderShippingAddress);

        // Get order billing address
        UUID orderBillingAddressId = order.getOrderBillingAddress();
        AddressDto orderBillingAddress = orderDao.getOrderBillingAddress(orderBillingAddressId);

        deepExtendedOrder.setOrderBillingAddress(orderBillingAddress);

        return deepExtendedOrder;
    }

}
