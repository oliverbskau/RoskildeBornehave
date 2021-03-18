package com.company;

public class Employee {

    private String firstname;
    private String lastname;
    private String email;
    private String phonenumber;
    private String id;


    public Employee(){
        setId(id);
        setFirstname(firstname);
        setLastname(lastname);
        setEmail(email);
        setPhonenumber(phonenumber);
    }

    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id = id;
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
        return "ID: " + getId() + " | Navn: " + getFirstname() + " " + getLastname() + " | Email: " + getEmail() + " | Telefon: " + getPhonenumber();
    }


}
