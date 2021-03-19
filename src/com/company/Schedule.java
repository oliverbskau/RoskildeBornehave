package com.company;


import java.sql.*;
import java.util.Scanner;

public class Schedule {
    Scanner in = new Scanner(System.in);
    Connection connection = null;

    public void seeSchedule(){
        String query = "SELECT FROM schedules";
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery(query);

        } catch (Exception e) {
            System.out.println("Fejl ved forsøg på at vise vagttider");
        }
    }

    public void addToSchedule(){
        System.out.println("Hvad tid starter din vagt?");
        String start = in.nextLine();
        System.out.println("Hvad tid slutter din vagt?");
        String slut = in.nextLine();

        String query = "INSERT INTO schedules(" + start + ", " + slut + ");";
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery(query);
        } catch (Exception e) {
            System.out.println("Fejl ved tilføjelse af dato for vagt");
        }
    }

    public void removeFromSchedule(){
        seeSchedule();
        System.out.println("Hvilken vagt ønsker du at slette?");
        System.out.println("ID for vagt: ");
        String iD = in.nextLine();
        System.out.println("Skriv datoen for vagten: (dd-mm-yyyy)");
        String dato = in.nextLine();
        System.out.println("Skriv starttidspunket for vagten: (hh:mm:ss)");
        String start = in.nextLine();

        String query = "DELETE FROM schedule WHERE scheduleid LIKE ? and dato LIKE ? and start LIKE ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, iD);
            preparedStatement.setString(2, dato);
            preparedStatement.setString(3, start);
            preparedStatement.executeQuery(query);
        } catch (Exception e) {
            System.out.println("Fejl ved tilføjelse af dato for vagt");
        }

    }
}
