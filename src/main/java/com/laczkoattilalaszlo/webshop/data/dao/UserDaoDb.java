package com.laczkoattilalaszlo.webshop.data.dao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

public class UserDaoDb implements UserDao {

    // Field(s)
    DataSource dataSource;

    // Constructor(s)
    public UserDaoDb(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void addUser(String email, String password) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO \"user\" (id, email, password) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, UUID.randomUUID());
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeUser(UUID userId) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "DELETE FROM \"user\" WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
