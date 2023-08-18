/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Admin;

import static Admin.Main.f;
import Resources.ConnectionProvider;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.*;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author kkk
 */
public final class Reports extends javax.swing.JPanel {

    /**
     * Creates new form Reports
     */
    private static final DecimalFormat df = new DecimalFormat("0.00");

    /**
     * Creates new form Reports
     */
    public Reports() {
        Toolkit tk = Toolkit.getDefaultToolkit(); //Initializing the Toolkit class.
        Dimension screenSize = tk.getScreenSize(); //Get the Screen resolution of our device.
        this.setSize(screenSize.width, screenSize.height); //Set the width and height of the JFrame.

        initComponents();
        lessquantityproductstxt.setText(String.valueOf(getLessQuantityProductsCount()));
        outofstockproductstxt.setText(String.valueOf(getOutOfStockProductsCount()));
        todayssalestxt.setText(getTodaysSales());
        todaysprofittxt.setText(getTodaysProfit());
        jTable1ComponentShown();
        getMonthlySalesChart();
    }

    public void getMonthlySalesChart() {
        DefaultCategoryDataset barChartData = new DefaultCategoryDataset();

        JFreeChart barChart = ChartFactory.createBarChart3D("Monthly Sales Report", "", "Sales Amount\n", barChartData, PlotOrientation.VERTICAL, false, true, false);

        CategoryPlot barchrt = barChart.getCategoryPlot();
        barchrt.setBackgroundPaint(Color.WHITE);
        barchrt.setRangeGridlinePaint(Color.BLUE);

        BarRenderer renderer = (BarRenderer) barchrt.getRenderer();
        Color clr = new Color(51, 204, 0);
        renderer.setSeriesPaint(0, clr);

        Font font = new Font("Segoe UI Semibold", Font.BOLD, 30);
        barChart.getTitle().setFont(font);

        ChartPanel barPanel = new ChartPanel(barChart);

        try {

            Connection con = ConnectionProvider.getcon();
            Statement st;
            ResultSet rs;

            DateTimeFormatter dtfd = DateTimeFormatter.ofPattern("dd");
            DateTimeFormatter dtfm = DateTimeFormatter.ofPattern("MM");
            DateTimeFormatter dtfy = DateTimeFormatter.ofPattern("yyyy");

            LocalDateTime now = LocalDateTime.now();

            int todaysDate = Integer.parseInt(dtfd.format(now));
            int todaysMonth = Integer.parseInt(dtfm.format(now));
            int todaysYear = Integer.parseInt(dtfy.format(now));

            float sales[] = new float[todaysDate];

            st = con.createStatement();
            rs = st.executeQuery("Select * From pharmacy.transactions");

            for (; rs.next();) {
                String id = rs.getString("bill_id");

                int transactionDate = Integer.parseInt(id.substring(0, 2));
                int transactionMonth = Integer.parseInt(id.substring(4, 6));
                int transactionYear = Integer.parseInt(id.substring(8, 12));

                if (transactionYear == todaysYear && transactionMonth == todaysMonth && transactionDate <= todaysDate && transactionMonth != 0 && transactionDate != 0) {
                    sales[transactionDate - 1] += Float.parseFloat(rs.getString("discounted_price"));
                }
            }

            for (int i = 0; i < todaysDate; i++) {
                barChartData.setValue(sales[i], "Sales Of The Day", String.valueOf(i + 1));
            }

        } catch (NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        chartpanel.removeAll();
        chartpanel.add(barPanel, BorderLayout.CENTER);
        chartpanel.validate();

    }
//
//    public void getYearlySalesChart() {
//        DefaultCategoryDataset barChartData = new DefaultCategoryDataset();
//
//        barChartData.setValue(50000, "Contribution Amount", "January");
//        barChartData.setValue(20000, "Contribution Amount", "Februaury");
//        barChartData.setValue(30000, "Contribution Amount", "March");
//        barChartData.setValue(60000, "Contribution Amount", "April");
//
//        JFreeChart barChart = ChartFactory.createBarChart("Monthly Sales Report", "Monthly", "Sales", barChartData, PlotOrientation.VERTICAL, false, true, false);
//
//        CategoryPlot barchrt = new CategoryPlot();
//
//        barchrt.setRangeGridlinePaint(Color.GREEN);
//
//        ChartPanel barPanel = new ChartPanel(barChart);
//
//        chartpanel.removeAll();
//        chartpanel.add(barPanel, BorderLayout.CENTER);
//        chartpanel.validate();
//
//    }

    public String getTodaysSales() {

        float sales = 0f;

        try {

            Connection con = ConnectionProvider.getcon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * From pharmacy.transactions");

            while (rs.next()) {
                String id = rs.getString("bill_id");

                int transactionDate = Integer.parseInt(id.substring(0, 2));

                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd");

                LocalDateTime now = LocalDateTime.now();

                int todaysDate = Integer.parseInt(dtf.format(now));

                if (transactionDate == todaysDate) {
                    sales += Float.parseFloat(rs.getString("discounted_price"));
                }
            }

        } catch (NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        return df.format(sales);
    }

    public String getTodaysProfit() {

        float profit = 0f;
        float wsales = 0f;
        float sales = 0f;

        try {

            Connection con = ConnectionProvider.getcon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * From pharmacy.transactions");
            st = con.createStatement();
            ResultSet rst;

            while (rs.next()) {
                String id = rs.getString("bill_id");

                int transactionDate = Integer.parseInt(id.substring(0, 2));

                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd");

                LocalDateTime now = LocalDateTime.now();

                int todaysDate = Integer.parseInt(dtf.format(now));

                if (transactionDate == todaysDate) {
                    rst = st.executeQuery("Select * From pharmacy.products where name = '" + rs.getString("name") + "'");
                    rst.next();
                    sales += Float.parseFloat(rs.getString("discounted_price"));
                    wsales += Float.parseFloat(rst.getString("wprice_of_pack")) * Float.parseFloat(rs.getString("packs"));
                    wsales += Float.parseFloat(rst.getString("wprice_of_loose")) * Float.parseFloat(rs.getString("loose"));
                }
            }

            profit = sales - wsales;

        } catch (NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        return df.format(profit);
    }

    public int getLessQuantityProductsCount() {

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

    public int showLessQuantityProducts() {

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

        int a = 0;

        try {

            for (int i = jTable1.getRowCount() - 1; i >= 0; i--) {
                model.removeRow(i);
            }

            Connection con = ConnectionProvider.getcon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * From products where packs < minimum_quantity");

            while (rs.next()) {
                if (Integer.parseInt(rs.getString("packs")) < Integer.parseInt(rs.getString("minimum_quantity"))) {
                    model.addRow(new Object[]{rs.getString("name"), rs.getString("formula"), rs.getString("type"),
                        rs.getString("packs"), rs.getString("loose"), rs.getString("minimum_quantity")});
                }
            }

        } catch (NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return a;
    }

    public int getOutOfStockProductsCount() {

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

    public int showOutOfStockProducts() {

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

        int a = 0;

        try {

            for (int i = jTable1.getRowCount() - 1; i >= 0; i--) {
                model.removeRow(i);
            }

            Connection con = ConnectionProvider.getcon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * From products where packs < '1'");

            while (rs.next()) {
                if (Integer.parseInt(rs.getString("packs")) < 1) {
                    model.addRow(new Object[]{rs.getString("name"), rs.getString("formula"), rs.getString("type"),
                        rs.getString("packs"), rs.getString("loose"), rs.getString("minimum_quantity")});
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

        ReportsL = new javax.swing.JPanel();
        jButton11 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        ReportsR = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        lessquantityproductstxt = new javax.swing.JLabel();
        todayssalestxt = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        outofstockproductstxt = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        todaysprofittxt = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        profitDateChooser = new com.toedter.calendar.JDateChooser();
        jButton15 = new javax.swing.JButton();
        reqdateprofittxt = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        chartpanel = new javax.swing.JPanel();
        salesDateChooser = new com.toedter.calendar.JDateChooser();
        jLabel15 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        reqdatesalestxt = new javax.swing.JTextField();
        jButton14 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();

        ReportsL.setBackground(new java.awt.Color(0, 0, 102));
        ReportsL.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        ReportsL.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        ReportsL.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 260, 60));

        jButton8.setBackground(new java.awt.Color(204, 255, 255));
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
        ReportsL.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 520, 280, 60));

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logout.png"))); // NOI18N
        jButton3.setText("  LOGOUT ");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        ReportsL.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 723, 140, 40));

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
        ReportsL.add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 660, 260, 60));

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
        ReportsL.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 260, 60));

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
        ReportsL.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 590, 260, 60));

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
        ReportsL.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 260, 60));

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
        ReportsL.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 450, 260, 60));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/profile(2).png"))); // NOI18N
        ReportsL.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 180, 190));

        jLabel4.setBackground(new java.awt.Color(0, 0, 51));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 178, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("ADMIN\n");
        ReportsL.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, 180, 40));

        ReportsR.setBackground(new java.awt.Color(204, 255, 255));
        ReportsR.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        ReportsR.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        ReportsR.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 0, 30, 30));

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
        ReportsR.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 0, 30, 30));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sales.png"))); // NOI18N
        ReportsR.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 90, -1, 140));

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
        ReportsR.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(-270, 660, 260, 60));

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/less quantity.png"))); // NOI18N
        ReportsR.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 70, 120, 80));

        lessquantityproductstxt.setFont(new java.awt.Font("Segoe UI", 0, 60)); // NOI18N
        lessquantityproductstxt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lessquantityproductstxt.setText("36");
        ReportsR.add(lessquantityproductstxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 80, 90, 90));

        todayssalestxt.setFont(new java.awt.Font("Segoe UI", 0, 54)); // NOI18N
        todayssalestxt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        todayssalestxt.setText("12346.00");
        ReportsR.add(todayssalestxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, 240, 110));

        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton4.setText("View");
        jButton4.setBorder(null);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        ReportsR.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 150, 80, 30));

        jLabel5.setBackground(new java.awt.Color(153, 255, 153));
        jLabel5.setFont(new java.awt.Font("Segoe UI Semibold", 1, 36)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Today's Sales");
        jLabel5.setToolTipText("");
        jLabel5.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        jLabel5.setOpaque(true);
        ReportsR.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 420, 200));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Name", "Formula", "Type", "Packs", "Loose", "Minimum Quantity"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
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
        jScrollPane1.setViewportView(jTable1);

        ReportsR.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 210, 540, 270));

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/out of stock.png"))); // NOI18N
        ReportsR.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 70, 120, 80));

        outofstockproductstxt.setFont(new java.awt.Font("Segoe UI", 0, 60)); // NOI18N
        outofstockproductstxt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        outofstockproductstxt.setText("36");
        ReportsR.add(outofstockproductstxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 80, 90, 90));

        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton5.setText("View");
        jButton5.setBorder(null);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        ReportsR.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 150, 80, 30));

        jLabel6.setBackground(new java.awt.Color(255, 153, 153));
        jLabel6.setFont(new java.awt.Font("Segoe UI Semibold", 1, 20)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Out Of Stock Products");
        jLabel6.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        jLabel6.setOpaque(true);
        ReportsR.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 40, 250, 150));

        jLabel7.setBackground(new java.awt.Color(255, 227, 153));
        jLabel7.setFont(new java.awt.Font("Segoe UI Semibold", 1, 20)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Products In Less Quantity");
        jLabel7.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        jLabel7.setOpaque(true);
        ReportsR.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 40, 250, 150));

        todaysprofittxt.setFont(new java.awt.Font("Segoe UI", 0, 54)); // NOI18N
        todaysprofittxt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        todaysprofittxt.setText("12346.00");
        ReportsR.add(todaysprofittxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 320, 240, 110));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/profit.png"))); // NOI18N
        ReportsR.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 300, -1, 140));

        jLabel16.setBackground(java.awt.Color.yellow);
        jLabel16.setFont(new java.awt.Font("Segoe UI Semibold", 1, 36)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Today's Profit");
        jLabel16.setToolTipText("");
        jLabel16.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        jLabel16.setOpaque(true);
        ReportsR.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, 420, 200));

        jLabel17.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Date");
        ReportsR.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 660, 50, 30));
        ReportsR.add(profitDateChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 660, 290, 30));

        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search.png"))); // NOI18N
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        ReportsR.add(jButton15, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 700, 50, 30));

        reqdatesalestxt.setEditable(false);
        reqdateprofittxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reqdateprofittxtActionPerformed(evt);
            }
        });
        ReportsR.add(reqdateprofittxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 700, 240, 30));

        jLabel18.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Sales");
        ReportsR.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 700, 50, 30));

        jLabel9.setBackground(java.awt.Color.yellow);
        jLabel9.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Find Profit");
        jLabel9.setToolTipText("");
        jLabel9.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        jLabel9.setOpaque(true);
        ReportsR.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 620, 370, 130));

        chartpanel.setBackground(new java.awt.Color(51, 204, 0));
        chartpanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        chartpanel.setLayout(new java.awt.BorderLayout());
        ReportsR.add(chartpanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 510, 620, 240));
        ReportsR.add(salesDateChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 520, 290, 30));

        jLabel15.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Date");
        ReportsR.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 520, 50, 30));

        jLabel14.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Sales");
        ReportsR.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 560, 50, 30));

        reqdatesalestxt.setEditable(false);
        reqdatesalestxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reqdatesalestxtActionPerformed(evt);
            }
        });
        ReportsR.add(reqdatesalestxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 560, 240, 30));

        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search.png"))); // NOI18N
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        ReportsR.add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 560, 50, 30));

        jLabel13.setBackground(new java.awt.Color(153, 255, 153));
        jLabel13.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Find Sales");
        jLabel13.setToolTipText("");
        jLabel13.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        jLabel13.setOpaque(true);
        ReportsR.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 480, 370, 130));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1370, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(ReportsL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(ReportsR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 770, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(ReportsL, javax.swing.GroupLayout.PREFERRED_SIZE, 770, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ReportsR, javax.swing.GroupLayout.PREFERRED_SIZE, 770, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        f.getContentPane().removeAll();
        f.getContentPane().add(new AdminDashboard());
        f.revalidate();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        f.getContentPane().removeAll();
        f.getContentPane().add(new LoginPanel());
        f.revalidate();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        f.getContentPane().removeAll();
        f.getContentPane().add(new History());
        f.revalidate();
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        f.getContentPane().removeAll();
        f.getContentPane().add(new PosPanel());
        f.revalidate();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        f.getContentPane().removeAll();
        f.getContentPane().add(new ManageDistributors());
        f.revalidate();
    }//GEN-LAST:event_jButton9ActionPerformed

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

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        showLessQuantityProducts();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable1ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jTable1ComponentShown
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

        for (int i = jTable1.getRowCount() - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }//GEN-LAST:event_jTable1ComponentShown

    private void jTable1ComponentShown() {
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

        for (int i = jTable1.getRowCount() - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        showOutOfStockProducts();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
        SimpleDateFormat dateformat = new SimpleDateFormat("ddMMyyyy");
        Date date = profitDateChooser.getDate();

        String reqProfitDate = "";
        if (date != null) {
            reqProfitDate = dateformat.format(profitDateChooser.getDate());
        } else {
            JOptionPane.showMessageDialog(null, "Please select valid date to find sales!");
            return;
        }

        try {

            Connection con = ConnectionProvider.getcon();
            Statement st, stm;
            ResultSet rs, rst;

            float sales = 0f;
            float wsales = 0f;
            float profit = 0f;

            st = con.createStatement();
            rs = st.executeQuery("Select * From pharmacy.transactions");

            for (int i = 0; rs.next(); i++) {
                String id = rs.getString("bill_id");

                String transactionDate = id.substring(0, 2);
                String transactionMonth = id.substring(4, 6);
                String transactionyear = id.substring(8, 12);

                String thisDate = transactionDate + transactionMonth + transactionyear;

                if (thisDate.equals(reqProfitDate)) {
                    stm = con.createStatement();
                    rst = stm.executeQuery("Select * From pharmacy.products where name = '" + rs.getString("name") + "'");
                    rst.next();
                    sales += Float.parseFloat(rs.getString("discounted_price"));
                    wsales += Float.parseFloat(rst.getString("wprice_of_pack")) * Float.parseFloat(rs.getString("packs"));
                    wsales += Float.parseFloat(rst.getString("wprice_of_loose")) * Float.parseFloat(rs.getString("loose"));
                    stm = null;
                }
            }

            profit = sales - wsales;

            reqdateprofittxt.setText(String.valueOf(df.format(profit)));

        } catch (NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_jButton15ActionPerformed

    private void reqdateprofittxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reqdateprofittxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_reqdateprofittxtActionPerformed

    private void reqdatesalestxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reqdatesalestxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_reqdatesalestxtActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
        SimpleDateFormat dateformat = new SimpleDateFormat("ddMMyyyy");
        Date date = salesDateChooser.getDate();

        String reqSalesDate = "";
        if (date != null) {
            reqSalesDate = dateformat.format(salesDateChooser.getDate());
        } else {
            JOptionPane.showMessageDialog(null, "Please select valid date to find sales!");
            return;
        }

        try {

            Connection con = ConnectionProvider.getcon();
            Statement st;
            ResultSet rs;

            float sales = 0f;

            st = con.createStatement();
            rs = st.executeQuery("Select * From pharmacy.transactions");

            for (int i = 0; rs.next(); i++) {
                String id = rs.getString("bill_id");

                String transactionDate = id.substring(0, 2);
                String transactionMonth = id.substring(4, 6);
                String transactionyear = id.substring(8, 12);

                String thisDate = transactionDate + transactionMonth + transactionyear;

                if (thisDate.equals(reqSalesDate)) {
                    sales += Float.parseFloat(rs.getString("discounted_price"));
                }

            }

            reqdatesalestxt.setText(String.valueOf(df.format(sales)));

        } catch (NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_jButton14ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ReportsL;
    private javax.swing.JPanel ReportsR;
    private javax.swing.JPanel chartpanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lessquantityproductstxt;
    private javax.swing.JLabel outofstockproductstxt;
    private com.toedter.calendar.JDateChooser profitDateChooser;
    private javax.swing.JTextField reqdateprofittxt;
    private javax.swing.JTextField reqdatesalestxt;
    private com.toedter.calendar.JDateChooser salesDateChooser;
    private javax.swing.JLabel todaysprofittxt;
    private javax.swing.JLabel todayssalestxt;
    // End of variables declaration//GEN-END:variables
}
