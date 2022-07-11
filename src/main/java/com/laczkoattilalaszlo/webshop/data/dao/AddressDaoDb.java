package com.laczkoattilalaszlo.webshop.data.dao;

import com.laczkoattilalaszlo.webshop.data.dto.AddressDto;

import javax.sql.DataSource;
import java.sql.*;
import java.util.UUID;

public class AddressDaoDb implements AddressDao {

    // Field(s)
    DataSource dataSource;

    // Constructor(s)
    public AddressDaoDb(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // Implemented method(s)
    @Override
    public AddressDto getAddress(String tableName, UUID userId) {
        try (Connection connection = dataSource.getConnection()) {
            // Execute SQL query
            String sql = "SELECT zip, country, city, address FROM " + tableName + " WHERE user_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Extract result
            if (resultSet.next()) {
                AddressDto addressDto = new AddressDto();
                addressDto.setZip(resultSet.getString("zip"));
                addressDto.setCountry(resultSet.getString("country"));
                addressDto.setCity(resultSet.getString("city"));
                addressDto.setAddress(resultSet.getString("address"));
                return addressDto;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateAddress(String tableName, UUID userId, AddressDto addressDto) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "UPDATE " + tableName + " SET zip=?, country=?, city=?, address=? WHERE user_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, addressDto.getZip());
            preparedStatement.setString(2, addressDto.getCountry());
            preparedStatement.setString(3, addressDto.getCity());
            preparedStatement.setString(4, addressDto.getAddress());
            preparedStatement.setObject(5, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
