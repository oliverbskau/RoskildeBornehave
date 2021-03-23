package com.company;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.*;
import java.util.Scanner;

public class HandleKids {

    private JDBCWriter jdbcWriter= new JDBCWriter();
    private     Scanner in = new Scanner(System.in);
    private ArrayList<Kid> kids = new ArrayList<>();
    private ArrayList<Kid> waitingList = new ArrayList<>();

    public void addKid(HandleGuardian handleGuardian) {
        in.nextLine();
        System.out.println("Fornavn: ");
        String firstname = in.nextLine();
        System.out.println("Efternavn: ");
        String lastname = in.nextLine();
        System.out.println("Fødselsdato: (yyyy-mm-dd)");
        Date dateOfBirth = Date.valueOf(in.next());
        in.nextLine();


        System.out.println("Tilstede: (true/false)");
        String presentYN = in.nextLine();
        System.out.println("Er barnet på venteliste? (true/false)");
        String onWaitinglistYN = in.nextLine();

        boolean present = false;

        if (presentYN.equalsIgnoreCase("false")) {
            present = false;
        } else  if (presentYN.equalsIgnoreCase("true")){
            present = true;
        } else {
            System.out.println("Skriv venligst enten true eller false: present");
        }

        boolean onWaitinglist = false;

        if (onWaitinglistYN.equalsIgnoreCase("false")) {
            onWaitinglist = false;
        } else  if (onWaitinglistYN.equalsIgnoreCase("true")){
            onWaitinglist = true;
        } else {
            System.out.println("Skriv venligst enten true eller false: onWaitinglist");
        }
/*
        String insertInto = "INSERT INTO kids(firstname, lastname, dateofbirth) values(?,?,?,?,?);";

        try {
            PreparedStatement insertValuesKid = jdbcWriter.getConnection().prepareStatement(insertInto);
            insertValuesKid.setString(1,firstname);
            insertValuesKid.setString(2,lastname);
            insertValuesKid.setString(3,dateOfBirth);
            insertValuesKid.executeQuery();

            System.out.println("\n| Barn tilføjet til database |");

        }catch(SQLException sqlError){
            System.out.println("Fejl, barnet blev ikke gemt i database");
        }
*/
        System.out.println("Hvem er barnets forælder? ");
        handleGuardian.guardianList();
        int theGuradian = in.nextInt()-1;

        Kid newKid = new Kid(firstname, lastname, dateOfBirth, handleGuardian.getGuardians().get(theGuradian), present, onWaitinglist);
        if(onWaitinglist) {
            waitingList.add(newKid);
        } else {
            kids.add(newKid);
        }
    }

    public void removeKid(){
        System.out.println("Skal barnet fjernes fra børnehavelisten eller ventelisten?");
        System.out.println("1. Børnehavelisten");
        System.out.println("2. Ventelisten");
        int answer = in.nextInt();

        if(answer == 1) {
            seeAllKids();
            System.out.println("Hvilke barn vil du gerne fjerne? ");
            int removeKid = in.nextInt()-1;
            kids.remove(removeKid);
        } else if(answer == 2) {
            seeAllKidsOnWaitingList();
            System.out.println("Hvilke barn vil du gerne fjerne?");
            int removeKid = in.nextInt()-1;
            waitingList.remove(removeKid);
        }
    }

    public void transferKidFromWaitingList() {
        seeAllKidsOnWaitingList();
        System.out.println("Hvilke barn skal overføres til børnehaven?");
        int transferKid = in.nextInt()-1;

        Kid kidToTransfer = waitingList.get(transferKid);
        waitingList.remove(transferKid);

        kids.add(kidToTransfer);
    }

    public void seeAllKids(){
        int count = 1;
        for (int i = 0; i < kids.size(); i++ ){
            System.out.println(count + ". " + kids.get(i).toString());
            count++;
        }
    }

    public void seeAllKidsOnWaitingList() {
        int count = 1;
        System.out.println("Børn på venteliste: ");
        for (int i = 0; i < waitingList.size(); i++) {
            System.out.println(count + ". " + waitingList.get(i).toString());
            count++;
        }
    }

    public void seeProtocol(){
        int count = 1;
        for (int i = 0; i < kids.size(); i++){
            System.out.println(count + ". " + kids.get(i).protocolString());
            count++;
        }
    }

    public void setProtocol(){

        int count = 1;
        for (int i = 0; i < kids.size(); i++){
            System.out.println(count + ". " + kids.get(i).protocolString());
            System.out.println("Er dette barn tilstede - tast 1 for ja og 0 for nej:");
            int present = in.nextInt();

            if(present == 1){
                kids.get(i).setPresent(true);
            }else if(present == 0){
                kids.get(i).setPresent(false);
            }

        }
    }
    public ArrayList<Kid> getKids(){
        return kids;
    }

    public ArrayList<Kid> getWaitingList() {
        return waitingList;
    }
}
