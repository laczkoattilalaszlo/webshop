package com.laczkoattilalaszlo.webshop.controller;

import com.laczkoattilalaszlo.webshop.service.CartService;
import com.laczkoattilalaszlo.webshop.service.ServiceProvider;

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

    // Overridden HTTP method(s)
    @Override   // Get total price
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get user id parameter
        UUID userId = UUID.fromString(request.getParameter("user-id"));

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
