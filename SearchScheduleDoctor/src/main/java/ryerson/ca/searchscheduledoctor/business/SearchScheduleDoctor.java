/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.searchscheduledoctor.business;

import ryerson.ca.searchscheduledoctor.helper.ScheduleWorkXML;
import static java.lang.System.in;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import ryerson.ca.searchscheduledoctor.helper.ScheduleWorkAvailable;
import ryerson.ca.searchscheduledoctor.persistence.ScheduleWork_CRUD;

/**
 *
 * @author student
 */
public class SearchScheduleDoctor {
    
    public  ScheduleWorkXML getScheduleDoctorsByQuery(String query){
        Set<ScheduleWorkAvailable> scheduleWorks = ScheduleWork_CRUD.searchForScheduleWorks(query);
        ArrayList<ScheduleWorkAvailable> scheduleWorkList = new ArrayList<>(scheduleWorks);
        
        ScheduleWorkXML bs = new ScheduleWorkXML();
        bs.setScheduleWork(scheduleWorkList);
        return (bs);
    }
    
      
}
