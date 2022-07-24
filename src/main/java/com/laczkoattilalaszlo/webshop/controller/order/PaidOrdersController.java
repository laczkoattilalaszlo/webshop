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
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

@WebServlet(urlPatterns = {"/paid-orders"})
public class PaidOrdersController extends HttpServlet {

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

}
