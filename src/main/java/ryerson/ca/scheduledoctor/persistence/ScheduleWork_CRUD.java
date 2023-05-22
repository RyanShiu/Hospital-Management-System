
package ryerson.ca.scheduledoctor.persistence;

import ryerson.ca.scheduledoctor.helper.ScheduleWorkList;
import ryerson.ca.scheduledoctor.helper.ScheduleWorkAvailable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;


public class ScheduleWork_CRUD {
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
    
    public static ScheduleWorkList getScheduleWorkList(){
        ScheduleWorkList swa = new ScheduleWorkList();
        
        try{
            Connection con=getCon();
            
            String q = "SELECT * FROM SCHEDULEWORK";
            
            PreparedStatement ps=con.prepareStatement(q);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                int managerID = rs.getInt("managerID");
                int doctorID=rs.getInt("doctorID");
                String date=rs.getString("date");
                String startTime=rs.getString("starttime");
                String endTime = rs.getString("endtime");
                swa.addScheduleWork(new ScheduleWorkAvailable(managerID, doctorID, date, startTime, endTime));
                
            }
            con.close();
        }catch(Exception e){System.out.println(e);}
           
        return swa;
    }
    public static void UpdateList(int adminID, int doctorID, String date, String startTime, String endTime){
        
        
        try{
            Connection con=getCon();
            
            String r = "INSERT INTO SCHEDULEWORK (managerID, doctorID, date, starttime, endtime) VALUES (?, ?, ?, ?, ?)";
            
            PreparedStatement ps=con.prepareStatement(r);
            ps.setInt(1, adminID);
            ps.setInt(2, doctorID);
            ps.setString(3, date);
            ps.setString(4, startTime);
            ps.setString(5, endTime);
            
            int rowsAffected=ps.executeUpdate();
            System.out.println(rowsAffected + " rows inserted.");
        
            con.close();
    
        } catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
    
}
