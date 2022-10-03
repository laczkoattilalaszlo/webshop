package com.laczkoattilalaszlo.webshop.controller;

import com.google.gson.Gson;
import com.laczkoattilalaszlo.webshop.model.ProductSupplier;
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

@WebServlet(urlPatterns = {"/product-suppliers-by-category"})
public class ProductSupplierController extends HttpServlet {

    // Field(s)
    ProductService productService;

    // Overridden HTTP method(s)
    @Override   // Get product suppliers by category
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get parameter(s)
        UUID categoryId = UUID.fromString(request.getParameter("category-id"));

        // Get product suppliers by category
        productService = ServiceProvider.getInstance().getProductService();
        List<ProductSupplier> productSuppliers = productService.getProductSuppliersByCategory(categoryId);

        // Serialize data
        String serializedProductSuppliers = new Gson().toJson(productSuppliers);

        // Edit response
        response.setContentType("application/json; charset=utf-8");
        response.setCharacterEncoding("UTF-8");

        // Send response
        PrintWriter printWriter = response.getWriter();
        printWriter.print(serializedProductSuppliers);
        printWriter.flush();
    }

}
