package com.laczkoattilalaszlo.webshop.data.dao;

import javax.sql.DataSource;
import java.sql.*;
import java.util.UUID;

public class CartDaoDb implements CartDao {

    // Field(s)
    DataSource dataSource;

    // Constructor(s)
    public CartDaoDb(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // Implemented method(s)
    @Override
    public Integer getQuantityOfGivenProductInCart(UUID productId, UUID userId) {
        try (Connection connection = dataSource.getConnection()) {
            // Execute SQL query
            String sql = "SELECT quantity FROM cart WHERE product_id=? AND user_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, productId);
            preparedStatement.setObject(2, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

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
