package com.company;

import java.util.Date;

public class Kids {

    private String firstname;
    private String lastname;
    private Date dateOfBirth;

    public Kids(){
        setFirstname(firstname);
        setLastname(lastname);
        setDateOfBirth(dateOfBirth);
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

    public String toString(){
        return "Navn: " + getFirstname() + " " + getLastname() + " | Født: " + getDateOfBirth();
    }



}
