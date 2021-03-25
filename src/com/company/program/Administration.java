package com.company.program;

import com.company.DB.JDBCWriter;
import com.company.dataHandlers.HandleEmployee;
import com.company.dataHandlers.HandleGuardian;
import com.company.dataHandlers.HandleKids;
import com.company.dataHandlers.HandleSchedule;

import java.util.Scanner;


public class Administration {

    private Scanner in = new Scanner(System.in);
    private HandleEmployee handleEmployee = new HandleEmployee();
    private HandleGuardian handleGuardian = new HandleGuardian();
    private HandleKids handleKids = new HandleKids();
    private HandleSchedule handleSchedule = new HandleSchedule();
    /**
     * Administrates methods tied to handleemployees, see, add and delete employees
     */
    public void administrateEmployee(){

        boolean run = true;
        while(run) {
            System.out.println("\n1. Se liste over medarbejdere\n2. Tilføj medarbejder\n3. Slet medarbejder\n\n0. Tilbage");
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
        boolean run = true;
        while(run) {
            System.out.println("\n1. Se liste over børn\n2. Se liste over børn på venteliste\n3. før barn over fra venteliste" +
                    "\n4. Tilføj barn\n5. Slet barn\n\n0. Tilbage");
            int choice = in.nextInt();
            switch (choice) {
                case 0:
                    run = false;
                    break;
                case 1:
                    handleKids.seeAllKids();
                    break;
                case 3:
                    handleKids.transferKidFromWaitingList();
                    break;
                case 2:
                    handleKids.seeAllKidsOnWaitingList();
                    break;
                case 4:
                    handleKids.addKid(handleGuardian);
                    break;
                case 5:
                    handleKids.removeKid();
                    break;
            }
        }
    }
    public void administrateSchedule() {
        boolean run = true;
        while(run) {
            System.out.println("\n1. Se liste over vagter\n2. Tilføj vagt\n3. Slet vagt\n\n0. Tilbage");
            int choice = in.nextInt();
            switch (choice) {
                case 0:
                    run = false;
                    break;
                case 1:
                    handleSchedule.seeSchedule();
                    break;
                case 2:
                    handleSchedule.addToSchedule(handleEmployee);
                    break;
                case 3:
                    handleSchedule.removeFromSchedule();
                    break;
            }
        }
    }
    public void administrateProtocol(){
        boolean run = true;
        while(run) {
            System.out.println("\n1. Se protokol\n2. Rediger protokol\n\n0. Tilbage");
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
    public void administrateGuardian(){
        handleGuardian.guardianList();

        boolean run = true;
        while(run) {
            System.out.println("\n1. Se forældre\n2. tilføj forældre\n3. Opdater forældre\n\n0. Tilbage");
            int choice = in.nextInt();
            switch(choice){
                case 0:
                    run = false;
                    break;
                case 1:
                    handleGuardian.guardianList();
                    break;
                case 2:
                    handleGuardian.addGuardian();
                    break;
                case 3:
                    handleGuardian.updateGuardian(handleKids);
                    break;
            }
        }
    }
    public void preload(){
        handleGuardian.loadGuardiansFromDB();
        handleEmployee.loadEmployeesFromDB();
        handleKids.loadKidsFromDB();
        handleSchedule.loadScheduleFromDB();
    }
}

