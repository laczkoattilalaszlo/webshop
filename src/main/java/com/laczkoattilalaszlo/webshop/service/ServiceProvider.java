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
    ProductCategoryService productCategoryService = null;
    ProductSupplierService productSupplierService = null;
    ProductService productService = null;

    // Method(s)
    public ProductCategoryService getProductCategoryService() {
        if (productCategoryService == null) {
            try {
                DataBaseManager dataBaseManager = new DataBaseManager();
                DataSource dataSource = dataBaseManager.connect();
                ProductCategoryDao productCategoryDao = new ProductCategoryDaoDb(dataSource);
                productCategoryService = new ProductCategoryService(productCategoryDao);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return productCategoryService;
    }

    public ProductSupplierService getProductSupplierService() {
        if (productSupplierService == null) {
            try {
                DataBaseManager dataBaseManager = new DataBaseManager();
                DataSource dataSource = dataBaseManager.connect();
                ProductSupplierDao productSupplierDao = new ProductSupplierDaoDb(dataSource);
                productSupplierService = new ProductSupplierService(productSupplierDao);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return productSupplierService;
    }

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

}
