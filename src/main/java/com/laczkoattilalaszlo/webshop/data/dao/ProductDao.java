package com.laczkoattilalaszlo.webshop.data.dao;

import com.laczkoattilalaszlo.webshop.data.dto.ProductDto;
import com.laczkoattilalaszlo.webshop.model.ProductCategory;
import com.laczkoattilalaszlo.webshop.model.ProductSupplier;

import java.util.List;
import java.util.UUID;

public interface ProductDao {

    List<ProductCategory> getProductCategories();

    List<ProductSupplier> getProductSuppliersByCategory(UUID categoryId);

    List<ProductDto> getProductsByCategoryAndSupplier(UUID categoryId, UUID supplierId);

    List<ProductDto> getRandomProducts(int quantity);

    List<ProductDto> getSearchedProducts(String searchedText);

}
