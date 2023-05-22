/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.deletepatient.helper;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import ryerson.ca.deletepatient.helper.PatientAvailable;

@XmlRootElement(name = "deletepatients")
@XmlAccessorType (XmlAccessType.FIELD)
public class PatientXML{
     @XmlElement(name="deletepatient")
           private ArrayList<PatientAvailable> patientList;
           
           public List<PatientAvailable>getPatients(){
               return patientList;
               
           }
           public  PatientXML(){
               
               
           }
           public void setPatient(ArrayList<PatientAvailable> bs){
               patientList = bs;
               
           }
           
}
