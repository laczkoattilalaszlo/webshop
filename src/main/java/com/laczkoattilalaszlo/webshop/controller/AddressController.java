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
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

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
            addressDto = addressService.getAddress("billing_address", userId);
        } else if (type.equals("billing")) {
            addressDto = addressService.getAddress("shipping_address", userId);
        } else {
            throw new ServletException();
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

}
