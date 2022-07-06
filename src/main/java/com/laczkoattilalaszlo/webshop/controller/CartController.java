package com.laczkoattilalaszlo.webshop.controller;

import com.google.gson.Gson;
import com.laczkoattilalaszlo.webshop.data.dto.CartDto;
import com.laczkoattilalaszlo.webshop.service.CartService;
import com.laczkoattilalaszlo.webshop.service.ServiceProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.UUID;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/cart"})
public class CartController extends HttpServlet {

    // Field(s)
    CartService cartService;

    // Overridden HTTP method(s)
    @Override   // Add product to cart
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get payload
        BufferedReader bufferedReader = request.getReader();
        String payload = bufferedReader.lines().collect(Collectors.joining());

        // Deserialize payload
        CartDto cartDto = new Gson().fromJson(payload, CartDto.class);
        UUID productId = cartDto.getProductId();
        UUID userId = cartDto.getUserId();

        // Add product to cart
        cartService = ServiceProvider.getInstance().getCartService();
        cartService.addProductToCart(productId, userId);
    }

    @Override   // Remove product from cart
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get payload
        BufferedReader bufferedReader = request.getReader();
        String payload = bufferedReader.lines().collect(Collectors.joining());

        // Deserialize payload
        CartDto cartDto = new Gson().fromJson(payload, CartDto.class);
        UUID productId = cartDto.getProductId();
        UUID userId = cartDto.getUserId();

        // Remove product from cart
        cartService = ServiceProvider.getInstance().getCartService();
        cartService.removeProductFromCart(productId, userId);
    }

}
