package com.company;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class HandleGuardian {

    private JDBCWriter jdbcWriter = new JDBCWriter();
    ArrayList<Guardian> guardians = new ArrayList<>();
    Scanner in = new Scanner(System.in);

    public void guardianList() {
/*
       jdbcWriter.setConnection();

        String listGuardians = "select * from guardians;";

        try{
            PreparedStatement guardianStatement = jdbcWriter.getConnection().prepareStatement(listGuardians);
            ResultSet guardianRs = guardianStatement.executeQuery();
            System.out.println("\nForældre liste: ");
            int count = 1;
            while(guardianRs.next()){
                System.out.println(count + ". Forælder: " + guardianRs.getString("firstname")
                + " " + guardianRs.getString("lastname") + " | Telefon: " + guardianRs.getString("phonenumber"));
                count++;
                System.out.println("");

            }

        }catch (SQLException SQLerror){
            System.out.println("Kunne ikke finde listen i database.");
        }

 */
        int count = 1;
        for (int i = 0; i < guardians.size(); i++){
            System.out.println(count + ". " + guardians.get(i).toString());
            count++;
        }

    }

    public void addGuardian(){
        System.out.println("Fornavn: ");
        String firstname = in.nextLine();
        System.out.println("Efternavn: ");
        String lastname = in.nextLine();
        System.out.println("Email: ");
        String email = in.nextLine();
        System.out.println("Telefon: ");
        String phonenumber = in.nextLine();

        guardians.add(new Guardian(firstname, lastname, email, phonenumber));
    }
    public void updateGuardian(HandleKids handleKids){
        System.out.println("Hvilken forældrer skal administreres: ");
        guardianList();
        int guardianChoice = in.nextInt()-1;
        in.nextLine();

       Guardian tempGuardian = guardians.get(guardianChoice);

        System.out.println("1. Fjern forælder og barn\n2. Ændrer forælders oplysninger");
        System.out.print("Vælg: ");
        int choice = in.nextInt();
        in.nextLine();
        switch(choice){
            case 1:
                for (int i = 0; i < handleKids.getKids().size(); i++){
                    if (handleKids.getKids().get(i).getGuardian().equals(tempGuardian)){
                        handleKids.getKids().remove(i);
                        guardians.remove(guardianChoice);
                    }
                }
                break;
            case 2:
                boolean updating = true;
                while (updating) {
                    System.out.println("\nHvad skal ændres:");
                    int updateChoice = in.nextInt();
                    in.nextLine();
                    System.out.println("\n1. Fornavn, 2. Efternavn, 3. Email, 4. Telefon, 5.Færdig");

                    if(updateChoice == 1){
                        System.out.println("Fornavn: ");
                        String firstname = in.nextLine();
                        tempGuardian.setFirstname(firstname);
                    }else if(updateChoice == 2){
                        System.out.println("Efternavn: ");
                        String lastname = in.nextLine();
                        tempGuardian.setLastname(lastname);
                    }else if(updateChoice == 3){
                        System.out.println("Email: ");
                        String email = in.nextLine();
                        tempGuardian.setEmail(email);
                    }else if(updateChoice == 4){
                        System.out.println("Telefon: ");
                        String phonenumber = in.nextLine();
                        tempGuardian.setFirstname(phonenumber);
                    }else if(updateChoice == 5){
                        updating = false;
                    }

                }
                break;
        }
    }

    public ArrayList<Guardian> getGuardians(){
        return guardians;
    }

}
