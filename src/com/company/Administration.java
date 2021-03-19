package com.company;

import java.util.Scanner;

public class Administration {

    private JDBCWriter jdbcWriter;

    public Administration() {
        jdbcWriter = new JDBCWriter();
        jdbcWriter.setConnection();
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
                handleEmployee.viewEmployeeList();
            case 2:
                handleEmployee.addEmployee();
            case 3:
                handleEmployee.deleteEmployee();
        }
    }

    public void administrateKids(){

    }

    public void administrateSchedule() {

    }
}
