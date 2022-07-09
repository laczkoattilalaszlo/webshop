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
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

@WebServlet(urlPatterns = {"/order-contact"})
public class InProgressOrderContactController extends HttpServlet {

    // Field(s)
    OrderService orderService;
    UserService userService;

    @Override   // Get in progress order contact
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get session token from header
        String sessionToken = request.getHeader("session-token");

        // Get user id from session token
        userService = ServiceProvider.getInstance().getUserService();
        UUID userId = userService.getUserIdBySessionToken(sessionToken);

        // Get in progress order id
        orderService = ServiceProvider.getInstance().getOrderService();
        UUID inProgressOrderId = orderService.getInProgressOrderId(userId);

        // Get in progress order contact id
        UUID inProgressOrderContactId = orderService.getOrderContactId(inProgressOrderId);

        // Get in progress order-contact
        UserDto inProgressOrderContact = orderService.getOrderContact(inProgressOrderContactId);

        // Serialize data
        String serializedInProgressOrderContact = new Gson().toJson(inProgressOrderContact);

        // Edit response
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Send response
        PrintWriter printWriter = response.getWriter();
        printWriter.print(serializedInProgressOrderContact);
        printWriter.flush();
    }

}
