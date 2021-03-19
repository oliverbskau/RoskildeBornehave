package com.company;

import java.util.Date;
import java.util.ArrayList;

public class Kid {

    private String firstname;
    private String lastname;
    private Date dateOfBirth;
    private boolean present;
    private boolean onWaitinglist;
    private ArrayList<Guardian> guardians;

    public Kid(String firstname, String lastname, Date dateOfBirth, Guardian guardian1, Guardian guardian2) {
        setFirstname(firstname);
        setLastname(lastname);
        setDateOfBirth(dateOfBirth);
        setGuardians(guardian1,guardian2);
    }

    public String getFirstname(){
        return firstname;
    }
    public void setFirstname(String firstname){
        this.firstname = firstname;
    }

    public String getLastname(){
        return lastname;
    }
    public void setLastname(String lastname){
        this.lastname = lastname;
    }

    public Date getDateOfBirth(){
        return dateOfBirth;
    }
    public void setDateOfBirth(Date dateOfBirth){
        this.dateOfBirth = dateOfBirth;
    }

    public boolean getPresent(){
        return present;
    }
    public void setPresent(boolean present){
        this.present = present;
    }
    public ArrayList<Guardian> getGuardians() {
        return guardians;
    }

    public void setGuardians(Guardian guardians1, Guardian guardian2){
        this.guardians.add(guardians1);
        this.guardians.add(guardian2);
    }

    public String toString(){
        return "Navn: " + getFirstname() + " " + getLastname() + " | Født: " + getDateOfBirth() + " | Tilstede: " + getPresent();
    }
}
