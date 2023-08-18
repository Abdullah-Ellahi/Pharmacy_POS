/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Cashier;

import Admin.AdminDashboard;
import Admin.LoginPanel;
import static Admin.Main.f;
import Resources.ConnectionProvider;
import Resources.Receipt;

import posRaven.PTableActionCellEditor;
import posRaven.PTableActionCellRender;
import java.awt.HeadlessException;
import javax.swing.*;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import posRaven.PTableActionEvent;
import removeRaven.RTableActionCellEditor;
import removeRaven.RTableActionCellRender;
import removeRaven.RTableActionEvent;

/**
 *
 * @author kkk
 */
public class CashierPosPanel extends javax.swing.JPanel {

    /**
     * Creates new form PosPanel
     */
    public CashierPosPanel() {
        initComponents();

        PTableActionEvent event = new PTableActionEvent() {
            @Override
            public void onAdd1Cmd(int row) {

                int index = jTable1.getSelectedRow();

                if (index == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a record!");
                } else {

                    DefaultTableModel model2 = (DefaultTableModel) jTable2.getModel();
                    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

                    String name = (String) model.getValueAt(index, 0);
                    String packs = (String) model.getValueAt(index, 4);
                    String price_of_pack = (String) model.getValueAt(index, 7);
                    String discount_rate = (String) model.getValueAt(index, 9);

                    float price = Float.parseFloat(price_of_pack);
                    float discounted_price = price - (price * (Float.parseFloat(discount_rate) / 100));

                    int a = 0;
                    for (int i = jTable2.getRowCount() - 1; i >= 0; i--) {

                        int column = 0;
                        String rowName = jTable2.getModel().getValueAt(i, column).toString();
                        if (rowName.equals(name)) {
                            a++;
                        }

                    }

                    if (a > 0) {
                        JOptionPane.showMessageDialog(null, "Product already added!");
                    } else {
                        if (Integer.parseInt(packs) < 1) {
                            model2.addRow(new Object[]{name, 0, 0, 0.00f, 0.00f});
                        } else {
                            model2.addRow(new Object[]{name, 1, 0, price, discounted_price});
                        }

                        float discounted_total = 0f;
                        float total = 0f;

                        for (int i = jTable2.getRowCount() - 1; i >= 0; i--) {
                            total += (float) jTable2.getModel().getValueAt(i, 3);
                            discounted_total += (float) jTable2.getModel().getValueAt(i, 4);
                        }

                        totaltxt1.setText(Float.toString(total));
                        discounttxt1.setText(Float.toString(discounted_total));
                    }
                }
            }

            @Override
            public void onAdd2Cmd(int row) {

                int index = jTable1.getSelectedRow();

                if (index == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a record!");
                } else {
                    DefaultTableModel model2 = (DefaultTableModel) jTable3.getModel();
                    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

                    String name = (String) model.getValueAt(index, 0);
                    String packs = (String) model.getValueAt(index, 4);
                    String price_of_pack = (String) model.getValueAt(index, 7);
                    String discount_rate = (String) model.getValueAt(index, 9);

                    float price = Float.parseFloat(price_of_pack);
                    float discounted_price = price - (price * (Float.parseFloat(discount_rate) / 100));

                    int a = 0;
                    for (int i = jTable3.getRowCount() - 1; i >= 0; i--) {

                        int column = 0;
                        String rowName = jTable3.getModel().getValueAt(i, column).toString();
                        if (rowName.equals(name)) {
                            a++;
                        }

                    }

                    if (a > 0) {
                        JOptionPane.showMessageDialog(null, "Product already added!");
                    } else {
                        if (Integer.parseInt(packs) < 1) {
                            model2.addRow(new Object[]{name, 0, 0, 0.00f, 0.00f});
                        } else {
                            model2.addRow(new Object[]{name, 1, 0, price, discounted_price});
                        }

                        float discounted_total = 0f;
                        float total = 0f;

                        for (int i = jTable3.getRowCount() - 1; i >= 0; i--) {
                            total += (float) jTable3.getModel().getValueAt(i, 3);
                            discounted_total += (float) jTable3.getModel().getValueAt(i, 4);
                        }

                        totaltxt2.setText(Float.toString(total));
                        discounttxt2.setText(Float.toString(discounted_total));

                    }
                }

            }
        };

        RTableActionEvent event2 = new RTableActionEvent() {
            @Override
            public void onRemoveCmd(int row) {
                if (jTable2.isEditing()) {
                    jTable2.getCellEditor().stopCellEditing();
                }

                int index = jTable2.getSelectedRow();

                DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
//                String id = model.getValueAt(index, 0).toString();
//                String name = model.getValueAt(index, 1).toString();

                model.removeRow(row);
            }

        };

        RTableActionEvent event3 = new RTableActionEvent() {
            @Override
            public void onRemoveCmd(int row) {
                if (jTable3.isEditing()) {
                    jTable3.getCellEditor().stopCellEditing();
                }

                int index = jTable3.getSelectedRow();

                DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
//                String id = model.getValueAt(index, 0).toString();
//                String name = model.getValueAt(index, 1).toString();

                model.removeRow(row);
            }

        };

        initComponents();
        jTable1.getColumnModel().getColumn(10).setCellRenderer(new PTableActionCellRender());
        jTable1.getColumnModel().getColumn(10).setCellEditor(new PTableActionCellEditor(event));
        jTable2.getColumnModel().getColumn(5).setCellRenderer(new RTableActionCellRender());
        jTable2.getColumnModel().getColumn(5).setCellEditor(new RTableActionCellEditor(event2));
        jTable3.getColumnModel().getColumn(5).setCellRenderer(new RTableActionCellRender());
        jTable3.getColumnModel().getColumn(5).setCellEditor(new RTableActionCellEditor(event3));
        jTable1ComponentShown();
        jTable2ComponentShown();
        jTable3ComponentShown();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        posL = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        posR = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        searchcombo = new javax.swing.JComboBox<>();
        searchtxt = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        discounttxt1 = new javax.swing.JTextField();
        totaltxt1 = new javax.swing.JTextField();
        printBill1 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jButton13 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        totaltxt2 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        discounttxt2 = new javax.swing.JTextField();

        posL.setBackground(new java.awt.Color(0, 0, 102));
        posL.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        posL.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton7.setBackground(new java.awt.Color(204, 255, 255));
        jButton7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pos.png"))); // NOI18N
        jButton7.setText("     POS     ");
        jButton7.setAlignmentX(0.5F);
        jButton7.setAlignmentY(0.0F);
        jButton7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton7.setPreferredSize(new java.awt.Dimension(202, 107));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        posL.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 310, 280, 60));

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
        posL.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 260, 60));

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logout.png"))); // NOI18N
        jButton3.setText("  LOGOUT ");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        posL.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 723, 140, 40));

        jLabel4.setBackground(new java.awt.Color(0, 0, 51));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 178, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("CASHIER");
        posL.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, 180, 40));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/profile(2).png"))); // NOI18N
        posL.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 180, 190));

        posR.setBackground(new java.awt.Color(204, 255, 255));
        posR.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        posR.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        posR.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 0, 30, 30));

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
        posR.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 0, 30, 30));

        searchcombo.setBackground(new java.awt.Color(204, 255, 255));
        searchcombo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        searchcombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Name", "Formula", "Type", "Company", "Discount Rate" }));
        searchcombo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        searchcombo.setOpaque(true);
        searchcombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchcomboActionPerformed(evt);
            }
        });
        posR.add(searchcombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 110, 30));

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
        posR.add(searchtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 420, 30));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search x30.png"))); // NOI18N
        jButton4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        posR.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 10, 60, 30));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Name", "Formula", "Type", "Company", "Packs", "Loose", "Quantity In Pack", "Price Of Pack", "Price Of Loose", "Discount Rate", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setGridColor(new java.awt.Color(0, 0, 0));
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
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(110);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(80);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(80);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(70);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(30);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(30);
            jTable1.getColumnModel().getColumn(6).setPreferredWidth(50);
            jTable1.getColumnModel().getColumn(7).setPreferredWidth(50);
            jTable1.getColumnModel().getColumn(8).setPreferredWidth(50);
            jTable1.getColumnModel().getColumn(9).setPreferredWidth(40);
            jTable1.getColumnModel().getColumn(10).setPreferredWidth(80);
        }

        posR.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 660, 690));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Name", "Packs", "Loose", "Price", "Discounted Price", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setGridColor(new java.awt.Color(0, 0, 0));
        jTable2.setName(""); // NOI18N
        jTable2.setRowHeight(40);
        jTable2.setSelectionBackground(new java.awt.Color(0, 195, 210));
        jTable2.setShowGrid(false);
        jTable2.setShowHorizontalLines(true);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jTable2.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jTable2ComponentShown(evt);
            }
        });
        jTable2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable2KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable2KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTable2KeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(1).setPreferredWidth(30);
            jTable2.getColumnModel().getColumn(2).setPreferredWidth(30);
            jTable2.getColumnModel().getColumn(3).setPreferredWidth(50);
            jTable2.getColumnModel().getColumn(4).setPreferredWidth(50);
            jTable2.getColumnModel().getColumn(5).setPreferredWidth(20);
        }

        posR.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 30, 400, 290));

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Name", "Packs", "Loose", "Price", "Discounted Price", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable3.setGridColor(new java.awt.Color(0, 0, 0));
        jTable3.setName(""); // NOI18N
        jTable3.setRowHeight(40);
        jTable3.setSelectionBackground(new java.awt.Color(0, 195, 210));
        jTable3.setShowGrid(false);
        jTable3.setShowHorizontalLines(true);
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jTable3.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jTable3ComponentShown(evt);
            }
        });
        jTable3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable3KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTable3KeyTyped(evt);
            }
        });
        jScrollPane3.setViewportView(jTable3);
        if (jTable3.getColumnModel().getColumnCount() > 0) {
            jTable3.getColumnModel().getColumn(1).setPreferredWidth(30);
            jTable3.getColumnModel().getColumn(2).setPreferredWidth(30);
            jTable3.getColumnModel().getColumn(3).setPreferredWidth(50);
            jTable3.getColumnModel().getColumn(4).setPreferredWidth(50);
            jTable3.getColumnModel().getColumn(5).setPreferredWidth(30);
        }

        posR.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 410, 400, 290));

        jLabel6.setBackground(new java.awt.Color(204, 255, 255));
        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Search");
        jLabel6.setAlignmentX(0.5F);
        jLabel6.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jLabel6.setOpaque(true);
        posR.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 60, 30));

        jSeparator1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        posR.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 390, 420, -1));

        discounttxt1.setEditable(false);
        discounttxt1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        discounttxt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                discounttxt1ActionPerformed(evt);
            }
        });
        posR.add(discounttxt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 360, 100, -1));

        totaltxt1.setEditable(false);
        totaltxt1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        totaltxt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totaltxt1ActionPerformed(evt);
            }
        });
        posR.add(totaltxt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 360, 100, -1));

        printBill1.setBackground(new java.awt.Color(0, 0, 255));
        printBill1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        printBill1.setForeground(new java.awt.Color(255, 255, 255));
        printBill1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/raven/print2.png"))); // NOI18N
        printBill1.setText("Print");
        printBill1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        printBill1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printBill1ActionPerformed(evt);
            }
        });
        posR.add(printBill1, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 340, 90, 30));

        jButton17.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/refresh.png"))); // NOI18N
        jButton17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });
        posR.add(jButton17, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 340, 30, 30));

        jLabel9.setText("Discounted Price");
        posR.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 340, -1, -1));

        jLabel10.setText("Total Price");
        posR.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 340, 70, -1));

        jButton13.setBackground(new java.awt.Color(0, 0, 255));
        jButton13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton13.setForeground(new java.awt.Color(255, 255, 255));
        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/raven/print2.png"))); // NOI18N
        jButton13.setText("Print");
        jButton13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        posR.add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 720, 90, 30));

        jButton18.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/refresh.png"))); // NOI18N
        jButton18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });
        posR.add(jButton18, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 720, 30, 30));

        jLabel11.setText("Total Price");
        posR.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 720, 70, -1));

        totaltxt2.setEditable(false);
        totaltxt2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        totaltxt2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totaltxt2ActionPerformed(evt);
            }
        });
        posR.add(totaltxt2, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 740, 100, -1));

        jLabel12.setText("Discounted Price");
        posR.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 720, -1, -1));

        discounttxt2.setEditable(false);
        discounttxt2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        discounttxt2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                discounttxt2ActionPerformed(evt);
            }
        });
        posR.add(discounttxt2, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 740, 100, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1370, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(posL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(posR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 770, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(posL, javax.swing.GroupLayout.PREFERRED_SIZE, 770, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(posR, javax.swing.GroupLayout.PREFERRED_SIZE, 770, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        f.getContentPane().removeAll();
        f.getContentPane().add(new CashierDashboard());
        f.revalidate();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        f.getContentPane().removeAll();
        f.getContentPane().add(new LoginPanel());
        f.revalidate();
    }//GEN-LAST:event_jButton3ActionPerformed

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

            if (selected.equals("Name")) {
                selected = "name";
            } else if (selected.equals("Formula")) {
                selected = "formula";
            } else if (selected.equals("Type")) {
                selected = "type";
            } else if (selected.equals("Company")) {
                selected = "company";
            } else if (selected.equals("Discount Rate")) {
                selected = "discount_rate";
            } else {
                selected = "";
            }

            Connection con = ConnectionProvider.getcon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * From products;");

            while (rs.next()) {
                String coloumn_name = rs.getString(selected);
                Boolean bool = (coloumn_name).contains(searchtxt.getText()) || (coloumn_name.toLowerCase()).contains(searchtxt.getText().toLowerCase()) || (coloumn_name.toUpperCase()).contains(searchtxt.getText().toUpperCase());
                if (bool) {
                    model.addRow(new Object[]{rs.getString("id"), rs.getString("name"), rs.getString("formula"), rs.getString("type"),
                        rs.getString("company"), rs.getString("packs"), rs.getString("loose"), rs.getString("quantity_in_packs"),
                        rs.getString("price_of_pack"), rs.getString("price_of_loose"), rs.getString("Discount_rate"),
                        rs.getString("minimum_quantity")});
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_searchtxtKeyReleased

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

        try {
            for (int i = jTable1.getRowCount() - 1; i >= 0; i--) {
                model.removeRow(i);
            }

            String selected = (String) searchcombo.getSelectedItem();
            if (selected.equals("Name")) {
                selected = "name";
            } else if (selected.equals("Formula")) {
                selected = "formula";
            } else if (selected.equals("Type")) {
                selected = "type";
            } else if (selected.equals("Company")) {
                selected = "company";
            } else if (selected.equals("Discount Rate")) {
                selected = "discount_rate";
            } else {
                selected = "";
            }

            Connection con = ConnectionProvider.getcon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * From products;");

            while (rs.next()) {
                String coloumn_name = rs.getString(selected);
                Boolean bool = (coloumn_name).contains(searchtxt.getText()) || (coloumn_name.toLowerCase()).contains(searchtxt.getText().toLowerCase()) || (coloumn_name.toUpperCase()).contains(searchtxt.getText().toUpperCase());
                if (bool) {
                    model.addRow(new Object[]{rs.getString("id"), rs.getString("name"), rs.getString("formula"), rs.getString("type"),
                        rs.getString("company"), rs.getString("packs"), rs.getString("loose"), rs.getString("quantity_in_packs"),
                        rs.getString("price_of_pack"), rs.getString("price_of_loose"), rs.getString("Discount_rate"),
                        rs.getString("minimum_quantity")});
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        //        int index = jTable2.getSelectedRow();
        //
        //        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        //
        //        String name = (String) model.getValueAt(index, 1);
        //        String formula = (String) model.getValueAt(index, 2);
        //        String company = (String) model.getValueAt(index, 4);
        //        String packs = (String) model.getValueAt(index, 5);
        //        String loose = (String) model.getValueAt(index, 6);
        //        String quantity_in_pack = (String) model.getValueAt(index, 7);
        //        String price_of_pack = (String) model.getValueAt(index, 8);
        //        String wprice_of_pack = (String) model.getValueAt(index, 9);
        //        String discount_rate = (String) model.getValueAt(index, 11);
        //        String minimum_quantity = (String) model.getValueAt(index, 12);
        //
        //        nametxt.setText(name);
        //        formulatxt.setText(formula);
        //        distributorcombotxt.setSelectedItem(company);
        //        packstxt.setText(packs);
        //        loosetxt.setText(loose);
        //        quantityinpacktxt.setText(quantity_in_pack);
        //        priceofpacktxt.setText(price_of_pack);
        //        wpriceofpacktxt.setText(wprice_of_pack);
        //        discountratetxt.setText(discount_rate);
        //        minimumquantitytxt.setText(minimum_quantity);
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
            ResultSet rs = st.executeQuery("Select * From products");

            while (rs.next()) {
                model.addRow(new Object[]{rs.getString("name"), rs.getString("formula"), rs.getString("type"),
                    rs.getString("company"), rs.getString("packs"), rs.getString("loose"), rs.getString("quantity_in_packs"),
                    rs.getString("price_of_pack"), rs.getString("price_of_loose"), rs.getString("Discount_rate")});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_jTable1ComponentShown

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
        DefaultTableModel model2 = (DefaultTableModel) jTable2.getModel();
        for (int j = 0; j < jTable2.getRowCount(); j++) {
            int index2 = j;

            try {

                String name = (String) model2.getValueAt(index2, 0);
                String reqPacks = model2.getValueAt(index2, 1).toString();
                String reqLoose = model2.getValueAt(index2, 2).toString();

                if (reqPacks.equals("")) {
                    reqPacks = "0";
                    model2.setValueAt("0", index2, 1);
                }

                if (reqLoose.equals("")) {
                    reqLoose = "0";
                    model2.setValueAt("0", index2, 2);
                }

                Connection con = ConnectionProvider.getcon();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("Select * From products where name = '" + name + "'");

                rs.next();

                String packs_available = rs.getString("packs");
                String loose_available = rs.getString("loose");
                String quantity_in_pack = rs.getString("quantity_in_packs");
                String price_of_pack = rs.getString("price_of_pack");
                String type = rs.getString("type");
                String discount_rate = rs.getString("discount_rate");

                if (Integer.parseInt(reqLoose) >= Integer.parseInt(quantity_in_pack)) {
                    if (quantity_in_pack.equals("1")) {
                        JOptionPane.showMessageDialog(null, type + "s can not be loosened!");
                        model2.setValueAt(0, index2, 2);

                    } else {
                        JOptionPane.showMessageDialog(null, "'Quantity of loose' cannot be greater than or equal to 'Quantity in packs'!");
                        model2.setValueAt(0, index2, 2);

                    }
                } else if (Integer.parseInt(packs_available) < Integer.parseInt(reqPacks)) {
                    JOptionPane.showMessageDialog(null, "Not enough packs of " + name + "!");
                    model2.setValueAt(packs_available, index2, 1);

                } else {

                    int looseLeft = (Integer.parseInt(packs_available) - Integer.parseInt(reqPacks)) * Integer.parseInt(quantity_in_pack)
                            + Integer.parseInt(loose_available);

                    if (Integer.parseInt(reqLoose) > looseLeft) {
                        JOptionPane.showMessageDialog(null, "Not enough loose of " + name + "!");
                        model2.setValueAt(looseLeft, index2, 2);
                    }
                    float price_of_loose = Float.parseFloat(price_of_pack) / Float.parseFloat(quantity_in_pack);

                    float price = (Float.parseFloat(price_of_pack) * Float.parseFloat(reqPacks)) + (price_of_loose * Float.parseFloat(reqLoose));
                    float discounted_price = price - (price * (Float.parseFloat(discount_rate) / 100));

                    model2.setValueAt(price, index2, 3);
                    model2.setValueAt(discounted_price, index2, 4);

                    //setting the total and discounted total:
                    float discounted_total = 0f;
                    float total = 0f;

                    for (int i = jTable2.getRowCount() - 1; i >= 0; i--) {
                        total += (float) jTable2.getModel().getValueAt(i, 3);
                        discounted_total += (float) jTable2.getModel().getValueAt(i, 4);
                    }

                    totaltxt1.setText(Float.toString(total));
                    discounttxt1.setText(Float.toString(discounted_total));

                }

            } catch (NumberFormatException | SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }//GEN-LAST:event_jTable2MouseClicked

    private void jTable2ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jTable2ComponentShown
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();

        for (int i = jTable2.getRowCount() - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }//GEN-LAST:event_jTable2ComponentShown

    private void jTable2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable2KeyPressed
        // TODO add your handling code here:
        DefaultTableModel model2 = (DefaultTableModel) jTable2.getModel();
        int index2 = jTable2.getSelectedRow();

        try {

            String name = (String) model2.getValueAt(index2, 0);
            String reqPacks = model2.getValueAt(index2, 1).toString();
            String reqLoose = model2.getValueAt(index2, 2).toString();

            Connection con = ConnectionProvider.getcon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * From products where name = '" + name + "'");

            rs.next();

            String packs_available = rs.getString("packs");
            String loose_available = rs.getString("loose");
            String quantity_in_pack = rs.getString("quantity_in_packs");
            String price_of_pack = rs.getString("price_of_pack");
            String type = rs.getString("type");
            String discount_rate = rs.getString("discount_rate");

            if (Integer.parseInt(reqLoose) >= Integer.parseInt(quantity_in_pack)) {
                if (quantity_in_pack.equals(1)) {
                    JOptionPane.showMessageDialog(null, type + "s can not be loosened!");
                    model2.setValueAt(0, index2, 2);

                } else {
                    JOptionPane.showMessageDialog(null, "'Quantity of loose' cannot be greater than or equal to 'Quantity in packs'!");
                    model2.setValueAt(0, index2, 2);

                }
            } else if (Integer.parseInt(packs_available) < Integer.parseInt(reqPacks)) {
                JOptionPane.showMessageDialog(null, "Not enough packs of " + name + "!");
                model2.setValueAt(packs_available, index2, 1);

            } else {

                int looseLeft = (Integer.parseInt(packs_available) - Integer.parseInt(reqPacks)) * Integer.parseInt(quantity_in_pack)
                        + Integer.parseInt(loose_available);

                if (Integer.parseInt(reqLoose) > looseLeft) {
                    JOptionPane.showMessageDialog(null, "Not enough loose of " + name + "!");
                    model2.setValueAt(looseLeft, index2, 2);
                } else {
                }
                float price_of_loose = Float.parseFloat(price_of_pack) / Float.parseFloat(quantity_in_pack);

                float price = (Float.parseFloat(price_of_pack) * Float.parseFloat(reqPacks)) + (price_of_loose * Float.parseFloat(reqLoose));
                float discounted_price = price - (price * (Float.parseFloat(discount_rate) / 100));

                model2.setValueAt(price, index2, 3);
                model2.setValueAt(discounted_price, index2, 4);

                //setting the total and discounted total:
                float discounted_total = 0f;
                float total = 0f;

                for (int i = jTable2.getRowCount() - 1; i >= 0; i--) {
                    total += (float) jTable2.getModel().getValueAt(i, 3);
                    discounted_total += (float) jTable2.getModel().getValueAt(i, 4);
                }

                totaltxt1.setText(Float.toString(total));
                discounttxt1.setText(Float.toString(discounted_total));

            }

        } catch (NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_jTable2KeyPressed

    private void jTable2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable2KeyReleased
        // TODO add your handling code here:
        DefaultTableModel model2 = (DefaultTableModel) jTable2.getModel();
        int index2 = jTable2.getSelectedRow();

        try {

            String name = (String) model2.getValueAt(index2, 0);
            String reqPacks = model2.getValueAt(index2, 1).toString();
            String reqLoose = model2.getValueAt(index2, 2).toString();

            if (reqPacks.equals("")) {
                reqPacks = "0";
                model2.setValueAt("0", index2, 1);
            }

            if (reqLoose.equals("")) {
                reqLoose = "0";
                model2.setValueAt("0", index2, 2);
            }

            Connection con = ConnectionProvider.getcon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * From products where name = '" + name + "'");

            rs.next();

            String packs_available = rs.getString("packs");
            String loose_available = rs.getString("loose");
            String quantity_in_pack = rs.getString("quantity_in_packs");
            String price_of_pack = rs.getString("price_of_pack");
            String type = rs.getString("type");
            String discount_rate = rs.getString("discount_rate");

            if (Integer.parseInt(reqLoose) >= Integer.parseInt(quantity_in_pack)) {
                if (type.contains("Syrup") || type.contains("Syp") || type.contains("Syrup".toUpperCase())
                        || type.contains("Syp".toUpperCase()) || type.contains("Syrup".toLowerCase()) || type.contains("Syrup".toLowerCase())) {
                    JOptionPane.showMessageDialog(null, "Syrups can not be loosened!");
                    model2.setValueAt(0, index2, 2);

                } else {
                    JOptionPane.showMessageDialog(null, "'Quantity of loose' cannot be greater than or equal to 'Quantity in packs'!");
                    model2.setValueAt(0, index2, 2);

                }
            } else if (Integer.parseInt(packs_available) < Integer.parseInt(reqPacks)) {
                JOptionPane.showMessageDialog(null, "Not enough packs of " + name + "!");
                model2.setValueAt(packs_available, index2, 1);

            } else {

                int looseLeft = (Integer.parseInt(packs_available) - Integer.parseInt(reqPacks)) * Integer.parseInt(quantity_in_pack)
                        + Integer.parseInt(loose_available);

                if (Integer.parseInt(reqLoose) > looseLeft) {
                    JOptionPane.showMessageDialog(null, "Not enough loose of " + name + "!");
                    model2.setValueAt(looseLeft, index2, 2);
                } else {
                }
                float price_of_loose = Float.parseFloat(price_of_pack) / Float.parseFloat(quantity_in_pack);

                float price = (Float.parseFloat(price_of_pack) * Float.parseFloat(reqPacks)) + (price_of_loose * Float.parseFloat(reqLoose));
                float discounted_price = price - (price * (Float.parseFloat(discount_rate) / 100));

                model2.setValueAt(price, index2, 3);
                model2.setValueAt(discounted_price, index2, 4);

                //setting the total and discounted total:
                float discounted_total = 0f;
                float total = 0f;

                for (int i = jTable2.getRowCount() - 1; i >= 0; i--) {
                    total += (float) jTable2.getModel().getValueAt(i, 3);
                    discounted_total += (float) jTable2.getModel().getValueAt(i, 4);
                }

                totaltxt1.setText(Float.toString(total));
                discounttxt1.setText(Float.toString(discounted_total));

            }

        } catch (NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_jTable2KeyReleased

    private void jTable2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable2KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable2KeyTyped

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        // TODO add your handling code here:
        DefaultTableModel model2 = (DefaultTableModel) jTable3.getModel();
        for (int j = 0; j < jTable3.getRowCount(); j++) {
            int index2 = j;

            try {

                String name = (String) model2.getValueAt(index2, 0);
                String reqPacks = model2.getValueAt(index2, 1).toString();
                String reqLoose = model2.getValueAt(index2, 2).toString();

                if (reqPacks.equals("")) {
                    reqPacks = "0";
                    model2.setValueAt("0", index2, 1);
                }

                if (reqLoose.equals("")) {
                    reqLoose = "0";
                    model2.setValueAt("0", index2, 2);
                }

                Connection con = ConnectionProvider.getcon();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("Select * From products where name = '" + name + "'");

                rs.next();

                String packs_available = rs.getString("packs");
                String loose_available = rs.getString("loose");
                String quantity_in_pack = rs.getString("quantity_in_packs");
                String price_of_pack = rs.getString("price_of_pack");
                String type = rs.getString("type");
                String discount_rate = rs.getString("discount_rate");

                if (Integer.parseInt(reqLoose) >= Integer.parseInt(quantity_in_pack)) {
                    if (quantity_in_pack.equals("1")) {
                        JOptionPane.showMessageDialog(null, type + "s can not be loosened!");
                        model2.setValueAt(0, index2, 2);

                    } else {
                        JOptionPane.showMessageDialog(null, "'Quantity of loose' cannot be greater than or equal to 'Quantity in packs'!");
                        model2.setValueAt(0, index2, 2);

                    }
                } else if (Integer.parseInt(packs_available) < Integer.parseInt(reqPacks)) {
                    JOptionPane.showMessageDialog(null, "Not enough packs of " + name + "!");
                    model2.setValueAt(packs_available, index2, 1);

                } else {

                    int looseLeft = (Integer.parseInt(packs_available) - Integer.parseInt(reqPacks)) * Integer.parseInt(quantity_in_pack)
                            + Integer.parseInt(loose_available);

                    if (Integer.parseInt(reqLoose) > looseLeft) {
                        JOptionPane.showMessageDialog(null, "Not enough loose of " + name + "!");
                        model2.setValueAt(looseLeft, index2, 2);
                    } else {
                    }
                    float price_of_loose = Float.parseFloat(price_of_pack) / Float.parseFloat(quantity_in_pack);

                    float price = (Float.parseFloat(price_of_pack) * Float.parseFloat(reqPacks)) + (price_of_loose * Float.parseFloat(reqLoose));
                    float discounted_price = price - (price * (Float.parseFloat(discount_rate) / 100));

                    model2.setValueAt(price, index2, 3);
                    model2.setValueAt(discounted_price, index2, 4);

                    //setting the total and discounted total:
                    float discounted_total = 0f;
                    float total = 0f;

                    for (int i = jTable3.getRowCount() - 1; i >= 0; i--) {
                        total += (float) jTable3.getModel().getValueAt(i, 3);
                        discounted_total += (float) jTable3.getModel().getValueAt(i, 4);
                    }

                    totaltxt2.setText(Float.toString(total));
                    discounttxt2.setText(Float.toString(discounted_total));

                }

            } catch (NumberFormatException | SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }//GEN-LAST:event_jTable3MouseClicked

    private void jTable3ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jTable3ComponentShown
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) jTable3.getModel();

        for (int i = jTable3.getRowCount() - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }//GEN-LAST:event_jTable3ComponentShown

    private void jTable3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable3KeyReleased
        // TODO add your handling code here:
        DefaultTableModel model3 = (DefaultTableModel) jTable3.getModel();
        int index3 = jTable3.getSelectedRow();

        try {

            String name = (String) model3.getValueAt(index3, 0);
            String reqPacks = model3.getValueAt(index3, 1).toString();
            String reqLoose = model3.getValueAt(index3, 2).toString();

            if (reqPacks.equals("")) {
                reqPacks = "0";
                model3.setValueAt("0", index3, 1);
            }

            if (reqLoose.equals("")) {
                reqLoose = "0";
                model3.setValueAt("0", index3, 2);
            }

            Connection con = ConnectionProvider.getcon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * From products where name = '" + name + "'");

            rs.next();

            String packs_available = rs.getString("packs");
            String loose_available = rs.getString("loose");
            String quantity_in_pack = rs.getString("quantity_in_packs");
            String price_of_pack = rs.getString("price_of_pack");
            String type = rs.getString("type");
            String discount_rate = rs.getString("discount_rate");

            if (Integer.parseInt(reqLoose) >= Integer.parseInt(quantity_in_pack)) {
                if (quantity_in_pack.equals("1")) {
                    JOptionPane.showMessageDialog(null, type + "s can not be loosened!");
                    model3.setValueAt(0, index3, 2);

                } else {
                    JOptionPane.showMessageDialog(null, "'Quantity of loose' cannot be greater than or equal to 'Quantity in packs'!");
                    model3.setValueAt(0, index3, 2);

                }
            } else if (Integer.parseInt(packs_available) < Integer.parseInt(reqPacks)) {
                JOptionPane.showMessageDialog(null, "Not enough packs of " + name + "!");
                model3.setValueAt(packs_available, index3, 1);

            } else {

                int looseLeft = (Integer.parseInt(packs_available) - Integer.parseInt(reqPacks)) * Integer.parseInt(quantity_in_pack)
                        + Integer.parseInt(loose_available);

                if (Integer.parseInt(reqLoose) > looseLeft) {
                    JOptionPane.showMessageDialog(null, "Not enough loose of " + name + "!");
                    model3.setValueAt(looseLeft, index3, 2);
                } else {
                }
                float price_of_loose = Float.parseFloat(price_of_pack) / Float.parseFloat(quantity_in_pack);

                float price = (Float.parseFloat(price_of_pack) * Float.parseFloat(reqPacks)) + (price_of_loose * Float.parseFloat(reqLoose));
                float discounted_price = price - (price * (Float.parseFloat(discount_rate) / 100));

                model3.setValueAt(price, index3, 3);
                model3.setValueAt(discounted_price, index3, 4);

            }

            //setting the total and discounted total:
            float discounted_total = 0f;
            float total = 0f;

            for (int i = jTable3.getRowCount() - 1; i >= 0; i--) {
                total += (float) jTable3.getModel().getValueAt(i, 3);
                discounted_total += (float) jTable3.getModel().getValueAt(i, 4);
            }

            totaltxt2.setText(Float.toString(total));
            discounttxt2.setText(Float.toString(discounted_total));

        } catch (NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_jTable3KeyReleased

    private void jTable3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable3KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable3KeyTyped

    private void discounttxt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_discounttxt1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_discounttxt1ActionPerformed

    private void totaltxt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totaltxt1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totaltxt1ActionPerformed

    private void printBill1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printBill1ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model2 = (DefaultTableModel) jTable2.getModel();

        //setting all the price and discounted price values in the table in case if user has not pressed 'Enter'.
        for (int j = 0; j < jTable2.getRowCount(); j++) {
            int index2 = j;

            try {

                String name = (String) model2.getValueAt(index2, 0);
                String reqPacks = model2.getValueAt(index2, 1).toString();
                String reqLoose = model2.getValueAt(index2, 2).toString();

                Connection con = ConnectionProvider.getcon();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("Select * From products where name = '" + name + "'");

                rs.next();

                String packs_available = rs.getString("packs");
                String loose_available = rs.getString("loose");
                String quantity_in_pack = rs.getString("quantity_in_packs");
                String price_of_pack = rs.getString("price_of_pack");
                String type = rs.getString("type");
                String discount_rate = rs.getString("discount_rate");

                if (Integer.parseInt(reqLoose) >= Integer.parseInt(quantity_in_pack)) {
                    if (quantity_in_pack.equals("1")) {
                        JOptionPane.showMessageDialog(null, type + "s can not be loosened!");
                        model2.setValueAt(0, index2, 2);

                    } else {
                        JOptionPane.showMessageDialog(null, "'Quantity of loose' cannot be greater than or equal to 'Quantity in packs'!");
                        model2.setValueAt(0, index2, 2);

                    }
                } else if (Integer.parseInt(packs_available) < Integer.parseInt(reqPacks)) {
                    JOptionPane.showMessageDialog(null, "Not enough packs of " + name + "!");
                    model2.setValueAt(packs_available, index2, 1);

                } else {

                    int looseLeft = (Integer.parseInt(packs_available) - Integer.parseInt(reqPacks)) * Integer.parseInt(quantity_in_pack)
                            + Integer.parseInt(loose_available);

                    if (Integer.parseInt(reqLoose) > looseLeft) {
                        JOptionPane.showMessageDialog(null, "Not enough loose of " + name + "!");
                        model2.setValueAt(looseLeft, index2, 2);
                    } else {
                    }
                    float price_of_loose = Float.parseFloat(price_of_pack) / Float.parseFloat(quantity_in_pack);

                    float price = (Float.parseFloat(price_of_pack) * Float.parseFloat(reqPacks)) + (price_of_loose * Float.parseFloat(reqLoose));
                    float discounted_price = price - (price * (Float.parseFloat(discount_rate) / 100));

                    model2.setValueAt(price, index2, 3);
                    model2.setValueAt(discounted_price, index2, 4);

                    //setting the total and discounted total:
                    float discounted_total = 0f;
                    float total = 0f;

                    for (int i = jTable2.getRowCount() - 1; i >= 0; i--) {
                        total += (float) jTable2.getModel().getValueAt(i, 3);
                        discounted_total += (float) jTable2.getModel().getValueAt(i, 4);
                    }

                    totaltxt1.setText(Float.toString(total));
                    discounttxt1.setText(Float.toString(discounted_total));

                }

            } catch (NumberFormatException | SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }

        int a = 0;
        try {
            String bill_id = getBillid();
            for (int i = jTable2.getRowCount() - 1; i >= 0; i--) {

                String name = (String) model2.getValueAt(i, 0);
                String packsReq = model2.getValueAt(i, 1).toString();
                String looseReq = model2.getValueAt(i, 2).toString();

                Connection con = ConnectionProvider.getcon();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("Select * From products where name = '" + name + "'");

                rs.next();

                String packs_available = rs.getString("packs");
                String loose_available = rs.getString("loose");
                String formula = rs.getString("formula");
                String type = rs.getString("type");
                String company = rs.getString("company");
                String quantity_in_pack = rs.getString("quantity_in_packs");
                String price_of_pack = rs.getString("price_of_pack");
                String discount_rate = rs.getString("discount_rate");

                PreparedStatement stm = con.prepareStatement("insert into transactions (bill_id,shift,name,formula,type,company,packs,"
                        + "loose,quantity_in_pack,price_of_pack,discount_rate) values(?,?,?,?,?,?,?,?,?,?,?)");

                stm.setString(1, bill_id);
                stm.setInt(2, i);
                stm.setString(3, name);
                stm.setString(4, formula);
                stm.setString(5, type);
                stm.setString(6, company);
                stm.setInt(7, Integer.parseInt(packsReq));
                stm.setInt(8, Integer.parseInt(looseReq));
                stm.setFloat(9, Float.parseFloat(quantity_in_pack));
                stm.setFloat(10, Float.parseFloat(price_of_pack));
                stm.setFloat(11, Float.parseFloat(discount_rate));

                stm.executeUpdate();

                int packsLeft = Integer.parseInt(packs_available) - Integer.parseInt(packsReq);
                int looseLeft;

                if (Integer.parseInt(loose_available) >= Integer.parseInt(looseReq)) {
                    looseLeft = Integer.parseInt(loose_available) - Integer.parseInt(looseReq);
                } else {
                    packsLeft--;
                    looseLeft = Integer.parseInt(loose_available) - Integer.parseInt(looseReq) + Integer.parseInt(quantity_in_pack);
                }

                stm = con.prepareStatement("UPDATE `pharmacy`.`products` SET `packs` = ? , `loose` = ? WHERE (`name` = ?);");
                stm.setInt(1, packsLeft);
                stm.setInt(2, looseLeft);
                stm.setString(3, name);

                stm.executeUpdate();

                a++;

            }
            new Receipt(bill_id, totaltxt1.getText(), discounttxt1.getText(), jTable2.getModel()).setVisible(true);

        } catch (HeadlessException | NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error in Transaction: " + e.getMessage());
        } catch (PrinterException ex) {
            Logger.getLogger(CashierPosPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (a == jTable2.getRowCount()) {

            JOptionPane.showMessageDialog(null, "Transaction Successfull!");
        } else {
            JOptionPane.showMessageDialog(null, "Transaction Not Successfull!");
        }

        totaltxt1.setText("");
        discounttxt1.setText("");
        jButton17ActionPerformed(evt);
        jTable1ComponentShown();
    }//GEN-LAST:event_printBill1ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();

        for (int i = jTable2.getRowCount() - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model2 = (DefaultTableModel) jTable3.getModel();

        //setting all the price and discounted price values in the table in case if user has not pressed 'Enter'.
        for (int j = 0; j < jTable3.getRowCount(); j++) {
            int index2 = j;

            try {

                String name = (String) model2.getValueAt(index2, 0);
                String reqPacks = model2.getValueAt(index2, 1).toString();
                String reqLoose = model2.getValueAt(index2, 2).toString();

                Connection con = ConnectionProvider.getcon();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("Select * From products where name = '" + name + "'");

                rs.next();

                String packs_available = rs.getString("packs");
                String loose_available = rs.getString("loose");
                String quantity_in_pack = rs.getString("quantity_in_packs");
                String price_of_pack = rs.getString("price_of_pack");
                String type = rs.getString("type");
                String discount_rate = rs.getString("discount_rate");

                if (Integer.parseInt(reqLoose) >= Integer.parseInt(quantity_in_pack)) {
                    if (quantity_in_pack.equals("1")) {
                        JOptionPane.showMessageDialog(null, type + "s can not be loosened!");
                        model2.setValueAt(0, index2, 2);

                    } else {
                        JOptionPane.showMessageDialog(null, "'Quantity of loose' cannot be greater than or equal to 'Quantity in packs'!");
                        model2.setValueAt(0, index2, 2);

                    }
                } else if (Integer.parseInt(packs_available) < Integer.parseInt(reqPacks)) {
                    JOptionPane.showMessageDialog(null, "Not enough packs of " + name + "!");
                    model2.setValueAt(packs_available, index2, 1);

                } else {

                    int looseLeft = (Integer.parseInt(packs_available) - Integer.parseInt(reqPacks)) * Integer.parseInt(quantity_in_pack)
                            + Integer.parseInt(loose_available);

                    if (Integer.parseInt(reqLoose) > looseLeft) {
                        JOptionPane.showMessageDialog(null, "Not enough loose of " + name + "!");
                        model2.setValueAt(looseLeft, index2, 2);
                    } else {
                    }
                    float price_of_loose = Float.parseFloat(price_of_pack) / Float.parseFloat(quantity_in_pack);

                    float price = (Float.parseFloat(price_of_pack) * Float.parseFloat(reqPacks)) + (price_of_loose * Float.parseFloat(reqLoose));
                    float discounted_price = price - (price * (Float.parseFloat(discount_rate) / 100));

                    model2.setValueAt(price, index2, 3);
                    model2.setValueAt(discounted_price, index2, 4);

                    //setting the total and discounted total:
                    float discounted_total = 0f;
                    float total = 0f;

                    for (int i = jTable3.getRowCount() - 1; i >= 0; i--) {
                        total += (float) jTable3.getModel().getValueAt(i, 3);
                        discounted_total += (float) jTable3.getModel().getValueAt(i, 4);
                    }

                    totaltxt2.setText(Float.toString(total));
                    discounttxt2.setText(Float.toString(discounted_total));

                }

            } catch (NumberFormatException | SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }

        int a = 0;

        try {
            String bill_id = getBillid();
            for (int i = jTable3.getRowCount() - 1; i >= 0; i--) {

                String name = (String) model2.getValueAt(i, 0);
                String packsReq = model2.getValueAt(i, 1).toString();
                String looseReq = model2.getValueAt(i, 2).toString();

                Connection con = ConnectionProvider.getcon();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("Select * From products where name = '" + name + "'");

                rs.next();

                String packs_available = rs.getString("packs");
                String loose_available = rs.getString("loose");
                String formula = rs.getString("formula");
                String type = rs.getString("type");
                String company = rs.getString("company");
                String quantity_in_pack = rs.getString("quantity_in_packs");
                String price_of_pack = rs.getString("price_of_pack");
                String discount_rate = rs.getString("discount_rate");

                PreparedStatement stm = con.prepareStatement("insert into transactions (bill_id,shift,name,formula,type,company,packs,"
                        + "loose,quantity_in_pack,price_of_pack,discount_rate) values(?,?,?,?,?,?,?,?,?,?,?)");

                stm.setString(1, bill_id);
                stm.setInt(2, i);
                stm.setString(3, name);
                stm.setString(4, formula);
                stm.setString(5, type);
                stm.setString(6, company);
                stm.setInt(7, Integer.parseInt(packsReq));
                stm.setInt(8, Integer.parseInt(looseReq));
                stm.setFloat(9, Float.parseFloat(quantity_in_pack));
                stm.setFloat(10, Float.parseFloat(price_of_pack));
                stm.setFloat(11, Float.parseFloat(discount_rate));

                stm.executeUpdate();

                int packsLeft = Integer.parseInt(packs_available) - Integer.parseInt(packsReq);
                int looseLeft;

                if (Integer.parseInt(loose_available) >= Integer.parseInt(looseReq)) {
                    looseLeft = Integer.parseInt(loose_available) - Integer.parseInt(looseReq);
                } else {
                    packsLeft--;
                    looseLeft = Integer.parseInt(loose_available) - Integer.parseInt(looseReq) + Integer.parseInt(quantity_in_pack);
                }

                stm = con.prepareStatement("UPDATE `pharmacy`.`products` SET `packs` = ? , `loose` = ? WHERE (`name` = ?);");
                stm.setInt(1, packsLeft);
                stm.setInt(2, looseLeft);
                stm.setString(3, name);

                stm.executeUpdate();

                a++;

            }
            new Receipt(bill_id, totaltxt1.getText(), discounttxt1.getText(), jTable2.getModel()).setVisible(true);

        } catch (HeadlessException | NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error in Transaction: " + e.getMessage());
        } catch (PrinterException e) {
            JOptionPane.showMessageDialog(null, "Printing Error: " + e.getMessage());
        }

        if (a == jTable3.getRowCount()) {

            JOptionPane.showMessageDialog(null, "Transaction Successfull!");
        } else {
            JOptionPane.showMessageDialog(null, "Transaction Not Successfull!");
        }

        jButton18ActionPerformed(evt);
        jTable1ComponentShown();
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) jTable3.getModel();

        for (int i = jTable3.getRowCount() - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }//GEN-LAST:event_jButton18ActionPerformed

    private void totaltxt2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totaltxt2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totaltxt2ActionPerformed

    private void discounttxt2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_discounttxt2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_discounttxt2ActionPerformed

    public static String getBillid() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddHHMMmmyyyyss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    private void jTable3ComponentShown() {
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) jTable3.getModel();

        for (int i = jTable3.getRowCount() - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }

    private void jTable2ComponentShown() {
        // TODO add your handling code here:

        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();

        for (int i = jTable2.getRowCount() - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }

    private void jTable1ComponentShown() {
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

        try {
            for (int i = jTable1.getRowCount() - 1; i >= 0; i--) {
                model.removeRow(i);
            }

            Connection con = ConnectionProvider.getcon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * From products");

            while (rs.next()) {
                model.addRow(new Object[]{rs.getString("name"), rs.getString("formula"), rs.getString("type"),
                    rs.getString("company"), rs.getString("packs"), rs.getString("loose"), rs.getString("quantity_in_packs"),
                    rs.getString("price_of_pack"), rs.getString("price_of_loose"), rs.getString("Discount_rate")});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField discounttxt1;
    private javax.swing.JTextField discounttxt2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JPanel posL;
    private javax.swing.JPanel posR;
    private javax.swing.JButton printBill1;
    private javax.swing.JComboBox<String> searchcombo;
    private javax.swing.JTextField searchtxt;
    private javax.swing.JTextField totaltxt1;
    private javax.swing.JTextField totaltxt2;
    // End of variables declaration//GEN-END:variables
}
