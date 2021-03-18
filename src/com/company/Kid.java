package com.company;

import java.util.Date;

public class Kid {

    private String firstname;
    private String lastname;
    private Date dateOfBirth;
    private boolean present;

    public Kid() {
        setFirstname(firstname);
        setLastname(lastname);
        setDateOfBirth(dateOfBirth);
        setPresent(present);
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


    public String toString(){
        return "Navn: " + getFirstname() + " " + getLastname() + " | Født: " + getDateOfBirth() + " | Tilstede: " + getPresent();
    }



}
