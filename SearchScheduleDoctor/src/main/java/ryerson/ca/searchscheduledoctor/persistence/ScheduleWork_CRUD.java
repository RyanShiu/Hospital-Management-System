
package ryerson.ca.searchscheduledoctor.persistence;


import ryerson.ca.searchscheduledoctor.helper.ScheduleWorkAvailable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;


public class ScheduleWork_CRUD {
    private static Connection getCon(){
        Connection con=null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/HMS?autoReconnect=true&useSSL=false","root","student");
            System.out.println("Connection established.");
        } catch(Exception e){System.out.println(e);}
            return con;
    } 
    
    public static ArrayList<ScheduleWorkAvailable> getScheduleWorkList(){
        ArrayList<ScheduleWorkAvailable> swa= new ArrayList();
        
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
                swa.add(new ScheduleWorkAvailable(managerID, doctorID, date, startTime, endTime));
                
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
    public static Set<ScheduleWorkAvailable> searchForScheduleWorks(String query){
        Set<ScheduleWorkAvailable> swa= new HashSet<ScheduleWorkAvailable>();
        try{
            Connection con= getCon();
            
            String q = "select * from SCHEDULEWORK "
                    + "NATURAL JOIN DOCTOR WHERE fullName LIKE '%"+query+"%';";
                    System.out.println(q);
			PreparedStatement ps=con.prepareStatement(q);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				//been= new UserInfo();
				int managerID = rs.getInt("managerID");
                                int doctorID=rs.getInt("doctorID");
                                String date=rs.getString("date");
                                String startTime=rs.getString("starttime");
                                String endTime = rs.getString("endtime");
                                
                           
                                
                                swa.add(new ScheduleWorkAvailable(managerID, doctorID, date, startTime, endTime));
                                
                        }
			
			con.close();

		}catch(Exception e){System.out.println(e);}
            
        
        return swa;
        
    }
    
}
