package Velocity;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Patrick Fink
 */
public class VelocityTableRenderer extends DefaultTableCellRenderer
{

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col)
    {
        Component comp = super.getTableCellRendererComponent(table, value, hasFocus, hasFocus, row, col);

        int ueberschritten = (int) table.getValueAt(row, table.convertColumnIndexToModel(5));

        if (col == 5)
        {
            if (ueberschritten > 30)
            {
                comp.setBackground(Color.RED);
            } else if (ueberschritten <= 30 && ueberschritten > 20)
            {
                comp.setBackground(Color.ORANGE);
            } else if (ueberschritten <= 20 && ueberschritten > 10)
            {
                comp.setBackground(Color.YELLOW);
                comp.setForeground(Color.BLACK);
            } else
            {
                comp.setBackground(Color.blue);
            }
        } else
        {
            if (isSelected)
            {
                setBackground(Color.darkGray);
                setForeground(Color.WHITE);
            } else
            {
                setBackground(table.getBackground());
                setForeground(table.getForeground());
            }
        }

        return comp;
    }

}
