package com.laczkoattilalaszlo.webshop.data.dao;

import com.laczkoattilalaszlo.webshop.model.Product;
import com.laczkoattilalaszlo.webshop.model.ProductCategory;
import com.laczkoattilalaszlo.webshop.model.ProductSupplier;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProductDaoDb implements ProductDao {

    // Field(s)
    DataSource dataSource;

    // Constructor(s)
    public ProductDaoDb(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // Method(s)
    @Override
    public List<Product> getProductsByCategory(UUID id) {
        try (Connection connection = dataSource.getConnection()) {
            // Execute SQL query
            String sql = "SELECT * FROM product WHERE category_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Put the result in a List
            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getObject("id", java.util.UUID.class));
                product.setName(resultSet.getString("name"));
                product.setDescription(resultSet.getString("description"));
                product.setPrice(resultSet.getObject("price", java.math.BigDecimal.class));
                product.setCurrency(resultSet.getString("currency"));
                product.setSupplierId(resultSet.getObject("supplier_id", java.util.UUID.class));
                product.setCategoryId(resultSet.getObject("category_id", java.util.UUID.class));
                product.setPicture(resultSet.getString("picture"));
                products.add(product);
            }

            return products;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

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

    @Override
    public List<ProductSupplier> getProductSuppliers() {
        try (Connection connection = dataSource.getConnection()) {
            // Execute SQL query
            String sql = "SELECT * FROM product_supplier";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Put the result in a List
            List<ProductSupplier> productSuppliers = new ArrayList<>();
            while (resultSet.next()) {
                ProductSupplier productSupplier = new ProductSupplier();
                productSupplier.setId(resultSet.getObject("id", java.util.UUID.class));
                productSupplier.setName(resultSet.getString(2));
                productSuppliers.add(productSupplier);
            }

            return productSuppliers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
