package com.company;

import java.util.Scanner;

public class Administration {

    private JDBCWriter jdbcWriter;
    Scanner in = new Scanner(System.in);


    /**
     * Administrates methods tied to handleemployees, see, add and delete employees
     */
    public void administrateEmployee(){
        HandleEmployee handleEmployee = new HandleEmployee();

        boolean run = true;
        System.out.println("\n1. Se liste over medarbejdere\n2. Tilføj medarbejder\n3. Slet medarbejder\n\n0. Tilbage");

        while(run) {

            int choice = in.nextInt();
            switch (choice) {
                case 0:
                    run = false;
                    break;
                case 1:
                    handleEmployee.viewEmployeeList();
                    break;
                case 2:
                    handleEmployee.addEmployee();
                    break;
                case 3:
                    handleEmployee.deleteEmployee();
                    break;
            }
        }
    }

    public void administrateKids(){
        HandleKids handleKids = new HandleKids();
        boolean run = true;
        while(run) {
            System.out.println("1. Se liste over børn\n2. Tilføj barn\n3. Slet barn\n\n0. Tilbage");
            int choice = in.nextInt();
            switch (choice) {
                case 0:
                    run = false;
                    break;
                case 1:
                    handleKids.seeAllKids();
                    break;
                case 2:
                    handleKids.addKid();
                    break;
                case 3:
                    handleKids.removeKid();
                    break;
            }
        }
    }
    public void administrateSchedule() {
        Schedule schedule = new Schedule();
        boolean run = true;
        while(run) {
            System.out.println("1. Se liste over vagter\n2. Tilføj vagt\n3. Slet vagt\n\n0. Tilbage");
            int choice = in.nextInt();
            switch (choice) {
                case 0:
                    run = false;
                    break;
                case 1:
                    schedule.seeSchedule();
                    break;
                case 2:
                    schedule.addToSchedule();
                    break;
                case 3:
                    schedule.removeFromSchedule();
                    break;
            }
        }
    }
    public void administrateProtocol(){
        HandleKids handleKids = new HandleKids();
        boolean run = true;
        while(run) {
            System.out.println("1. Se protokol\n2. Rediger protokol\n0. Tilbage");
            int choice = in.nextInt();
            switch(choice){
                case 0:
                    run = false;
                    break;
                case 1:
                    handleKids.seeProtocol();
                    break;
                case 2:
                    handleKids.setProtocol();
                    break;
            }
        }
    }
}
