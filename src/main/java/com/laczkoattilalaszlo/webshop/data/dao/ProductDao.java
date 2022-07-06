package com.laczkoattilalaszlo.webshop.data.dao;

import com.laczkoattilalaszlo.webshop.model.Product;
import com.laczkoattilalaszlo.webshop.model.ProductCategory;
import com.laczkoattilalaszlo.webshop.model.ProductSupplier;

import java.util.List;
import java.util.UUID;

public interface ProductDao {

    List<Product> getProductsByCategory(UUID id);

    List<ProductCategory> getProductCategories();

    List<ProductSupplier> getProductSuppliers();

}
