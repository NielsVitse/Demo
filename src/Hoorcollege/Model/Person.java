package Hoorcollege.Model;

import java.io.Serializable;

public class Person implements Serializable {

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Person.count = count;
    }

    private static int count = 0;
    private int id;
    private String naam;
    private String occupation;
    private AgeCat ageCat;
    private EmplCat emplCat;
    private boolean citizenCheck;
    private String taxId;
    private Gender gender;

    public int getId() {
        return id;
    }

    public Person(String naam, String occupation, AgeCat ageCat, EmplCat emplCat, boolean citizenCheck, String taxId, Gender gender) {
        this.naam = naam;
        this.occupation = occupation;
        this.ageCat = ageCat;
        this.emplCat = emplCat;
        this.citizenCheck = citizenCheck;
        this.taxId = taxId;
        this.gender = gender;
        this.id = count;
        count++;
    }

    public Person(int id,String naam, String occupation, AgeCat ageCat, EmplCat emplCat, boolean citizenCheck, String taxId, Gender gender){
        this(naam, occupation, ageCat, emplCat, citizenCheck, taxId, gender);
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public AgeCat getAgeCat() {
        return ageCat;
    }

    public void setAgeCat(AgeCat ageCat) {
        this.ageCat = ageCat;
    }

    public EmplCat getEmplCat() {
        return emplCat;
    }

    public void setEmplCat(EmplCat emplCat) {
        this.emplCat = emplCat;
    }

    public boolean isCitizenCheck() {
        return citizenCheck;
    }

    public void setCitizenCheck(boolean citizenCheck) {
        this.citizenCheck = citizenCheck;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", naam='" + naam + '\'' +
                ", occupation='" + occupation + '\'' +
                ", ageCat=" + ageCat +
                ", emplCat=" + emplCat +
                ", citizenCheck=" + citizenCheck +
                ", taxId='" + taxId + '\'' +
                ", gender=" + gender +
                '}';
    }
}
