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

@WebServlet(urlPatterns = {"/searched-products"})
public class ProductSearchController extends HttpServlet {

    // Field(s)
    ProductService productService;

    // Overridden HTTP method(s)
    @Override   // Get products with matching search criteria (supplier name + product name or description contains the given string)
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get parameter(s)
        String searchedText = request.getParameter("searched-text");

        // Get List<Product>
        productService = ServiceProvider.getInstance().getProductService();
        List<ProductDto> products = productService.getSearchedProducts(searchedText);

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
