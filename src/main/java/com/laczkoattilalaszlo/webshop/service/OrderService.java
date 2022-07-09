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
    public UUID getInProgressOrderId(UUID userId) {
        return orderDao.getInProgressOrderId(userId);
    }

    public void createNewInProgressOrder(UUID userId) {
        orderDao.createNewInProgressOrder(userId);
    }

    public OrderExtendedDeepDto getInProgressOrder(UUID userId) {
        OrderExtendedDeepDto deepExtendedOrder = new OrderExtendedDeepDto();

        // Get in progress order id
        UUID orderId = orderDao.getInProgressOrderId(userId);

        // Get order transaction code
        OrderDto order = orderDao.getOrder(orderId);
        String TransactionCode = order.getTransactionCode();

        deepExtendedOrder.setTransactionCode(TransactionCode);

        // Get order date
        LocalDate orderDate = order.getDate();

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

        // Get order cart
        List<ProductInOrderCartDto> orderCart = orderDao.getOrderCart(orderId);

        deepExtendedOrder.setOrderCart(orderCart);

        return deepExtendedOrder;
    }

    public UUID getOrderContactId(UUID orderId) {
        return orderDao.getOrderContactId(orderId);
    }

    public UserDto getOrderContact(UUID orderContactId) {
        return orderDao.getOrderContact(orderContactId);
    }

}
