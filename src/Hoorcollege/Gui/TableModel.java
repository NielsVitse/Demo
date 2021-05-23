package Hoorcollege.Gui;

import Hoorcollege.Model.AgeCat;
import Hoorcollege.Model.EmplCat;
import Hoorcollege.Model.Gender;
import Hoorcollege.Model.Person;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TableModel extends AbstractTableModel {
    private ArrayList<Person> people;

    public boolean[] getSelection() {
        return selection;
    }

    public void setSelection() {
        this.selection = new boolean[people.size()];
        for(int i=0;i<selection.length;i++) selection[i]=false;
    }

    private boolean[] selection;


    public void setPeople(ArrayList<Person> p){
        this.people = p;
        System.out.println(people.size());

        //if(p!=MainFrame.getMainFrame().getController().getPeople()) {}
        System.out.println("arraylist in tableModel");
        for(Person person:p){
            System.out.println(person.getNaam());
        }
    }

    public ArrayList<Person> getPeople() {
        return people;
    }

    @Override
    public String getColumnName(int column) {
        String[] columnNames = {"Select","ID","Name","Occupation","AgeCat","EmplCat","Citizen","Gender"};
        return columnNames[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0: return true;
            case 2: return true;
            case 3: return true;
            case 4: return true;
            case 5: return true;
            case 6: return true;
            case 7: return true;
            default: return false;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex){
            case 0: return Boolean.class;
            case 1: return Integer.class;
            case 2: return String.class;
            case 3: return String.class;
            case 4: return AgeCat.class;
            case 5: return EmplCat.class;
            case 6: return Boolean.class;
            case 7: return Gender.class;
            default: return null;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Person p = people.get(rowIndex);

        switch (columnIndex){
            case 0:
                selection[rowIndex] = (boolean) aValue;
                break;
            case 2:
                p.setNaam((String)aValue);
                break;
            case 23:
                p.setOccupation((String)aValue);
                break;
            case 4:
                p.setAgeCat((AgeCat) aValue);
                break;
            case 5:
                p.setEmplCat((EmplCat) aValue);
                break;
            case 6:
                p.setCitizenCheck((boolean) aValue);
                break;
            case 7:
                p.setGender((Gender) aValue);
                break;
        }

    }

    @Override
    public int getRowCount() {
        return people.size();
    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Person p = people.get(rowIndex);


        switch (columnIndex){
            case 0: return selection[rowIndex];

            case 1:
                return p.getId();
            case 2:
                return p.getNaam();
            case 3:
                return p.getOccupation();
            case 4:
                return p.getAgeCat();
            case 5:
                return p.getEmplCat();
            case 6:
                return p.isCitizenCheck();
            case 7:
                return p.getGender();
        }
        return null;
    }
}
