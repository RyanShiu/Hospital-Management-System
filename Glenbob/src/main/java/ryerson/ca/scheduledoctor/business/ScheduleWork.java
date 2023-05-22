/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.scheduledoctor.business;




import ryerson.ca.scheduledoctor.persistence.ScheduleWork_CRUD;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ryerson.ca.scheduledoctor.helper.ScheduleWorkXML;
import ryerson.ca.scheduledoctor.helper.ScheduleWorkList;
import ryerson.ca.scheduledoctor.helper.ScheduleWorkAvailable;
/**
 *
 * @author student
 */

public class ScheduleWork {

    

 
    
    public ScheduleWorkXML getScheduleWorkInfo() {
        /**
         * to be completed. For now, we just return a user info object that has a default book in a default date;
         * This method must return null when user name and password is incorrect
         * otherwise it must return an object containing all books that have been borrowed by the user, in addition to user information like name, address, ...
         */
        ScheduleWorkList scheduleWorkList = ScheduleWork_CRUD.getScheduleWorkList();
        ScheduleWorkXML bs = new ScheduleWorkXML();
        ArrayList<ScheduleWorkAvailable> arrayList = scheduleWorkList.getScheduleWorkAvailable();
      
            if (arrayList== null) {
                  
                System.out.println("glen");
            } else {
                System.out.println("Yinho");
    // the patient object is not null
            }
        bs.setScheduleWork(scheduleWorkList.getScheduleWorkAvailable());
 
        return (bs);
        
    }
    public void UpdateInfo(int adminID, int doctorID, String date, String startTime, String endTime) {
        /**
         * to be completed. For now, we just return a user info object that has a default book in a default date;
         * This method must return null when user name and password is incorrect
         * otherwise it must return an object containing all books that have been borrowed by the user, in addition to user information like name, address, ...
         */
        ScheduleWork_CRUD.UpdateList(adminID, doctorID, date, startTime, endTime);
    }
    
    
    
    
    
 
}

