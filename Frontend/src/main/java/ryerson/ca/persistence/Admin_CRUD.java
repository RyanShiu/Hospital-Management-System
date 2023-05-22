package ryerson.ca.persistence;

import ryerson.ca.helper.AdminInfo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Admin_CRUD {
    public static Connection getCon() throws ClassNotFoundException{
       Connection con=null;
     try{
         Class.forName("com.mysql.jdbc.Driver");
        String connection=System.getenv("DB_URL");
        //String connection ="localhost:3306";
         con=DriverManager.getConnection("jdbc:mysql://"+connection+"/MANAGER_HMS?allowPublicKeyRetrieval=true&useSSL=false", "root", "student" );
        
         
         System.out.println("Connection established.");
     }
     catch(Exception e){ System.out.println(e);}
     return con;
     
    }
    
    public static AdminInfo read(String username, String password){
        AdminInfo bean=null;
        
        try{
            Connection con=getCon();
            
            String q = "select * from MANAGER WHERE username = '" + username + "';";
            
            PreparedStatement ps=con.prepareStatement(q);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                int workID = rs.getInt("managerID");
                String pass=rs.getString("password");
                if(pass.equals(password)){
                    bean = new AdminInfo(workID, username, pass);
                }
            }
            
            con.close();
        }catch(Exception e){System.out.println(e);}
           
        return bean;
    }
};
