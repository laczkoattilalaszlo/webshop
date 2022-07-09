package com.laczkoattilalaszlo.webshop.controller.order;

import com.google.gson.Gson;
import com.laczkoattilalaszlo.webshop.data.dto.OrderExtendedDeepDto;
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

@WebServlet(urlPatterns = {"/order"})
public class InProgressOrderController extends HttpServlet {

    // Field(s)
    OrderService orderService;
    UserService userService;

    // Overridden HTTP method(s)
    @Override   // Create new in progress order
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get session token from header
        String sessionToken = request.getHeader("session-token");

        // Get user id from session token
        userService = ServiceProvider.getInstance().getUserService();
        UUID userId = userService.getUserIdBySessionToken(sessionToken);

        // Create new in progress order
        orderService = ServiceProvider.getInstance().getOrderService();
        UUID orderId = orderService.getOrderIdOfInProgressOrder(userId);
        if (orderId == null) {
            orderService.createNewInProgressOrder(userId);
        } else {
            response.sendError(409, "Order already exists.");
        }

    }

    @Override   // Get in progress order
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get session token from header
        String sessionToken = request.getHeader("session-token");

        // Get user id from session token
        userService = ServiceProvider.getInstance().getUserService();
        UUID userId = userService.getUserIdBySessionToken(sessionToken);

        // Get in progress order
        orderService = ServiceProvider.getInstance().getOrderService();
        OrderExtendedDeepDto inProgressOrder = orderService.getInProgressOrder(userId);

        // Serialize data
        String serializedInProgressOrder = new Gson().toJson(inProgressOrder);

        // Edit response
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Send response
        PrintWriter printWriter = response.getWriter();
        printWriter.print(serializedInProgressOrder);
        printWriter.flush();
    }

}
