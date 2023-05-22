/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.scheduledoctor.helper;

import java.util.ArrayList;

/**
 *
 * @author student
 */
public class ScheduleWorkList {
    private ArrayList <ScheduleWorkAvailable> ScheduleWorkavailable = new ArrayList<>();
    public void addScheduleWork(ScheduleWorkAvailable ScheduleWork){
        ScheduleWorkavailable.add(ScheduleWork);
    }
    public ArrayList<ScheduleWorkAvailable> getScheduleWorkAvailable(){
        return ScheduleWorkavailable;
    }
}
