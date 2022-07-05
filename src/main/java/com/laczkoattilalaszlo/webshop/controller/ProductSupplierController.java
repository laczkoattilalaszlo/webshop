package com.laczkoattilalaszlo.webshop.controller;

import com.google.gson.Gson;
import com.laczkoattilalaszlo.webshop.data.dto.ProductSupplierDto;
import com.laczkoattilalaszlo.webshop.service.ProductSupplierService;
import com.laczkoattilalaszlo.webshop.service.ServiceProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = {"/product-supplier"})
public class ProductSupplierController extends HttpServlet {

    // Field(s)
    ProductSupplierService productSupplierService;

    @Override   // Get product suppliers
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get List<ProductCategoryDto>
        productSupplierService = ServiceProvider.getInstance().setupProductSupplierService();
        List<ProductSupplierDto> productSuppliers = productSupplierService.getProductSuppliers();

        // Serialize data
        String serializedProductCategories = new Gson().toJson(productSuppliers);

        // Edit response
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Send response
        PrintWriter printWriter = response.getWriter();
        printWriter.print(serializedProductCategories);
        printWriter.flush();
    }

}
