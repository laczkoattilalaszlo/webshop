package com.laczkoattilalaszlo.webshop.data.dao;

import com.laczkoattilalaszlo.webshop.data.dto.UserDto;
import com.laczkoattilalaszlo.webshop.model.User;

import javax.sql.DataSource;
import java.sql.*;
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

    @Override
    public UserDto getUser(UUID userId) {
        try (Connection connection = dataSource.getConnection()) {
            // Execute SQL query
            String sql = "SELECT email, name, phone FROM \"user\" WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                UserDto userDto = new UserDto();
                userDto.setEmail(resultSet.getString("email"));
                userDto.setName(resultSet.getString("name"));
                userDto.setPhone(resultSet.getString("phone"));
                return userDto;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateUser(UserDto userDto, UUID userId) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "UPDATE \"user\" SET email=?, name=?, phone=? WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, userDto.getEmail());
            preparedStatement.setString(2, userDto.getName());
            preparedStatement.setString(3, userDto.getPhone());
            preparedStatement.setObject(4, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UUID getUserIdByEmailAndPassword(String email, String hashedPassword) {
        try (Connection connection = dataSource.getConnection()) {
            // Execute SQL query
            String sql = "SELECT id FROM \"user\" WHERE email=? AND password=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, hashedPassword);
            ResultSet resultSet = preparedStatement.executeQuery();

            UUID userId = (resultSet.next()) ? resultSet.getObject("id", java.util.UUID.class) : null;

            return userId;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addSessionTokenToUser(String sessionToken, UUID userId) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "UPDATE \"user\" SET session_token=? WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, sessionToken);
            preparedStatement.setObject(2, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UUID getUserIdBySessionToken(String sessionToken) {
        try (Connection connection = dataSource.getConnection()) {
            // Execute SQL query
            String sql = "SELECT id FROM \"user\" WHERE session_token=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, sessionToken);
            ResultSet resultSet = preparedStatement.executeQuery();

            UUID userId = (resultSet.next()) ? resultSet.getObject("id", java.util.UUID.class) : null;

            return userId;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
