package com.company;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class HandleKids {

    private JDBCWriter jdbcWriter= new JDBCWriter();
    private     Scanner in = new Scanner(System.in);

    public void addKid() {
        System.out.println("Fornavn: ");
        String firstname = in.nextLine();
        System.out.println("Efternavn: ");
        String lastname = in.nextLine();
        System.out.println("Fødselsdato: (dd-mm-yyyy)");
        String dateOfBirth = in.nextLine();
        System.out.println("Er barnet på venteliste? (true/false)");
        String onWaitinglistYN = in.nextLine();

        String present = "0";
        // Standard for barnet er, at det ikke er til stede, da de fleste børn der bliver
        // oprettet formentlig er på venteliste, og dermed ikke til stede

        String onWaitinglist = null;

        if (onWaitinglistYN.equalsIgnoreCase("false")) {
            onWaitinglist = "0";
        } else  if (onWaitinglistYN.equalsIgnoreCase("true")){
            onWaitinglist = "1";
        } else {
            System.out.println("Skriv venligst enten true eller false: onWaitinglist");
        }

        String insertInto = "INSERT INTO present(date) VALUES(?);" +
                "SELECT " +
                "kids(firstname, lastname, dateofbirth, onWaitinglistid) values(?,?,?,?);";

        try {
            PreparedStatement insertValuesKid = jdbcWriter.getConnection().prepareStatement(insertInto);
            insertValuesKid.setString(1,firstname);
            insertValuesKid.setString(2,lastname);
            insertValuesKid.setString(3,dateOfBirth);
            insertValuesKid.setString(4,onWaitinglist);
            insertValuesKid.executeQuery();

            System.out.println("\n| Barn tilføjet til database |");

        }catch(SQLException sqlError){
            System.out.println("Fejl, barnet blev ikke gemt i database");
        }

    }

    public void removeKid(){

    }

    public void seeAllKids(){

    }

    public void seeProtocol(){

    }

    public void setProtocol(){

    }
}
