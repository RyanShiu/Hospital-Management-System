/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.helper;

import java.util.ArrayList;

/**
 *
 * @author student
 */


public class AdminInfo {
    
    public int getAdminID(){
        return AdminID;
    }
    public String getUsername(){
        return username;
    }
    
    public String getPassword(){
        return password;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    
    private int AdminID;
    private String username;
    private String password;
            
    public AdminInfo(int AdminID, String username, String password){
        this.AdminID = AdminID;
        this.username = username;
        this.password = password;
    }
    
}
