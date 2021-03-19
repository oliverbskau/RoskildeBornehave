package com.company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class HandleEmployee {

    Connection connection = null;

    public void viewEmployeeList(JDBCWriter jdbcWriter){
        printEmployees(jdbcWriter);

    }

    public void addEmployee() {
        Scanner addEmployeeScanner = new Scanner(System.in);
        System.out.println("Fornavn: ");
        String firstname = addEmployeeScanner.nextLine();
        System.out.println("Efternavn: ");
        String lastname = addEmployeeScanner.nextLine();
        System.out.println("Email: ");
        String email = addEmployeeScanner.nextLine();
        System.out.println("Telefon: ");
        String phonenumber = addEmployeeScanner.nextLine();

        String insertInto = "INSERT INTO employee(firstname, lastname, email, phonenumber) values(?,?,?,?);";

        try {
            PreparedStatement insertValuesEmployee = connection.prepareStatement(insertInto);
            insertValuesEmployee.setString(1,firstname);
            insertValuesEmployee.setString(2,lastname);
            insertValuesEmployee.setString(3,email);
            insertValuesEmployee.setString(4, phonenumber));
            insertValuesEmployee.executeQuery();
            System.out.println("\n| Medarbejder tilføjet til database |");

        }catch(SQLException sqlError){
            System.out.println("Fejl, medarbejderen blev ikke gemt i database");
        }
    }

    public void deleteEmployee(JDBCWriter jdbcWriter){
        Scanner deleteEmployeeScanner = new Scanner(System.in);
        printEmployees(jdbcWriter);

        System.out.println("Hvilken medarbejder vil du gerne fjerne?");
        System.out.println("Skriv fornavn:");
        String firstname = deleteEmployeeScanner.next();
        System.out.println("Skriv efternavn:");
        String lastname = deleteEmployeeScanner.next();
        jdbcWriter.deleteEmployeeFromTable(firstname,lastname);

        String query = "DELETE FROM employees WHERE firstname LIKE ? AND lastname LIKE ?";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, firstname);
            preparedStatement.setString(2, lastname);
            preparedStatement.executeQuery(query);
        } catch(SQLException e) {
            System.out.println("Fejl ved sletning af ansat");
        }
    }

    public void printEmployees(JDBCWriter jdbcWriter) {
        ResultSet resultset = jdbcWriter.retrieveDataFromDB("employee");
        int counter = 1;
        try {
            while (resultset.next()) {
                System.out.println(counter + ". Fornavn: " + resultset.getString("firstname") + ", " +
                        "efternavn " + resultset.getString("lastname") + ", " +
                        "Email: " + resultset.getString("email") + ", " +
                        "Telefonnummer: " + resultset.getString("phonenumber") + ", " +
                        "OpgaveID: " + resultset.getInt("dutyid"));
                counter ++;
            }
        } catch (Exception e) {
            System.out.println("Fejl ved forsøg på at printe dataset.");
        }
    }


}
