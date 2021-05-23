package Hoorcollege.Model;

public enum EmplCat {
    EMPLOYED("Employed"),SELF_EMPLOYED("Self Employed"),UNEMPLOYED("Unemployed");

    private String text;

    private EmplCat(String text){
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
