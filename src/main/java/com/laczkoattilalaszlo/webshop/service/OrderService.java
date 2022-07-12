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
    public List<OrderDto> getPaidOrdersByUserId(UUID userId) {
        // Get order ids related to user
        List<UUID> orderIds = orderDao.getOrderIdsByUserId(userId);

        // Get active order id
        UUID activeOrderId = orderDao.getActiveOrderId(userId);

        // Get paid orders
        List<OrderDto> orders = new ArrayList<>();
        for (UUID orderId : orderIds) {
            if (!orderId.equals(activeOrderId)) {
                OrderDto order = getOrder(orderId);
                orders.add(order);
            }
        }

        return orders;
    }

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
        AddressDto orderShippingAddress = orderDao.getOrderAddress("order_shipping_address", orderId);
        order.setOrderShippingAddress(orderShippingAddress);

        // Get order billing address
        AddressDto orderBillingAddress = orderDao.getOrderAddress("order_billing_address", orderId);
        order.setOrderBillingAddress(orderBillingAddress);

        // Get order payment
        List<OrderPaymentDto> orderPayments = orderDao.getOrderPayments(orderId);
        order.setOrderPayments(orderPayments);

        return order;
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

    public void updateOrderContact(UUID orderId, UserDto orderContact) {
        // Delete order contact
        orderDao.deleteOrderContact(orderId);

        // Add order contact
        orderDao.addOrderContact(orderId, orderContact);
    }

    public void updateOrderAddress(String tableName, UUID orderId, AddressDto orderAddress) {
        // Delete order ... (shipping / billing) address
        orderDao.deleteOrderAddress(tableName, orderId);

        // Update order ... (shipping / billing) address
        orderDao.addOrderAddress(tableName, orderId, orderAddress);
    }

}
