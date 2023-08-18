/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Admin;

import static Admin.Main.f;
import Resources.ConnectionProvider;
import Resources.Receipt;
import Resources.name;

import java.awt.HeadlessException;
import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.sql.PreparedStatement;

/**
 *
 * @author kkk
 */
public class AdminDashboard extends javax.swing.JPanel {

    private final String EmailPattern = "^[a-zA-Z0-9]+[@]+[a-zA-Z0-9]+[.]+[a-zA-Z0-9]+$";
    private final String MobileNumberPattern = "^[0-9]*$";
    private final String SalaryPattern = "^\\d+$";
    public int CheckUserName = 0;
    private static final DecimalFormat df = new DecimalFormat("0.0");

    /**
     * Creates new form AdminDashboard
     */
    public AdminDashboard() {
        initComponents();

        lessquantityproductstxt.setText(String.valueOf(getLessQuantityProductsCount()));
        outofstockproductstxt.setText(String.valueOf(getOutOfStockProductsCount()));
        totalproductstxt.setText(String.valueOf(getTotalProductsCount()));
        totalcategoriestxt.setText(String.valueOf(getTotalCatgoriesCount()));
        totaldebttxt.setText(String.valueOf(getTotalDebt()));
        ShowUserProfile();
    }

    private void ShowUserProfile() {

        try {

            Connection con = ConnectionProvider.getcon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * From users where user_name  = '" + name.getThisUser() + "'");

            rs.next();
            usertypecombotxt.setText(rs.getString("type"));
            firstnametxt.setText(rs.getString("first_name"));
            lastnametxt.setText(rs.getString("last_name"));
            mobilenumbertxt.setText(rs.getString("mobile_number"));
            usernametxt.setText(rs.getString("user_name"));
            passwordtxt.setText(rs.getString("password"));
            emailtxt.setText(rs.getString("email"));
            salarytxt.setText(rs.getString("salary"));
            Iconlabel1.setVisible(true);
            Iconlabel2.setVisible(true);
            name.setThisUser(rs.getString("user_name"));

        } catch (NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private String getTotalDebt() {

        float a = 0f;

        try {

            Connection con = ConnectionProvider.getcon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * From distributors");

            while (rs.next()) {
                a += Float.parseFloat(rs.getString("balance"));
            }

        } catch (NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return df.format(a);

    }

    private int getTotalProductsCount() {

        int a = 0;

        try {

            Connection con = ConnectionProvider.getcon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * From products");

            while (rs.next()) {
                a++;
            }

        } catch (NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return a;
    }

    private int getTotalCatgoriesCount() {

        int a = 0;

        try {

            Connection con = ConnectionProvider.getcon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * From types");

            while (rs.next()) {
                a++;
            }

        } catch (NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return a;
    }

    private int getLessQuantityProductsCount() {

        int a = 0;

        try {

            Connection con = ConnectionProvider.getcon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * From products where packs < minimum_quantity");

            while (rs.next()) {
                if (Integer.parseInt(rs.getString("packs")) < Integer.parseInt(rs.getString("minimum_quantity"))) {
                    a++;
                }
            }

        } catch (NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return a;
    }

    private int getOutOfStockProductsCount() {

        int a = 0;

        try {

            Connection con = ConnectionProvider.getcon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * From products where packs < '1'");

            while (rs.next()) {
                if (Integer.parseInt(rs.getString("packs")) < 1) {
                    a++;
                }
            }

        } catch (NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return a;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        AdminR = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        lessquantityproductstxt = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        outofstockproductstxt = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        totalproductstxt = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButton15 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        totalcategoriestxt = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton16 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        totaldebttxt = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel20 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        firstnametxt = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        lastnametxt = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        mobilenumbertxt = new javax.swing.JTextField();
        emailtxt = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        salarytxt = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        passwordtxt = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        usernametxt = new javax.swing.JTextField();
        Iconlabel1 = new javax.swing.JLabel();
        Iconlabel2 = new javax.swing.JLabel();
        usertypecombotxt = new javax.swing.JTextField();
        printchoice = new javax.swing.JComboBox<>();
        jButton5 = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        AdminL = new javax.swing.JPanel();
        jButton9 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();

        AdminR.setBackground(new java.awt.Color(204, 255, 255));
        AdminR.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        AdminR.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setBackground(new java.awt.Color(204, 255, 255));
        jButton1.setForeground(new java.awt.Color(204, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close.png"))); // NOI18N
        jButton1.setAlignmentY(0.0F);
        jButton1.setBorder(null);
        jButton1.setOpaque(true);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        AdminR.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 0, 30, 30));

        jButton2.setBackground(new java.awt.Color(204, 255, 255));
        jButton2.setForeground(new java.awt.Color(204, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/minimize.png"))); // NOI18N
        jButton2.setAlignmentY(0.0F);
        jButton2.setBorder(null);
        jButton2.setOpaque(true);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        AdminR.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 0, 30, 30));

        jButton10.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton10.setIcon(new javax.swing.ImageIcon(System.getProperty("user.dir") + "\\src\\images\\history.png"));
        jButton10.setText("  HISTORY");
        jButton10.setAlignmentY(0.0F);
        jButton10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton10.setIconTextGap(5);
        jButton10.setPreferredSize(new java.awt.Dimension(202, 107));
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        AdminR.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(-270, 660, 260, 60));

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/less quantity.png"))); // NOI18N
        AdminR.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 450, 90, 80));

        lessquantityproductstxt.setFont(new java.awt.Font("Segoe UI", 0, 60)); // NOI18N
        lessquantityproductstxt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lessquantityproductstxt.setText("36");
        AdminR.add(lessquantityproductstxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 440, 160, 90));

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/out of stock.png"))); // NOI18N
        AdminR.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 450, 100, 80));

        outofstockproductstxt.setFont(new java.awt.Font("Segoe UI", 0, 60)); // NOI18N
        outofstockproductstxt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        outofstockproductstxt.setText("36");
        AdminR.add(outofstockproductstxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 440, 140, 90));

        jLabel7.setBackground(new java.awt.Color(255, 227, 153));
        jLabel7.setFont(new java.awt.Font("Segoe UI Semibold", 1, 20)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Products In Less Quantity");
        jLabel7.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        jLabel7.setOpaque(true);
        AdminR.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, 250, 150));

