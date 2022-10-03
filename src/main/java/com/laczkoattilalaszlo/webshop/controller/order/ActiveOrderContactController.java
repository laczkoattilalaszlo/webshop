package com.laczkoattilalaszlo.webshop.controller.order;

import com.google.gson.Gson;
import com.laczkoattilalaszlo.webshop.data.dto.UserDto;
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
import java.util.UUID;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/active-order-contact"})
public class ActiveOrderContactController extends HttpServlet {

    // Field(s)
    OrderService orderService;
    UserService userService;

    // Overridden HTTP method(s)
    @Override   // Get active order contact
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get session token from header
        String sessionToken = request.getHeader("session-token");

        // Get user id from session token
        userService = ServiceProvider.getInstance().getUserService();
        UUID userId = userService.getUserIdBySessionToken(sessionToken);

        if (userId != null) {
            // Get active order id
            orderService = ServiceProvider.getInstance().getOrderService();
            UUID activeOrderId = orderService.getActiveOrderId(userId);

            // Get active order contact, if there is one
            if (activeOrderId != null) {
                UserDto orderActiveContact = orderService.getOrderContact(activeOrderId);

                // Serialize data
                String serializedActiveOrderContact = new Gson().toJson(orderActiveContact);

                // Edit response
                response.setContentType("application/json; charset=utf-8");
                response.setCharacterEncoding("UTF-8");

                // Send response
                PrintWriter printWriter = response.getWriter();
                printWriter.print(serializedActiveOrderContact);
                printWriter.flush();
            } else {
                response.setStatus(404);
            }
        } else {
            response.setStatus(401);
        }
    }

    @Override   // Update active order contact
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get session token from header
        String sessionToken = request.getHeader("session-token");

        // Get user id from session token
        userService = ServiceProvider.getInstance().getUserService();
        UUID userId = userService.getUserIdBySessionToken(sessionToken);

        // Get active order id
        orderService = ServiceProvider.getInstance().getOrderService();
        UUID activeOrderId = orderService.getActiveOrderId(userId);

        // Get payload
        BufferedReader bufferedReader = request.getReader();
        String payload = bufferedReader.lines().collect(Collectors.joining());

        // Deserialize payload
        UserDto NewOrderContact = new Gson().fromJson(payload, UserDto.class);

        // Update active order contact
        orderService.updateOrderContact(activeOrderId, NewOrderContact);
    }

}
