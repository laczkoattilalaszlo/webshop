package com.laczkoattilalaszlo.webshop.controller;

import com.google.gson.Gson;
import com.laczkoattilalaszlo.webshop.data.dto.ProductCategorySupplierDto;
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

@WebServlet(urlPatterns = {"/product-types"})
public class ProductCategorySupplierController extends HttpServlet {

    // Field(s)
    ProductService productService;

    // Overridden HTTP method(s)
    @Override   // Get product types by ... (category / supplier)
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get parameter(s)
        String by = request.getParameter("by");   // category / supplier

        // Get List<ProductCategorySupplierDto>
        productService = ServiceProvider.getInstance().getProductService();
        List<ProductCategorySupplierDto> productCategorySupplierDtos = null;
        if (by.equals("category")) {
            productCategorySupplierDtos = productService.getProductCategories();
        } else if (by.equals("supplier")) {
            productCategorySupplierDtos = productService.getProductSuppliers();
        }

        // Serialize data
        String serializedProductCategories = new Gson().toJson(productCategorySupplierDtos);

        // Edit response
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Send response
        PrintWriter printWriter = response.getWriter();
        printWriter.print(serializedProductCategories);
        printWriter.flush();
    }

}
