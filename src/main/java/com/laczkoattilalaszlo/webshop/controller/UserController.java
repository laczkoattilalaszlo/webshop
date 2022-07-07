package com.laczkoattilalaszlo.webshop.controller;

import com.google.gson.Gson;
import com.laczkoattilalaszlo.webshop.data.dto.ProductForCartOperationsDto;
import com.laczkoattilalaszlo.webshop.data.dto.ProductInCartDto;
import com.laczkoattilalaszlo.webshop.data.dto.UserRegistrationDto;
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

@WebServlet(urlPatterns = {"/user"})
public class UserController extends HttpServlet {

    // Field(s)
    UserService userService;

    // Overridden HTTP method(s)
    @Override   // Add user
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get payload
        BufferedReader bufferedReader = request.getReader();
        String payload = bufferedReader.lines().collect(Collectors.joining());

        // Deserialize payload (application/json)
        UserRegistrationDto userRegistrationDto = new Gson().fromJson(payload, UserRegistrationDto.class);
        String email = userRegistrationDto.getEmail();
        String password = userRegistrationDto.getPassword();

        // Add user to database
        userService = ServiceProvider.getInstance().getUserService();
        userService.addUser(email, password);
    }

    @Override   // Remove user
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get payload
        BufferedReader bufferedReader = request.getReader();
        String payload = bufferedReader.lines().collect(Collectors.joining());

        // Transform payload (text/plain) to proper form
        UUID userId = UUID.fromString(payload);

        // Add user to database
        userService = ServiceProvider.getInstance().getUserService();
        userService.removeUser(userId);
    }

}
