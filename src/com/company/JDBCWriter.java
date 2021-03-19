package com.company;

import java.sql.*;

public class JDBCWriter {

    private Connection connection = null;
    private final static String CONNECTIONSTRING = "jdbc:mysql://127.0.0.1:3306/roskildebornehave?ServerTimezone=UTC";
    private final static String DBUSER = "projectAccount";
    private final static String PASSWORD = "project";

    /**
     * sets the connection to the database if possible
     * @return true if connected, false otherwise
     */

    public void setConnection() {
        try {
            connection = DriverManager.getConnection(CONNECTIONSTRING,DBUSER,PASSWORD);
            System.out.println("Connection established...");

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

    public void addEmployeeToTable(Employee employee) {
        String insertInto = "INSERT INTO employee(firstname, lastname, email, phonenumber) values(?,?,?,?);";

        try {
            PreparedStatement insertValuesEmployee = connection.prepareStatement(insertInto);
            insertValuesEmployee.setString(1,employee.getFirstname());
            insertValuesEmployee.setString(2,employee.getLastname());
            insertValuesEmployee.setString(3,employee.getEmail());
            insertValuesEmployee.setString(4, employee.getPhonenumber());
            insertValuesEmployee.executeQuery();
            System.out.println("\n| Medarbejder tilføjet til database |");

        }catch(SQLException sqlError){
            System.out.println("Medarbejder tilføjet, men ikke gemt i database");
        }
    }

    public void deleteEmployeeFromTable(String firstname, String lastname) {
        String deleteQuery = "DELETE FROM employees WHERE firstname=? AND lastname=?";

        try{
            PreparedStatement deleteEmployee = connection.prepareStatement(deleteQuery);
        } catch(SQLException e) {
            System.out.println("");
        }

    }

    public void printTable(String table) {

    }

}