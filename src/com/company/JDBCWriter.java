package com.company;

import java.sql.*;

public class JDBCWriter {

    private Connection connection = null;
    private final static String CONNECTIONSTRING = "jdbc:mysql://127.0.0.1:3306/roskildebornehave?ServerTimezone=UTC";
    private final static String DBUSER = "ProjectAccount";
    private final static String PASSWORD = "project";
    private boolean isConnected;

    /**
     * sets the connection to the database if possible
     * @return true if connected, false otherwise
     */

    public boolean setConnection() {
        isConnected = false;
        try {
            connection = DriverManager.getConnection(CONNECTIONSTRING,DBUSER,PASSWORD);
            isConnected = true;
            System.out.println("Connection established...");

        } catch (SQLException e) {
            System.err.println("Error message: " + e.getMessage());
            System.err.println("Error code: " + e.getErrorCode());
            System.err.println("SQL state: " + e.getSQLState());
        }
        return isConnected;
    }

    /**
     *
     * @param tableName the table we retrieve data from
     * @return ResultSet with all data from the table
     */

    public ResultSet retrieveDataFromDB(String tableName) {
        ResultSet resultSet = null;
        String query = "SELECT * FROM " + tableName;

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
