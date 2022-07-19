package com.laczkoattilalaszlo.webshop.data.dao;

import com.laczkoattilalaszlo.webshop.data.dto.ProductInCartDto;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.util.*;

public class CartDaoDb implements CartDao {

    // Field(s)
    DataSource dataSource;

    // Constructor(s)
    public CartDaoDb(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // Implemented method(s)
    @Override
    public List<ProductInCartDto> getCart(UUID userId) {
        try (Connection connection = dataSource.getConnection()) {
            // Execute SQL query
            String sql =    "SELECT cart.product_id, " +
                                "product.picture, " +
                                "product_supplier.name AS supplier_name, " +
                                "product.name, " +
                                "product.price, " +
                                "product.currency, " +
                                "cart.quantity " +
                            "FROM cart " +
                            "INNER JOIN product " +
                            "ON cart.product_id = product.id " +
                            "INNER JOIN product_supplier " +
                            "ON product.supplier_id = product_supplier.id " +
                            "WHERE user_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Extract result: Create a ProductInCartDto from results and put them into a List
            List<ProductInCartDto> cart = new ArrayList<>();
            while (resultSet.next()) {
                ProductInCartDto productInCartDto = new ProductInCartDto();
                productInCartDto.setProductId(resultSet.getObject("product_id", java.util.UUID.class));
                productInCartDto.setPicture(resultSet.getString("picture"));
                productInCartDto.setSupplierName(resultSet.getString("supplier_name"));
                productInCartDto.setName(resultSet.getString("name"));
                productInCartDto.setPrice(resultSet.getBigDecimal("price"));
                productInCartDto.setCurrency(resultSet.getString("currency"));
                productInCartDto.setQuantity(resultSet.getInt("quantity"));
                cart.add(productInCartDto);
            }

            return cart;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public BigDecimal getTotalPrice(UUID userId) {
        try (Connection connection = dataSource.getConnection()) {
            // Execute SQL query
            String sql =    "SELECT SUM(cart.quantity * product.price) AS total_price " +
                            "FROM cart " +
                            "INNER JOIN product " +
                            "ON cart.product_id = product.id " +
                            "WHERE user_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Extract result
            BigDecimal totalPrice = resultSet.next() ? resultSet.getBigDecimal("total_price") : BigDecimal.valueOf(0);

            return totalPrice;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer getQuantityOfGivenProductInCart(UUID productId, UUID userId) {
        try (Connection connection = dataSource.getConnection()) {
            // Execute SQL query
            String sql = "SELECT quantity FROM cart WHERE product_id=? AND user_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, productId);
            preparedStatement.setObject(2, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Extract result
            Integer quantity = (resultSet.next()) ? resultSet.getInt("quantity") : null;

            return quantity;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addProductToCart(UUID productId, UUID userId) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO cart (product_id, quantity, user_id) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, productId);
            preparedStatement.setInt(2, 1);
            preparedStatement.setObject(3, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeProductFromCart(UUID productId, UUID userId) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "DELETE FROM cart WHERE product_id=? AND user_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, productId);
            preparedStatement.setObject(2, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateProductQuantityInCart(Integer quantity, UUID productId, UUID userId) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "UPDATE cart SET quantity = ? WHERE product_id=? AND user_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, quantity);
            preparedStatement.setObject(2, productId);
            preparedStatement.setObject(3, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
