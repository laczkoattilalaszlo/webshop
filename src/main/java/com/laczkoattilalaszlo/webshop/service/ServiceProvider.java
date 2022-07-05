package com.laczkoattilalaszlo.webshop.service;

import com.laczkoattilalaszlo.webshop.configuration.DataBaseManager;
import com.laczkoattilalaszlo.webshop.data.dao.ProductCategoryDao;
import com.laczkoattilalaszlo.webshop.data.dao.ProductCategoryDaoDb;

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

    // Method(s)
    public ProductCategoryService setupProductCategoryService() {
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

}
