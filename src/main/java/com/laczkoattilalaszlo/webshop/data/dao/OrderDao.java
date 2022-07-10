package com.laczkoattilalaszlo.webshop.data.dao;

import java.util.UUID;

public interface OrderDao {

    UUID getActiveOrderId(UUID userId);

    void createActiveOrder(UUID userId);

}
