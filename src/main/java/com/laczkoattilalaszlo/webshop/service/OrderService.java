package com.laczkoattilalaszlo.webshop.service;

import com.laczkoattilalaszlo.webshop.data.dao.OrderDao;
import com.laczkoattilalaszlo.webshop.data.dto.*;

import java.util.ArrayList;
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
    public void createActiveOrder(UUID userId) {
        orderDao.createActiveOrder(userId);
    }

    public UUID getActiveOrderId(UUID userId) {
        return orderDao.getActiveOrderId(userId);
    }

    public OrderDto getOrder(UUID orderId) {
        OrderDto order = new OrderDto();

        // Get order cart
        List<ProductInOrderCartDto> orderCart = orderDao.getOrderCart(orderId);
        order.setOrderCart(orderCart);

        // Get order contact
        UserDto orderContact = orderDao.getOrderContact(orderId);
        order.setOrderContact(orderContact);

        // Get order shipping address
        AddressDto orderShippingAddress = orderDao.getOrderShippingAddress(orderId);
        order.setOrderShippingAddress(orderShippingAddress);

        // Get order billing address
        AddressDto orderBillingAddress = orderDao.getOrderBillingAddress(orderId);
        order.setOrderBillingAddress(orderBillingAddress);

        // Get order payment
        List<OrderPaymentDto> orderPayments = orderDao.getOrderPayments(orderId);
        order.setOrderPayments(orderPayments);

        return order;
    }

    public List<ProductInOrderCartDto> getOrderCart(UUID orderId) {
        return orderDao.getOrderCart(orderId);
    }

    public void updateOrderCart(UUID orderId, List<ProductInCartDto> cart) {
        // Delete order cart
        orderDao.deleteOrderCart(orderId);

        // Convert cart to order cart
        List<ProductInOrderCartDto> orderCart = new ArrayList<>();
        for (ProductInCartDto product : cart) {
            ProductInOrderCartDto orderProduct = new ProductInOrderCartDto();
            orderProduct.setProductId(product.getProductId());
            orderProduct.setProductName(product.getName());
            orderProduct.setUnitPrice(product.getPrice());
            orderProduct.setCurrency(product.getCurrency());
            orderProduct.setQuantity(product.getQuantity());
            orderCart.add(orderProduct);
        }

        // Add order cart
        orderDao.addOrderCart(orderId, orderCart);
    }

    public UserDto getOrderContact(UUID orderId) {
        return orderDao.getOrderContact(orderId);
    }

    public void addOrderContact(UUID orderId, UserDto orderContact) {
        orderDao.addOrderContact(orderId, orderContact);
    }

    public void updateOrderContact(UUID orderId, UserDto orderContact) {
        orderDao.updateOrderContact(orderId, orderContact);
    }

}
