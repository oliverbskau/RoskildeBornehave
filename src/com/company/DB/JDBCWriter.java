package com.company.DB;

import java.sql.*;

public class JDBCWriter {

    private static Connection connection = null;
    private final static String CONNECTIONSTRING = "jdbc:mysql://127.0.0.1:3306/roskildebornehave?serverTimezone=UTC";
    private final static String DBUSER = "ProjectAccount";
    private final static String PASSWORD = "project";

    /**
     * sets the connection to the database if possible
     * @return true if connected, false otherwise
     */

    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(CONNECTIONSTRING,DBUSER,PASSWORD);

        } catch (SQLException e) {
            System.err.println("Error message: " + e.getMessage());
            System.err.println("Error code: " + e.getErrorCode());
            System.err.println("SQL state: " + e.getSQLState());
        }

        return connection;
    }

}