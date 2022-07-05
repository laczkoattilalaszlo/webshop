package com.laczkoattilalaszlo.webshop.data.dao;

import com.laczkoattilalaszlo.webshop.model.ProductCategory;

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
    public List<ProductCategory> getProductCategories() {
        try (Connection connection = dataSource.getConnection()) {
            // Execute SQL query
            String sql = "SELECT * FROM product_category";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Put the result in a List
            List<ProductCategory> productCategories = new ArrayList<>();
            while (resultSet.next()) {
                ProductCategory productCategory = new ProductCategory();
                productCategory.setId(resultSet.getObject("id", java.util.UUID.class));
                productCategory.setName(resultSet.getString(2));
                productCategories.add(productCategory);
            }

            return productCategories;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
