package com.laczkoattilalaszlo.webshop.controller;

import com.google.gson.Gson;
import com.laczkoattilalaszlo.webshop.model.Product;
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

@WebServlet(urlPatterns = {"/products"})
public class ProductController extends HttpServlet {

    // Field(s)
    ProductService productService;

    // Overridden HTTP method(s)
    @Override   // Get products by ... (category / supplier)
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get parameter(s)
        String by = request.getParameter("by");                 // category / supplier
        UUID id = UUID.fromString(request.getParameter("id"));  // category id / supplier id

        // Get List<Product>
        productService = ServiceProvider.getInstance().getProductService();
        List<Product> products;
        if (by.equals("category")) {
            products = productService.getProductsByCategory(id);
        } else if (by.equals("supplier")) {
            products = productService.getProductsBySupplier(id);
        } else {
            throw new ServletException();
        }

        // Serialize data
        String serializedProducts = new Gson().toJson(products);

        // Edit response
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Send response
        PrintWriter printWriter = response.getWriter();
        printWriter.print(serializedProducts);
        printWriter.flush();
    }

}
