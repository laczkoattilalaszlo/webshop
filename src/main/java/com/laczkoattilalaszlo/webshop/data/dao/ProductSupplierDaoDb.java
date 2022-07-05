package com.laczkoattilalaszlo.webshop.data.dao;

import com.laczkoattilalaszlo.webshop.model.ProductSupplier;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductSupplierDaoDb implements ProductSupplierDao {

    // Field(s)
    DataSource dataSource;

    // Constructor(s)
    public ProductSupplierDaoDb(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // Method(s)
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
