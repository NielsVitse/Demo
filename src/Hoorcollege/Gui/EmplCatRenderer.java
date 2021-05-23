package Hoorcollege.Gui;

import Hoorcollege.Model.EmplCat;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class EmplCatRenderer implements TableCellRenderer {
    private JComboBox combo;

    public EmplCatRenderer(){
        combo = new JComboBox(EmplCat.values());
    }


    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        combo.setSelectedItem(value);
        return combo;
    }
}
