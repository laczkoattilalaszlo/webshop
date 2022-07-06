package com.laczkoattilalaszlo.webshop.service;

import com.laczkoattilalaszlo.webshop.data.dao.ProductCategoryDao;
import com.laczkoattilalaszlo.webshop.data.dao.ProductDao;
import com.laczkoattilalaszlo.webshop.data.dao.ProductSupplierDao;
import com.laczkoattilalaszlo.webshop.model.Product;
import com.laczkoattilalaszlo.webshop.model.ProductCategory;
import com.laczkoattilalaszlo.webshop.model.ProductSupplier;

import java.util.List;
import java.util.UUID;

public class ProductService {

    // Field(s)
    private ProductDao productDao;
    private ProductCategoryDao productCategoryDAO;
    private ProductSupplierDao productSupplierDao;

    // Constructor(s)
    public ProductService(ProductDao productDao, ProductCategoryDao productCategoryDao, ProductSupplierDao productSupplierDao) {
        this.productDao = productDao;
        this.productCategoryDAO = productCategoryDao;
        this.productSupplierDao = productSupplierDao;
    }

    // Method(s)
    public List<Product> getProductsByCategory(UUID id) {
        return productDao.getProductsByCategory(id);
    }

    public List<ProductCategory> getProductCategories() {
        return productCategoryDAO.getProductCategories();
    }

    public List<ProductSupplier> getProductSuppliers() {
        return productSupplierDao.getProductSuppliers();
    }

}
