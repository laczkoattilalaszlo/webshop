package com.laczkoattilalaszlo.webshop.controller;

import com.laczkoattilalaszlo.webshop.service.CartService;
import com.laczkoattilalaszlo.webshop.service.ServiceProvider;
import com.laczkoattilalaszlo.webshop.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.UUID;

@WebServlet(urlPatterns = {"/total-price"})
public class CartTotalPriceController extends HttpServlet {

    // Field(s)
    CartService cartService;
    UserService userService;

    // Overridden HTTP method(s)
    @Override   // Get total price
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get session token from header
        String sessionToken = request.getHeader("session-token");

        // Get user id from session token
        userService = ServiceProvider.getInstance().getUserService();
        UUID userId = userService.getUserIdBySessionToken(sessionToken);

        // Get total price
        cartService = ServiceProvider.getInstance().getCartService();
        BigDecimal totalPrice = cartService.getTotalPrice(userId);

        // Edit response
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Send response
        PrintWriter printWriter = response.getWriter();
        printWriter.print(totalPrice);
        printWriter.flush();
    }

}
