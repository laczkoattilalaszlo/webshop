package com.laczkoattilalaszlo.webshop.controller;

import com.laczkoattilalaszlo.webshop.service.OrderService;
import com.laczkoattilalaszlo.webshop.service.ServiceProvider;
import com.laczkoattilalaszlo.webshop.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet(urlPatterns = {"/order"})
public class OrderController extends HttpServlet {

    // Field(s)
    OrderService orderService;
    UserService userService;

    // Overridden HTTP method(s)
    @Override   // Create active order
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get session token from header
        String sessionToken = request.getHeader("session-token");

        // Get user id from session token
        userService = ServiceProvider.getInstance().getUserService();
        UUID userId = userService.getUserIdBySessionToken(sessionToken);

        // Create active order, if there is no active one
        orderService = ServiceProvider.getInstance().getOrderService();
        UUID activeOrderId = orderService.getActiveOrderId(userId);
        if (activeOrderId == null) {
            orderService.createActiveOrder(userId);
        } else {
            response.sendError(409, "An active order already exists.");
        }
    }

}
