package Hoorcollege.Gui;

import Hoorcollege.Model.EmplCat;
import Hoorcollege.Model.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class TablePanel extends JPanel {
    private JTable table;
    private TableModel tableModel;
    private JLabel label;
    private JButton toonSelectie;
    private JPopupMenu popupMenu;
    private TableListener tableListener;


    public void setTableListener(TableListener tableListener) {
        this.tableListener = tableListener;
    }

    public TableModel getTableModel() {
        return tableModel;
    }

    public TablePanel(){
        tableModel = new TableModel();
        table = new JTable(tableModel);
        label = new JLabel("Table");
        toonSelectie = new JButton("ToonSelectie");
        popupMenu = new JPopupMenu();
        JMenuItem removeItem = new JMenuItem("Delete Row");
        popupMenu.add(removeItem);

        setLayout(new BorderLayout());
        add(label,BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        table.setRowHeight(25);
        table.setDefaultRenderer(EmplCat.class,new EmplCatRenderer());
        table.setDefaultEditor(EmplCat.class,new EmplCatEditor());
        table.setVisible(true);

        add(toonSelectie,BorderLayout.SOUTH);




        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                table.getSelectionModel().setSelectionInterval(row,row);
                if(e.getButton()==3) {
                    popupMenu.show(table,e.getX(),e.getY());
                }
            }
        });

        removeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                tableListener.removeRow(row);
                tableModel.fireTableRowsDeleted(row,row);
            }
        });


        toonSelectie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i=0;i<table.getRowCount();i++){
                    if((boolean)table.getValueAt(i,0)){
                        System.out.println(tableModel.getPeople().get(i).getNaam());
                    }
                }


                /*int[] index = table.getSelectedRows();
                for(int i=0;i<index.length;i++)
                System.out.println(tableModel.getPeople().get(index[i]).getNaam());*/
            }
        });
    }

    public void setPeople(ArrayList<Person> p){
        tableModel.setPeople(p);
    }

    public void refresh(){
        tableModel.setSelection();
        tableModel.fireTableDataChanged();

        System.out.println("Refresh table");
    }


}
