package com.company;

public class Guardian {

private String firstname;
private String lastname;
private String email;
private String phonenumber;


    public Guardian(String firstname, String lastname, String email, String phonenumber){
        setFirstname(firstname);
        setLastname(lastname);
        setEmail(email);
        setPhonenumber(phonenumber);
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

    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public String getPhonenumber(){
        return phonenumber;
    }
    public void setPhonenumber(String phonenumber){
        this.phonenumber = phonenumber;
    }

    public String toString(){
        return "Navn: " + getFirstname() + " " + getLastname() + " | Email: " + getEmail() + " | Telefon: " + getPhonenumber();
    }


}
