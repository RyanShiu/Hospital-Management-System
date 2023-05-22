/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.helper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


/**
 *
 * @author student
 */
@XmlRootElement(name = "schedulework")
@XmlAccessorType(XmlAccessType.FIELD)
public class ScheduleWorkAvailable {
    
    
@XmlTransient
    public int getManagerID(){
        return managerID;
    }
    
    public void setManagerID(int managerID) {
        this.managerID = managerID;
    }
    
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    public int getDoctorID(){
        return doctorID;
    }

    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }
    
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    
    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    public ScheduleWorkAvailable(){
        
    }

    int managerID;
    int doctorID;
    String date;
    String startTime;
    String endTime;
   
    
    public ScheduleWorkAvailable (int managerID, int doctorID, String date, String startTime, String endTime){
        this.managerID = managerID;
        this.doctorID = doctorID;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        
    }
}
