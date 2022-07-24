package com.laczkoattilalaszlo.webshop.controller.order;

import com.google.gson.Gson;
import com.laczkoattilalaszlo.webshop.data.dto.OrderDto;
import com.laczkoattilalaszlo.webshop.data.dto.PaidOrderDto;
import com.laczkoattilalaszlo.webshop.service.OrderService;
import com.laczkoattilalaszlo.webshop.service.ServiceProvider;
import com.laczkoattilalaszlo.webshop.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/paid-order"})
public class PaidOrderController extends HttpServlet {

    // Field(s)
    OrderService orderService;
    UserService userService;

    @Override   // Get paid orders
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get session token from header
        String sessionToken = request.getHeader("session-token");

        // Get user id from session token
        userService = ServiceProvider.getInstance().getUserService();
        UUID userId = userService.getUserIdBySessionToken(sessionToken);

        if (userId != null) {
            // Get paid orders
            orderService = ServiceProvider.getInstance().getOrderService();
            List<PaidOrderDto> paidOrders = orderService.getPaidOrdersByUserId(userId);

            // Serialize data
            String serializedPaidOrders = new Gson().toJson(paidOrders);

            // Edit response
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            // Send response
            PrintWriter printWriter = response.getWriter();
            printWriter.print(serializedPaidOrders);
            printWriter.flush();

        } else {
            response.setStatus(401);
        }
    }

    @Override   // Get paid order
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get session token from header
        String sessionToken = request.getHeader("session-token");

        // Get user id from session token
        userService = ServiceProvider.getInstance().getUserService();
        UUID userId = userService.getUserIdBySessionToken(sessionToken);

        if (userId != null) {
            // Get payload (paid order id) from body
            BufferedReader bufferedReader = request.getReader();
            String payload = bufferedReader.lines().collect(Collectors.joining());

            // Deserialize payload
            UUID paidOrderId = UUID.fromString(payload);

            // Get paid order
            OrderDto paidOrder = orderService.getOrder(paidOrderId);

            // Serialize data
            String serializedPaidOrder = new Gson().toJson(paidOrder);

            // Edit response
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            // Send response
            PrintWriter printWriter = response.getWriter();
            printWriter.print(serializedPaidOrder);
            printWriter.flush();
        } else {
            response.setStatus(401);
        }
    }

}
