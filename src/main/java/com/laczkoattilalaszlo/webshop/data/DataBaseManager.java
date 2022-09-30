package com.laczkoattilalaszlo.webshop.data;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public class DataBaseManager {

    // Constructor(s)
    public DataBaseManager() {
    }

    // Method(s)
    public DataSource connect() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();

//        String dbName = System.getenv().get("DB_NAME");
//        String dbUserName = System.getenv().get("DB_USER");
//        String dbPassword = System.getenv().get("DB_PASSWORD");
//
//        dataSource.setDatabaseName(dbName);
//        dataSource.setUser(dbUserName);
//        dataSource.setPassword(dbPassword);
        dataSource.setURL(System.getenv().get("JDBC_DATABASE_URL"));

        System.out.println("Trying to connect...");
        dataSource.getConnection().close();
        System.out.println("Connection OK");

        return dataSource;
    }

}
