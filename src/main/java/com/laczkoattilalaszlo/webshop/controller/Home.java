package com.laczkoattilalaszlo.webshop.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/"})
public class Home extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Edit response
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        // Send response
        PrintWriter printWriter = response.getWriter();
        try {
            printWriter.println("<!DOCTYPE html>" +
                                "<html lang=\"en\">" +
                                    "<head>" +
                                        "<meta charset=\"utf-8\" />" +
                                        "<title>Web Shop</title>" +
                                        "<link rel=\"stylesheet\" type=\"text/css\" href=\"/css/main.css\" />" +
                                        "<script src=\"/static/javascript/home.js\" defer></script>" +
                                    "</head>" +
                                    "<body>" +
                                        "<div>Index page</div>" +
                                    "</body>" +
                                "</html>");
        } finally {
            printWriter.close();
        }
    }

}
