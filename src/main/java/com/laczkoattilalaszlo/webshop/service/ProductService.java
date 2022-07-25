package com.laczkoattilalaszlo.webshop.service;

import com.laczkoattilalaszlo.webshop.data.dao.ProductDao;
import com.laczkoattilalaszlo.webshop.data.dto.ProductDto;
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
    public List<ProductCategory> getProductCategories() {
        return productDao.getProductCategories();
    }

    public List<ProductSupplier> getProductSuppliersByCategory(UUID categoryId) {
        return productDao.getProductSuppliersByCategory(categoryId);
    }

    public List<ProductDto> getProductsByCategoryAndSupplier(UUID categoryId, UUID supplierId) {
        return productDao.getProductsByCategoryAndSupplier(categoryId, supplierId);
    }

    public List<ProductDto> getRandomProducts(int quantity) {
        return productDao.getRandomProducts(quantity);
    }

    public List<ProductDto> getSearchedProducts(String searchedText) {
        return productDao.getSearchedProducts(searchedText);
    }

}
