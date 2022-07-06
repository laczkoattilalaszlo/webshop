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

    // Method(s)
    public ProductService getProductService() {
        if (productService == null) {
            try {
                DataBaseManager dataBaseManager = new DataBaseManager();
                DataSource dataSource = dataBaseManager.connect();

                ProductDao productDao = new ProductDaoDb(dataSource);
                ProductCategoryDao productCategoryDao = new ProductCategoryDaoDb(dataSource);
                ProductSupplierDao productSupplierDao = new ProductSupplierDaoDb(dataSource);

                productService = new ProductService(productDao, productCategoryDao, productSupplierDao);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return productService;
    }

}
