package com.laczkoattilalaszlo.webshop.data.dao;

import com.laczkoattilalaszlo.webshop.data.dto.BankCardDto;
import com.laczkoattilalaszlo.webshop.data.dto.UserDto;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
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
                bankCardDto.setExpirationDate(resultSet.getObject("expiration_date", java.time.LocalDate.class));
                bankCardDto.setSecurityCode(resultSet.getString("security_code"));
                return bankCardDto;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateBankCard(BankCardDto bankCardDto, UUID userId) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "UPDATE bank_card SET name=?, card_number=?, expiration_date=?, security_code=? WHERE user_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, bankCardDto.getName());
            preparedStatement.setString(2, bankCardDto.getCardNumber());
            preparedStatement.setDate(3, java.sql.Date.valueOf(bankCardDto.getExpirationDate()));
            preparedStatement.setString(4, bankCardDto.getSecurityCode());
            preparedStatement.setObject(5, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
