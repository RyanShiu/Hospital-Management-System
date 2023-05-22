/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.helper;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import ryerson.ca.helper.ScheduleWorkAvailable;

@XmlRootElement(name = "scheduleworks")
@XmlAccessorType (XmlAccessType.FIELD)
       public class ScheduleWorkXML{
     @XmlElement(name="schedulework")
           private ArrayList<ScheduleWorkAvailable> scheduleWorkList;
           
           public List<ScheduleWorkAvailable>getScheduleWorks(){
               return scheduleWorkList;
               
           }
          public  ScheduleWorkXML(){
               
               
           }
           public void setScheduleWork(ArrayList<ScheduleWorkAvailable> bs){
               scheduleWorkList = bs;
               
           }
           
       }