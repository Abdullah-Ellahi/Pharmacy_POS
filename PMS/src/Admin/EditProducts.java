/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Admin;

import static Admin.Main.f;
import EditRemoveRaven.TableActionCellEditor;
import EditRemoveRaven.TableActionCellRender;
import EditRemoveRaven.TableActionEvent;
import Resources.ConnectionProvider;

import java.awt.Dimension;
import java.awt.HeadlessException;
import javax.swing.*;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kkk
 */
public final class EditProducts extends javax.swing.JPanel {

    /**
     * Creates new form EditProducts
     */
    public String IntegerPattern = "^\\d+$";
    public String DecimalPattern = "^[1-9]\\d*([\\,\\.]\\d{2})?$";//for decimal values upto two places
    public int CheckName = 0;

    /**
     * Creates new form EditProducts
     */
    public EditProducts() {
        Toolkit tk = Toolkit.getDefaultToolkit(); //Initializing the Toolkit class.
        Dimension screenSize = tk.getScreenSize(); //Get the Screen resolution of our device.
        this.setSize(screenSize.width, screenSize.height); //Set the width and height of the JFrame.

        initComponents();

        TableActionEvent event;
        event = new TableActionEvent() {
            @Override
            public void onEdit(int row) {

                int index = jTable2.getSelectedRow();

                DefaultTableModel model = (DefaultTableModel) jTable2.getModel();

                String name = (String) model.getValueAt(index, 1);
                String formula = (String) model.getValueAt(index, 2);
                String type = (String) model.getValueAt(index, 3);
                String company = (String) model.getValueAt(index, 4);
                String packs = (String) model.getValueAt(index, 5);
                String loose = (String) model.getValueAt(index, 6);
                String quantity_in_pack = (String) model.getValueAt(index, 7);
                String price_of_pack = (String) model.getValueAt(index, 8);
                String wprice_of_pack = (String) model.getValueAt(index, 9);
                String discount_rate = (String) model.getValueAt(index, 11);
                String minimum_quantity = (String) model.getValueAt(index, 12);

                nametxt.setText(name);
                formulatxt.setText(formula);
                typecombotxt.setSelectedItem(type);
                distributorcombotxt.setSelectedItem(company);
                packstxt.setText(packs);
                loosetxt.setText(loose);
                quantityinpacktxt.setText(quantity_in_pack);
                priceofpacktxt.setText(price_of_pack);
                wpriceofpacktxt.setText(wprice_of_pack);
                discountratetxt.setText(discount_rate);
                minimumquantitytxt.setText(minimum_quantity);
            }

            @Override
            public void onDelete(int row) {
                if (jTable2.isEditing()) {
                    jTable2.getCellEditor().stopCellEditing();

                }
                int index = jTable2.getSelectedRow();

                DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
                String id = model.getValueAt(index, 0).toString();
                String name = model.getValueAt(index, 1).toString();

                int result = JOptionPane.showConfirmDialog(null, "Do you really want to delete the Product: " + name, "Pharmacy Management System",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (result == JOptionPane.YES_OPTION) {

                    Connection con;
                    PreparedStatement stm;

                    try {
                        con = ConnectionProvider.getcon();
                        stm = con.prepareStatement("DELETE FROM `pharmacy`.`products` WHERE (`id` = '" + id + "') and (`name` = '" + name + "');");
                        stm.execute();
                        model.removeRow(row);
                        JOptionPane.showMessageDialog(null, "Product deleted successfully!");
                    } catch (HeadlessException | SQLException e) {
                        JOptionPane.showMessageDialog(null,"Error"+ e.getMessage());
                    }
                }
            }
        };

        jTable2.getColumnModel().getColumn(13).setCellRenderer(new TableActionCellRender());
        jTable2.getColumnModel().getColumn(13).setCellEditor(new TableActionCellEditor(event));
        jTable2ComponentShown();
        types();
        Companies();
    }

    private void types() {
        Connection con;
        Statement stm;
        ResultSet rs;
        try {
            con = ConnectionProvider.getcon();
            stm = con.createStatement();
            rs = stm.executeQuery("select * from types");

            while (rs.next()) {
                typecombotxt.addItem(rs.getString("type_name"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error"+ e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error"+ e.getMessage());
        }
    }

    private void Companies() {
        Connection con;
        Statement stm;
        ResultSet rs;
        try {
            con = ConnectionProvider.getcon();
            stm = con.createStatement();
            rs = stm.executeQuery("select * from companies");

            while (rs.next()) {
                distributorcombotxt.addItem(rs.getString("company_name"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error"+ e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error"+ e.getMessage());
        }
    }
    
    public void jTable2ComponentShown() {
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();

        try {
            for (int i = jTable2.getRowCount() - 1; i >= 0; i--) {
                model.removeRow(i);
            }

            Connection con = ConnectionProvider.getcon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * From products");

            while (rs.next()) {
                model.addRow(new Object[]{rs.getString("id"), rs.getString("name"), rs.getString("formula"), rs.getString("type"),
                    rs.getString("company"), rs.getString("packs"), rs.getString("loose"), rs.getString("quantity_in_packs"),
                    rs.getString("price_of_pack"), rs.getString("wprice_of_pack"), rs.getString("price_of_loose"), rs.getString("Discount_rate"),
                    rs.getString("minimum_quantity")});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error"+ e.getMessage());
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        EPL = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        EPR = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        searchtxt = new javax.swing.JTextField();
        searchcombo = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        packstxt = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        loosetxt = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        quantityinpacktxt = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        priceofpacktxt = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        discountratetxt = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        minimumquantitytxt = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        nametxt = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        formulatxt = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        wpriceofpacktxt = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        typecombotxt = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        distributorcombotxt = new javax.swing.JComboBox<>();

        EPL.setBackground(new java.awt.Color(0, 0, 102));
        EPL.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        EPL.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton6.setBackground(new java.awt.Color(204, 255, 255));
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
        EPL.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 450, 280, 60));

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
        EPL.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 260, 60));

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
        EPL.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 520, 260, 60));

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
        EPL.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 590, 260, 60));

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
        EPL.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 660, 260, 60));

        jButton11.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dashboard.png"))); // NOI18N
        jButton11.setText("    DASHBOARD");
        jButton11.setAlignmentY(0.0F);
        jButton11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton11.setPreferredSize(new java.awt.Dimension(202, 107));
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        EPL.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 260, 60));

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
        EPL.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 260, 60));

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(System.getProperty("user.dir") + "\\src\\images\\logout.png"));
        jButton3.setText("  LOGOUT ");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        EPL.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 723, 140, 40));

        jLabel4.setBackground(new java.awt.Color(0, 0, 51));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 178, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("ADMIN\n");
        EPL.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, 180, 40));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/profile(2).png"))); // NOI18N
        EPL.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 180, 190));

        EPR.setBackground(new java.awt.Color(204, 255, 255));
        EPR.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        EPR.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        EPR.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 0, 30, 30));

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
        EPR.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 0, 30, 30));

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
        EPR.add(searchtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, 660, 30));

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
        EPR.add(searchcombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 140, 30));

        jLabel5.setBackground(new java.awt.Color(204, 255, 255));
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel5.setText("Search by  ");
        jLabel5.setAlignmentX(0.5F);
        jLabel5.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jLabel5.setOpaque(true);
        EPR.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 90, 30));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search x30.png"))); // NOI18N
        jButton4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        EPR.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 10, 60, 30));

        jPanel1.setLayout(new javax.swing.OverlayLayout(jPanel1));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Formula", "Type", "Distributor", "Packs", "Loose", "Quantity In Pack", "Price Of Pack", "Wholesale Price POf Pack", "Price Of Loose", "Discount Rate", "Minimum Quantity", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
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
        jScrollPane2.setViewportView(jTable2);

        jPanel1.add(jScrollPane2);

        EPR.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 1070, 340));

        jLabel7.setText("Packs");
        EPR.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 400, 60, -1));
        EPR.add(packstxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 420, 190, -1));

        jLabel8.setText("Loose");
        EPR.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 400, 60, -1));
        EPR.add(loosetxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 420, 190, -1));

        jLabel9.setText("Quantity In Pack");
        EPR.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 450, 90, -1));
        EPR.add(quantityinpacktxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 470, 190, -1));

        jLabel10.setText("Price Of Pack");
        EPR.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 400, 90, -1));
        EPR.add(priceofpacktxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 420, 190, -1));

        jLabel11.setText("Minimum Quantity");
        EPR.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 450, 110, -1));
        EPR.add(discountratetxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 470, 190, -1));

        jLabel12.setText("Discount Rate");
        EPR.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 450, 80, -1));
        EPR.add(minimumquantitytxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 470, 190, -1));

        jLabel13.setText("Name");
        EPR.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, 40, -1));

        nametxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nametxtKeyReleased(evt);
            }
        });
        EPR.add(nametxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, 190, -1));

        jLabel14.setText("Formula");
        EPR.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 400, 60, -1));
        EPR.add(formulatxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 420, 190, -1));

        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/save.png"))); // NOI18N
        jButton5.setText(" Save Changes");
        jButton5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        EPR.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 510, 150, 40));

        jButton13.setBackground(new java.awt.Color(153, 153, 153));
        jButton13.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add company.png"))); // NOI18N
        jButton13.setText(" Add Company");
        jButton13.setToolTipText("");
        jButton13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        EPR.add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 610, 250, 100));

        jButton14.setBackground(new java.awt.Color(204, 255, 204));
        jButton14.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add product.png"))); // NOI18N
        jButton14.setText(" Add Product ");
        jButton14.setToolTipText("");
        jButton14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        EPR.add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 610, 250, 100));

        jSeparator1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        EPR.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 570, 1090, 10));

        jButton15.setBackground(new java.awt.Color(102, 204, 255));
        jButton15.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add category.png"))); // NOI18N
        jButton15.setText("<html>&ensp;Add Product<br>&emsp;&ensp;Type</html>");
        jButton15.setToolTipText("");
        jButton15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jButton15.setIconTextGap(5);
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        EPR.add(jButton15, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 610, 250, 100));

        jButton16.setBackground(new java.awt.Color(255, 255, 204));
        jButton16.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Edit Products.png"))); // NOI18N
        jButton16.setText(" Edit Products ");
        jButton16.setToolTipText("");
        jButton16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        EPR.add(jButton16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 600, 260, 120));

        jLabel15.setText("Wholesale Price Of Pack");
        EPR.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 450, 130, -1));
        EPR.add(wpriceofpacktxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 470, 190, -1));

        jLabel16.setText("Product Type");
        EPR.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 510, 80, -1));

        EPR.add(typecombotxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 530, 190, 30));

        jLabel17.setText("Company");
        EPR.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 450, 80, -1));

        EPR.add(distributorcombotxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 470, 190, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1370, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(EPL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(EPR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 770, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(EPL, javax.swing.GroupLayout.PREFERRED_SIZE, 770, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(EPR, javax.swing.GroupLayout.PREFERRED_SIZE, 770, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
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

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        f.getContentPane().removeAll();
        f.getContentPane().add(new ManageDistributors());
        f.revalidate();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        f.getContentPane().removeAll();
        f.getContentPane().add(new History());
        f.revalidate();
    }//GEN-LAST:event_jButton10ActionPerformed

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

    private void searchtxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchtxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchtxtActionPerformed

    private void searchtxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchtxtKeyReleased
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();

        try {
            for (int i = jTable2.getRowCount() - 1; i >= 0; i--) {
                model.removeRow(i);
            }

            String selected = (String) searchcombo.getSelectedItem();
            
            selected = switch (selected) {
                case "Name" -> "name";
                case "Formula" -> "formula";
                case "Type" -> "type";
                case "Company" -> "company";
                case "Discount Rate" -> "discount_rate";
                default -> "";
            };

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

    private void searchcomboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchcomboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchcomboActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();

        try {
            for (int i = jTable2.getRowCount() - 1; i >= 0; i--) {
                model.removeRow(i);
            }

            String selected = (String) searchcombo.getSelectedItem();
            
            selected = switch (selected) {
                case "Name" -> "name";
                case "Formula" -> "formula";
                case "Type" -> "type";
                case "Company" -> "company";
                case "Discount Rate" -> "discount_rate";
                default -> "";
            };

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

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
        int index = jTable2.getSelectedRow();

        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();

        String name = (String) model.getValueAt(index, 1);
        String formula = (String) model.getValueAt(index, 2);
        String type = (String) model.getValueAt(index, 3);
        String company = (String) model.getValueAt(index, 4);
        String packs = (String) model.getValueAt(index, 5);
        String loose = (String) model.getValueAt(index, 6);
        String quantity_in_pack = (String) model.getValueAt(index, 7);
        String price_of_pack = (String) model.getValueAt(index, 8);
        String wprice_of_pack = (String) model.getValueAt(index, 9);
        String discount_rate = (String) model.getValueAt(index, 11);
        String minimum_quantity = (String) model.getValueAt(index, 12);

        nametxt.setText(name);
        formulatxt.setText(formula);
        typecombotxt.setSelectedItem(type);
        distributorcombotxt.setSelectedItem(company);
        packstxt.setText(packs);
        loosetxt.setText(loose);
        quantityinpacktxt.setText(quantity_in_pack);
        priceofpacktxt.setText(price_of_pack);
        wpriceofpacktxt.setText(wprice_of_pack);
        discountratetxt.setText(discount_rate);
        minimumquantitytxt.setText(minimum_quantity);
    }//GEN-LAST:event_jTable2MouseClicked

    private void jTable2ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jTable2ComponentShown
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();

        try {
            for (int i = jTable2.getRowCount() - 1; i >= 0; i--) {
                model.removeRow(i);
            }

            Connection con = ConnectionProvider.getcon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * From products");

            while (rs.next()) {
                model.addRow(new Object[]{rs.getString("id"), rs.getString("name"), rs.getString("formula"), rs.getString("type"),
                    rs.getString("company"), rs.getString("packs"), rs.getString("loose"), rs.getString("quantity_in_packs"),
                    rs.getString("price_of_pack"), rs.getString("wprice_of_pack"), rs.getString("price_of_loose"), rs.getString("Discount_rate"),
                    rs.getString("minimum_quantity")});
        }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_jTable2ComponentShown

    
    
    private void nametxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nametxtKeyReleased
        // TODO add your handling code here:
        String name = nametxt.getText();

        if (!name.equals("")) {
            CheckName = 0;

            Connection myConn = null;
            Statement myStmt = null;
            ResultSet myRs = null;

            try {
                // 1. Get a connection to database
                myConn = ConnectionProvider.getcon();

                // 2. Create a statement
                myStmt = myConn.createStatement();

                // 3. Execute SQL query
                myRs = myStmt.executeQuery("select * from products where name = '" + name + "'");

                while (myRs.next()) {
                    CheckName = 1;
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }//GEN-LAST:event_nametxtKeyReleased

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:

        int index = jTable2.getSelectedRow();

        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        String id = model.getValueAt(index, 0).toString();
        String old_name = model.getValueAt(index, 1).toString();

        String new_name = nametxt.getText();
        String formula = formulatxt.getText();
        String type = (String) typecombotxt.getSelectedItem();
        String company = (String) distributorcombotxt.getSelectedItem();
        String packs = packstxt.getText();
        String loose = loosetxt.getText();
        String quantity_in_pack = quantityinpacktxt.getText();
        String price_of_pack = priceofpacktxt.getText();
        String wprice_of_pack = wpriceofpacktxt.getText();
        String discount_rate = discountratetxt.getText();
        String minimum_quantity = minimumquantitytxt.getText();

        if (new_name.equals("")) {
            JOptionPane.showMessageDialog(null, "Name cannot be empty!");
        } else if (formula.equals("")) {
            JOptionPane.showMessageDialog(null, "Formula cannot be empty!");
        } else if (!packs.matches(IntegerPattern) || packs.equals("")) {
            JOptionPane.showMessageDialog(null, "Invalid pattern!\n'Packs' field should contain 'Integers' only!");
        } else if (!loose.matches(IntegerPattern) || loose.equals("")) {
            JOptionPane.showMessageDialog(null, "Invalid pattern!\n'Loose' field should contain 'Integers' only!");
        } else if (!quantity_in_pack.matches(IntegerPattern) || quantity_in_pack.equals("")) {
            JOptionPane.showMessageDialog(null, "Invalid pattern!\n'Quantity In Pack' field should contain 'Integers' only.");
        } else if (!price_of_pack.matches(DecimalPattern) && !price_of_pack.matches(IntegerPattern) || price_of_pack.equals("")) {
            JOptionPane.showMessageDialog(null, "Invalid pattern!\n'Price Of Pack' field should contain 'Integers' only.");
        } else if (!wprice_of_pack.matches(DecimalPattern) && !wprice_of_pack.matches(IntegerPattern) || wprice_of_pack.equals("")) {
            JOptionPane.showMessageDialog(null, "Invalid pattern!\n'Wholesale Price Of Pack' field should contain 'Integers' only.");
        } else if (!discount_rate.matches(DecimalPattern) || discount_rate.equals("")) {
            JOptionPane.showMessageDialog(null, "Invalid pattern!\n'Discount Rate' field should contain Decimal values upto two places only.");
        } else if (!minimum_quantity.matches(IntegerPattern) || minimum_quantity.equals("")) {
            JOptionPane.showMessageDialog(null, "Invalid pattern!\n'Minimum Quantity' field should contain 'Integers' only.");
        } else if (CheckName == 1 && !(old_name.toUpperCase()).equals(new_name.toUpperCase())) {
            JOptionPane.showMessageDialog(null, "Product name already exists!\nPlease enter another name");
        } else {

            Connection con;
            PreparedStatement stm;

            try {
                con = ConnectionProvider.getcon();
                stm = con.prepareStatement("UPDATE `pharmacy`.`products` SET `name` = ? , `formula` = ? , `type` = ? , `company` = ? , `packs` = ? , `loose` = ?"
                    + ", `quantity_in_packs` = ? , `price_of_pack` = ?  , `wprice_of_pack` = ?  , `price_of_loose` = ?  , `discount_rate` = ?  , `minimum_quantity` = ?  WHERE (`id` = ?) and (`name` = ?);");

                stm.setString(1, new_name);
                stm.setString(2, formula);
                stm.setString(3, type);
                stm.setString(4, company);
                stm.setString(5, packs);
                stm.setString(6, loose);
                stm.setString(7, quantity_in_pack);
                stm.setString(8, price_of_pack);
                stm.setString(9, wprice_of_pack);

                String price_of_loose = Float.toString(Float.valueOf(price_of_pack) / Float.valueOf(quantity_in_pack));
                stm.setString(10, price_of_loose);

                stm.setString(11, discount_rate);
                stm.setString(12, minimum_quantity);
                stm.setString(13, id);
                stm.setString(14, old_name);

                stm.executeUpdate();
                JOptionPane.showMessageDialog(null, "Changes Saved successfully!");

                //refreshing the table
                jTable2ComponentShown();

            } catch (HeadlessException | NumberFormatException | SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        f.getContentPane().removeAll();
        f.getContentPane().add(new AddCompany());
        f.revalidate();
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
        f.getContentPane().removeAll();
        f.getContentPane().add(new AddProducts());
        f.revalidate();
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
        f.getContentPane().removeAll();
        f.getContentPane().add(new AddType());
        f.revalidate();
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton16ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel EPL;
    private javax.swing.JPanel EPR;
    private javax.swing.JTextField discountratetxt;
    private javax.swing.JComboBox<String> distributorcombotxt;
    private javax.swing.JTextField formulatxt;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField loosetxt;
    private javax.swing.JTextField minimumquantitytxt;
    private javax.swing.JTextField nametxt;
    private javax.swing.JTextField packstxt;
    private javax.swing.JTextField priceofpacktxt;
    private javax.swing.JTextField quantityinpacktxt;
    private javax.swing.JComboBox<String> searchcombo;
    private javax.swing.JTextField searchtxt;
    private javax.swing.JComboBox<String> typecombotxt;
    private javax.swing.JTextField wpriceofpacktxt;
    // End of variables declaration//GEN-END:variables
}
