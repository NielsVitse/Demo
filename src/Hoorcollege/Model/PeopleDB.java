package Hoorcollege.Model;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class PeopleDB {
    private static PeopleDB instance;
    private ArrayList<Person> people;
    private Connection connection;

    private PeopleDB() {
        this.people = new ArrayList<>();
    }

    public void addPerson(Person p){
        if(p!=null) this.people.add(p);
    }

    public ArrayList<Person> getPeople(){
        return people;
    }

    public void saveToFile(File file) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));

        Person[] persons = people.toArray(new Person[people.size()]);

        oos.writeObject(persons);
        oos.close();
    }

    public void loadFromFile(File file) throws IOException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));

        try {
            Person[] persons = (Person[])ois.readObject();
            people.clear();
            people.addAll(Arrays.asList(persons));
            Person.setCount(people.size());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ois.close();
    }


    public void removeItem(int index){
        people.remove(index);
    }

    public static PeopleDB getIntance(){
        if(instance==null) instance = new PeopleDB();
        return instance;
    }

    public Connection getConnection() throws SQLException {
        if(connection==null|| connection.isClosed())
            connection = DriverManager.getConnection("jdbc:mysql://dt5.ehb.be/2021PROGESS009","2021PROGESS009","74356289");
        return connection;
    }

    public void setPeople(ArrayList<Person> people) {
        this.people = people;
    }

    public void disconnect() throws SQLException {
        if(connection!=null) connection.close();
    }
}
