package com.laczkoattilalaszlo.webshop.controller;

import com.google.gson.Gson;
import com.laczkoattilalaszlo.webshop.data.SecurityUtility;
import com.laczkoattilalaszlo.webshop.data.dto.UserDto;
import com.laczkoattilalaszlo.webshop.data.dto.UserRegistrationAndAuthenticationDto;
import com.laczkoattilalaszlo.webshop.model.User;
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
        UserRegistrationAndAuthenticationDto userRegistrationAndAuthenticationDto = new Gson().fromJson(payload, UserRegistrationAndAuthenticationDto.class);
        String email = userRegistrationAndAuthenticationDto.getEmail();
        String password = userRegistrationAndAuthenticationDto.getPassword();

        // Add user to database
        userService = ServiceProvider.getInstance().getUserService();
        userService.addUser(email, password);
    }

    @Override   // Remove user
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get session token from header
        String sessionToken = request.getHeader("session-token");

        // Add user to database
        userService = ServiceProvider.getInstance().getUserService();
        userService.removeUser(sessionToken);
    }

    @Override   // Get user
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get session token from header
        String sessionToken = request.getHeader("session-token");

        // Get user
        userService = ServiceProvider.getInstance().getUserService();
        UserDto userDto = userService.getUser(sessionToken);

        // Serialize data
        String serializedUserDto = new Gson().toJson(userDto);

        // Edit response
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Send response
        PrintWriter printWriter = response.getWriter();
        printWriter.print(serializedUserDto);
        printWriter.flush();
    }

    @Override   // Update user
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get payload
        BufferedReader bufferedReader = request.getReader();
        String payload = bufferedReader.lines().collect(Collectors.joining());

        // Deserialize payload
        User user = new Gson().fromJson(payload, User.class);

        // Update user
        userService = ServiceProvider.getInstance().getUserService();
        userService.updateUser(user);
    }

}
