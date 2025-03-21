package ru.warrantyauto.repository;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

public class DBConnectionProvider {
    private final String url;
    private final String username;
    private final String password;

    public DBConnectionProvider(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    Connection getConnection() {
        Connection myConnection;
        try {
            DriverManager.registerDriver((Driver)
                    Class.forName("org.postgresql.Driver").newInstance());
            myConnection = DriverManager.getConnection(url, username, password);
            return myConnection;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
