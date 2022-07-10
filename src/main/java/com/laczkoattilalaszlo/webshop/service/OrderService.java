package com.laczkoattilalaszlo.webshop.service;

import com.laczkoattilalaszlo.webshop.data.dao.OrderDao;

import java.util.UUID;

public class OrderService {

    // Field(s)
    private OrderDao orderDao;

    // Constructor(s)
    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    // Method(s)
    public UUID getActiveOrderId(UUID userId) {
        return orderDao.getActiveOrderId(userId);
    }

    public void createActiveOrder(UUID userId) {
        orderDao.createActiveOrder(userId);
    }

}
