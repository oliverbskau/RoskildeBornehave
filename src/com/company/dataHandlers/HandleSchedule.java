package com.company.dataHandlers;


import com.company.DB.JDBCWriter;
import com.company.dataObjects.Duty;
import com.company.dataObjects.Employee;

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
        Time startTime = Time.valueOf(in.nextLine());
        System.out.println("Hvad tid slutter vagten? (HH:MM:SS)");
        Time endTime = Time.valueOf(in.nextLine());
        System.out.println("Hvilken medarbejder skal tilføjes tilføjes til vagten?");
        handleEmployee.printEmployees();
        int assignEmployee = in.nextInt()-1;

        Employee employee = handleEmployee.getEmployees().get(assignEmployee);

        duties.add(new Duty(date , startTime, endTime, employee));

        String query = "INSERT INTO duties(Date,start,end,employeeid) VALUES(?,?,?,?);";
        try (
                Connection conn = JDBCWriter.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                PreparedStatement subPreparedStmt = conn.prepareStatement("SELECT * FROM employee WHERE firstname like ? AND lastname like ?;")
                ){

            preparedStatement.setDate(1,date);
            preparedStatement.setTime(2,startTime);
            preparedStatement.setTime(3,endTime);

            subPreparedStmt.setString(1,employee.getFirstname());
            subPreparedStmt.setString(2,employee.getLastname());

            ResultSet rs = subPreparedStmt.executeQuery();
            int employeeId = 0;

            if(rs.next()) {
                employeeId = rs.getInt("employeeid");
            }

            preparedStatement.setInt(4,employeeId);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Fejl i tilføjelse af vagt til DB");
            System.err.println(e.getMessage());
            System.err.println(e.getSQLState());
            System.err.println(e.getErrorCode());
        }
    }

    public void removeFromSchedule(){

        seeSchedule();

        System.out.println("\nHvilken vagt ønsker du at slette?");

        int removeDuty = in.nextInt()-1;
        Duty duty = duties.get(removeDuty);
        duties.remove(duty);

        String sql = "DELETE FROM duties WHERE Date=? and start=? and end=?";

        try(
                Connection conn = JDBCWriter.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                ) {

            preparedStatement.setDate(1,duty.getDate());
            preparedStatement.setTime(2,duty.getStartTime());
            preparedStatement.setTime(3,duty.getEndTime());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Fejl i fjernelse af vagt");
            System.err.println(e.getMessage());
            System.err.println(e.getSQLState());
            System.err.println(e.getErrorCode());
        }
    }

    public void loadScheduleFromDB() {
        String sql = "SELECT * FROM duties JOIN employee using(employeeid)";

        try(
                Connection con = JDBCWriter.getConnection();
                Statement statement = con.createStatement();
        ) {

            ResultSet dutiesRs = statement.executeQuery(sql);

            while(dutiesRs.next()) {

                duties.add(new Duty(dutiesRs.getDate("Date"),
                        dutiesRs.getTime("start"),
                        dutiesRs.getTime("end"),
                        new Employee(dutiesRs.getString("firstname"),
                                dutiesRs.getString("lastname"),
                                dutiesRs.getString("email"),
                                dutiesRs.getString("phonenumber")))); //Mangler employee assigned to task));
            }

        } catch (SQLException e) {
            System.out.println("Fejl i at hente guardians fra db");
        }
    }
}
