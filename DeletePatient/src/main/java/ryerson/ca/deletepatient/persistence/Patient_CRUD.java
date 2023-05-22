package ryerson.ca.deletepatient.persistence;

import ryerson.ca.deletepatient.helper.PatientList;
import ryerson.ca.deletepatient.helper.PatientAvailable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Patient_CRUD {
    public static Connection getCon() throws ClassNotFoundException{
       Connection con=null;
     try{
         Class.forName("com.mysql.jdbc.Driver");
        String connection=System.getenv("DB_URL");
        //String connection ="localhost:3306";
         con=DriverManager.getConnection("jdbc:mysql://"+ connection + "/PATIENT_HMS?allowPublicKeyRetrieval=true&useSSL=false", "root", "student" );
        
         
         System.out.println("Connection established.");
     } catch(Exception e){ System.out.println(e);}
     return con;
     
    }
    
    public static PatientList getPatientList(){
        PatientList pa = new PatientList();
        
        try{
            Connection con=getCon();
            
            String q = "SELECT * FROM PATIENT";
            
            PreparedStatement ps=con.prepareStatement(q);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                String fullName=rs.getString("fullName");
                String address=rs.getString("address");
                String birthDate=rs.getString("dateofbirth");
                String phone = rs.getString("phoneNumber");
                String gender = rs.getString("gender");
                int patientID = rs.getInt("patientID");
                pa.addPatient(new PatientAvailable(fullName, address, birthDate, phone, gender, patientID));
                
            }
            con.close();
        }catch(Exception e){System.out.println(e);}
        if (pa.getPatientAvailable() == null)
        {
            System.out.println("GlenBOB");
        }
        else{
            System.out.println("wonk");
        }
        return pa;
    }
    
    public static void DeletePatient(int patientID){
        
        try{
            Connection con=getCon();
            
            String r = "DELETE FROM PATIENT WHERE patientID = ?";
            PreparedStatement ps=con.prepareStatement(r);
            ps.setInt(1, patientID);
            
            ps.executeUpdate();
            
        
            con.close();
    
        } catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    
}