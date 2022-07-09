package com.laczkoattilalaszlo.webshop.data.dao;

import com.laczkoattilalaszlo.webshop.data.dto.*;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderDaoDb implements OrderDao{

    // Field(s)
    DataSource dataSource;

    // Constructor(s)
    public OrderDaoDb(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // Implemented method(s)
    @Override
    public void createOrder(UUID userId) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO \"order\" (id, user_id) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, UUID.randomUUID());
            preparedStatement.setObject(2, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UUID getOrderIdOfOrderInProgress(UUID userId) {
        try (Connection connection = dataSource.getConnection()) {
            // Execute SQL query
            String sql = "SELECT id FROM \"order\" WHERE user_id=?";
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
    public OrderDto getOrder(UUID orderId) {
        try (Connection connection = dataSource.getConnection()) {
            // Execute SQL query
            String sql = "SELECT order_contact, order_shipping_address, order_billing_address, successful_transaction_code, date FROM \"order\" WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Extract result
            if (resultSet.next()) {
                OrderDto orderDto = new OrderDto();
                orderDto.setOrderContact(resultSet.getObject("order_contact", UUID.class));
                orderDto.setOrderShippingAddress(resultSet.getObject("order_shipping_address", UUID.class));
                orderDto.setOrderBillingAddress(resultSet.getObject("order_billing_address", UUID.class));
                orderDto.setSuccessfulTransactionCode(resultSet.getString("successful_transaction_code"));
                orderDto.setDate(resultSet.getObject("date", java.time.LocalDate.class));
                return orderDto;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserDto getOrderContact(UUID orderContactId) {
        try (Connection connection = dataSource.getConnection()) {
            // Execute SQL query
            String sql = "SELECT name, email, phone FROM order_contact WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, orderContactId);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Extract result
            if (resultSet.next()) {
                UserDto userDto = new UserDto();
                userDto.setName(resultSet.getString("name"));
                userDto.setEmail(resultSet.getString("email"));
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
    public AddressDto getOrderShippingAddress(UUID orderShippingAddressId) {
        try (Connection connection = dataSource.getConnection()) {
            // Execute SQL query
            String sql = "SELECT zip, country, city, address FROM order_shipping_address WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, orderShippingAddressId);
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
    public AddressDto getOrderBillingAddress(UUID orderBillingAddressId) {
        try (Connection connection = dataSource.getConnection()) {
            // Execute SQL query
            String sql = "SELECT zip, country, city, address FROM order_billing_address WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, orderBillingAddressId);
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
    public List<ProductInOrderCartDto> getOrderCart(UUID orderId) {
        try (Connection connection = dataSource.getConnection()) {
            // Execute SQL query
            String sql =    "SELECT product_id, name, unit_price, currency, quantity FROM order_cart WHERE order_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Extract result: Create a ProductInOrderCartDto from results and put them into a List
            List<ProductInOrderCartDto> orderCart = new ArrayList<>();
            while (resultSet.next()) {
                ProductInOrderCartDto productInOrderCartDto = new ProductInOrderCartDto();
                productInOrderCartDto.setProductId(resultSet.getObject("product_id", java.util.UUID.class));
                productInOrderCartDto.setName(resultSet.getString("name"));
                productInOrderCartDto.setUnitPrice(resultSet.getBigDecimal("unit_price"));
                productInOrderCartDto.setCurrency(resultSet.getString("currency"));
                productInOrderCartDto.setQuantity(resultSet.getInt("quantity"));
                orderCart.add(productInOrderCartDto);
            }

            return orderCart;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
