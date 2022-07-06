package com.laczkoattilalaszlo.webshop.service;

import com.laczkoattilalaszlo.webshop.data.dao.ProductDao;
import com.laczkoattilalaszlo.webshop.model.Product;
import com.laczkoattilalaszlo.webshop.model.ProductCategory;
import com.laczkoattilalaszlo.webshop.model.ProductSupplier;

import java.util.List;
import java.util.UUID;

public class ProductService {

    // Field(s)
    private ProductDao productDao;

    // Constructor(s)
    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    // Method(s)
    public List<Product> getProductsByCategory(UUID id) {
        return productDao.getProductsByCategory(id);
    }

    public List<Product> getProductsBySupplier(UUID id) {
        return productDao.getProductsBySupplier(id);
    }

    public List<ProductCategory> getProductCategories() {
        return productDao.getProductCategories();
    }

    public List<ProductSupplier> getProductSuppliers() {
        return productDao.getProductSuppliers();
    }

}
