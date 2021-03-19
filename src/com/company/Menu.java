package com.company;

import java.util.Scanner;

public class Menu {
    Scanner in = new Scanner(System.in);
    Administration administration = new Administration();
    ListAdministration listAdministration = new ListAdministration();

    public void menu(){
        System.out.println("\nRoskilde Frie Børnehave\nVælg menufunktion: \n\n");

        boolean run = true;

        while (run) {
            System.out.println("1. Vagtplan\n2. Protokol\n3. Venteliste\n" +
                    "4. Børn\n5. Ansatte\n6. Telefonliste\n7. Forældreliste\n\n0. Afslut");

            int choice = in.nextInt();

            switch (choice) {
                case 0:
                    run = false;
                    break;
                case 1:
                    administration.administrateSchedule();
                    break;

                case 2:

                    break;

                case 3:

                    break;

                case 4:
                    administration.administrateKids();
                    break;

                case 5:
                    administration.administrateEmployee();
                    break;

                case 6:

                    break;

                case 7:

                    listAdministration.guardianList();

                    break;

                default:
                    System.out.println("Fejl, vælg et tal der svarer til menuens muligheder.");
            }
        }
    }
}
