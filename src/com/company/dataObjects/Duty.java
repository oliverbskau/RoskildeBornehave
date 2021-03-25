package com.company.dataObjects;

import java.sql.Date;
import java.sql.Time;

public class Duty {

    private Date date;
    private Time startTime;
    private Time endTime;
    private Employee assignEmployee;

    public Duty(Date date, Time startTime, Time endTime, Employee assignEmployee){
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

    public Time getStartTime(){
        return startTime;
    }
    public void setStartTime(Time startTime){
        this.startTime = startTime;
    }

    public Time getEndTime(){
        return endTime;
    }
    public void setEndTime(Time endTime){
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
