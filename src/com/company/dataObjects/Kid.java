package com.company.dataObjects;

import java.util.Date;

public class Kid {

    private String firstname;
    private String lastname;
    private Date dateOfBirth;
    private boolean present;
    private boolean onWaitinglist;
    private Guardian guardian;

    public Kid(String firstname, String lastname, Date dateOfBirth, Guardian guardian, boolean present , boolean onWaitinglist) {
        setFirstname(firstname);
        setLastname(lastname);
        setDateOfBirth(dateOfBirth);
        setPresent(present);
        setGuardian(guardian);
        setOnWaitinglist(onWaitinglist);
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

    public Guardian getGuardian() {
        return guardian;
    }

    public void setGuardian(Guardian guardian){
        this.guardian = guardian;

    }

    public boolean getOnWaitinglist(){
        return onWaitinglist;
    }
    public void setOnWaitinglist(boolean onWaitinglist){
        this.onWaitinglist = onWaitinglist;
    }

    public String toString(){
        return "Navn: " + getFirstname() + " " + getLastname() + " | Født: " + getDateOfBirth() + " | Tilstede: "
                + getPresent() + " | på venteliste: " + getOnWaitinglist() +
                "\n | Forældre tilknyttet til barn : " + guardian.toString();
    }
    public String protocolString(){
        return "Navn: " + getFirstname() + " " + getLastname() + " | Tilstede: " + getPresent();
    }
}
