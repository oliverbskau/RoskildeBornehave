package com.company;

import java.sql.*;

public class JDBCWriter {

    private Connection connection = null;
    private final static String CONNECTIONSTRING = "jdbc:mysql://127.0.0.1:3306/roskildebornehave?serverTimezone=UTC";
    private final static String DBUSER = "ProjectAccount";
    private final static String PASSWORD = "project";

    /**
     * sets the connection to the database if possible
     * @return true if connected, false otherwise
     */

    public void setConnection() {
        try {
            connection = DriverManager.getConnection(CONNECTIONSTRING,DBUSER,PASSWORD);

        } catch (SQLException e) {
            System.err.println("Error message: " + e.getMessage());
            System.err.println("Error code: " + e.getErrorCode());
            System.err.println("SQL state: " + e.getSQLState());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    /**
     * @param tableName the table we retrieve data from
     * @return ResultSet with all data from the table
     */

    public ResultSet retrieveDataFromDB(String tableName) {
        ResultSet resultSet = null;
        String query = "SELECT * FROM " + tableName + " ORDER BY lastname DESC;";

        try{
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

        } catch (SQLException e) {
            System.err.println("Error message: " + e.getMessage());
            System.err.println("Error code: " + e.getErrorCode());
            System.err.println("SQL state: " + e.getSQLState());
        }

        return resultSet;
    }

}