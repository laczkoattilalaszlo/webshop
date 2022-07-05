package com.laczkoattilalaszlo.webshop.service;

import com.laczkoattilalaszlo.webshop.data.dao.ProductSupplierDao;
import com.laczkoattilalaszlo.webshop.model.ProductSupplier;

import java.util.List;

public class ProductSupplierService {

    private ProductSupplierDao productSupplierDao;

    public ProductSupplierService(ProductSupplierDao productSupplierDao) {
        this.productSupplierDao = productSupplierDao;
    }

    public List<ProductSupplier> getProductSuppliers() {
        return productSupplierDao.getProductSuppliers();
    }

}
