/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OnTimeDatabaseConnector;

import java.io.*;
import java.io.IOException;
import java.util.Scanner;


public class User {
    
    private int id;
    private String username;
    private String email;
    private String password;
    private int[] contactIDs;
    private boolean isAdmin;

    public User() 
    {}
    
    public User(String username, String email, String password)
    {
        this.username = username;
        this.email = email;
        this.password = password;
        this.isAdmin = false;
        int[] temp = new int[1];
        temp[0] = 0;
        this.contactIDs = temp;
    }

    public User(int id, String username, String email, String password, String friendIDs, int isadmin) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        
        String[] temp1 = friendIDs.split(",");
        int[] temp2 = new int[temp1.length];
        for(int i = 0;i<temp1.length;i++)
        {
            temp2[i] = Integer.parseInt(temp1[i]);
        }
        this.contactIDs = temp2;
        
        switch(isadmin)
        {
            case 0: this.isAdmin = false; break;
            case 1: this.isAdmin = true; break;
            default: this.isAdmin = false; break;
        }
    }

    public void print()
    {
        System.out.println(this.id+" "+this.username+" "+this.email+" "+this.password+" "+this.contactIDs[0]+" "+this.isAdmin);
    }
    
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int[] getContactIDs() {
        return contactIDs;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setContactIDs(int[] contactIDs) {
        this.contactIDs = contactIDs;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    
}




