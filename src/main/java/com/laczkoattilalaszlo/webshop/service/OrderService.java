package com.laczkoattilalaszlo.webshop.service;

import com.laczkoattilalaszlo.webshop.data.dao.OrderDao;

public class OrderService {

    // Field(s)
    private OrderDao orderDao;

    // Constructor(s)
    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    // Method(s)

}
