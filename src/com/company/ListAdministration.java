package com.company;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ListAdministration {

JDBCWriter jdbcWriter = new JDBCWriter();

    public void guardianList() {

        jdbcWriter.setConnection();
        String listGuardians = "SELECT firstname, lastname, phonenumber FROM guardians;";

        try{
            PreparedStatement guardianStatement = jdbcWriter.getConnection().prepareStatement(listGuardians);
            ResultSet guardianRs = guardianStatement.executeQuery();

            int count = 1;
            while(guardianRs.next()){
                System.out.println(count + ". Fornavn: " + guardianRs.getString("firstname")
                + " | Efternavn: " + guardianRs.getString("lastname" + " | Telefon: " + guardianRs.getString("phonenumber")));
                count++;
            }

        }catch (SQLException SQLerror){
            System.out.println("Kunne ikke finde listen i database.");
        }

    }

    public void phoneList () {

    }



}
