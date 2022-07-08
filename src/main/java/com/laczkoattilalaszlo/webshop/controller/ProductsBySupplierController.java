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

@WebServlet(urlPatterns = {"/products-by-supplier"})
public class ProductsBySupplierController extends HttpServlet {

    // Field(s)
    ProductService productService;

    // Overridden HTTP method(s)
    @Override   // Get products by supplier
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get product supplier id parameter
        UUID supplierId = UUID.fromString(request.getParameter("supplier-id"));

        // Get List<Product>
        productService = ServiceProvider.getInstance().getProductService();
        List<Product> products = productService.getProductsBySupplier(supplierId);

        // Serialize data
        String serializedProductsBySuppliers = new Gson().toJson(products);

        // Edit response
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Send response
        PrintWriter printWriter = response.getWriter();
        printWriter.print(serializedProductsBySuppliers);
        printWriter.flush();
    }

}
