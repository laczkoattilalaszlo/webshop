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
public class OrderController extends HttpServlet {

    // Field(s)
    OrderService orderService;
    UserService userService;

    // Overridden HTTP method(s)
    @Override   // Create order
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get session token from header
        String sessionToken = request.getHeader("session-token");

        // Get user id from session token
        userService = ServiceProvider.getInstance().getUserService();
        UUID userId = userService.getUserIdBySessionToken(sessionToken);

        // Create order
        orderService = ServiceProvider.getInstance().getOrderService();
        orderService.createOrder(userId);
    }

    @Override   // Get order
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get session token from header
        String sessionToken = request.getHeader("session-token");

        // Get user id from session token
        userService = ServiceProvider.getInstance().getUserService();
        UUID userId = userService.getUserIdBySessionToken(sessionToken);

        // Get order
        orderService = ServiceProvider.getInstance().getOrderService();
        OrderExtendedDeepDto orderExtendedDeepDto = orderService.getOrder(userId);

        // Serialize data
        String serializedOrderExtendedDeepDto = new Gson().toJson(orderExtendedDeepDto);

        // Edit response
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Send response
        PrintWriter printWriter = response.getWriter();
        printWriter.print(serializedOrderExtendedDeepDto);
        printWriter.flush();
    }

}
