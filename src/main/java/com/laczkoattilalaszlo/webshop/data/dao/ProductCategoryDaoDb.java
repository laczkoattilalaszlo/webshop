package com.laczkoattilalaszlo.webshop.data.dao;

import com.laczkoattilalaszlo.webshop.data.dto.ProductCategoryDto;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoDb implements ProductCategoryDao {

    // Field(s)
    DataSource dataSource;

    // Constructor(s)
    public ProductCategoryDaoDb(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // Method(s)
    @Override
    public List<ProductCategoryDto> getProductCategories() {
        try (Connection conn = dataSource.getConnection()) {
            // Execute SQL query
            String sql = "SELECT name FROM product_category";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Put the result in a List<ProductCategoryDto>
            List<ProductCategoryDto> productCategories = new ArrayList<>();
            while (resultSet.next()) {
                ProductCategoryDto productCategoryDto = new ProductCategoryDto();
                productCategoryDto.setName(resultSet.getString(1));
                productCategories.add(productCategoryDto);
            }

            return productCategories;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
