package PrintRaven;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author RAVEN
 */
public class PrintTableActionCellRender extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable jtable, Object o, boolean isSelected, boolean bln1, int rows, int columns) {
        Component com = super.getTableCellRendererComponent(jtable, o, isSelected, bln1, rows, columns);
        PrintPanelAction action = new PrintPanelAction();
        if (isSelected == false && rows % 2 == 0) {
            action.setBackground(Color.WHITE);
        } else {
            action.setBackground(com.getBackground());
        } 
        return action;
    }
}
