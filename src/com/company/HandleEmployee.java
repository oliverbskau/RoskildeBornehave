package com.company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class HandleEmployee {

    JDBCWriter jdbcWriter = new JDBCWriter();
    Connection connection = null;
    Scanner in = new Scanner(System.in);

    public void viewEmployeeList(){
        jdbcWriter.retrieveDataFromDB("employee");
    }

    public void addEmployee() {
        System.out.println("Fornavn: ");
        String firstname = in.nextLine();
        System.out.println("Efternavn: ");
        String lastname = in.nextLine();
        System.out.println("Email: ");
        String email = in.nextLine();
        System.out.println("Telefon: ");
        String phonenumber = in.nextLine();

        String insertInto = "INSERT INTO employee(firstname, lastname, email, phonenumber) values(?,?,?,?);";

        try {
            PreparedStatement insertValuesEmployee = connection.prepareStatement(insertInto);
            insertValuesEmployee.setString(1,firstname);
            insertValuesEmployee.setString(2,lastname);
            insertValuesEmployee.setString(3,email);
            insertValuesEmployee.setString(4, phonenumber);
            insertValuesEmployee.executeQuery();
            System.out.println("\n| Medarbejder tilføjet til database |");

        }catch(SQLException sqlError){
            System.out.println("Fejl, medarbejderen blev ikke gemt i database");
        }
    }

    public void deleteEmployee(){
        Scanner in = new Scanner(System.in);
        jdbcWriter.retrieveDataFromDB("employee");

        System.out.println("Hvilken medarbejder vil du gerne fjerne?");
        System.out.println("Skriv fornavn:");
        String firstname = in.nextLine();
        System.out.println("Skriv efternavn:");
        String lastname = in.nextLine();

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
                        " | Efternavn " + resultset.getString("lastname") +
                        " | Email: " + resultset.getString("email") +
                        " | Telefonnummer: " + resultset.getString("phonenumber") +
                        " | OpgaveID: " + resultset.getInt("dutyid"));
                counter ++;
            }
        } catch (Exception e) {
            System.out.println("Fejl ved forsøg på at printe dataset.");
        }
    }


}
