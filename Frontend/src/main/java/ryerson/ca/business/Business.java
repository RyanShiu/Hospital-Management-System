/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.business;

import io.kubemq.sdk.basic.ServerAddressNotSuppliedException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.servlet.RequestDispatcher;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.apache.commons.io.IOUtils;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import ryerson.ca.helper.ScheduleWorkAvailable;
import ryerson.ca.helper.ScheduleWorkXML;
import ryerson.ca.helper.PatientAvailable;
import ryerson.ca.helper.PatientXML;
import ryerson.ca.helper.AdminInfo;
import ryerson.ca.persistence.Admin_CRUD;

/**
 *
 * @author student
 */
public class Business {

    public static boolean isAuthenticated(String username, String password) {
       
       AdminInfo Ainfo= getAdminInfo(username, password);
       
       if (Ainfo != null){
           return true;
       }
       return false;
      
    }
    
    
    private static AdminInfo getAdminInfo(String username, String password) {
        return Admin_CRUD.read(username, password);
    }
    public static void Login(String username) throws IOException {
        
        
            
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
             LocalDate date = LocalDate.now();
        
    

            Messaging.sendmessage("LOGIN:"+ username + ":" +date.format(formatter));
            
            
        
        
    }
    
    public static ScheduleWorkXML getScheduleServices(String token) throws IOException {
        
        Client searchclient = ClientBuilder.newClient();
        String scheduleService= System.getenv("scheduleService");
        //String scheduleService= "localhost:8080";
        WebTarget searchwebTarget
                = searchclient.target("http://"+ scheduleService + "/ScheduleDoctor/webresources/scheduleDoctor/schedule");
        InputStream is
                = searchwebTarget.path("").request(MediaType.APPLICATION_XML).get(InputStream.class);
        String xml = IOUtils.toString(is, "utf-8");
        ScheduleWorkXML scheduleWork = scheduleworkxmltoObjects(xml);
        //System.out.println(scheduleWork.getScheduleWorks().get(0).getDoctorID());
        
     /* if (token != null) {
            Client holdclient = ClientBuilder.newClient();
            WebTarget holdwebTarget
                    = holdclient.target("http://localhost:8080/HoldBook/webresources/hold/isOnHold");
            for (Book book : books.getBooks()) {

                InputStream holddata
                        = holdwebTarget.path(book.getIsbn()).queryParam("token", token).
                                request(MediaType.APPLICATION_XML).get(InputStream.class);
                try{
                    Book a=bookholdxmltoObjects(IOUtils.toString(holddata, "utf-8"));
                    if(a!=null)
                        book.setTobeHold(true);
                    else
                        book.setTobeHold(false);
                }
                catch(Exception e){
                    book.setTobeHold(false);
                }
                
                
            }
        }
*/

        return (scheduleWork);

    }
    public static PatientXML getPatientServices(String token) throws IOException {
        
        Client searchclient = ClientBuilder.newClient();
        String patientService= System.getenv("patientService");
        //String patientService= "localhost:8080";
        WebTarget searchwebTarget
                = searchclient.target("http://" + patientService + "/DeletePatient/webresources/deletePatient/patients");
        InputStream is
                = searchwebTarget.path("").request(MediaType.APPLICATION_XML).get(InputStream.class);
        String xml = IOUtils.toString(is, "utf-8");
        PatientXML patient = patientxmltoObjects(xml);
        //System.out.println(scheduleWork.getScheduleWorks().get(0).getDoctorID());
        
     /* if (token != null) {
            Client holdclient = ClientBuilder.newClient();
            WebTarget holdwebTarget
                    = holdclient.target("http://localhost:8080/HoldBook/webresources/hold/isOnHold");
            for (Book book : books.getBooks()) {

                InputStream holddata
                        = holdwebTarget.path(book.getIsbn()).queryParam("token", token).
                                request(MediaType.APPLICATION_XML).get(InputStream.class);
                try{
                    Book a=bookholdxmltoObjects(IOUtils.toString(holddata, "utf-8"));
                    if(a!=null)
                        book.setTobeHold(true);
                    else
                        book.setTobeHold(false);
                }
                catch(Exception e){
                    book.setTobeHold(false);
                }
                
                
            }
        }
*/

        return (patient);

    }
    
    public static void getUpdateWorkService(int doctorID, String date, String starttime, String endtime) {
        Client client = ClientBuilder.newClient();
        String scheduleService= System.getenv("scheduleService");
        //String scheduleService= "localhost:8080";
        WebTarget webTarget = client.target("http://" + scheduleService + "/ScheduleDoctor/webresources/scheduleDoctor/updateSchedule/{doctorID}/{date}/{starttime}/{endtime}")
                .resolveTemplate("doctorID", doctorID)
                .resolveTemplate("date", date)
                .resolveTemplate("starttime", starttime)
                .resolveTemplate("endtime", endtime);
        Response response = webTarget.request().post(Entity.entity(null, MediaType.TEXT_HTML));
        // Process the response
        // ...
        client.close();
    }  
    public static void getDeletePatientService(int patientID) {
        Client client = ClientBuilder.newClient();
        String patientService= System.getenv("patientService");
        //String patientService= "localhost:8080";
        WebTarget webTarget = client.target("http://" + patientService + "/DeletePatient/webresources/deletePatient/delete/{patientID}")
                .resolveTemplate("patientID", patientID);
                
        Response response = webTarget.request().post(Entity.entity(null, MediaType.TEXT_HTML));
        // Process the response
        // ...
        client.close();
    }  
    private static ScheduleWorkXML scheduleworkxmltoObjects(String xml) {
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(ScheduleWorkXML.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            ScheduleWorkXML schedulework = (ScheduleWorkXML) jaxbUnmarshaller.unmarshal(new StringReader(xml));
            return schedulework;

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
    private static PatientXML patientxmltoObjects(String xml) {
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(PatientXML.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            PatientXML patient = (PatientXML) jaxbUnmarshaller.unmarshal(new StringReader(xml));
            return patient;

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
/*
    private static Book bookholdxmltoObjects(String xml) {
        if(xml.isEmpty())
            return null;
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(Book.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            Book book = (Book) jaxbUnmarshaller.unmarshal(new StringReader(xml));
            return book;

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
*/
}
