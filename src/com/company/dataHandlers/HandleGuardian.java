package com.company.dataHandlers;

import com.company.DB.JDBCWriter;
import com.company.dataObjects.Guardian;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class HandleGuardian {

    private ArrayList<Guardian> guardians = new ArrayList<>();
    private Scanner in = new Scanner(System.in);

    public void guardianList() {

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

        String sql = "INSERT INTO guardians(firstname,lastname,email,phonenumber) VALUES(?,?,?,?)";

        try (
                Connection conn = JDBCWriter.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                ){
            preparedStatement.setString(1,firstname);
            preparedStatement.setString(2,lastname);
            preparedStatement.setString(3,email);
            preparedStatement.setString(4,phonenumber);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Fejl i tilføjelse af guardian til DB");
            System.err.println(e.getMessage());
            System.err.println(e.getSQLState());
            System.err.println(e.getErrorCode());
        }

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

    public void loadGuardiansFromDB() {
        String sql = "SELECT * FROM guardians";

        try(
                Connection con = JDBCWriter.getConnection();
                Statement statement = con.createStatement();
                ) {

            ResultSet guardianRs = statement.executeQuery(sql);

            while(guardianRs.next()) {
                guardians.add(new Guardian(guardianRs.getString("firstname"),
                        guardianRs.getString("lastname"),
                        guardianRs.getString("email"),
                        guardianRs.getString("phonenumber")));
            }

        } catch (SQLException e) {
            System.err.println("Fejl i at hente guardians fra db");
            System.err.println(e.getMessage());
            System.err.println(e.getSQLState());
            System.err.println(e.getErrorCode());
        }
    }

    public ArrayList<Guardian> getGuardians(){
        return guardians;
    }

}
