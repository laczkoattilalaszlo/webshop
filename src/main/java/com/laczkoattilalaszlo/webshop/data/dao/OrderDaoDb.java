package com.laczkoattilalaszlo.webshop.data.dao;

import com.laczkoattilalaszlo.webshop.data.dto.*;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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
    public List<PaidOrderDto> getPaidOrdersByUserId(UUID userId) {
        try (Connection connection = dataSource.getConnection()) {
            // Execute SQL query
            String sql = "SELECT id, start_timestamp FROM \"order\" " +
                         "INNER JOIN \"order_payment\" ON \"order\".id = order_payment.order_id " +
                         "WHERE user_id=? AND payment_state='Succeeded'" +
                         "GROUP BY id, start_timestamp ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Extract result: Create a PaidOrderDto from results and put them into a List
            List<PaidOrderDto> paidOrders = new ArrayList<>();
            while (resultSet.next()) {
                PaidOrderDto paidOrderDto = new PaidOrderDto();
                paidOrderDto.setId(resultSet.getObject("id", java.util.UUID.class));
                paidOrderDto.setSuccessfulPaymentStartTimestamp(resultSet.getObject("start_timestamp", java.time.LocalDateTime.class));
                paidOrders.add(paidOrderDto);
            }
            return paidOrders;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ProductInOrderCartDto> getOrderCart(UUID orderId){
        try (Connection connection = dataSource.getConnection()) {
            // Execute SQL query
            String sql = "SELECT product_id, product_supplier, product_name, unit_price, currency, quantity FROM order_cart WHERE order_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Extract result: Create a ProductInOrderCartDto from results and put them into a List
            List<ProductInOrderCartDto> orderCart = new ArrayList<>();
            while (resultSet.next()) {
                ProductInOrderCartDto productInOrderCartDto = new ProductInOrderCartDto();
                productInOrderCartDto.setProductId(resultSet.getObject("product_id", java.util.UUID.class));
                productInOrderCartDto.setProductSupplier(resultSet.getString("product_supplier"));
                productInOrderCartDto.setProductName(resultSet.getString("product_name"));
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

    @Override
    public void addOrderCart(UUID orderId, List<ProductInOrderCartDto> orderCart) {
        try (Connection connection = dataSource.getConnection()) {
            for (ProductInOrderCartDto product : orderCart) {
                // Execute SQL query
                String sql = "INSERT INTO order_cart (product_id, product_supplier,product_name, unit_price, currency, quantity, order_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setObject(1, product.getProductId());
                preparedStatement.setString(2, product.getProductSupplier());
                preparedStatement.setString(3, product.getProductName());
                preparedStatement.setBigDecimal(4, product.getUnitPrice());
                preparedStatement.setString(5, product.getCurrency());
                preparedStatement.setInt(6, product.getQuantity());
                preparedStatement.setObject(7, orderId);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteOrderCart(UUID orderId) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "DELETE FROM order_cart WHERE order_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, orderId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserDto getOrderContact(UUID orderId) {
        try (Connection connection = dataSource.getConnection()) {
            // Execute SQL query
            String sql = "SELECT name, email, phone FROM order_contact WHERE order_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, orderId);
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
    public void addOrderContact(UUID orderId, UserDto orderContact) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO order_contact (name, email, phone, order_id) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, orderContact.getName());
            preparedStatement.setString(2, orderContact.getEmail());
            preparedStatement.setString(3, orderContact.getPhone());
            preparedStatement.setObject(4, orderId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteOrderContact(UUID orderId) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "DELETE FROM order_contact WHERE order_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, orderId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public AddressDto getOrderAddress(String tableName, UUID orderId) {
        try (Connection connection = dataSource.getConnection()) {
            // Execute SQL query
            String sql = "SELECT name, zip, country, city, address FROM " + tableName + " WHERE order_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Extract result
            if (resultSet.next()) {
                AddressDto addressDto = new AddressDto();
                addressDto.setName(resultSet.getString("name"));
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
    public void addOrderAddress(String tableName, UUID orderId, AddressDto orderAddress) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO " + tableName + " (name, zip, country, city, address, order_id) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, orderAddress.getName());
            preparedStatement.setString(2, orderAddress.getZip());
            preparedStatement.setString(3, orderAddress.getCountry());
            preparedStatement.setString(4, orderAddress.getCity());
            preparedStatement.setObject(5, orderAddress.getAddress());
            preparedStatement.setObject(6, orderId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteOrderAddress(String tableName, UUID orderId) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "DELETE FROM " + tableName + " WHERE order_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, orderId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<OrderPaymentDto> getOrderPayments(UUID orderId) {
        try (Connection connection = dataSource.getConnection()) {
            // Execute SQL query
            String sql = "SELECT payment_id, payment_state, start_timestamp FROM order_payment WHERE order_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Extract result
            List<OrderPaymentDto> orderPayments = new ArrayList<>();
            while (resultSet.next()) {
                OrderPaymentDto orderPaymentDto = new OrderPaymentDto();
                orderPaymentDto.setPayment_id(resultSet.getString("payment_id"));
                orderPaymentDto.setPayment_state(resultSet.getString("payment_state"));
                orderPaymentDto.setStartTimestamp(resultSet.getObject("start_timestamp", java.time.LocalDateTime.class));
                orderPayments.add(orderPaymentDto);
            }

            return orderPayments;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void mockSuccessfulPayment(UUID activeOrderId) {
        try (Connection connection = dataSource.getConnection()) {
            // Execute SQL query
            String sql = "INSERT INTO order_payment (payment_id, payment_state, start_timestamp, order_id) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, UUID.randomUUID());
            preparedStatement.setString(2, "Succeeded");
            preparedStatement.setObject(3, java.time.LocalDateTime.now());
            preparedStatement.setObject(4, activeOrderId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
