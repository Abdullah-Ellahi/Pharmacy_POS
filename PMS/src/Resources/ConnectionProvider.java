package Resources;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.sql.Connection;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author kkk
 */
public class ConnectionProvider {
    public static Connection getcon(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch(ClassNotFoundException c){
            JOptionPane.showMessageDialog(null, c.getMessage());
        }

        try{
            // changed the credentials to local database for security reasons!
 
            String url = "jdbc:mysql://localhost:3306/pharmacy";
            String user = "pharmacist";
            String pass = "pharmacist";
            Connection con;
            con = DriverManager.getConnection(url, user, pass);
            return con;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
    }
}
