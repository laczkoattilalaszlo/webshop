package com.laczkoattilalaszlo.webshop.service;

import com.laczkoattilalaszlo.webshop.data.dao.ProductCategoryDao;
import com.laczkoattilalaszlo.webshop.model.ProductCategory;

import java.util.List;

public class ProductCategoryService {

    // Field(s)
    private ProductCategoryDao productCategoryDAO;

    // Constructor(s)
    public ProductCategoryService(ProductCategoryDao productCategoryDao) {
        this.productCategoryDAO = productCategoryDao;
    }

    // Method(s)
    public List<ProductCategory> getProductCategories() {
        return productCategoryDAO.getProductCategories();
    }

}
