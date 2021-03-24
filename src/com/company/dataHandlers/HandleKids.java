package com.company.dataHandlers;

import com.company.DB.JDBCWriter;
import com.company.dataObjects.Guardian;
import com.company.dataObjects.Kid;

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

    /**
     * Metode der fjerner et barn enten fra ventelisten eller børnelisten og fjerner barnet fra databasen også
     */
    public void removeKid(){
        String sql = "DELETE FROM kids WHERE firstname=? and lastname=?";

        System.out.println("Skal barnet fjernes fra børnehavelisten eller ventelisten?");
        System.out.println("1. Børnehavelisten");
        System.out.println("2. Ventelisten");
        int answer = in.nextInt();
        try (
                Connection conn = JDBCWriter.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                ){

            if (answer == 1) {

                seeAllKids();
                System.out.println("Hvilke barn vil du gerne fjerne? ");
                int removeKid = in.nextInt() - 1;
                Kid kid = kids.get(removeKid);
                kids.remove(kid);

                preparedStatement.setString(1,kid.getFirstname());
                preparedStatement.setString(2,kid.getLastname());

            } else if (answer == 2) {

                seeAllKidsOnWaitingList();
                System.out.println("Hvilke barn vil du gerne fjerne?");
                int removeKid = in.nextInt() - 1;
                Kid kid = waitingList.get(removeKid);
                waitingList.remove(kid);

                preparedStatement.setString(1,kid.getFirstname());
                preparedStatement.setString(2,kid.getLastname());
            }
        } catch (SQLException e) {
            System.err.println("Fejl i fjernelse af barn fra DB");
            System.err.println(e.getMessage());
            System.err.println(e.getErrorCode());
            System.err.println(e.getSQLState());
        }
    }

    public void transferKidFromWaitingList() {
        seeAllKidsOnWaitingList();
        System.out.println("Hvilke barn skal overføres til børnehaven?");
        int transferKid = in.nextInt()-1;

        Kid kidToTransfer = waitingList.get(transferKid);
        kidToTransfer.setOnWaitinglist(false);
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

    /**
     * Metode der hælder alle børn fra databasen ind i arraylisterne til manipulering i programmet
     */
    public void loadKidsFromDB() {
        String sql = "SELECT * FROM kids";
        boolean present = false;
        boolean onWaitingList = false;

        try(
                Connection con = JDBCWriter.getConnection();
                Statement statement = con.createStatement();
        ) {

            ResultSet rs = statement.executeQuery(sql);
            ResultSet guardian;

            while(rs.next()) {
                //Vi henter det guardianrow som har samme guardianid som barnet vi prøver at hælde ind
                guardian = statement.executeQuery("SELECT * FROM guardians WHERE guardianid=" + rs.getInt("guardianid"));

                //Vi er nød til at konvertere presentid og onWaitinglist til true og false fra databasen(java vs sql typer)
                if(rs.getInt("presentid") == 1) {
                    present = true;
                }
                if(rs.getInt("onWaitinglist") == 1) {
                    onWaitingList = true;
                }

                Kid kid = new Kid(rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getDate("dateofbirth"),
                        new Guardian(guardian.getString("firstname"),
                                guardian.getString("lastname"),
                                guardian.getString("email"),
                                guardian.getString("phonenumber")),
                        present,
                        onWaitingList);

                if(kid.getOnWaitinglist()) {
                    waitingList.add(kid);
                } else {
                    kids.add(kid);
                }
            }

        } catch (SQLException e) {
            System.out.println("Fejl i at hente guardians fra db");
        }
    }
    public ArrayList<Kid> getKids(){
        return kids;
    }

    public ArrayList<Kid> getWaitingList() {
        return waitingList;
    }
}
