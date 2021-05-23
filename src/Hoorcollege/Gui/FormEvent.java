package Hoorcollege.Gui;

import java.util.EventObject;

public class FormEvent extends EventObject {
    private String naam;
    private String occupation;
    private AgeCategorie ageCat;
    private String emplCat;
    private boolean citizenCheck;
    private String taxId;
    private String gender;

    public FormEvent(Object source, String t1, String t2,AgeCategorie ac, String emplCat, boolean check,String taxId,String gender){
        super(source);
        this.naam = t1;
        this.occupation = t2;
        this.ageCat = ac;
        this.emplCat = emplCat;
        this.citizenCheck = check;
        this.taxId = taxId;
        this.gender = gender;
    }

    public String getNaam() {
        return naam;
    }

    public String getOccupation() {
        return occupation;
    }

    public AgeCategorie getAgeCat() {
        return ageCat;
    }

    public String getEmplCat(){
        return emplCat;
    }

    public String getGender(){
        return gender;
    }

    public boolean isCitizenCheck() {
        return citizenCheck;
    }

    public String getTaxId() {
        return taxId;
    }
}