        jLabel6.setBackground(new java.awt.Color(255, 153, 153));
        jLabel6.setFont(new java.awt.Font("Segoe UI Semibold", 1, 20)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Out Of Stock Products");
        jLabel6.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        jLabel6.setOpaque(true);
        AdminR.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 400, 250, 150));

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/total products.png"))); // NOI18N
        AdminR.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 250, -1, 110));

        totalproductstxt.setBackground(new java.awt.Color(204, 204, 255));
        totalproductstxt.setFont(new java.awt.Font("Segoe UI", 0, 60)); // NOI18N
        totalproductstxt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalproductstxt.setText("36");
        AdminR.add(totalproductstxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 130, 90));

        jLabel8.setBackground(new java.awt.Color(204, 204, 255));
        jLabel8.setFont(new java.awt.Font("Segoe UI Semibold", 1, 20)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Total Products");
        jLabel8.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        jLabel8.setOpaque(true);
        AdminR.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 250, 150));

        jButton15.setBackground(new java.awt.Color(204, 255, 204));
        jButton15.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add product.png"))); // NOI18N
        jButton15.setText("<html>&ensp;&nbsp;Add<br>Products</html>");
        jButton15.setToolTipText("");
        jButton15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        AdminR.add(jButton15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 250, 150));

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/category.png"))); // NOI18N
        AdminR.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 260, 100, 90));

        totalcategoriestxt.setBackground(new java.awt.Color(204, 204, 255));
        totalcategoriestxt.setFont(new java.awt.Font("Segoe UI", 0, 60)); // NOI18N
        totalcategoriestxt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalcategoriestxt.setText("36");
        AdminR.add(totalcategoriestxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 260, 130, 80));

        jLabel9.setBackground(new java.awt.Color(0, 204, 204));
        jLabel9.setFont(new java.awt.Font("Segoe UI Semibold", 1, 20)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Total Categories");
        jLabel9.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        jLabel9.setOpaque(true);
        AdminR.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 220, 250, 150));

        jButton16.setBackground(new java.awt.Color(255, 255, 102));
        jButton16.setFont(new java.awt.Font("Segoe UI Semibold", 0, 21)); // NOI18N
        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Edit Products.png"))); // NOI18N
        jButton16.setText("<html>Change Product<br>&ensp;Distributors</html>");
        jButton16.setToolTipText("");
        jButton16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        AdminR.add(jButton16, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 40, 250, 150));

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/debt.png"))); // NOI18N
        AdminR.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 610, 100, 110));

        totaldebttxt.setBackground(new java.awt.Color(204, 204, 255));
        totaldebttxt.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        totaldebttxt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totaldebttxt.setText("36.00");
        AdminR.add(totaldebttxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 620, 160, 90));

        jLabel10.setBackground(new java.awt.Color(220, 27, 84));
        jLabel10.setFont(new java.awt.Font("Segoe UI Semibold", 1, 21)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Total Debt");
        jLabel10.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        jLabel10.setOpaque(true);
        AdminR.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 580, 270, 150));

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        AdminR.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 0, 10, 770));

        jSeparator3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        AdminR.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 10, 450, -1));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("MY PROFILE");
        AdminR.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 10, 360, 60));

        jSeparator2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        AdminR.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 70, 510, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel5.setText("USER TYPE");
        AdminR.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 110, 100, 30));

        jLabel16.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel16.setText("FIRST NAME");
        AdminR.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 180, 110, 30));

        firstnametxt.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        firstnametxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                firstnametxtActionPerformed(evt);
            }
        });
        AdminR.add(firstnametxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 180, 320, 30));

        jLabel17.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel17.setText("LAST NAME");
        AdminR.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 250, 120, 30));

        lastnametxt.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        lastnametxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastnametxtActionPerformed(evt);
            }
        });
        AdminR.add(lastnametxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 250, 320, 30));

        jLabel18.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel18.setText("MOBILE NUMBER");
        AdminR.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 320, 120, 30));

        mobilenumbertxt.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        mobilenumbertxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mobilenumbertxtActionPerformed(evt);
            }
        });
        mobilenumbertxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                mobilenumbertxtKeyReleased(evt);
            }
        });
        AdminR.add(mobilenumbertxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 320, 320, 30));

        emailtxt.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        emailtxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailtxtActionPerformed(evt);
            }
        });
        AdminR.add(emailtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 600, 320, 30));

        jButton4.setFont(new java.awt.Font("Segoe UI Semibold", 1, 15)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/save.png"))); // NOI18N
        jButton4.setText(" Save Changes");
        jButton4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        AdminR.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 670, 150, 30));

        jLabel19.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel19.setText("EMAIL");
        AdminR.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 600, 70, 30));

        jLabel21.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel21.setText("SALARY");
        AdminR.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 530, 80, 30));

        salarytxt.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        salarytxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salarytxtActionPerformed(evt);
            }
        });
        AdminR.add(salarytxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 530, 320, 30));

        jLabel22.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel22.setText("PASSWORD");
        AdminR.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 460, 100, 30));

        passwordtxt.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        passwordtxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordtxtActionPerformed(evt);
            }
        });
        passwordtxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                passwordtxtKeyReleased(evt);
            }
        });
        AdminR.add(passwordtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 460, 320, 30));

        jLabel23.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel23.setText("USER NAME");
        AdminR.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 390, 110, 30));

        usernametxt.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        usernametxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernametxtActionPerformed(evt);
            }
        });
        usernametxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                usernametxtKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                usernametxtKeyTyped(evt);
            }
        });
        AdminR.add(usernametxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 390, 320, 30));
        AdminR.add(Iconlabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 390, 30, 30));
        AdminR.add(Iconlabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 320, 30, 30));

        usertypecombotxt.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        usertypecombotxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usertypecombotxtActionPerformed(evt);
            }
        });
        AdminR.add(usertypecombotxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 110, 320, 30));

        printchoice.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Company wise", "Alphabatical order" }));
        printchoice.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        AdminR.add(printchoice, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 640, 130, 30));

        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/raven/print.png"))); // NOI18N
        jButton5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        AdminR.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 640, 60, 30));

        jLabel24.setBackground(new java.awt.Color(0, 0, 204));
        jLabel24.setFont(new java.awt.Font("Segoe UI Semibold", 1, 20)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("Print All Products");
        jLabel24.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        jLabel24.setOpaque(true);
        AdminR.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 580, 230, 120));

        AdminL.setBackground(new java.awt.Color(0, 0, 102));
        AdminL.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        AdminL.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton9.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/suppliers.png"))); // NOI18N
        jButton9.setText(" DISTRIBUTORS ");
        jButton9.setAlignmentY(0.0F);
        jButton9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton9.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jButton9.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton9.setIconTextGap(0);
        jButton9.setPreferredSize(new java.awt.Dimension(202, 107));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        AdminL.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 590, 260, 60));

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logout.png"))); // NOI18N
        jButton3.setText("  LOGOUT ");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        AdminL.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 723, 140, 40));

        jLabel4.setBackground(new java.awt.Color(0, 0, 51));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 178, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("ADMIN\n");
        AdminL.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, 180, 40));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/profile(2).png"))); // NOI18N
        AdminL.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 180, 190));

        jButton6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ITEMS.png"))); // NOI18N
        jButton6.setText(" PRODUCTS");
        jButton6.setAlignmentY(0.0F);
        jButton6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton6.setPreferredSize(new java.awt.Dimension(202, 107));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        AdminL.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 450, 260, 60));

        jButton7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pos.png"))); // NOI18N
        jButton7.setText("       POS     ");
        jButton7.setAlignmentX(0.5F);
        jButton7.setAlignmentY(0.0F);
        jButton7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton7.setPreferredSize(new java.awt.Dimension(202, 107));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        AdminL.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 260, 60));

        jButton8.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/report.png"))); // NOI18N
        jButton8.setText(" REPORTS");
        jButton8.setAlignmentY(0.0F);
        jButton8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton8.setPreferredSize(new java.awt.Dimension(202, 107));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        AdminL.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 520, 260, 60));

        jButton11.setBackground(new java.awt.Color(204, 255, 255));
        jButton11.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dashboard.png"))); // NOI18N
        jButton11.setText("    DASHBOARD");
        jButton11.setAlignmentY(0.0F);
        jButton11.setBorder(null);
        jButton11.setBorderPainted(false);
        jButton11.setPreferredSize(new java.awt.Dimension(202, 107));
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        AdminL.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 280, 60));

        jButton12.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/manage users.png"))); // NOI18N
        jButton12.setText("     USERS   ");
        jButton12.setAlignmentY(0.0F);
        jButton12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton12.setPreferredSize(new java.awt.Dimension(202, 107));
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        AdminL.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 260, 60));

        jButton13.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/history.png"))); // NOI18N
        jButton13.setText("  HISTORY");
        jButton13.setAlignmentY(0.0F);
        jButton13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton13.setIconTextGap(5);
        jButton13.setPreferredSize(new java.awt.Dimension(202, 107));
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        AdminL.add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 660, 260, 60));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1370, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(AdminL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(AdminR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 770, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(AdminL, javax.swing.GroupLayout.PREFERRED_SIZE, 770, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(AdminR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        f.getContentPane().removeAll();
        f.getContentPane().add(new ManageDistributors());
        f.revalidate();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        f.getContentPane().removeAll();
        f.getContentPane().add(new LoginPanel());
        f.revalidate();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        f.getContentPane().removeAll();
        f.getContentPane().add(new EditProducts());
        f.revalidate();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        f.getContentPane().removeAll();
        f.getContentPane().add(new PosPanel());
        f.revalidate();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        f.getContentPane().removeAll();
        f.getContentPane().add(new Reports());
        f.revalidate();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        f.getContentPane().removeAll();
        f.getContentPane().add(new ManageUsers());
        f.revalidate();
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        f.getContentPane().removeAll();
        f.getContentPane().add(new History());
        f.revalidate();
    }//GEN-LAST:event_jButton13ActionPerformed

    private void usertypecombotxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usertypecombotxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usertypecombotxtActionPerformed

    private void usernametxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usernametxtKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_usernametxtKeyTyped

    private void usernametxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usernametxtKeyReleased
        // TODO add your handling code here:
        String username = usernametxt.getText();

        if (!username.equals("")) {
            Iconlabel1.setVisible(true);
            Iconlabel1.setIcon(new ImageIcon("src\\images\\yes.png"));
            Iconlabel1.setText("");
            CheckUserName = 0;

            Connection myConn = null;
            Statement myStmt = null;
            ResultSet myRs = null;

            try {
                // 1. Get a connection to database
                myConn = ConnectionProvider.getcon();

                // 2. Create a statement
                myStmt = myConn.createStatement();

                // 3. Execute SQL query
                myRs = myStmt.executeQuery("select * from users where user_name = '" + username + "'");

                while (myRs.next()) {
                    CheckUserName = 1;
                    Iconlabel1.setIcon(new ImageIcon("src\\images\\no.png"));
                    Iconlabel1.setText("");
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        } else {
            Iconlabel1.setVisible(false);
        }
    }//GEN-LAST:event_usernametxtKeyReleased

    private void usernametxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernametxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernametxtActionPerformed

    private void passwordtxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordtxtKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordtxtKeyReleased

    private void passwordtxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordtxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordtxtActionPerformed

    private void salarytxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salarytxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_salarytxtActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        String user_type = usertypecombotxt.getText();
        String first_name = firstnametxt.getText();
        String last_name = lastnametxt.getText();
        String mobile_number = mobilenumbertxt.getText();
        String user_name = usernametxt.getText();
        String password = passwordtxt.getText();
        String email = emailtxt.getText();
        String salary = salarytxt.getText();

        Connection con;
        PreparedStatement stm;
        Statement st;
        ResultSet rs;

        if (first_name.equals("")) {
            JOptionPane.showMessageDialog(null, "First name cannot be empty!");
        } else if (last_name.equals("")) {
            JOptionPane.showMessageDialog(null, "Last name cannot be empty!");
        } else if (mobile_number.equals("")) {
            JOptionPane.showMessageDialog(null, "Mobile number cannot be empty!");
        } else if (!mobile_number.matches(MobileNumberPattern) || mobile_number.length() != 11) {
            JOptionPane.showMessageDialog(null, "Invalid mobile number!");
        } else if (user_name.equals("")) {
            JOptionPane.showMessageDialog(null, "Username cannot be empty!");
        } else if (password.equals("")) {
            JOptionPane.showMessageDialog(null, "Password cannot be empty!");
        } else if (!email.matches(EmailPattern) && !email.equals("")) {
            JOptionPane.showMessageDialog(null, "Invalid email pattern!");
        } else if (!salary.matches(SalaryPattern) && !(salary.equals(""))) {
            JOptionPane.showMessageDialog(null, "Invalid salary pattern!\nSalary should contain 'Integers' only.");
        } else if (CheckUserName == 1) {
            JOptionPane.showMessageDialog(null, "Username already exists!\nPlease enter another Username.");
        } else {

            try {
                con = ConnectionProvider.getcon();

                st = con.createStatement();
                rs = st.executeQuery("Select * From `pharmacy`.`users` Where user_name  = '" + name.getThisUser() + "'");
                rs.next();

                stm = con.prepareStatement("UPDATE `pharmacy`.`users` SET `type` = ? ,`first_name` = ? ,`last_name` = ? ,`user_name` = ? ,`password` = ? ,"
                        + "`mobile_number` = ? ,`email` = ? ,`salary` = ?  WHERE `user_name` = ? AND `id` = ? ;");

                stm.setString(1, user_type);
                stm.setString(2, first_name);
                stm.setString(3, last_name);
                stm.setString(4, user_name);
                stm.setString(5, password);
                stm.setString(6, mobile_number);
                stm.setString(7, email);
                stm.setString(8, salary);
                stm.setString(9, name.getThisUser());
                stm.setString(10, rs.getString("id"));

                stm.executeUpdate();

                name.setThisUser(user_name);

                JOptionPane.showMessageDialog(null, "Changes Saved successfully!");

            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void emailtxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailtxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailtxtActionPerformed

    private void mobilenumbertxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mobilenumbertxtKeyReleased
        // TODO add your handling code here:
        String mobile_number = mobilenumbertxt.getText();

        if (!mobile_number.equals("")) {
            Iconlabel2.setVisible(true);
            Iconlabel2.setIcon(new ImageIcon("src\\images\\no.png"));
            Iconlabel2.setText("");

            if (mobile_number.length() != 11) {
                Iconlabel2.setIcon(new ImageIcon("src\\images\\no.png"));
                Iconlabel2.setText("");
            } else {
                Iconlabel2.setIcon(new ImageIcon("src\\images\\yes.png"));
            }
        } else {
            Iconlabel2.setVisible(false);
        }
    }//GEN-LAST:event_mobilenumbertxtKeyReleased

    private void mobilenumbertxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mobilenumbertxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mobilenumbertxtActionPerformed

    private void lastnametxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastnametxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lastnametxtActionPerformed

    private void firstnametxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_firstnametxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_firstnametxtActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
        f.getContentPane().removeAll();
        f.getContentPane().add(new AssignProducts());
        f.revalidate();
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
        f.getContentPane().removeAll();
        f.getContentPane().add(new AddProducts());
        f.revalidate();
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        f.setExtendedState(JFrame.ICONIFIED);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int result = JOptionPane.showConfirmDialog(null, "Do you really want to close this application?", "Pharmacy Management System",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (result == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        int choice;
        if(printchoice.getSelectedItem().toString().equals("Company wise")){
            choice = 1;
        }
        else{
            choice = 2;
        }
        new Receipt(choice).setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AdminL;
    private javax.swing.JPanel AdminR;
    private javax.swing.JLabel Iconlabel1;
    private javax.swing.JLabel Iconlabel2;
    private javax.swing.JTextField emailtxt;
    private javax.swing.JTextField firstnametxt;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTextField lastnametxt;
    private javax.swing.JLabel lessquantityproductstxt;
    private javax.swing.JTextField mobilenumbertxt;
    private javax.swing.JLabel outofstockproductstxt;
    private javax.swing.JTextField passwordtxt;
    private javax.swing.JComboBox<String> printchoice;
    private javax.swing.JTextField salarytxt;
    private javax.swing.JLabel totalcategoriestxt;
    private javax.swing.JLabel totaldebttxt;
    private javax.swing.JLabel totalproductstxt;
    private javax.swing.JTextField usernametxt;
    private javax.swing.JTextField usertypecombotxt;
    // End of variables declaration//GEN-END:variables
}
