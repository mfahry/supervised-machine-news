/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author mfahry
 */
public class Database {
    String username;
    String password;
    Connection con;
    
    public Database(String username, String password) {
        this.username = username;
        this.password = password;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classification_detikcom",this.username,this.password); 
        } 
        
        catch (ClassNotFoundException | SQLException e) {
            System.out.print("error message :"+e);
        }
    }

    public Connection getCon() {
        return con;
    }
    
    
}
