package com.company;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class HandleKids {

    JDBCWriter jdbcWriter= new JDBCWriter();
    Scanner in = new Scanner(System.in);

    public void addKid() {
        System.out.println("Fornavn: ");
        String firstname = in.nextLine();
        System.out.println("Efternavn: ");
        String lastname = in.nextLine();
        System.out.println("Fødselsdato: (dd-mm-yyyy)");
        String dateOfBirth = in.nextLine();
        System.out.println("Tilstede: (true/false)");
        String presentYN = in.nextLine();
        System.out.println("Er barnet på venteliste? (true/false)");
        String onWaitinglistYN = in.nextLine();

        String present = null;

        if (presentYN.equalsIgnoreCase("false")) {
            present = "0";
        } else  if (presentYN.equalsIgnoreCase("true")){
            present = "1";
        } else {
            System.out.println("Skriv enligst enten true eller false: present");
        }

        String onWaitinglist = null;

        if (onWaitinglistYN.equalsIgnoreCase("false")) {
            onWaitinglist = "0";
        } else  if (onWaitinglistYN.equalsIgnoreCase("true")){
            onWaitinglist = "1";
        } else {
            System.out.println("Skriv venligst enten true eller false: onWaitinglist");
        }

        String insertInto = "INSERT INTO kids(firstname, lastname, dateofbirth, present, onWaitinglist) values(?,?,?,?,?);";

        try {
            PreparedStatement insertValuesEmployee = jdbcWriter.getConnection().prepareStatement(insertInto);
            insertValuesEmployee.setString(1,firstname);
            insertValuesEmployee.setString(2,lastname);
            insertValuesEmployee.setString(3,dateOfBirth);
            insertValuesEmployee.setString(4, present);

            insertValuesEmployee.executeQuery();
            System.out.println("\n| Barn tilføjet til database |");

        }catch(SQLException sqlError){
            System.out.println("Fejl, barnet blev ikke gemt i database");
        }

    }

    public void removeKid(){

    }

    public void seeAllKids(){

    }

    public void setPresence(){

    }
}
