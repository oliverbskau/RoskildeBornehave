package com.company;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.*;
import java.util.Scanner;

public class HandleKids {

    private     Scanner in = new Scanner(System.in);
    private ArrayList<Kid> kids = new ArrayList<>();
    private ArrayList<Kid> waitingList = new ArrayList<>();

    /**
     * Metoden tilføjer et barn til børnelisten og tilføjer samtidigt barnet til databasen
     * @param handleGuardian påkrævet da vi har brug for en forældre når vi oprettet et barn
     */
    public void addKid(HandleGuardian handleGuardian) {
        in.nextLine();
        System.out.println("Fornavn: ");
        String firstname = in.nextLine();
        System.out.println("Efternavn: ");
        String lastname = in.nextLine();
        System.out.println("Fødselsdato: (yyyy-mm-dd)");
        Date dateOfBirth = Date.valueOf(in.next());
        in.nextLine();


        System.out.println("Er barnet på venteliste? (true/false)");
        String onWaitinglistYN = in.nextLine();

        boolean onWaitinglist = false;

        if (onWaitinglistYN.equalsIgnoreCase("true")){
            onWaitinglist = true;
        } else {
            System.out.println("Skriv venligst enten true eller false: onWaitinglist");
        }

        System.out.println("Hvem er barnets værge? ");
        handleGuardian.guardianList();
        int theGuardian = in.nextInt()-1;
        Guardian guardian = handleGuardian.getGuardians().get(theGuardian);

        Kid newKid = new Kid(firstname, lastname, dateOfBirth, guardian, false, onWaitinglist);
        if(onWaitinglist) {
            waitingList.add(newKid);
        } else {
            kids.add(newKid);
        }


        String insertInto = "INSERT INTO kids(firstname, lastname, dateofbirth, guardianid, onWaitinglist) values(?,?,?,?,?);";

        try (
                Connection conn = JDBCWriter.getConnection();
                PreparedStatement ps = conn.prepareStatement(insertInto);
                Statement statement = conn.createStatement();
                ){
            //Resulsettet returnere en forældre der matcher med det brugeren taster når vi spørger om hvilke forældre barnet har
            ResultSet rs = statement.executeQuery("SELECT * FROM guardians WHERE firstname="
                    + guardian.getFirstname() + " and lastname=" + guardian.getLastname() + ";");

            ps.setString(1,firstname);
            ps.setString(2,lastname);
            ps.setDate(3,dateOfBirth);
            ps.setInt(4,rs.getInt("guardianid")); //Vi henter guardianid på den guardian som vi har valgt i resultsettet

            if(onWaitinglist) { //Hvis barnet er på venteliste så skriver vi 1 og ellers 0
                ps.setInt(5,1);
            } else {
                ps.setInt(5,0);
            }

            ps.executeQuery();

            System.out.println("\n| Barn tilføjet til database |");

        }catch(SQLException sqlError){
            System.out.println("Fejl, barnet blev ikke gemt i database");
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
