package com.laczkoattilalaszlo.webshop.data;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public class DatabaseManager {

    // Constructor(s)
    public DatabaseManager() {
    }

    // Method(s)
    public DataSource connect() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();

        if (System.getenv().get("JDBC_DATABASE_URL") == null) {
            String[] dbHosts = new String[] {"localhost"};
            int[] dbPorts = new int[] {5432};
            String dbName = System.getenv().get("DB_NAME");
            String dbUserName = System.getenv().get("DB_USER");
            String dbPassword = System.getenv().get("DB_PASSWORD");

            dataSource.setServerNames(dbHosts);
            dataSource.setPortNumbers(dbPorts);
            dataSource.setDatabaseName(dbName);
            dataSource.setUser(dbUserName);
            dataSource.setPassword(dbPassword);
        } else {
            dataSource.setURL(System.getenv().get("JDBC_DATABASE_URL"));
        }

        System.out.println("Trying to connect...");
        dataSource.getConnection().close();
        System.out.println("Connection OK");

        return dataSource;
    }

}
