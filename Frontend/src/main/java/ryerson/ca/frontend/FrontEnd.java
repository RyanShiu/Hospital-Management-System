/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.frontend;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.AbstractMap;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.NewCookie;
import ryerson.ca.business.Business;
import ryerson.ca.helper.ScheduleWorkXML;
import ryerson.ca.helper.PatientXML;

/**
 *
 * @author student
 */
@WebServlet(name = "FrontEnd", urlPatterns = {"/FrontEnd"})
public class FrontEnd extends HttpServlet {

    Authenticate autho;

    public FrontEnd() {
        autho = new Authenticate();
    }
    private final String authenticationCookieName = "login_token";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private Map.Entry<String, String> isAuthenticated(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String token = "";
        
        System.out.println("TOKEN IS");
        try {
            for (Cookie cookie : cookies) {
                System.out.println(cookie.getName());
                if (cookie.getName().equals(authenticationCookieName)) {
                    token = cookie.getValue();
                }
            }
        } catch (Exception e) {

        }
        if (!token.isEmpty())
           try {
            
            if (this.autho.verify(token).getKey()) {
                  Map.Entry entry= new  AbstractMap.SimpleEntry<String, String>
                             (token,this.autho.verify(token).getValue());
                return entry;

            } else {
                 Map.Entry entry= new  AbstractMap.SimpleEntry<String, String>("","");
            return entry;
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(FrontEnd.class.getName()).log(Level.SEVERE, null, ex);
        }

       Map.Entry entry= new  AbstractMap.SimpleEntry<String, String>("","");
            return entry;

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String token = isAuthenticated(request).getKey();
        String uname = isAuthenticated(request).getValue();
        String hiddenParam = request.getParameter("pageName");
        ScheduleWorkXML result;
        PatientXML result2;
        switch (hiddenParam) {
            case "login":
                String username = request.getParameter("username");
                String password = request.getParameter("password");
               
                System.out.println(username);
                System.out.println(password);
                boolean isAuthenticated = Business.isAuthenticated(username, password);
                
                System.out.println(isAuthenticated);
                if (isAuthenticated) {
                       
                    request.setAttribute("user", username);
                    //retrieveMessagingServiceFromBackend(username);
                    token = autho.createJWT("FrontEnd", username, 100000);
                    
                    Cookie newCookie = new Cookie(authenticationCookieName, token);
                    response.addCookie(newCookie);
                    RequestDispatcher requestDispatcher = request.
                            getRequestDispatcher("frontpageWithLogin.jsp");

                    requestDispatcher.forward(request, response);

                } else {
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("frontpageWithoutLogin.jsp");

                    requestDispatcher.forward(request, response);
                }
                break;
            case "schedule":

                
                int doctorID = Integer.parseInt(request.getParameter("doctorID"));
                String date = request.getParameter("date");
                String starttime = request.getParameter("starttime");
                String endtime = request.getParameter("endtime");
                
                if (token.isEmpty()) {
                    
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("frontpageWithoutLogin.jsp");

                    requestDispatcher.forward(request, response);
                    break;
                } else {
                    System.out.println(doctorID + " " + date + " " + starttime + " " + endtime);
                    retrieveUpdateScheduleServiceFromBackend(doctorID, date, starttime, endtime);
                    result = retrieveScheduleServicesFromBackend(token);
                   
                    request.setAttribute("scheduleWorkResults", result);

                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("ScheduleWork.jsp");

                    requestDispatcher.forward(request, response);
                }
                
                break;
            case "delete":

                
                int patientID = Integer.parseInt(request.getParameter("patientID"));
                
                
                if (token.isEmpty()) {
                    
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("frontpageWithoutLogin.jsp");

                    requestDispatcher.forward(request, response);
                    break;
                } else {
                    
                    retrieveDeletePatientServiceFromBackend(patientID);
                    result2 = retrieveDeleteServicesFromBackend(token);
                   
                    request.setAttribute("deleteResults", result2);
                    RequestDispatcher requestDispatcher = request.
                                getRequestDispatcher("DeletePatient.jsp");

                    requestDispatcher.forward(request, response);
                }
                
                break;
            case "scheduleWorkPage":
                
                result = retrieveScheduleServicesFromBackend(token);
                   
                request.setAttribute("scheduleWorkResults", result);
                RequestDispatcher requestDispatcher = request.
                            getRequestDispatcher("ScheduleWork.jsp");

                requestDispatcher.forward(request, response);
                
                
                break;
            case "deletePatientPage":
               
                result2 = retrieveDeleteServicesFromBackend(token);
                
                request.setAttribute("deleteResults", result2);
                requestDispatcher = request.
                            getRequestDispatcher("DeletePatient.jsp");

                requestDispatcher.forward(request, response);
                
                
                break;
                
                
                
                
        }

        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private ScheduleWorkXML retrieveScheduleServicesFromBackend(String token) {
        try {
            return (Business.getScheduleServices(token));
        } catch (IOException ex) {
            Logger.getLogger(FrontEnd.class.getName()).log(Level.SEVERE, null, ex);
            return (null);
        }

    }
    private PatientXML retrieveDeleteServicesFromBackend(String token) {
        try {
            return (Business.getPatientServices(token));
        } catch (IOException ex) {
            Logger.getLogger(FrontEnd.class.getName()).log(Level.SEVERE, null, ex);
            return (null);
        }

    }
    private void retrieveUpdateScheduleServiceFromBackend(int doctorID, String date, String starttime, String endtime){
        
        Business.getUpdateWorkService(doctorID, date, starttime, endtime);
        
    } 
    private void retrieveDeletePatientServiceFromBackend(int patientID){
        
        Business.getDeletePatientService(patientID);
        
    } 
    private void retrieveMessagingServiceFromBackend(String username){
        try {
            Business.Login(username);
        } catch (IOException e) {
            System.out.println("An IO exception occurred: " + e.getMessage());
            e.printStackTrace();
        }
        
    } 
    

}
