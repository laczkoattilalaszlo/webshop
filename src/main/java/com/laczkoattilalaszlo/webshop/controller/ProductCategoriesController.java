package com.laczkoattilalaszlo.webshop.controller;

import com.google.gson.Gson;
import com.laczkoattilalaszlo.webshop.model.ProductCategory;
import com.laczkoattilalaszlo.webshop.service.ProductCategoryService;
import com.laczkoattilalaszlo.webshop.service.ServiceProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = {"/product-categories"})
public class ProductCategoriesController extends HttpServlet {

    // Field(s)
    ProductCategoryService productCategoryService;

    @Override   // Get product categories
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get List<ProductCategoryDto>
        productCategoryService = ServiceProvider.getInstance().getProductCategoryService();
        List<ProductCategory> productCategories = productCategoryService.getProductCategories();

        // Serialize data
        String serializedProductCategories = new Gson().toJson(productCategories);

        // Edit response
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Send response
        PrintWriter printWriter = response.getWriter();
        printWriter.print(serializedProductCategories);
        printWriter.flush();
    }

}
