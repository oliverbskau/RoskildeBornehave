package com.company;
import com.mysql.cj.protocol.Resultset;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class HandleEmployee {

    public ArrayList<Employee> employees = new ArrayList<>();

    JDBCWriter jdbcWriter = new JDBCWriter();


    public void seeEmployees(){

        System.out.println("Alle medarbejdere ");
        int count = 1;
        Collections.sort(employees, Comparator.comparing(Employee::getLastname));
        for(int i = 0; i < employees.size(); i++){
            System.out.println(count + ". " + employees.get(i).toString());
            count++;
        }

        ResultSet resultset = jdbcWriter.retrieveDataFromDB("employee");
        try {
            while (resultset.next()) {
                System.out.println(resultset.getString("firstname") +
                        resultset.getString("lastname") +
                        resultset.getString("email") +
                        resultset.getString("phonenumber") +
                        resultset.getInt("dutyid"));
            }
        } catch (Exception e) {
            System.out.println("Fejl ved forsøg på at printe dataset.");
        }

    }

    public void addEmployee(){
        Scanner addEmployeeScanner = new Scanner(System.in);
        System.out.println("Fornavn: ");
        String firstname = addEmployeeScanner.nextLine();
        System.out.println("Efternavn: ");
        String lastname = addEmployeeScanner.nextLine();
        System.out.println("Email: ");
        String email = addEmployeeScanner.nextLine();
        System.out.println("Telefon: ");
        String phonenumber = addEmployeeScanner.nextLine();

        employees.add(new Employee(firstname, lastname, email, phonenumber));

        jdbcWriter.setConnection();

        String insertInto = "INSERT INTO employee(firstname, lastname, email, phonenumber) values(?,?,?,?);";

        try {
            PreparedStatement insertValuesEmployee = jdbcWriter.connection.prepareStatement(insertInto);
            insertValuesEmployee.setString(1,firstname);
            insertValuesEmployee.setString(2,lastname);
            insertValuesEmployee.setString(3,email);
            insertValuesEmployee.setString(4,phonenumber);
            insertValuesEmployee.executeQuery();
            System.out.println("\n| Medarbejder tilføjet til database |");

        }catch(SQLException sqlError){
            System.out.println("Medarbejder tilføjet, men ikke gemt i database");
        }
    }

    public void deleteEmployee(){
        System.out.println("Skriv tallet foran navnet på den person der skal fjernes: ");
        Scanner deleteEmployeeScanner = new Scanner(System.in);
        int count = 1;
        for (int i = 0; i < employees.size(); i++){
            System.out.println(count + ". " + employees.get(i).toString());
            count++;
        }
        int deleteChoice = deleteEmployeeScanner.nextInt()-1;

        employees.remove(deleteChoice);
    }


}
