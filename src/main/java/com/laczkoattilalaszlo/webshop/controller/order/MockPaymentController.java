package com.laczkoattilalaszlo.webshop.controller.order;

import com.laczkoattilalaszlo.webshop.service.CartService;
import com.laczkoattilalaszlo.webshop.service.OrderService;
import com.laczkoattilalaszlo.webshop.service.ServiceProvider;
import com.laczkoattilalaszlo.webshop.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet(urlPatterns = {"/mock-payment"})
public class MockPaymentController extends HttpServlet {

    // Field(s)
    OrderService orderService;
    UserService userService;
    CartService cartService;

    // Overridden HTTP method(s)
    @Override   // Mock successful payment
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get session token from header
        String sessionToken = request.getHeader("session-token");

        // Get user id from session token
        userService = ServiceProvider.getInstance().getUserService();
        UUID userId = userService.getUserIdBySessionToken(sessionToken);

        // Get active order id
        orderService = ServiceProvider.getInstance().getOrderService();
        UUID activeOrderId = orderService.getActiveOrderId(userId);

        if (userId != null) {
            // Mock an unsuccessful payment
            orderService.mockPayment(activeOrderId, "Failed");

            // Mock a successful payment
            orderService.mockPayment(activeOrderId, "Succeeded");

            // Empty cart
            cartService = ServiceProvider.getInstance().getCartService();
            cartService.removeAllProductsFromCart(userId);
        } else {
            response.setStatus(401);
        }
    }

}
