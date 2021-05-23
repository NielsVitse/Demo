package Hoorcollege.Logic;

import Hoorcollege.Gui.MainFrame;
import Hoorcollege.Model.*;
import Hoorcollege.Gui.FormEvent;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Controller {
    private PeopleDB people;
    private PersonDAO personDAO;

    public Controller(){
        people = PeopleDB.getIntance();
        personDAO = new PersonDAO();
    }

    public ArrayList<Person> getPeople(){
        return people.getPeople();
    }

    public void addPerson(FormEvent fe){
        String naam = fe.getNaam();
        String occupation = fe.getOccupation();

        AgeCat ageCat = null;
        switch(fe.getAgeCat().getId()){
            case 0:
                ageCat = AgeCat.UNDER_18;
                break;
            case 1:
                ageCat = AgeCat.FROM_18_TO_65;
                break;
            case 2:
                ageCat = AgeCat.OVER_65;
                break;
        }

        EmplCat emplCat = null;
        switch (fe.getEmplCat()){
            case "Employed":
                emplCat = EmplCat.EMPLOYED;
                break;
            case "Self-Employed":
                emplCat = EmplCat.SELF_EMPLOYED;
                break;
            case "Unemployed":
                emplCat = EmplCat.UNEMPLOYED;
                break;
        }

        boolean citizenCheck = fe.isCitizenCheck();
        String taxId = fe.getTaxId();

        Gender gender = null;
        if(fe.getGender().equals("female")) gender = Gender.FEMALE;
            else gender = Gender.MALE;

        Person p = new Person(naam,occupation,ageCat,emplCat,citizenCheck,taxId,gender);

        people.addPerson(p);
        //personDAO.addPersonToDB(p);

    }

    public void saveToFile(File file) throws IOException {
       people.saveToFile(file);
    }


    public void loadFromFile(File file) throws IOException {
        people.loadFromFile(file);
    }

    public void saveToDB(){
        personDAO.addPersonToDB(people.getPeople());
    }

    public void loadFromDB(){
        //people.setPeople(personDAO.loadPersonsFromDB());
        people.getPeople().clear();
        people.getPeople().addAll(personDAO.loadPersonsFromDB());
        Person.setCount(people.getPeople().get(people.getPeople().size()-1).getId()+1);
    }

    public void removeItem(int index){
        people.removeItem(index);
    }

    public void disconnect(){
        try {
            people.disconnect();
        } catch (SQLException throwables) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(),"De connectie kon niet worden gesloten.");
        }
    }

}
