package Hoorcollege.Gui;

import Hoorcollege.Model.EmplCat;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;

public class EmplCatEditor extends AbstractCellEditor implements TableCellEditor {
    private JComboBox combo;

    public EmplCatEditor(){
        combo = new JComboBox(EmplCat.values());
    }


    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        combo.setSelectedItem(value);
        combo.addActionListener(e -> fireEditingStopped());
        return combo;
    }

    @Override
    public Object getCellEditorValue() {
        return combo.getSelectedItem();
    }

    @Override
    public boolean isCellEditable(EventObject e) {
        return true;
    }
}
