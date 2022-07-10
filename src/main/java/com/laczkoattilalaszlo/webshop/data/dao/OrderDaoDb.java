package com.laczkoattilalaszlo.webshop.data.dao;

import javax.sql.DataSource;
import java.sql.*;
import java.util.UUID;

public class OrderDaoDb implements OrderDao {

    // Field(s)
    DataSource dataSource;

    // Constructor(s)
    public OrderDaoDb(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // Implemented method(s)
    @Override
    public UUID getActiveOrderId(UUID userId) {
        try (Connection connection = dataSource.getConnection()) {
            // Execute SQL query
            String sql = "SELECT id FROM \"order\" " +
                         "LEFT OUTER JOIN \"order_payment\" ON \"order\".id = order_payment.order_id " +
                         "WHERE user_id=? " +
                         "GROUP BY id " +
                         "HAVING COUNT(payment_state) FILTER ( WHERE payment_state='Succeeded' ) = 0";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Extract result
            UUID orderId = (resultSet.next()) ? resultSet.getObject("id", java.util.UUID.class) : null;
            return orderId;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createActiveOrder(UUID userId) {
        try (Connection connection = dataSource.getConnection()) {
            // Execute SQL query
            String sql = "INSERT INTO \"order\" (id, user_id) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, UUID.randomUUID());
            preparedStatement.setObject(2, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
