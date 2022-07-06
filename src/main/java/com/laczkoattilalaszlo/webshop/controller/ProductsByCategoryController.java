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

@WebServlet(urlPatterns = {"/products-by-category"})
public class ProductsByCategoryController extends HttpServlet {

    // Field(s)
    ProductService productService;

    @Override   // Get products by categories
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get product category id parameter
        UUID categoryID = UUID.fromString(request.getParameter("category-id"));

        // Get List<Product>
        productService = ServiceProvider.getInstance().getProductService();
        List<Product> products = productService.getProductsByCategory(categoryID);

        // Serialize data
        String serializedProductCategories = new Gson().toJson(products);

        // Edit response
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Send response
        PrintWriter printWriter = response.getWriter();
        printWriter.print(serializedProductCategories);
        printWriter.flush();
    }

}
