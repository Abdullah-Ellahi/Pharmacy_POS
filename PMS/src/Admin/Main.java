/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Admin;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author kkk
 */
public class Main {

    public static JFrame f = null;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        f = new JFrame();        
        f.setTitle("Pharmacy Management System"); 
        f.setUndecorated(true); 
       
        Toolkit tk = Toolkit.getDefaultToolkit(); //Initializing the Toolkit class.
        Dimension screenSize = tk.getScreenSize(); //Get the Screen resolution of our device.
        f.setSize(screenSize.width,screenSize.height); //Set the width and height of the JFrame.
    
        f.getContentPane().add(new LoginPanel());
        f.setVisible(true);
    }
}
