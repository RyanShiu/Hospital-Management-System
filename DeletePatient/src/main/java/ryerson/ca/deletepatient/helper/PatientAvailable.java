/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.deletepatient.helper;


/**
 *
 * @author student
 */
public class PatientAvailable {
    
    
    
    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
     public String getPhoneNumber() {
        return phone;
    }

    public void setPhoneNumber(String phone) {
        this.phone = phone;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getDateOfBirth() {
        return birthDate;
    }

    public void setDateOfBirth(String birthDate) {
        this.birthDate = birthDate;
    }
    
    public String getPatientName(){
        return patientName;
    }
    
    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }
    public PatientAvailable(){
        
    }
    
    int patientID;
    String gender;
    String phone;
    String address;
    String birthDate;
    String patientName;
    
    public PatientAvailable (String patientName, String address, String birthDate, String phone, String gender, int patientID){
        this.patientID = patientID;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.birthDate = birthDate;
        this.patientName = patientName;
    }
}



