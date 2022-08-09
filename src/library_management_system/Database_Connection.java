/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package library_management_system;

import java.sql.*;

/**
 *
 * @author Rajat
 */
public class Database_Connection {
    
    static Connection con = null;
    
    public static Connection getConnection(){
        try {
             Class.forName("com.mysql.jdbc.Driver");
             con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_data","root","qwerty@123");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
        
    }
}
