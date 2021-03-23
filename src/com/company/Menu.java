package com.company;

import java.util.Scanner;

public class Menu {
    Scanner in = new Scanner(System.in);
    Administration administration = new Administration();


    public void menu(){

        System.out.println("\nRoskilde Frie Børnehave\nVælg menufunktion: \n\n");

        boolean run = true;

        while (run) {
            System.out.println("\nMenu: ");
            System.out.println("1. Vagtplan\n2. Protokol\n" +
                    "3. Børn\n4. Ansatte\n5. Forældre\n\n0. Afslut");
            System.out.print("Vælg: ");

            int choice = in.nextInt();

            switch (choice) {
                case 0:
                    run = false;
                    break;
                case 1:
                    administration.administrateSchedule();
                    break;

                case 2:
                    administration.administrateProtocol();
                    break;
                case 3:
                    administration.administrateKids();
                    break;
                case 4:
                    administration.administrateEmployee();
                    break;
                case 5:
                    administration.administrateGuardian();
                    break;
                default:
                    System.out.println("Fejl, vælg et tal der svarer til menuens muligheder.");
            }
        }
    }
}
