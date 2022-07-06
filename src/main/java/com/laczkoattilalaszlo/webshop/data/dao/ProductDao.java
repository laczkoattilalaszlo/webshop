package com.laczkoattilalaszlo.webshop.data.dao;

import com.laczkoattilalaszlo.webshop.model.Product;

import java.util.List;
import java.util.UUID;

public interface ProductDao {

    List<Product> getProductsByCategory(UUID id);

}
