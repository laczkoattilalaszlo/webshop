package com.laczkoattilalaszlo.webshop.service;

import com.laczkoattilalaszlo.webshop.data.dao.ProductCategoryDao;
import com.laczkoattilalaszlo.webshop.data.dto.ProductCategoryDto;

import java.util.List;

public class ProductCategoryService {

    private ProductCategoryDao productCategoryDAO;

    public ProductCategoryService(ProductCategoryDao productCategoryDao) {
        this.productCategoryDAO = productCategoryDao;
    }

    public List<ProductCategoryDto> getProductCategories() {
        return productCategoryDAO.getProductCategories();
    }

}
