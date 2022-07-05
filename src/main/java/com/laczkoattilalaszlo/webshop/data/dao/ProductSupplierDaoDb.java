package com.laczkoattilalaszlo.webshop.data.dao;

import com.laczkoattilalaszlo.webshop.data.dto.ProductSupplierDto;

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
    public List<ProductSupplierDto> getProductSuppliers() {
        try (Connection connection = dataSource.getConnection()) {
            // Execute SQL query
            String sql = "SELECT name FROM product_supplier";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Put the result in a List<ProductCategoryDto>
            List<ProductSupplierDto> productSuppliers = new ArrayList<>();
            while (resultSet.next()) {
                ProductSupplierDto productSupplierDto = new ProductSupplierDto();
                productSupplierDto.setName(resultSet.getString(1));
                productSuppliers.add(productSupplierDto);
            }

            return productSuppliers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
