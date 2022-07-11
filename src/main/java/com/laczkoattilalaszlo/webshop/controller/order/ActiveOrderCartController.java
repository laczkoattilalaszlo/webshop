package com.laczkoattilalaszlo.webshop.controller.order;

import com.laczkoattilalaszlo.webshop.data.dto.ProductInCartDto;
import com.laczkoattilalaszlo.webshop.data.dto.ProductInOrderCartDto;
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
import java.util.List;
import java.util.UUID;

@WebServlet(urlPatterns = {"/order-cart"})
public class ActiveOrderCartController extends HttpServlet {

    // Field(s)
    OrderService orderService;
    UserService userService;
    CartService cartService;

    // Overridden HTTP method(s)
    @Override   // Update active order cart
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get session token from header
        String sessionToken = request.getHeader("session-token");

        // Get user id from session token
        userService = ServiceProvider.getInstance().getUserService();
        UUID userId = userService.getUserIdBySessionToken(sessionToken);

        // Get active order id
        orderService = ServiceProvider.getInstance().getOrderService();
        UUID activeOrderId = orderService.getActiveOrderId(userId);

        // Get active order cart
        List<ProductInOrderCartDto> activeOrderCart = orderService.getOrderCart(activeOrderId);

        // Get cart
        cartService = ServiceProvider.getInstance().getCartService();
        List<ProductInCartDto> cart = cartService.getCart(userId);

        // Transfer products from cart to active order cart
        orderService.updateOrderCart(activeOrderId, cart);
    }

}
