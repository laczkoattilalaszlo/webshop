package com.laczkoattilalaszlo.webshop.data.dao;

import com.laczkoattilalaszlo.webshop.data.dto.ProductCategorySupplierDto;
import com.laczkoattilalaszlo.webshop.model.Product;

import javax.sql.DataSource;
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

    // Implemented method(s)
    @Override
    public List<Product> getProductsByCategory(UUID id) {
        try (Connection connection = dataSource.getConnection()) {
            // Execute SQL query
            String sql = "SELECT * FROM product WHERE category_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Create Product objects from results and put them into a List
            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getObject("id", java.util.UUID.class));
                product.setName(resultSet.getString("name"));
                product.setDescription(resultSet.getString("description"));
                product.setPrice(resultSet.getBigDecimal("price"));
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
    public List<Product> getProductsBySupplier(UUID id) {
        try (Connection connection = dataSource.getConnection()) {
            // Execute SQL query
            String sql = "SELECT * FROM product WHERE supplier_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Create Product objects from results and put them into a List
            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getObject("id", java.util.UUID.class));
                product.setName(resultSet.getString("name"));
                product.setDescription(resultSet.getString("description"));
                product.setPrice(resultSet.getBigDecimal("price"));
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
    public List<ProductCategorySupplierDto> getProductCategories() {
        try (Connection connection = dataSource.getConnection()) {
            // Execute SQL query
            String sql = "SELECT * FROM product_category";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Create ProductCategory objects from results and put them into a List
            List<ProductCategorySupplierDto> productCategories = new ArrayList<>();
            while (resultSet.next()) {
                ProductCategorySupplierDto productCategorySupplierDto = new ProductCategorySupplierDto();
                productCategorySupplierDto.setId(resultSet.getObject("id", java.util.UUID.class));
                productCategorySupplierDto.setName(resultSet.getString(2));
                productCategories.add(productCategorySupplierDto);
            }

            return productCategories;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ProductCategorySupplierDto> getProductSuppliers() {
        try (Connection connection = dataSource.getConnection()) {
            // Execute SQL query
            String sql = "SELECT * FROM product_supplier";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Create ProductSupplier objects from results and put them into a List
            List<ProductCategorySupplierDto> productSuppliers = new ArrayList<>();
            while (resultSet.next()) {
                ProductCategorySupplierDto productCategorySupplierDto = new ProductCategorySupplierDto();
                productCategorySupplierDto.setId(resultSet.getObject("id", java.util.UUID.class));
                productCategorySupplierDto.setName(resultSet.getString(2));
                productSuppliers.add(productCategorySupplierDto);
            }

            return productSuppliers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
