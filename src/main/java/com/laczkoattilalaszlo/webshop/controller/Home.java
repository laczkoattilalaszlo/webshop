package com.laczkoattilalaszlo.webshop.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/"})
public class Home extends HttpServlet {

    // Overridden HTTP method(s)
    @Override   // Render index.html
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Edit response
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        // Send response
        PrintWriter printWriter = response.getWriter();
        try {
            String IndexHtmlAsString = convertFileToString("src/main/webapp/templates/index.html");
            printWriter.println(IndexHtmlAsString);
        } finally {
            printWriter.close();
        }
    }

    private String convertFileToString (String filePath) {
        StringBuilder builderResult = new StringBuilder();
        try {
            FileReader file = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(file);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                builderResult.append(line);
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        String result = builderResult.toString();
        return result;
    }

}
