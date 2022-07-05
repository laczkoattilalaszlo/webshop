package com.laczkoattilalaszlo.webshop.data.dao;

import com.laczkoattilalaszlo.webshop.data.dto.ProductCategoryDto;

import java.util.List;

public interface ProductCategoryDao {

    List<ProductCategoryDto> getProductCategories();

}
