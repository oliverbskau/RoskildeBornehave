package com.company.dataHandlers;

import com.company.DB.JDBCWriter;
import com.company.dataObjects.Employee;

import java.sql.*;
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
            insertValuesEmployee.executeUpdate();
            System.out.println("\n| Medarbejder tilføjet til database |");

        }catch(SQLException sqlError){
            System.err.println("Fejl, medarbejderen blev ikke gemt i database");
            System.err.println(sqlError.getMessage());
        }

        employees.add(new Employee(firstname, lastname, email, phonenumber));

    }

    public void deleteEmployee(){
        Scanner in = new Scanner(System.in);

        printEmployees();
        System.out.println("\nHvilken medarbejder vil du gerne slette?");
        int employee = in.nextInt()-1;

        String firstname = employees.get(employee).getFirstname();
        String lastname = employees.get(employee).getLastname();

        employees.remove(employee);

        String query = "DELETE FROM employee WHERE firstname LIKE ? AND lastname LIKE ?";

        try{
            PreparedStatement preparedStatement = JDBCWriter.getConnection().prepareStatement(query);
            preparedStatement.setString(1, firstname);
            preparedStatement.setString(2, lastname);
            preparedStatement.executeUpdate();
        } catch(SQLException e) {
            System.out.println("Fejl ved sletning af ansat i databasen");
            System.err.println(e.getMessage());
        }
    }

    public void printEmployees() {
        int count = 1;
        for (int i = 0; i < employees.size(); i++ ){
            System.out.println(count + ". " + employees.get(i).toString());
            count++;
        }
    }

    public void loadEmployeesFromDB() {
        String sql = "SELECT * FROM employee";

        try(
                Connection con = JDBCWriter.getConnection();
                Statement statement = con.createStatement();
        ) {

            ResultSet employeeRs = statement.executeQuery(sql);

            while(employeeRs.next()) {
                employees.add(new Employee(employeeRs.getString("firstname"),
                        employeeRs.getString("lastname"),
                        employeeRs.getString("email"),
                        employeeRs.getString("phonenumber")));
            }

        } catch (SQLException e) {
            System.err.println("Fejl i at hente employees fra db");
            System.err.println(e.getMessage());
            System.err.println(e.getSQLState());
            System.err.println(e.getErrorCode());
        }
    }

    public ArrayList<Employee> getEmployees(){
        return employees;
    }
}