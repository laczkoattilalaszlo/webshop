package com.laczkoattilalaszlo.webshop.controller;

import com.google.gson.Gson;

import com.laczkoattilalaszlo.webshop.data.dto.BankCardDto;
import com.laczkoattilalaszlo.webshop.data.dto.UserDto;
import com.laczkoattilalaszlo.webshop.service.BankCardService;
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

@WebServlet(urlPatterns = {"/bank-card"})
public class BankCardController extends HttpServlet {

    // Field(s)
    BankCardService bankCardService;
    UserService userService;

    @Override   // Get bank card
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get session token from header
        String sessionToken = request.getHeader("session-token");

        // Get user id from session token
        userService = ServiceProvider.getInstance().getUserService();
        UUID userId = userService.getUserIdBySessionToken(sessionToken);

        // Get bank card
        bankCardService = ServiceProvider.getInstance().getBankCardService();
        BankCardDto bankCardDto = bankCardService.getBankCard(userId);

        // Serialize data
        String serializedUserDto = new Gson().toJson(bankCardDto);

        // Edit response
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Send response
        PrintWriter printWriter = response.getWriter();
        printWriter.print(serializedUserDto);
        printWriter.flush();
    }

    @Override   // Update bank card
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get session token from header
        String sessionToken = request.getHeader("session-token");

        // Get user id from session token
        userService = ServiceProvider.getInstance().getUserService();
        UUID userId = userService.getUserIdBySessionToken(sessionToken);

        // Get payload
        BufferedReader bufferedReader = request.getReader();
        String payload = bufferedReader.lines().collect(Collectors.joining());

        // Deserialize payload
        BankCardDto bankCardDto = new Gson().fromJson(payload, BankCardDto.class);

        // Update user
        bankCardService.updateBankCard(bankCardDto, userId);
    }

}
