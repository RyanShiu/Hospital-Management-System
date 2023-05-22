package ryerson.ca.deletepatient.endpoint;

import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

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

import ryerson.ca.deletepatient.business.DeletePatient;
import ryerson.ca.deletepatient.helper.PatientXML;

@Path("deletePatient")
public class DeletePatientResource {

    @Context
    private UriInfo context;

    public DeletePatientResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("patients")
    public String getXml() {
        DeletePatient patient = new DeletePatient();
        PatientXML patients = patient.getPatientInfo();
        
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(PatientXML.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(patients, sw);
            return (sw.toString());
        } catch (JAXBException ex) {
            Logger.getLogger(DeletePatientResource.class.getName()).log(Level.SEVERE, null, ex);
            return ("error happened");
        }
    }
    
    @POST
    @Produces(MediaType.TEXT_HTML)
    @Path("delete/{patientID}")
    public void deletePatient(@PathParam("patientID") int patientID) {
        DeletePatient patients = new DeletePatient();
        patients.DeleteInfo(patientID);
        
    }
}