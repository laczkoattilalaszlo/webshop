package com.laczkoattilalaszlo.webshop.controller;

import com.google.gson.Gson;
import com.laczkoattilalaszlo.webshop.data.dto.AddressDto;
import com.laczkoattilalaszlo.webshop.service.AddressService;
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

@WebServlet(urlPatterns = {"/address"})
public class AddressController extends HttpServlet {

    // Field(s)
    AddressService addressService;
    UserService userService;

    @Override   // Get ... (shipping / billing) address
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get session token from header
        String sessionToken = request.getHeader("session-token");

        // Get user id from session token
        userService = ServiceProvider.getInstance().getUserService();
        UUID userId = userService.getUserIdBySessionToken(sessionToken);

        // Get parameter(s)
        String type = request.getParameter("type");     // shipping / billing

        // Get ... (shipping / billing) address
        addressService = ServiceProvider.getInstance().getAddressService();
        AddressDto addressDto = null;
        if (type.equals("shipping")) {
            addressDto = addressService.getAddress("shipping_address", userId);
        } else if (type.equals("billing")) {
            addressDto = addressService.getAddress("billing_address", userId);
        } else {
            response.setStatus(400);
        }

        // Serialize data
        String serializedUserDto = new Gson().toJson(addressDto);

        // Edit response
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Send response
        PrintWriter printWriter = response.getWriter();
        printWriter.print(serializedUserDto);
        printWriter.flush();
    }

    @Override   // Update ... (shipping / billing) address
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get session token from header
        String sessionToken = request.getHeader("session-token");

        // Get user id from session token
        userService = ServiceProvider.getInstance().getUserService();
        UUID userId = userService.getUserIdBySessionToken(sessionToken);

        // Get parameter(s)
        String type = request.getParameter("type");     // shipping / billing

        System.out.println(request.getCharacterEncoding());
        request.setCharacterEncoding("UTF-8");
        System.out.println(request.getCharacterEncoding());
        // Get payload
        BufferedReader bufferedReader = request.getReader();
        String payload = bufferedReader.lines().collect(Collectors.joining());
        System.out.println(payload);
        // Deserialize payload
        AddressDto addressDto = new Gson().fromJson(payload, AddressDto.class);

        // Update ... (shipping / billing) address
        addressService = ServiceProvider.getInstance().getAddressService();
        if (type.equals("shipping")) {
            AddressDto existingAddress = addressService.getAddress("shipping_address", userId);
            if (existingAddress == null) {
                addressService.addAddress("shipping_address", userId, addressDto);
            } else {
                addressService.updateAddress("shipping_address", userId, addressDto);
            }
        } else if (type.equals("billing")) {
            AddressDto existingAddress = addressService.getAddress("billing_address", userId);
            if (existingAddress == null) {
                addressService.addAddress("billing_address", userId, addressDto);
            } else {
                addressService.updateAddress("billing_address", userId, addressDto);
            }
        } else {
            response.setStatus(400);
        }
    }

}
