package com.company.dataHandlers;


import com.company.DB.JDBCWriter;
import com.company.dataHandlers.HandleEmployee;
import com.company.dataObjects.Duty;
import com.company.dataObjects.Guardian;

import java.sql.*;
import java.util.Scanner;
import java.util.ArrayList;

public class HandleSchedule {
    private Scanner in = new Scanner(System.in);
    private ArrayList<Duty> duties = new ArrayList<>();


    public void seeSchedule(){

        System.out.println();
        int count = 1;
        for (int i = 0; i < duties.size(); i++){
            System.out.println(count + ". " + duties.get(i).toString());
            count++;
        }
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

        int removeDuty = in.nextInt()-1;
        Duty duty = duties.get(removeDuty);
        duties.remove(duty);

        String sql = "DELETE FROM schedule WHERE Date LIKE ? and start LIKE ? and end LIKE ?";

        try(
                Connection conn = JDBCWriter.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                ) {

            preparedStatement.setDate(1,duty.getDate());
            preparedStatement.setTime(2,Time.valueOf(duty.getStartTime()));
            preparedStatement.setTime(3,Time.valueOf(duty.getEndTime()));

            preparedStatement.executeQuery();

        } catch (SQLException e) {
            System.err.println("Fejl i fjernelse af vagt");
            System.err.println(e.getMessage());
            System.err.println(e.getSQLState());
            System.err.println(e.getErrorCode());
        }
    }

    public void loadScheduleFromDB() {
        String sql = "SELECT * FROM duties";

        try(
                Connection con = JDBCWriter.getConnection();
                Statement statement = con.createStatement();
        ) {

            ResultSet dutiesRs = statement.executeQuery(sql);
            /*
            while(dutiesRs.next()) {
                duties.add(new Duty(dutiesRs.getDate("Date"),
                        dutiesRs.getTime("start"),
                        dutiesRs.getTime("end"), //Mangler employee assigned to task));
            }

             */

        } catch (SQLException e) {
            System.out.println("Fejl i at hente guardians fra db");
        }
    }


}
