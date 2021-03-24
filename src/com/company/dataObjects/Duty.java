package com.company.dataObjects;

import java.sql.Date;

public class Duty {

    private Date date;
    private String startTime;
    private String endTime;
    private Employee assignEmployee;

    public Duty(Date date, String startTime, String endTime, Employee assignEmployee){
        setDate(date);
        setStartTime(startTime);
        setEndTime(endTime);
        setAssignEmployee(assignEmployee);
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date){
        this.date = date;
    }

    public String getStartTime(){
        return startTime;
    }
    public void setStartTime(String startTime){
        this.startTime = startTime;
    }

    public String getEndTime(){
        return endTime;
    }
    public void setEndTime(String endTime){
        this.endTime = endTime;
    }

    public Employee getAssignEmployee(){
        return assignEmployee;
    }
    public void setAssignEmployee(Employee assignEmployee){
        this.assignEmployee = assignEmployee;
    }


    public String toString(){
        return "Dato: " + getDate() + " Kl. " + getStartTime() + " - " + getEndTime() + " | Medarbejder: " + getAssignEmployee();
    }

}
