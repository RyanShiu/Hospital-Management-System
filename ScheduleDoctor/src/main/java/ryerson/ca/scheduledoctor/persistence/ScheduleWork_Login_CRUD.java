/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.scheduledoctor.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author student
 */
public class ScheduleWork_Login_CRUD {
    public static Connection getCon() throws ClassNotFoundException{
       Connection con=null;
     try{
         Class.forName("com.mysql.jdbc.Driver");
        String connection=System.getenv("DB_URL");
        //String connection ="localhost:3306";
         con=DriverManager.getConnection("jdbc:mysql://"+connection+"/SCHEDULEWORK_HMS?allowPublicKeyRetrieval=true&useSSL=false", "root", "student" );
        
         
         System.out.println("Connection established.");
     }
     catch(Exception e){ System.out.println(e);}
     return con;
     
    }
    public static void addUsername(String username, String date) throws ClassNotFoundException, SQLException{
      
        
            Connection con= getCon();
          
            String q = "insert into SCHEDULEWORK_MANAGER "
                    + "(username, dateSent) values "
                    + "("+
                    "'"+username+"'" + ","
                    +"'"+date+"'"
                    +"');";
            Statement stmt = con.createStatement(); 
           
            stmt.execute(q);
			con.close();
                        

		 
 
        
    }
}
