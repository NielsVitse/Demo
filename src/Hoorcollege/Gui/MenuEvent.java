package Hoorcollege.Gui;

import jdk.jfr.Event;

import java.io.File;

public class MenuEvent extends Event {
    private boolean isChecked;
    private String tekst;

    private File file;

    public MenuEvent(){

    }

    public File getFile() {
        return file;
    }



    public MenuEvent(boolean isChecked){
        this.isChecked = isChecked;
    }

    public MenuEvent(String tekst){
        this.tekst = tekst;
    }

    public MenuEvent(File file) {
        this.file = file;
    }

    public boolean getIsChecked(){
        return isChecked;
    }

    public String getTekst() {
        return tekst;
    }
}
