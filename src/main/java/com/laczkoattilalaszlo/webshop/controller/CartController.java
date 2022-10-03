package com.laczkoattilalaszlo.webshop.controller;

import com.google.gson.Gson;

import com.laczkoattilalaszlo.webshop.data.dto.ProductInCartDto;
import com.laczkoattilalaszlo.webshop.service.CartService;
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

@WebServlet(urlPatterns = {"/cart"})
public class CartController extends HttpServlet {

    // Field(s)
    CartService cartService;
    UserService userService;

    // Overridden HTTP method(s)
    @Override   // Get cart
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get session token from header
        String sessionToken = request.getHeader("session-token");

        // Get user id from session token
        userService = ServiceProvider.getInstance().getUserService();
        UUID userId = userService.getUserIdBySessionToken(sessionToken);

        // Get cart content
        cartService = ServiceProvider.getInstance().getCartService();
        List<ProductInCartDto> cart = cartService.getCart(userId);

        // Serialize data
        String serializedProductCategories = new Gson().toJson(cart);

        // Edit response
        response.setContentType("application/json; charset=utf-8");
        response.setCharacterEncoding("UTF-8");

        // Send response
        PrintWriter printWriter = response.getWriter();
        printWriter.print(serializedProductCategories);
        printWriter.flush();
    }

    @Override   // Add product to cart
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get session token from header
        String sessionToken = request.getHeader("session-token");

        // Get user id from session token
        userService = ServiceProvider.getInstance().getUserService();
        UUID userId = userService.getUserIdBySessionToken(sessionToken);

        if (userId != null) {
            // Get payload (product id) from body
            BufferedReader bufferedReader = request.getReader();
            String payload = bufferedReader.lines().collect(Collectors.joining());

            // Deserialize payload
            UUID productId = UUID.fromString(payload);

            // Add product to cart
            cartService = ServiceProvider.getInstance().getCartService();
            cartService.addProductToCart(productId, userId);
        } else {
            response.setStatus(401);
        }
    }

    @Override   // Remove product from cart
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get session token from header
        String sessionToken = request.getHeader("session-token");

        // Get user id from session token
        userService = ServiceProvider.getInstance().getUserService();
        UUID userId = userService.getUserIdBySessionToken(sessionToken);

        // Get payload (product id) from body
        BufferedReader bufferedReader = request.getReader();
        String payload = bufferedReader.lines().collect(Collectors.joining());

        // Deserialize payload
        UUID productId = UUID.fromString(payload);

        // Remove product from cart
        cartService = ServiceProvider.getInstance().getCartService();
        cartService.removeProductFromCart(productId, userId);
    }

}
