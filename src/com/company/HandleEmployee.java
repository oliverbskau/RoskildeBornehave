package com.company;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class HandleEmployee {

    private JDBCWriter jdbcWriter = new JDBCWriter();
    private Scanner in = new Scanner(System.in);
    private ArrayList<Employee> employees = new ArrayList<>();

    public void viewEmployeeList(){
        printEmployees();
    }

    public void addEmployee() {
        System.out.println("\nFornavn: ");
        String firstname = in.nextLine();
        System.out.println("Efternavn: ");
        String lastname = in.nextLine();
        System.out.println("Email: ");
        String email = in.nextLine();
        System.out.println("Telefon: ");
        String phonenumber = in.nextLine();

        String query = "INSERT INTO employee(firstname, lastname, email, phonenumber) values(?,?,?,?);";

        try {
            PreparedStatement insertValuesEmployee = JDBCWriter.getConnection().prepareStatement(query);
            insertValuesEmployee.setString(1,firstname);
            insertValuesEmployee.setString(2,lastname);
            insertValuesEmployee.setString(3,email);
            insertValuesEmployee.setString(4, phonenumber);
            insertValuesEmployee.executeQuery();
            System.out.println("\n| Medarbejder tilføjet til database |");

        }catch(SQLException sqlError){
            System.out.println("Fejl, medarbejderen blev ikke gemt i database");
        }

        employees.add(new Employee(firstname, lastname, email, phonenumber));

    }

    public void deleteEmployee(){
        Scanner in = new Scanner(System.in);

        printEmployees();
        System.out.println("Hvilken medarbejder vil du gerne slette?");
        int employee = in.nextInt();

        employees.remove(employee);

        String firstname = employees.get(employee).getFirstname();
        String lastname = employees.get(employee).getLastname();

        String query = "DELETE FROM employees WHERE firstname LIKE ? AND lastname LIKE ?";

        try{
            PreparedStatement preparedStatement = JDBCWriter.getConnection().prepareStatement(query);
            preparedStatement.setString(1, firstname);
            preparedStatement.setString(2, lastname);
            preparedStatement.executeQuery(query);
        } catch(SQLException e) {
            System.out.println("Fejl ved sletning af ansat i databasen");
        }
    }

    public void printEmployees() {
        int count = 1;
        for (int i = 0; i < employees.size(); i++ ){
            System.out.println(count + ". " + employees.get(i).toString());
            count++;
        }

    }
    public ArrayList<Employee> getEmployees(){
        return employees;
    }
}
