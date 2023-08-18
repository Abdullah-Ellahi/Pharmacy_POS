package EditRemoveRaven;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author RAVEN
 */
public class TableActionCellRender extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable jtable, Object o, boolean isSelected, boolean bln1, int rows, int columns) {
        Component com = super.getTableCellRendererComponent(jtable, o, isSelected, bln1, rows, columns);
        PanelAction action = new PanelAction();
        if (isSelected == false && rows % 2 == 0) {
            action.setBackground(Color.WHITE);
        } else {
            action.setBackground(com.getBackground());
        } 
        return action;
    }
}
