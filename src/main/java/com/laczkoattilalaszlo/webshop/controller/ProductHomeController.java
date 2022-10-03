package com.laczkoattilalaszlo.webshop.controller;

import com.google.gson.Gson;
import com.laczkoattilalaszlo.webshop.data.dto.ProductDto;
import com.laczkoattilalaszlo.webshop.service.ProductService;
import com.laczkoattilalaszlo.webshop.service.ServiceProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

@WebServlet(urlPatterns = {"/random-products"})
public class ProductHomeController extends HttpServlet {

    // Field(s)
    ProductService productService;

    // Overridden HTTP method(s)
    @Override   // Get ... pieces of random products
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get parameter(s)
        int quantity = Integer.valueOf(request.getParameter("product-quantity"));

        // Get List<Product>
        productService = ServiceProvider.getInstance().getProductService();
        List<ProductDto> products = productService.getRandomProducts(quantity);

        // Serialize data
        String serializedProducts = new Gson().toJson(products);

        // Edit response
        response.setContentType("application/json; charset=utf-8");
        response.setCharacterEncoding("UTF-8");

        // Send response
        PrintWriter printWriter = response.getWriter();
        printWriter.print(serializedProducts);
        printWriter.flush();
    }

}
