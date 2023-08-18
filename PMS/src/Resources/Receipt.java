/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Resources;

import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

/**
 *
 * @author kkk
 */
public class Receipt extends javax.swing.JPanel {

    /**
     * Creates new form Receipt
     *
     * @param bill_id
     * @param shift
     * @param model
     */
    public Receipt(String bill_id, String shift, TableModel model) {

        Object[] choices = {"Selected Product", "All products", "Cancel"};
        Object defaultChoice = choices[1];

        int choice = JOptionPane.showOptionDialog(null, "Do you want to print bill for selected product only?\nOr all the products with this Bill-Id?",
                "Pharmacy Managemnt System", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, choices, defaultChoice);

        initComponents();

        try {

            Connection con = ConnectionProvider.getcon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * From pharmacy.transactions WHERE bill_id = '" + bill_id + "' AND shift = '" + shift + "';");

            rs.next();

            String product_name = rs.getString("name");
            int packs = Integer.parseInt(rs.getString("packs"));
            int loose = Integer.parseInt(rs.getString("loose"));
            float product_price = Float.parseFloat(rs.getString("product_price"));
            float discount_price = Float.parseFloat(rs.getString("discounted_price"));

            if (choice == 0) {
                printtxt.setText(printtxt.getText() + "***********************************MEDIMISTIC*********************************\n");
                printtxt.setText(printtxt.getText() + "*********************************SALES RECEIPT********************************\n");
                printtxt.setText(printtxt.getText() + "\n");

                printtxt.setText(printtxt.getText() + "Bill-Id:  " + bill_id + "\n");

                printtxt.setText(printtxt.getText() + "------------------------------------------------------------------------------------------------------\n");
                printtxt.setText(printtxt.getText() + "Product\tPacks\tLoose\tPrice\tDisc.Price\n");
                printtxt.setText(printtxt.getText() + "------------------------------------------------------------------------------------------------------\n");

                printtxt.setText(printtxt.getText() + "\n");

                printtxt.setText(printtxt.getText() + product_name + "\t" + packs + "\t" + loose + "\t" + product_price + "\t" + discount_price);

                printtxt.setText(printtxt.getText() + "\n");

                printtxt.setText(printtxt.getText() + "------------------------------------------------------------------------------------------------------\n");

                printtxt.setText(printtxt.getText() + "\t\t" + "Total Price:\t\t" + product_price + "\n");
                printtxt.setText(printtxt.getText() + "\t\t" + "Discounted Price:\t" + discount_price + "\n");

                printtxt.setText(printtxt.getText() + "------------------------------------------------------------------------------------------------------\n");

                printtxt.setText(printtxt.getText() + "\n");
                printtxt.setText(printtxt.getText() + "**********************************************************************************\n");
                printtxt.setText(printtxt.getText() + "**********************************************************************************\n");
                printtxt.setText(printtxt.getText() + ".............................................Thank You and come again..........................................\n");

                printtxt.print();

            } else if (choice == 1) {
                float total_price = 0;
                float discounted_price = 0;

                printtxt.setText(printtxt.getText() + "***********************************MEDIMISTIC*********************************\n");
                printtxt.setText(printtxt.getText() + "*********************************SALES RECEIPT********************************\n");
                printtxt.setText(printtxt.getText() + "\n");

                printtxt.setText(printtxt.getText() + "Bill-Id:  " + bill_id + "\n");

                printtxt.setText(printtxt.getText() + "------------------------------------------------------------------------------------------------------\n");
                printtxt.setText(printtxt.getText() + "Product\tPacks\tLoose\tPrice\tDisc.Price\n");
                printtxt.setText(printtxt.getText() + "------------------------------------------------------------------------------------------------------\n");

                rs = st.executeQuery("Select * From pharmacy.transactions WHERE bill_id = '" + bill_id + "';");

                while (rs.next()) {

                    printtxt.setText(printtxt.getText() + "\n");

                    String name = rs.getString("name");
                    packs = rs.getInt("packs");
                    loose = rs.getInt("loose");
                    product_price = rs.getInt("product_price");
                    discount_price = rs.getInt("discounted_price");

                    total_price = (total_price + product_price);
                    discounted_price = (discounted_price + discount_price);

                    printtxt.setText(printtxt.getText() + name + "\t" + packs + "\t" + loose + "\t" + product_price + "\t" + discount_price);
                }
                printtxt.setText(printtxt.getText() + "\n");

                printtxt.setText(printtxt.getText() + "------------------------------------------------------------------------------------------------------\n");

                printtxt.setText(printtxt.getText() + "\t\t" + "Total Price:\t\t" + total_price + "\n");
                printtxt.setText(printtxt.getText() + "\t\t" + "Discounted Price:\t" + discounted_price + "\n");

                printtxt.setText(printtxt.getText() + "------------------------------------------------------------------------------------------------------\n");

                printtxt.setText(printtxt.getText() + "\n");
                printtxt.setText(printtxt.getText() + "**********************************************************************************\n");
                printtxt.setText(printtxt.getText() + "**********************************************************************************\n");
                printtxt.setText(printtxt.getText() + ".............................................Thank You and come again..........................................\n");

                printtxt.print();
            }

        } catch (PrinterException | NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    public Receipt(String bill_id, String price, String discounted_price, TableModel tablemodel) throws PrinterException {
        initComponents();

        printtxt.setText(printtxt.getText() + "***********************************MEDIMISTIC*********************************\n");
        printtxt.setText(printtxt.getText() + "*********************************SALES RECEIPT********************************\n");
        printtxt.setText(printtxt.getText() + "\n");

        printtxt.setText(printtxt.getText() + "Bill-Id:  " + bill_id + "\n");

        printtxt.setText(printtxt.getText() + "------------------------------------------------------------------------------------------------------\n");
        printtxt.setText(printtxt.getText() + "Product\tPacks\tLoose\tPrice\tDisc.Price\n");
        printtxt.setText(printtxt.getText() + "------------------------------------------------------------------------------------------------------\n");

        for (int i = 0; i < tablemodel.getRowCount(); i++) {

            printtxt.setText(printtxt.getText() + "\n");
            String name = (String) tablemodel.getValueAt(i, 0);
            String packs = tablemodel.getValueAt(i, 1).toString();
            String loose = tablemodel.getValueAt(i, 2).toString();
            String productPrice = tablemodel.getValueAt(i, 3).toString();
            String discountedProductPrice = tablemodel.getValueAt(i, 4).toString();

            printtxt.setText(printtxt.getText() + name + "\t" + packs + "\t" + loose + "\t" + productPrice + "\t" + discountedProductPrice);
        }
        printtxt.setText(printtxt.getText() + "\n");

        printtxt.setText(printtxt.getText() + "------------------------------------------------------------------------------------------------------\n");

        printtxt.setText(printtxt.getText() + "\t\t" + "Total Price:\t\t" + price + "\n");
        printtxt.setText(printtxt.getText() + "\t\t" + "Discounted Price:\t" + discounted_price + "\n");

        printtxt.setText(printtxt.getText() + "------------------------------------------------------------------------------------------------------\n");

        printtxt.setText(printtxt.getText() + "\n");
        printtxt.setText(printtxt.getText() + "**********************************************************************************\n");
        printtxt.setText(printtxt.getText() + "**********************************************************************************\n");
        printtxt.setText(printtxt.getText() + ".............................................Thank You and come again..........................................\n");

        printtxt.print();

    }

    public Receipt(int choice) {

        initComponents();

        if (choice == 1) {
            try {

                Connection con = ConnectionProvider.getcon();
                Statement st = con.createStatement();
                ResultSet rst,rs = st.executeQuery("Select * From pharmacy.companies ORDER BY company_name ASC");
                Statement stm = con.createStatement();
                
                String name;
                int packs, loose;

                printtxt.setText(printtxt.getText() + "***********************************MEDIMISTIC*********************************\n");
                printtxt.setText(printtxt.getText() + "*********************************PRODUCTS  LIST********************************\n");
                printtxt.setText(printtxt.getText() + "\n");

                printtxt.setText(printtxt.getText() + "------------------------------------------------------------------------------------------------------\n");
                printtxt.setText(printtxt.getText() + "Product\tPacks\tLoose\n");
                printtxt.setText(printtxt.getText() + "------------------------------------------------------------------------------------------------------\n");

                while (rs.next()) {

                    printtxt.setText(printtxt.getText() + "\t\t<- " + rs.getString("company_name") + " ->\t\t\n");

                    rst = stm.executeQuery("Select * From pharmacy.products WHERE company = '"+rs.getString("company_name")+"' ORDER BY name ASC");

                    while (rst.next()) {

                        printtxt.setText(printtxt.getText() + "\n");

                        name = rst.getString("name");
                        packs = rst.getInt("packs");
                        loose = rst.getInt("loose");

                        printtxt.setText(printtxt.getText() + name + "\t" + packs + "\t" + loose);
                    }
                    printtxt.setText(printtxt.getText() + "\n");

                }
                printtxt.setText(printtxt.getText() + "\n");

                printtxt.setText(printtxt.getText() + "------------------------------------------------------------------------------------------------------\n");

                printtxt.setText(printtxt.getText() + "------------------------------------------------------------------------------------------------------\n");

                printtxt.setText(printtxt.getText() + "\n");
                printtxt.setText(printtxt.getText() + "**********************************************************************************\n");
                printtxt.setText(printtxt.getText() + "**********************************************************************************\n");

                printtxt.print();

            } catch (PrinterException | NumberFormatException | SQLException e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
        } else if (choice == 2) {
            try {

                Connection con = ConnectionProvider.getcon();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("Select * From pharmacy.products ORDER BY name ASC");

                String name;
                int packs, loose;

                printtxt.setText(printtxt.getText() + "***********************************MEDIMISTIC*********************************\n");
                printtxt.setText(printtxt.getText() + "*********************************PRODUCTS  LIST********************************\n");
                printtxt.setText(printtxt.getText() + "\n");

                printtxt.setText(printtxt.getText() + "------------------------------------------------------------------------------------------------------\n");
                printtxt.setText(printtxt.getText() + "Product\tPacks\tLoose\n");
                printtxt.setText(printtxt.getText() + "------------------------------------------------------------------------------------------------------\n");

                while (rs.next()) {

                    printtxt.setText(printtxt.getText() + "\n");

                    name = rs.getString("name");
                    packs = rs.getInt("packs");
                    loose = rs.getInt("loose");

                    printtxt.setText(printtxt.getText() + name + "\t" + packs + "\t" + loose);
                }
                printtxt.setText(printtxt.getText() + "\n");

                printtxt.setText(printtxt.getText() + "------------------------------------------------------------------------------------------------------\n");

                printtxt.setText(printtxt.getText() + "------------------------------------------------------------------------------------------------------\n");

                printtxt.setText(printtxt.getText() + "\n");
                printtxt.setText(printtxt.getText() + "**********************************************************************************\n");
                printtxt.setText(printtxt.getText() + "**********************************************************************************\n");

                printtxt.print();

            } catch (PrinterException | NumberFormatException | SQLException e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
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

        jScrollPane1 = new javax.swing.JScrollPane();
        printtxt = new javax.swing.JTextArea();

        setLayout(null);

        printtxt.setColumns(20);
        printtxt.setRows(5);
        jScrollPane1.setViewportView(printtxt);

        add(jScrollPane1);
        jScrollPane1.setBounds(0, 0, 420, 420);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea printtxt;
    // End of variables declaration//GEN-END:variables
}
