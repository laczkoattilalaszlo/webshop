package com.laczkoattilalaszlo.webshop.data.dao;

import com.laczkoattilalaszlo.webshop.data.dto.BankCardDto;
import com.laczkoattilalaszlo.webshop.data.dto.UserDto;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class BankCardDaoDb implements BankCardDao {

    // Field(s)
    DataSource dataSource;

    // Constructor(s)
    public BankCardDaoDb(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // Implemented method(s)
    @Override
    public BankCardDto getBankCard(UUID userId) {
        try (Connection connection = dataSource.getConnection()) {
            // Execute SQL query
            String sql = "SELECT name, card_number, expiration_date, security_code FROM bank_card WHERE user_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                BankCardDto bankCardDto = new BankCardDto();
                bankCardDto.setName(resultSet.getString("name"));
                bankCardDto.setCardNumber(resultSet.getString("card_number"));
                bankCardDto.setExpirationDate(resultSet.getDate("expiration_date"));
                bankCardDto.setSecurityCode(resultSet.getString("security_code"));
                return bankCardDto;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
