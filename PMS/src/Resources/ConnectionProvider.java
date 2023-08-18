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
            String url = "jdbc:mysql://localhost:3306/pharmacy";
            String user = "pharmacist";
            String pass = "pharmacist";
            Connection con;
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pharmacy", "pharmacist" , "pharmacist");
            return con;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
    }
}