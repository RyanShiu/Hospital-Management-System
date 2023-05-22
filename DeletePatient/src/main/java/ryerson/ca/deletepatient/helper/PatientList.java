/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.deletepatient.helper;

import java.util.ArrayList;

/**
 *
 * @author student
 */
public class PatientList {
    private ArrayList <PatientAvailable> patientAvailable = new ArrayList<>();
    
    public void addPatient(PatientAvailable patient){
        patientAvailable.add(patient);
    }
    
    public ArrayList<PatientAvailable> getPatientAvailable(){
        return patientAvailable;
    }
}
