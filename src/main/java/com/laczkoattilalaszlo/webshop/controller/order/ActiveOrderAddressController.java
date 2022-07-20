package com.laczkoattilalaszlo.webshop.controller.order;

import com.google.gson.Gson;
import com.laczkoattilalaszlo.webshop.data.dto.AddressDto;
import com.laczkoattilalaszlo.webshop.service.OrderService;
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

@WebServlet(urlPatterns = {"/active-order-address"})
public class ActiveOrderAddressController extends HttpServlet {

    // Field(s)
    OrderService orderService;
    UserService userService;

    @Override   // Get active order ... (shipping / billing) address
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get session token from header
        String sessionToken = request.getHeader("session-token");

        // Get user id from session token
        userService = ServiceProvider.getInstance().getUserService();
        UUID userId = userService.getUserIdBySessionToken(sessionToken);

        if (userId != null) {
            // Get active order id
            orderService = ServiceProvider.getInstance().getOrderService();
            UUID activeOrderId = orderService.getActiveOrderId(userId);

            // Get active order ... (shipping / billing) address, if there is one
            if (activeOrderId != null) {
                // Get parameter(s)
                String type = request.getParameter("type");     // shipping / billing

                // Get active order ... (shipping / billing) address
                AddressDto activeOrderAddress = null;
                if (type.equals("shipping")) {
                    activeOrderAddress = orderService.getOrderAddress("order_shipping_address", activeOrderId);
                } else if (type.equals("billing")) {
                    activeOrderAddress = orderService.getOrderAddress("order_billing_address", activeOrderId);
                } else {
                    throw new ServletException();
                }

                // Serialize data
                String serializedActiveOrderAddress = new Gson().toJson(activeOrderAddress);

                // Edit response
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");

                // Send response
                PrintWriter printWriter = response.getWriter();
                printWriter.print(serializedActiveOrderAddress);
                printWriter.flush();
            } else {
                response.setStatus(404);
            }
        } else {
            response.setStatus(401);
        }
    }

    @Override   // Update active order ... (shipping / billing) address
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get session token from header
        String sessionToken = request.getHeader("session-token");

        // Get user id from session token
        userService = ServiceProvider.getInstance().getUserService();
        UUID userId = userService.getUserIdBySessionToken(sessionToken);

        // Get active order id
        orderService = ServiceProvider.getInstance().getOrderService();
        UUID activeOrderId = orderService.getActiveOrderId(userId);

        // Get parameter(s)
        String type = request.getParameter("type");     // shipping / billing

        // Get payload
        BufferedReader bufferedReader = request.getReader();
        String payload = bufferedReader.lines().collect(Collectors.joining());

        // Deserialize payload
        AddressDto NewOrderAddress = new Gson().fromJson(payload, AddressDto.class);

        // Update active order ... (shipping / billing) address
        if (type.equals("shipping")) {
            orderService.updateOrderAddress("order_shipping_address", activeOrderId, NewOrderAddress);
        } else if (type.equals("billing")) {
            orderService.updateOrderAddress("order_billing_address", activeOrderId, NewOrderAddress);
        } else {
            response.setStatus(400);
        }
    }

}
