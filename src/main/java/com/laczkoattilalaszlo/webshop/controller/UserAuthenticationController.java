package com.laczkoattilalaszlo.webshop.controller;

import com.google.gson.Gson;
import com.laczkoattilalaszlo.webshop.data.dto.UserPasswordDto;
import com.laczkoattilalaszlo.webshop.data.dto.UserRegistrationAndAuthenticationDto;
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
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/user-authentication"})
public class UserAuthenticationController extends HttpServlet {

    // Field(s)
    UserService userService;

    // Overridden HTTP method(s)
    @Override   // Authenticate user
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get payload
        BufferedReader bufferedReader = request.getReader();
        String payload = bufferedReader.lines().collect(Collectors.joining());

        // Deserialize payload (application/json)
        UserRegistrationAndAuthenticationDto userRegistrationAndAuthenticationDto = new Gson().fromJson(payload, UserRegistrationAndAuthenticationDto.class);
        String email = userRegistrationAndAuthenticationDto.getEmail();
        String password = userRegistrationAndAuthenticationDto.getPassword();

        // Try to authenticate user and get session token
        userService = ServiceProvider.getInstance().getUserService();
        String sessionToken = userService.authenticateUser(email, password);

        // Edit response
        response.setContentType("plain/text");
        response.setCharacterEncoding("UTF-8");

        // Send response according to the authentication result
        PrintWriter printWriter = response.getWriter();
        if (sessionToken != null) {
            printWriter.print(sessionToken);
        } else {
            printWriter.print("Authentication failed");
        }
        printWriter.flush();
    }

    @Override   // Update user password
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get session token from header
        String sessionToken = request.getHeader("session-token");

        // Get payload
        BufferedReader bufferedReader = request.getReader();
        String payload = bufferedReader.lines().collect(Collectors.joining());

        // Deserialize payload (application/json)
        UserPasswordDto userPasswordDto = new Gson().fromJson(payload, UserPasswordDto.class);
        String currentPassword = userPasswordDto.getCurrentPassword();
        String newPassword = userPasswordDto.getNewPassword();

        userService = ServiceProvider.getInstance().getUserService();
        userService.updatePassword(currentPassword, newPassword, sessionToken);
    }

}
