<%-- 
    Document   : ScheduleWork
    Created on : Apr 9, 2023, 2:52:01 AM
    Author     : student
--%>

<%@page import="java.util.Arrays"%>
<%@page import="ryerson.ca.helper.PatientAvailable"%>
<%@page import="ryerson.ca.helper.PatientXML"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <style>
            * {box-sizing: border-box;}

            body {
                margin: 0;
                font-family: Arial, Helvetica, sans-serif;
            }

            .topnav {
                overflow: hidden;
                background-color: #e9e9e9;
            }

            .topnav a {
                float: left;
                display: block;
                color: black;
                text-align: center;
                padding: 14px 16px;
                text-decoration: none;
                font-size: 17px;
            }

            .topnav a:hover {
                background-color: #ddd;
                color: black;
            }

            .topnav a.active {
                background-color: #2196F3;
                color: white;
            }

            .topnav .login-container {
                float: right;
            }

            .topnav input[type=text] {
                padding: 6px;
                margin-top: 8px;
                font-size: 17px;
                border: none;
                width:120px;
            }

            .topnav .login-container button {
                float: right;
                padding: 6px 10px;
                margin-top: 8px;
                margin-right: 16px;
                background-color: #555;
                color: white;
                font-size: 17px;
                border: none;
                cursor: pointer;
            }

            .topnav .login-container button:hover {
                background-color: green;
            }

            @media screen and (max-width: 600px) {
                .topnav .login-container {
                    float: none;
                }
                .topnav a, .topnav input[type=text], .topnav .login-container button {
                    float: none;
                    display: block;
                    text-align: left;
                    width: 100%;
                    margin: 0;
                    padding: 14px;
                }
                .topnav input[type=text] {
                    border: 1px solid #ccc;  
                }
            }
       
table {
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 60%;
}

td, th {
  border: 1px solid #dddddd;
  text-align: left;
  padding: 15px;
}

tr:nth-child(even) {
  background-color: #dddddd;
}

        </style>
    </head>
    <body>

        <div class="topnav">
            Delete Patient

        </div>

        <div style="padding-left:16px">
            <h2>Delete Patient</h2> 
        </div>
        <div style="padding-left:16px">
            <p></p>
            
                <table>
                    <tr><th></th><th>Full Name</th><th>Patient ID</th><th>Address</th><th>Gender</th><th>Date Of Birth</th><th>Phone Number</th><th>Delete</th></tr>
                            <%  PatientXML patients = (PatientXML) request.getAttribute("deleteResults");
                            if(patients.getPatients()!=null){
                                int i=0;   
                                for (PatientAvailable patient : patients.getPatients()) {
                                    i++;%>
                 
                    <tr>            <td> <%=i%></td>
                                    <td> <%=patient.getPatientName()%> </td>
                                    <td> <%=patient.getPatientID()%></td>
                                    <td> <%=patient.getAddress()%></td>
                                    <td> <%=patient.getGender()%> </td>
                                    <td> <%=patient.getDateOfBirth()%> </td>
                                    <td> <%=patient.getPhoneNumber()%> </td>
                                    <td>
                                        <form action="FrontEnd" method="POST"> 
                                            <input type="hidden" name="pageName" value="delete"/>
                                            <input type="hidden" name="patientID" value="<%=patient.getPatientID()%>">
                                            <input type="submit" value="Delete Patient">
                                        </form>                                        
                                    </td>
                                    
                        <%      }
                            }

                        %>
                        </tr>
                </table>
                
                    
                
           
        
    </div>
</body>
