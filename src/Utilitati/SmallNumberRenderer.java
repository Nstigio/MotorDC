
package Utilitati;

import javax.swing.table.TableCellRenderer;

 //@author Bogdan Lazar

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.text.DecimalFormat;

public class SmallNumberRenderer extends DefaultTableCellRenderer {
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.##########"); // Adjust the format as needed

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component rendererComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (value instanceof Number) {
            Number numberValue = (Number) value;
            if (Math.abs(numberValue.doubleValue()) < 0.000000001) {
                setText(DECIMAL_FORMAT.format(numberValue)); // Format small numbers
            }
        }
        return rendererComponent;
    }
}
