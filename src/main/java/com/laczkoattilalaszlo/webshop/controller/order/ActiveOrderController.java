package com.laczkoattilalaszlo.webshop.controller.order;

import com.google.gson.Gson;
import com.laczkoattilalaszlo.webshop.data.dto.OrderDto;
import com.laczkoattilalaszlo.webshop.service.OrderService;
import com.laczkoattilalaszlo.webshop.service.ServiceProvider;
import com.laczkoattilalaszlo.webshop.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

@WebServlet(urlPatterns = {"/active-order"})
public class ActiveOrderController extends HttpServlet {

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

    @Override   // Get active order
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get session token from header
        String sessionToken = request.getHeader("session-token");

        // Get user id from session token
        userService = ServiceProvider.getInstance().getUserService();
        UUID userId = userService.getUserIdBySessionToken(sessionToken);

        // Get active order id
        orderService = ServiceProvider.getInstance().getOrderService();
        UUID activeOrderId = orderService.getActiveOrderId(userId);

        // Get active order, if there is one
        if (activeOrderId != null) {
            OrderDto activeOrder = orderService.getOrder(activeOrderId);

            // Serialize data
            String serializedActiveOrder = new Gson().toJson(activeOrder);

            // Edit response
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            // Send response
            PrintWriter printWriter = response.getWriter();
            printWriter.print(serializedActiveOrder);
            printWriter.flush();
        } else {
            response.sendError(404, "Active order not found.");
        }
    }

}
