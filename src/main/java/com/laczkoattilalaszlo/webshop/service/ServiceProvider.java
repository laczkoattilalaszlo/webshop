package com.laczkoattilalaszlo.webshop.service;

import com.laczkoattilalaszlo.webshop.data.DataBaseManager;
import com.laczkoattilalaszlo.webshop.data.dao.ProductCategoryDao;
import com.laczkoattilalaszlo.webshop.data.dao.ProductCategoryDaoDb;
import com.laczkoattilalaszlo.webshop.data.dao.ProductSupplierDao;
import com.laczkoattilalaszlo.webshop.data.dao.ProductSupplierDaoDb;

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

    public ProductSupplierService setupProductSupplierService() {
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

}
