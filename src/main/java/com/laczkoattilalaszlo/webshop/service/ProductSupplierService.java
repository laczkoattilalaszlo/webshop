package com.laczkoattilalaszlo.webshop.service;

import com.laczkoattilalaszlo.webshop.data.dao.ProductSupplierDao;
import com.laczkoattilalaszlo.webshop.model.ProductSupplier;

import java.util.List;

public class ProductSupplierService {

    // Field(s)
    private ProductSupplierDao productSupplierDao;

    // Constructor(s)
    public ProductSupplierService(ProductSupplierDao productSupplierDao) {
        this.productSupplierDao = productSupplierDao;
    }

    // Method(s)
    public List<ProductSupplier> getProductSuppliers() {
        return productSupplierDao.getProductSuppliers();
    }

}
