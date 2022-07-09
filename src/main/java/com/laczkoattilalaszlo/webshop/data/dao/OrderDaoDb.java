package com.laczkoattilalaszlo.webshop.data.dao;

import javax.sql.DataSource;

public class OrderDaoDb implements OrderDao {

    // Field(s)
    DataSource dataSource;

    // Constructor(s)
    public OrderDaoDb(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // Implemented method(s)

}
