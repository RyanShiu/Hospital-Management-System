/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.deletepatient.business;


import ryerson.ca.deletepatient.helper.PatientList;
import ryerson.ca.deletepatient.helper.PatientXML;
import ryerson.ca.deletepatient.helper.PatientAvailable;
import ryerson.ca.deletepatient.persistence.Patient_CRUD;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
/**
 *
 * @author student
 */
public class DeletePatient {
    
    public PatientXML getPatientInfo() {
        /**
         * to be completed. For now, we just return a user info object that has a default book in a default date;
         * This method must return null when user name and password is incorrect
         * otherwise it must return an object containing all books that have been borrowed by the user, in addition to user information like name, address, ...
         */
        PatientList patientList = Patient_CRUD.getPatientList();
        
      
            
            
        
        PatientXML bs = new PatientXML();
        bs.setPatient(patientList.getPatientAvailable());
        return bs;
    }
    public void DeleteInfo(int patientID) {
        /**
         * to be completed. For now, we just return a user info object that has a default book in a default date;
         * This method must return null when user name and password is incorrect
         * otherwise it must return an object containing all books that have been borrowed by the user, in addition to user information like name, address, ...
         */
        Patient_CRUD.DeletePatient(patientID);
    }
    
    
   


}
