<%-- 
    Document   : ScheduleWork
    Created on : Apr 9, 2023, 2:52:01 AM
    Author     : student
--%>

<%@page import="java.util.Arrays"%>
<%@page import="ryerson.ca.helper.ScheduleWorkAvailable"%>
<%@page import="ryerson.ca.helper.ScheduleWorkXML"%>
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
            Schedule Work

        </div>

        <div style="padding-left:16px">
            <h2>Schedule Work</h2> 
        </div>
        <div style="padding-left:16px">
            <p></p>
            <form action="FrontEnd" method="POST"> 
                <input type="hidden" name="pageName" value="schedule"/>
                <table>
                    <tr><th></th><th>Doctor ID</th><th>Date</th><th>Start Time</th><th>End Time</th></tr>
                            <%  ScheduleWorkXML scheduleWorks = (ScheduleWorkXML) request.getAttribute("scheduleWorkResults");
                            if(scheduleWorks.getScheduleWorks()!=null){
                                int i=0;   
                                for (ScheduleWorkAvailable scheduleWork : scheduleWorks.getScheduleWorks()) {
                                    i++;%>
                        <tr>    
                                <td><%=i%></td>
                                <td><%=scheduleWork.getDoctorID()%></td>
                                <td><%=scheduleWork.getDate()%></td>
                                <td><%=scheduleWork.getStartTime()%></td>
                                <td><%=scheduleWork.getEndTime()%></td>
                        <%      }
                            }

                        %>
                        </tr>
                </table>
                <div style="padding-left:16px">
                    <h3>When entering the date please be sure to put DD-MM-YYYY instead of DD/MM/YYYY</h3> 
                </div>
                    <input type="text" id="doctorID" name="doctorID">
                    <input type="text" id="date" name="date">
                    <input type="text" id="starttime" name="starttime">
                    <input type="text" id="endtime" name="endtime">
           
                    <button type="submit">create</button>
                
            </form>
        
    </div>
</body>
