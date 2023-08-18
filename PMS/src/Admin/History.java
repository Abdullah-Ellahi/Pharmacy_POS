/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Admin;

import static Admin.Main.f;
import PrintRaven.PrintTableActionCellEditor;
import PrintRaven.PrintTableActionCellRender;
import PrintRaven.PrintTableActionEvent;
import Resources.ConnectionProvider;
import Resources.Receipt;

import java.awt.Dimension;
import java.awt.HeadlessException;
import javax.swing.*;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kkk
 */
public class History extends javax.swing.JPanel {

    public String IntegerPattern = "^\\d+$";
    public String DecimalPattern = "^[1-9]\\d*([\\,\\.]\\d{2})?$";//for decimal values upto two places

    /**
     * Creates new form History
     */
    public History() {
        Toolkit tk = Toolkit.getDefaultToolkit(); //Initializing the Toolkit class.
        Dimension screenSize = tk.getScreenSize(); //Get the Screen resolution of our device.
        this.setSize(screenSize.width, screenSize.height); //Set the width and height of the JFrame.

        initComponents();

        PrintTableActionEvent event;
        event = new PrintTableActionEvent() {

            @Override
            public void onEdit(int row) {

                int index = jTable1.getSelectedRow();

                DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

                String packs = (String) model.getValueAt(index, 5);
                String loose = (String) model.getValueAt(index, 6);
                String product_price = (String) model.getValueAt(index, 11);
                String discounted_price = (String) model.getValueAt(index, 12);

                packstxt.setText(packs);
                loosetxt.setText(loose);
                pricetxt.setText(product_price);
                discountedpricetxt.setText(discounted_price);
            }

            @Override
            public void onPrint(int row) {

                int index = jTable1.getSelectedRow();

                DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                String bill_id = model.getValueAt(index, 0).toString();
                String shift = model.getValueAt(index, 1).toString();

                new Receipt(bill_id, shift, jTable1.getModel()).setVisible(true);
            }

            @Override
            public void onDelete(int row) {
                if (jTable1.isEditing()) {
                    jTable1.getCellEditor().stopCellEditing();

                }
                int index = jTable1.getSelectedRow();

                DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                String bill_id = model.getValueAt(index, 0).toString();
                String shift = model.getValueAt(index, 1).toString();
                String name = model.getValueAt(index, 2).toString();
                String packs = model.getValueAt(index, 5).toString();
                String loose = model.getValueAt(index, 6).toString();

                int result = JOptionPane.showConfirmDialog(null, "Do you really want to delete the transaction with\n Bill-Id " + bill_id,
                        "Pharmacy Management System", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (result == JOptionPane.YES_OPTION) {

                    Connection con;
                    PreparedStatement stm;

                    try {
                        con = ConnectionProvider.getcon();
                        stm = con.prepareStatement("DELETE FROM `pharmacy`.`transactions` WHERE (`bill_id` = '" + bill_id + "')"
                                + "and (`shift` = '" + shift + "');");
                        stm.execute();

                        model.removeRow(row);

                        Statement st = con.createStatement();
                        ResultSet rs = st.executeQuery("Select * From `pharmacy`.`products` WHERE name = '" + name + "'");

                        rs.next();
                        int old_packs = Integer.parseInt(rs.getString("packs"));
                        int old_loose = Integer.parseInt(rs.getString("loose"));
                        int quantity_in_pack = Integer.parseInt(rs.getString("quantity_in_packs"));

                        int new_packs = old_packs + Integer.parseInt(packs);
                        int new_loose = old_loose + Integer.parseInt(loose);

                        if (new_loose >= quantity_in_pack) {
                            new_packs++;
                            new_loose -= quantity_in_pack;
                        }

                        stm = con.prepareStatement("UPDATE `pharmacy`.`products` SET `packs` = '" + new_packs + "', `loose` = '" + new_loose + "'"
                                + " WHERE (`name` = '" + name + "');");
                        stm.execute();

                        JOptionPane.showMessageDialog(null, "Product deleted successfully!");

                    } catch (HeadlessException | NumberFormatException | SQLException e) {
                        JOptionPane.showMessageDialog(null, "Error"+e.getMessage());
                    }
                }
            }
        };

        jTable1.getColumnModel().getColumn(13).setCellRenderer(new PrintTableActionCellRender());
        jTable1.getColumnModel().getColumn(13).setCellEditor(new PrintTableActionCellEditor(event));
        jTable1ComponentShown();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        HistoryL = new javax.swing.JPanel();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        HistoryR = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        searchcombo = new javax.swing.JComboBox<>();
        searchtxt = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel20 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        packstxt = new javax.swing.JTextField();
        loosetxt = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        pricetxt = new javax.swing.JTextField();
        loosetxt1 = new javax.swing.JLabel();
        discountedpricetxt = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();

        HistoryL.setBackground(new java.awt.Color(0, 0, 102));
        HistoryL.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        HistoryL.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        HistoryL.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 260, 60));

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
        HistoryL.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 260, 60));

        jButton6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ITEMS.png"))); // NOI18N
        jButton6.setText("  PRODUCTS");
        jButton6.setAlignmentY(0.0F);
        jButton6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton6.setPreferredSize(new java.awt.Dimension(202, 107));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        HistoryL.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 450, 260, 60));

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logout.png"))); // NOI18N
        jButton3.setText("  LOGOUT ");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        HistoryL.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 723, 140, 40));

        jButton10.setBackground(new java.awt.Color(204, 255, 255));
        jButton10.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/history.png"))); // NOI18N
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
        HistoryL.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 660, 280, 60));

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
        HistoryL.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 260, 60));

        jLabel4.setBackground(new java.awt.Color(0, 0, 51));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 178, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("ADMIN\n");
        HistoryL.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, 180, 40));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/profile(2).png"))); // NOI18N
        HistoryL.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 180, 190));

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
        HistoryL.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 520, 260, 60));

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
        HistoryL.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 590, 260, 60));

        HistoryR.setBackground(new java.awt.Color(204, 255, 255));
        HistoryR.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        HistoryR.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        HistoryR.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 0, 30, 30));

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
        HistoryR.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 0, 30, 30));

        jLabel5.setBackground(new java.awt.Color(204, 255, 255));
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel5.setText("Search by  ");
        jLabel5.setAlignmentX(0.5F);
        jLabel5.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jLabel5.setOpaque(true);
        HistoryR.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 90, 30));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Bill-Id", "Shift", "Name", "Formula", "Type", "Packs", "Loose", "Quantity In Pack", "Price of Pack", "Price Of Loose", "DIscount Rate", "Product Price", "Discounted Price", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setName(""); // NOI18N
        jTable1.setRowHeight(40);
        jTable1.setSelectionBackground(new java.awt.Color(0, 195, 210));
        jTable1.setShowGrid(false);
        jTable1.setShowHorizontalLines(true);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jTable1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jTable1ComponentShown(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(120);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(30);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(30);
            jTable1.getColumnModel().getColumn(6).setPreferredWidth(30);
            jTable1.getColumnModel().getColumn(7).setPreferredWidth(50);
            jTable1.getColumnModel().getColumn(8).setPreferredWidth(70);
            jTable1.getColumnModel().getColumn(9).setPreferredWidth(70);
            jTable1.getColumnModel().getColumn(10).setPreferredWidth(70);
            jTable1.getColumnModel().getColumn(11).setPreferredWidth(70);
            jTable1.getColumnModel().getColumn(12).setPreferredWidth(70);
        }

        HistoryR.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 1070, 550));

        searchcombo.setBackground(new java.awt.Color(204, 255, 255));
        searchcombo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        searchcombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bill-Id", "Name", "Formula", "Type", "Discount Rate" }));
        searchcombo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        searchcombo.setOpaque(true);
        searchcombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchcomboActionPerformed(evt);
            }
        });
        HistoryR.add(searchcombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 150, 30));

        searchtxt.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        searchtxt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        searchtxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchtxtActionPerformed(evt);
            }
        });
        searchtxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchtxtKeyReleased(evt);
            }
        });
        HistoryR.add(searchtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 80, 760, 30));

        searchButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search x30.png"))); // NOI18N
        searchButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });
        HistoryR.add(searchButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 80, 60, 30));

        jSeparator3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        HistoryR.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 1030, -1));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 44)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("TRANSACTION HISTORY");
        HistoryR.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 10, 540, 50));

        jSeparator2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        HistoryR.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 1090, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Packs");
        HistoryR.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 700, 50, 20));

        packstxt.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        packstxt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        packstxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                packstxtKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                packstxtKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                packstxtKeyTyped(evt);
            }
        });
        HistoryR.add(packstxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 720, 130, 30));

        loosetxt.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        loosetxt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        loosetxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loosetxtActionPerformed(evt);
            }
        });
        loosetxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                loosetxtKeyReleased(evt);
            }
        });
        HistoryR.add(loosetxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 720, 130, 30));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Price");
        HistoryR.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 700, 50, 20));

        pricetxt.setEditable(false);
        pricetxt.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        pricetxt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        HistoryR.add(pricetxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 720, 130, 30));

        loosetxt1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        loosetxt1.setText("Discounted Price");
        HistoryR.add(loosetxt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 700, 120, 20));

        discountedpricetxt.setEditable(false);
        discountedpricetxt.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        discountedpricetxt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        discountedpricetxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                discountedpricetxtActionPerformed(evt);
            }
        });
        HistoryR.add(discountedpricetxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 720, 130, 30));

        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/save.png"))); // NOI18N
        jButton5.setText(" Save Changes");
        jButton5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        HistoryR.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 710, 150, 40));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Loose");
        HistoryR.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 700, 50, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1370, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(HistoryL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(HistoryR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 770, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(HistoryL, javax.swing.GroupLayout.PREFERRED_SIZE, 770, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(HistoryR, javax.swing.GroupLayout.PREFERRED_SIZE, 770, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        f.getContentPane().removeAll();
        f.getContentPane().add(new AdminDashboard());
        f.revalidate();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        f.getContentPane().removeAll();
        f.getContentPane().add(new ManageUsers());
        f.revalidate();
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        f.getContentPane().removeAll();
        f.getContentPane().add(new EditProducts());
        f.revalidate();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        f.getContentPane().removeAll();
        f.getContentPane().add(new LoginPanel());
        f.revalidate();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

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

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        f.getContentPane().removeAll();
        f.getContentPane().add(new ManageDistributors());
        f.revalidate();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int result = JOptionPane.showConfirmDialog(null, "Do you really want to close this application?", "Pharmacy Management System",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (result == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        f.setExtendedState(JFrame.ICONIFIED);
    }//GEN-LAST:event_jButton2ActionPerformed

    @SuppressWarnings("null")
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int index = jTable1.getSelectedRow();

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

        String packs = (String) model.getValueAt(index, 5);
        String loose = (String) model.getValueAt(index, 6);
        String product_price = (String) model.getValueAt(index, 11);
        String discounted_price = (String) model.getValueAt(index, 12);

        if (packs.equals("0") || packs == null) {
            packstxt.setText("0");
        } else {
            packstxt.setText(packs);
        }

        if (loose.equals("0") || loose == null) {
            loosetxt.setText("0");
        } else {
            loosetxt.setText(loose);
        }

        pricetxt.setText(product_price);
        discountedpricetxt.setText(discounted_price);
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable1ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jTable1ComponentShown
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

        try {
            for (int i = jTable1.getRowCount() - 1; i >= 0; i--) {
                model.removeRow(i);
            }

            Connection con = ConnectionProvider.getcon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM pharmacy.transactions ORDER BY id DESC");

            while (rs.next()) {
                model.addRow(new Object[]{rs.getString("bill_id"), rs.getString("shift"), rs.getString("name"), rs.getString("formula"),
                    rs.getString("type"), rs.getString("packs"), rs.getString("loose"), rs.getString("quantity_in_pack"),
                    rs.getString("price_of_pack"), rs.getString("price_of_loose"), rs.getString("discount_rate"),
                    rs.getString("product_price"), rs.getString("discounted_price")});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_jTable1ComponentShown

    private void jTable1ComponentShown() {
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

        try {
            for (int i = jTable1.getRowCount() - 1; i >= 0; i--) {
                model.removeRow(i);
            }

            Connection con = ConnectionProvider.getcon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM pharmacy.transactions ORDER BY id DESC");

            while (rs.next()) {
                model.addRow(new Object[]{rs.getString("bill_id"), rs.getString("shift"), rs.getString("name"), rs.getString("formula"),
                    rs.getString("type"), rs.getString("packs"), rs.getString("loose"), rs.getString("quantity_in_pack"),
                    rs.getString("price_of_pack"), rs.getString("price_of_loose"), rs.getString("discount_rate"),
                    rs.getString("product_price"), rs.getString("discounted_price")});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private void searchcomboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchcomboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchcomboActionPerformed

    private void searchtxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchtxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchtxtActionPerformed

    private void searchtxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchtxtKeyReleased
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

        try {
            for (int i = jTable1.getRowCount() - 1; i >= 0; i--) {
                model.removeRow(i);
            }

            String selected = (String) searchcombo.getSelectedItem();
            selected = switch (selected) {
                case "Bill-Id" ->
                    "bill_id";
                case "Name" ->
                    "name";
                case "Formula" ->
                    "formula";
                case "Type" ->
                    "type";
                case "Discount Rate" ->
                    "discount_rate";
                default ->
                    "";
            };

            Connection con = ConnectionProvider.getcon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * From transactions;");

            while (rs.next()) {
                String selected_object = rs.getString(selected);
                Boolean bool = (selected_object).contains(searchtxt.getText()) || (selected_object.toLowerCase()).contains(searchtxt.getText().toLowerCase())
                        || (selected_object.toUpperCase()).contains(searchtxt.getText().toUpperCase());
                if (bool) {
                    model.addRow(new Object[]{rs.getString("bill_id"), rs.getString("shift"), rs.getString("name"), rs.getString("formula"),
                        rs.getString("type"), rs.getString("packs"), rs.getString("loose"), rs.getString("quantity_in_pack"),
                        rs.getString("price_of_pack"), rs.getString("price_of_loose"), rs.getString("discount_rate"),
                        rs.getString("product_price"), rs.getString("discounted_price")});
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_searchtxtKeyReleased

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

        try {
            for (int i = jTable1.getRowCount() - 1; i >= 0; i--) {
                model.removeRow(i);
            }

            String selected = (String) searchcombo.getSelectedItem();
            selected = switch (selected) {
                case "Bill-Id" ->
                    "bill_id";
                case "Name" ->
                    "name";
                case "Formula" ->
                    "formula";
                case "Type" ->
                    "type";
                case "Discount Rate" ->
                    "discount_rate";
                default ->
                    "";
            };

            Connection con = ConnectionProvider.getcon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * From transactions;");

            while (rs.next()) {
                String selected_object = rs.getString(selected);
                Boolean bool = (selected_object).contains(searchtxt.getText()) || (selected_object.toLowerCase()).contains(searchtxt.getText().toLowerCase())
                        || (selected_object.toUpperCase()).contains(searchtxt.getText().toUpperCase());
                if (bool) {
                    model.addRow(new Object[]{rs.getString("id"), rs.getString("name"), rs.getString("formula"), rs.getString("type"),
                        rs.getString("company"), rs.getString("packs"), rs.getString("loose"), rs.getString("quantity_in_packs"),
                        rs.getString("price_of_pack"), rs.getString("price_of_loose"), rs.getString("discount_rate"),
                        rs.getString("minimum_quantity")});
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_searchButtonActionPerformed

    private void packstxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_packstxtKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_packstxtKeyPressed

    private void packstxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_packstxtKeyReleased
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int index = jTable1.getSelectedRow();

        if (index == -1) {
            JOptionPane.showMessageDialog(null, "Please select a record to edit!");
            return;
        }

        try {

            String bill_id = (String) model.getValueAt(index, 0);
            String shift = (String) model.getValueAt(index, 1);
            String reqPacks = packstxt.getText();
            if (reqPacks.equals("")) {
                reqPacks = "0";
            }

            Connection con = ConnectionProvider.getcon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * From transactions where bill_id = '" + bill_id + "' AND shift = '" + shift + "'");

            rs.next();

            String old_packs = rs.getString("packs");
            String old_loose = rs.getString("loose");
            String type = rs.getString("type");
            String quantity_in_pack = rs.getString("quantity_in_pack");
            String price_of_pack = rs.getString("price_of_pack");
            String discount_rate = rs.getString("discount_rate");

            if (Integer.parseInt(old_packs) < Integer.parseInt(reqPacks)) {
                JOptionPane.showMessageDialog(null, "Product quantity can not be increased from this section!");
                packstxt.setText(old_packs);
            } else {

                float price_of_loose = Float.parseFloat(price_of_pack) / Float.parseFloat(quantity_in_pack);

                float price = (Float.parseFloat(price_of_pack) * Float.parseFloat(reqPacks)) + (price_of_loose * Float.parseFloat(old_loose));
                float discounted_price = price - (price * (Float.parseFloat(discount_rate) / 100));

                pricetxt.setText(String.valueOf(price));
                discountedpricetxt.setText(String.valueOf(discounted_price));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: Invalid Format");
            packstxt.setText("0");
        }
    }//GEN-LAST:event_packstxtKeyReleased

    private void packstxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_packstxtKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_packstxtKeyTyped

    private void loosetxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loosetxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_loosetxtActionPerformed

    private void loosetxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_loosetxtKeyReleased
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int index = jTable1.getSelectedRow();

        if (index == -1) {
            JOptionPane.showMessageDialog(null, "Please select a record to edit!");
            return;
        }

        try {

            String bill_id = (String) model.getValueAt(index, 0);
            String shift = (String) model.getValueAt(index, 1);
            String reqLoose = loosetxt.getText();
            String reqPacks = packstxt.getText();
            if (reqLoose.equals("")) {
                reqLoose = "0";
            }

            Connection con = ConnectionProvider.getcon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * From transactions where bill_id = '" + bill_id + "' AND shift = '" + shift + "'");

            rs.next();

            String old_packs = rs.getString("packs");
            String old_loose = rs.getString("loose");
            String quantity_in_pack = rs.getString("quantity_in_pack");
            String price_of_pack = rs.getString("price_of_pack");
            String discount_rate = rs.getString("discount_rate");

            if (Integer.parseInt(old_loose) < Integer.parseInt(reqLoose)) {
                int new_loose = (Integer.parseInt(old_packs) - Integer.parseInt(reqPacks)) * Integer.parseInt(quantity_in_pack) + Integer.parseInt(old_loose);

                if (new_loose < Integer.parseInt(reqLoose)) {
                    JOptionPane.showMessageDialog(null, "Product quantity can not be increased from this section!");
                    loosetxt.setText(old_loose);

                } else {
                    if (Integer.parseInt(quantity_in_pack) <= Integer.parseInt(reqLoose)) {
                        JOptionPane.showMessageDialog(null, "'Loose' cannot be greater than or equal to 'Quantity In Pack'!");
                        loosetxt.setText(old_loose);
                        return;
                    }
                    float price_of_loose = Float.parseFloat(price_of_pack) / Float.parseFloat(quantity_in_pack);

                    float price = (Float.parseFloat(price_of_pack) * Float.parseFloat(reqPacks)) + (price_of_loose * Integer.parseInt(reqLoose));
                    float discounted_price = price - (price * (Float.parseFloat(discount_rate) / 100));

                    pricetxt.setText(String.valueOf(price));
                    discountedpricetxt.setText(String.valueOf(discounted_price));
                }

            } else {

                float price_of_loose = Float.parseFloat(price_of_pack) / Float.parseFloat(quantity_in_pack);

                float price = (Float.parseFloat(price_of_pack) * Float.parseFloat(reqPacks)) + (price_of_loose * Float.parseFloat(reqLoose));
                float discounted_price = price - (price * (Float.parseFloat(discount_rate) / 100));

                pricetxt.setText(String.valueOf(price));
                discountedpricetxt.setText(String.valueOf(discounted_price));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: Invalid Format");
            if (packstxt.getText().equals("0")) {
                loosetxt.setText("0");
            } else {
                loosetxt.setText("1");
            }
        }
    }//GEN-LAST:event_loosetxtKeyReleased

    private void discountedpricetxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_discountedpricetxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_discountedpricetxtActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:

        int index = jTable1.getSelectedRow();

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        String bill_id = model.getValueAt(index, 0).toString();
        String shift = model.getValueAt(index, 1).toString();
        String name = model.getValueAt(index, 2).toString();
        String old_packs = model.getValueAt(index, 5).toString();
        String old_loose = model.getValueAt(index, 6).toString();
        String quantity_in_pack = model.getValueAt(index, 7).toString();

        String new_packs = packstxt.getText();
        String new_loose = loosetxt.getText();

        if (!new_packs.matches(IntegerPattern) || new_packs.equals("")) {
            JOptionPane.showMessageDialog(null, "Invalid pattern!\n'Packs' field should contain 'Integers' only!");
        } else if (!new_loose.matches(IntegerPattern) || new_loose.equals("")) {
            JOptionPane.showMessageDialog(null, "Invalid pattern!\n'Loose' field should contain 'Integers' only!");
        } else {

            Connection con;
            PreparedStatement stm;

            try {
                con = ConnectionProvider.getcon();
                stm = con.prepareStatement("UPDATE `pharmacy`.`transactions` SET `packs` = ? , `loose` = ? "
                        + "WHERE (`bill_id` = ?) and (`shift` = ?);");

                stm.setString(1, new_packs);
                stm.setString(2, new_loose);
                stm.setString(3, bill_id);
                stm.setString(4, shift);

                stm.executeUpdate();

                ResultSet rs = stm.executeQuery("Select * From products where name = '" + name + "'");
                rs.next();
                int product_packs = Integer.parseInt(rs.getString("packs"));
                int product_loose = Integer.parseInt(rs.getString("loose"));

                int net_packs = Integer.parseInt(old_packs) - Integer.parseInt(new_packs);
                int net_loose = Integer.parseInt(old_loose) - Integer.parseInt(new_loose);

                if (net_loose < 0) {
                    net_loose += Integer.parseInt(quantity_in_pack);
                }

                product_packs += net_packs;
                product_loose += net_packs;

                if (product_loose > Integer.parseInt(quantity_in_pack)) {
                    product_loose -= Integer.parseInt(quantity_in_pack);
                    product_packs++;
                }

                stm = con.prepareStatement("UPDATE `pharmacy`.`products` SET `packs` = ? , `loose` = ? "
                        + "WHERE (`name` = ?);");

                stm.setString(1, String.valueOf(product_packs));
                stm.setString(2, String.valueOf(product_loose));
                stm.setString(3, name);

                stm.executeUpdate();

                JOptionPane.showMessageDialog(null, "Changes Saved successfully!");

                //refreshing the table
                jTable1ComponentShown();

            } catch (HeadlessException | NumberFormatException | SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }//GEN-LAST:event_jButton5ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel HistoryL;
    private javax.swing.JPanel HistoryR;
    private javax.swing.JTextField discountedpricetxt;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField loosetxt;
    private javax.swing.JLabel loosetxt1;
    private javax.swing.JTextField packstxt;
    private javax.swing.JTextField pricetxt;
    private javax.swing.JButton searchButton;
    private javax.swing.JComboBox<String> searchcombo;
    private javax.swing.JTextField searchtxt;
    // End of variables declaration//GEN-END:variables
}
