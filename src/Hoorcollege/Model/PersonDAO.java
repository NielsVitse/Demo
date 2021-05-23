package Hoorcollege.Model;

import Hoorcollege.Gui.MainFrame;
import com.sun.tools.javac.Main;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PersonDAO extends BaseDAO{

    public void addPersonToDB(ArrayList<Person> list){
        String countQuery = "select count(*) from Person where id=?";
        String insertQuery = "Insert into Person(name,occupation,ageCat,emplCat,citizen,taxId,gender) values(?,?,?,?,?,?,?)";
        String updateQuery = "update Person set name=?, occupation=?,ageCat=?,emplCat=?,citizen=?,taxId=?,gender=? where id=?";
        try (PreparedStatement s1 = getConnection().prepareStatement(countQuery);
             PreparedStatement s = getConnection().prepareStatement(insertQuery);
             PreparedStatement s2 = getConnection().prepareStatement(updateQuery)) {
            int tellerUpdate=0, tellerInsert=0;
            for (Person p : list) {
                s1.setInt(1, p.getId());
                ResultSet result = s1.executeQuery();
                result.next();
                int aantal = result.getInt(1);
                if (aantal != 0) {
                    int col = 1;
                    s2.setString(col++, p.getNaam());
                    s2.setString(col++, p.getOccupation());
                    s2.setString(col++, p.getAgeCat().toString());
                    s2.setString(col++, p.getEmplCat().toString());
                    s2.setBoolean(col++, p.isCitizenCheck());
                    s2.setString(col++, p.getTaxId());
                    s2.setString(col++, p.getGender().toString());
                    s2.setInt(col++, p.getId());
                    s2.executeUpdate();
                    tellerUpdate++;
                    //JOptionPane.showMessageDialog(MainFrame.getMainFrame(),"The data of "+p.getNaam()+" where updated.");
                } else {

                    //s.setInt(1, );
                    s.setString(1, p.getNaam());
                    s.setString(2, p.getOccupation());
                    s.setString(3, p.getAgeCat().toString());
                    s.setString(4, p.getEmplCat().toString());
                    s.setBoolean(5, p.isCitizenCheck());
                    s.setString(6, p.getTaxId());
                    s.setString(7, p.getGender().toString());
                    s.executeUpdate();
                    tellerInsert++;
                }

            }
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(),"Details van het wegschrijven naar de DB: \n"+tellerUpdate+" aangapast in de DB; \n"+tellerInsert+" toegevoegd aan de DB.");
        } catch(SQLException throwables){
                JOptionPane.showMessageDialog(MainFrame.getMainFrame(),"De data konden niet worden weggeschreven");
            }

    }


    public ArrayList<Person> loadPersonsFromDB(){
        String selectQuery = "Select * from Person";

        try(Statement s = getConnection().createStatement()) {
            ResultSet result = s.executeQuery(selectQuery);
            ArrayList<Person> personenLijstDB = new ArrayList<>();
            while(result.next()){
                int id = result.getInt(1);
                String name = result.getString(2);
                String occupation = result.getString(3);
                String ageCat = result.getString(4);
                String empCat = result.getString(5);
                boolean citizen = result.getBoolean(6);
                String taxId = result.getString(7);
                String gender = result.getString(8);
                Person p = new Person(id,name,occupation,AgeCat.valueOf(ageCat),EmplCat.valueOf(empCat),citizen,taxId,Gender.valueOf(gender));
                personenLijstDB.add(p);
            }
            System.out.println("DB opgeladen.");
            return personenLijstDB;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

    }

}
