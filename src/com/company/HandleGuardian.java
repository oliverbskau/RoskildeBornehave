package com.company;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class HandleGuardian {

    private JDBCWriter jdbcWriter = new JDBCWriter();
    ArrayList<Guardian> guardians = new ArrayList<>();

    public void guardianList() {
/*
       jdbcWriter.setConnection();

        String listGuardians = "select * from guardians;";

        try{
            PreparedStatement guardianStatement = jdbcWriter.getConnection().prepareStatement(listGuardians);
            ResultSet guardianRs = guardianStatement.executeQuery();
            System.out.println("\nForældre liste: ");
            int count = 1;
            while(guardianRs.next()){
                System.out.println(count + ". Forælder: " + guardianRs.getString("firstname")
                + " " + guardianRs.getString("lastname") + " | Telefon: " + guardianRs.getString("phonenumber"));
                count++;
                System.out.println("");

            }

        }catch (SQLException SQLerror){
            System.out.println("Kunne ikke finde listen i database.");
        }

 */
        int count = 1;
        for (int i = 0; i < guardians.size(); i++){
            System.out.println(count + ". " + guardians.get(i).toString());
            count++;
        }

    }

    public ArrayList<Guardian> getGuardians(){
        return guardians;
    }

}
