package com.company;


import java.sql.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Schedule {
    private Scanner in = new Scanner(System.in);
    private JDBCWriter jdbcWriter = new JDBCWriter();
    private Connection connection = null;
    private ArrayList<Duty> duties = new ArrayList<>();

    public Schedule() {
        //  jdbcWriter.setConnection();
        connection = jdbcWriter.getConnection();
    }

    public void seeSchedule(){

        System.out.println();
        int count = 1;
        for (int i = 0; i < duties.size(); i++){
            System.out.println(count + ". " + duties.get(i).toString());
            count++;
        }

       /*
        String query = "SELECT * FROM duties";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()) {
                System.out.println("Dato for vagt: " + resultSet.getDate("Date") +
                        " | Starttidspunkt" + resultSet.getTime("start") +
                        " | Sluttidspunkt: " + resultSet.getTime("end"));
            }

        } catch (Exception e) {
            System.out.println("Fejl ved forsøg på at vise vagttider");
        }

        */
    }

    public void addToSchedule(HandleEmployee handleEmployee){

        System.out.println("\nHvad dato skal vagten ligge på?(yyyy-mm-dd)");
        Date date = Date.valueOf(in.next());
        in.nextLine();
        System.out.println("Hvad tid starter vagten? (HH:MM:SS)");
        String startTime = in.nextLine();
        System.out.println("Hvad tid slutter vagten? (HH:MM:SS)");
        String endTime = in.nextLine();
        System.out.println("Hvilken medarbejder skal tilføjes tilføjes til vagten?");
        handleEmployee.printEmployees();
        int assignEmployee = in.nextInt()-1;


        /*
        String query = "INSERT INTO schedules(" + shiftDate + ", " + start + ", " + slut + ");";
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery(query);

        } catch (Exception e) {
            System.out.println("Fejl ved tilføjelse af dato for vagt");
        }

         */

        duties.add(new Duty(date , startTime, endTime, handleEmployee.getEmployees().get(assignEmployee)));


    }

    public void removeFromSchedule(){

        seeSchedule();

        System.out.println("\nHvilken vagt ønsker du at slette?");
        /*
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
*/
        int removeDutie = in.nextInt()-1;
        duties.remove(removeDutie);

    }
}
