package ru.warrantyauto.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Objects;
import java.util.Scanner;

public class DBConnectionProvider {

    private final String url;
    private final String username;
    private final String password;

    public DBConnectionProvider(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }
    public DBConnectionProvider() {
        this.url = getStringInFileToIndex(0);
        this.username = getStringInFileToIndex(1);
        this.password = getStringInFileToIndex(2);
    }

    public String getStringInFileToIndex(int Index) {
        URI uri;
        String fileScan;
        try {
            uri = getClass().getResource(String.format("/%s", "bdconnect.txt")).toURI();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        try {
            fileScan = Files.readString(Paths.get(uri));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String[] resArray = fileScan.split(" ");
        return resArray[Index];
    }
    public Connection getConnection() {
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
