/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.scheduledoctor.endpoint;

import java.io.StringWriter;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import ryerson.ca.scheduledoctor.business.ScheduleWork;
import ryerson.ca.scheduledoctor.helper.ScheduleWorkList;
import ryerson.ca.scheduledoctor.helper.ScheduleWorkAvailable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import ryerson.ca.scheduledoctor.helper.ScheduleWorkXML;
import java.util.Date;

/**
 * REST Web Service
 *
 * @author student
 */
@Path("scheduleDoctor")
public class ScheduleDoctorResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of SearchResource
     */
    public ScheduleDoctorResource() {
    }

    /**
     * Retrieves representation of an instance of
     * ryerson.ca.searchbook.endpoint.SearchResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("schedule")
    public String getXml() {
        
        ScheduleWork scheduleWork = new ScheduleWork();
        
        ScheduleWorkXML schedule = scheduleWork.getScheduleWorkInfo();
        System.out.println(">>>>>>>>>>>>>>>>>>" + schedule);
        if (schedule == null) {
            return("");
        }
        
        JAXBContext jaxbContext;
        
        try {
            jaxbContext = JAXBContext.newInstance(ScheduleWorkXML.class);

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(schedule, sw);

            return (sw.toString());

        } catch (JAXBException ex) {
            Logger.getLogger(ScheduleDoctorResource.class.getName()).log(Level.SEVERE, null, ex);
            return ("error happened");
        }
    }
    
    
    @POST
    @Produces(MediaType.TEXT_HTML)
    @Path("updateSchedule/{doctorID}/{date}/{starttime}/{endtime}")
    public void  updateScheduleWork(@PathParam("doctorID") int doctorID, @PathParam("date") String date, @PathParam("starttime") String starttime, @PathParam("endtime") String endtime) 
    {
          
          
          ScheduleWork schedule = new ScheduleWork();
          schedule.UpdateInfo(1, doctorID, date, starttime, endtime);
          
          
    }
}
