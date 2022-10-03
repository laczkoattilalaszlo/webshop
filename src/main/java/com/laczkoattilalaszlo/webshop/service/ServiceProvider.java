package com.laczkoattilalaszlo.webshop.service;

import com.laczkoattilalaszlo.webshop.data.DatabaseManager;
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
    AddressService addressService = null;
    OrderService orderService = null;

    // Method(s)
    public ProductService getProductService() {
        if (productService == null) {
            try {
                DatabaseManager dataBaseManager = new DatabaseManager();
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
                DatabaseManager dataBaseManager = new DatabaseManager();
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
                DatabaseManager dataBaseManager = new DatabaseManager();
                DataSource dataSource = dataBaseManager.connect();
                UserDao userDao = new UserDaoDb(dataSource);
                userService = new UserService(userDao);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return userService;
    }

    public AddressService getAddressService() {
        if (addressService == null) {
            try {
                DatabaseManager dataBaseManager = new DatabaseManager();
                DataSource dataSource = dataBaseManager.connect();
                AddressDao addressDao = new AddressDaoDb(dataSource);
                addressService = new AddressService(addressDao);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return addressService;
    }

    public OrderService getOrderService() {
        if (orderService == null) {
            try {
                DatabaseManager dataBaseManager = new DatabaseManager();
                DataSource dataSource = dataBaseManager.connect();
                OrderDao orderDao = new OrderDaoDb(dataSource);
                orderService = new OrderService(orderDao);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return orderService;
    }

}
