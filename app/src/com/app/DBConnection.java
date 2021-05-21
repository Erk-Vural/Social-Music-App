package com.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static Connection getConnection() throws SQLException {

        String dbURL = "jdbc:mysql://localhost:3306/music_app";
        String dbUserName = "root";
        String dbPassword = "root.117";

        return DriverManager.getConnection(dbURL, dbUserName, dbPassword);
    }


}