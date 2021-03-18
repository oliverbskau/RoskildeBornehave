package com.company;

import java.sql.Connection;
import java.util.Scanner;

public class Administration {

    private JDBCWriter jdbcWriter;

    public Administration() {
        jdbcWriter = new JDBCWriter();
    }

    /**
     * Administrates methods tied to handleemployees, see, add and delete employees
     */
    public void administrateEmployee(){
        Scanner scanner = new Scanner(System.in);
        HandleEmployee handleEmployee = new HandleEmployee();

        System.out.println("1. Se liste over medarbejdere");
        System.out.println("2. Tilføj medarbejder");
        System.out.println("3. Slet medarbejder");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                handleEmployee.seeEmployees(jdbcWriter);
            case 2:
                handleEmployee.addEmployee(jdbcWriter);
            case 3:
                handleEmployee.deleteEmployee(jdbcWriter);
        }
    }

    public void administrateKids(){

    }
}
