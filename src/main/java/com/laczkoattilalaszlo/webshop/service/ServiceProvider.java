package com.laczkoattilalaszlo.webshop.service;

import com.laczkoattilalaszlo.webshop.data.DataBaseManager;
import com.laczkoattilalaszlo.webshop.data.dao.*;

import javax.sql.DataSource;
import java.sql.SQLException;

public class ServiceProvider {

    // Singleton class definition
    private static ServiceProvider instance = null;

    private ServiceProvider() {

    }

    public static ServiceProvider getInstance() {
        if (instance == null) {
            instance = new ServiceProvider();
        }
        return instance;
    }

    // Field(s)
    ProductService productService = null;
    CartService cartService = null;
    UserService userService = null;

    // Method(s)
    public ProductService getProductService() {
        if (productService == null) {
            try {
                DataBaseManager dataBaseManager = new DataBaseManager();
                DataSource dataSource = dataBaseManager.connect();
                ProductDao productDao = new ProductDaoDb(dataSource);
                productService = new ProductService(productDao);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return productService;
    }

    public CartService getCartService() {
        if (cartService == null) {
            try {
                DataBaseManager dataBaseManager = new DataBaseManager();
                DataSource dataSource = dataBaseManager.connect();
                CartDao cartDao = new CartDaoDb(dataSource);
                cartService = new CartService(cartDao);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return cartService;
    }

    public UserService getUserService() {
        if (userService == null) {
            try {
                DataBaseManager dataBaseManager = new DataBaseManager();
                DataSource dataSource = dataBaseManager.connect();
                UserDao userDao = new UserDaoDb(dataSource);
                userService = new UserService(userDao);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return userService;
    }

}
