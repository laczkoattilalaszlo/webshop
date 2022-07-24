package com.laczkoattilalaszlo.webshop.data.dao;

import com.laczkoattilalaszlo.webshop.data.dto.ProductDto;
import com.laczkoattilalaszlo.webshop.model.ProductCategory;
import com.laczkoattilalaszlo.webshop.model.ProductSupplier;

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
    public List<ProductCategory> getProductCategories() {
        try (Connection connection = dataSource.getConnection()) {
            // Execute SQL query
            String sql = "SELECT id, name FROM product_category";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Extract result: Create ProductCategory objects from results and put them into a List
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
    public List<ProductSupplier> getProductSuppliersByCategory(UUID categoryId) {
        try (Connection connection = dataSource.getConnection()) {
            // Execute SQL query
            String sql = "SELECT DISTINCT product_supplier.id, product_supplier.name " +
                         "FROM product_supplier " +
                         "INNER JOIN product ON product_supplier.id = product.supplier_id " +
                         "WHERE product.category_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, categoryId);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Extract result: Create ProductSupplier objects from results and put them into a List
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

    @Override
    public List<ProductDto> getProductsByCategoryAndSupplier(UUID categoryId, UUID supplierId) {
        try (Connection connection = dataSource.getConnection()) {
            // Execute SQL query
            String sql = "SELECT product.id AS id, " +
                                "product.name AS name, " +
                                "product.description AS description, " +
                                "product.price AS price, " +
                                "product.currency AS currency, " +
                                "product_supplier.name AS supplier_name, " +
                                "product_category.name AS category_name, " +
                                "product.picture AS picture " +
                         "FROM product " +
                         "INNER JOIN product_category ON product.category_id = product_category.id " +
                         "INNER JOIN product_supplier ON product.supplier_id = product_supplier.id " +
                         "WHERE product.category_id=? AND product.supplier_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, categoryId);
            preparedStatement.setObject(2, supplierId);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Create ProductDto objects from results and put them into a List
            List<ProductDto> products = new ArrayList<>();
            while (resultSet.next()) {
                ProductDto productDto = new ProductDto();
                productDto.setId(resultSet.getObject("id", java.util.UUID.class));
                productDto.setName(resultSet.getString("name"));
                productDto.setDescription(resultSet.getString("description"));
                productDto.setPrice(resultSet.getBigDecimal("price"));
                productDto.setCurrency(resultSet.getString("currency"));
                productDto.setSupplierName(resultSet.getString("supplier_name"));
                productDto.setCategoryName(resultSet.getString("category_name"));
                productDto.setPicture(resultSet.getString("picture"));
                products.add(productDto);
            }

            return products;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ProductDto> getRandomProducts(int quantity) {
        try (Connection connection = dataSource.getConnection()) {
            // Execute SQL query
            String sql = "SELECT product.id AS id, " +
                         "product.name AS name, " +
                         "product.description AS description, " +
                         "product.price AS price, " +
                         "product.currency AS currency, " +
                         "product_supplier.name AS supplier_name, " +
                         "product_category.name AS category_name, " +
                         "product.picture AS picture " +
                         "FROM product " +
                         "INNER JOIN product_category ON product.category_id = product_category.id " +
                         "INNER JOIN product_supplier ON product.supplier_id = product_supplier.id " +
                         "ORDER BY RANDOM() " +
                         "LIMIT ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, quantity);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Create ProductDto objects from results and put them into a List
            List<ProductDto> products = new ArrayList<>();
            while (resultSet.next()) {
                ProductDto productDto = new ProductDto();
                productDto.setId(resultSet.getObject("id", java.util.UUID.class));
                productDto.setName(resultSet.getString("name"));
                productDto.setDescription(resultSet.getString("description"));
                productDto.setPrice(resultSet.getBigDecimal("price"));
                productDto.setCurrency(resultSet.getString("currency"));
                productDto.setSupplierName(resultSet.getString("supplier_name"));
                productDto.setCategoryName(resultSet.getString("category_name"));
                productDto.setPicture(resultSet.getString("picture"));
                products.add(productDto);
            }

            return products;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
