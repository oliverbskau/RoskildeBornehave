package com.company;

import java.sql.ResultSet;
import java.util.*;

public class HandleEmployee {

    public void viewEmployeeList(JDBCWriter jdbcWriter){
        printEmployees(jdbcWriter);

    }

    public void addEmployee(JDBCWriter jdbcWriter){
        Scanner addEmployeeScanner = new Scanner(System.in);
        System.out.println("Fornavn: ");
        String firstname = addEmployeeScanner.nextLine();
        System.out.println("Efternavn: ");
        String lastname = addEmployeeScanner.nextLine();
        System.out.println("Email: ");
        String email = addEmployeeScanner.nextLine();
        System.out.println("Telefon: ");
        String phonenumber = addEmployeeScanner.nextLine();
        Employee employee = new Employee(firstname, lastname, email, phonenumber);

        jdbcWriter.addEmployeeToTable(employee);
    }

    public void deleteEmployee(JDBCWriter jdbcWriter){
        System.out.println("Hvilken medarbejder vil du gerne fjerne?");

        /*
        System.out.println("Skriv tallet foran navnet på den person der skal fjernes: ");
        Scanner deleteEmployeeScanner = new Scanner(System.in);
        int count = 1;
        for (int i = 0; i < employees.size(); i++){
            System.out.println(count + ". " + employees.get(i).toString());
            count++;
        }
        int deleteChoice = deleteEmployeeScanner.nextInt()-1;

        employees.remove(deleteChoice);

         */


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
