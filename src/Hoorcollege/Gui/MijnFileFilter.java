package Hoorcollege.Gui;

import javax.swing.filechooser.FileFilter;
import java.io.File;

//FileFilter is een abstracte klasse die vraagt om methoden te implementeren die bepalen wanneer een bestand mag worden weergegeven
//FileFilter moet ook een methode implementeren die een tekst geeft voor de filter
//FileChooser zal deze methoden gebruiken om de weer te geven bestanden te filteren

public class MijnFileFilter extends FileFilter {
    private String fileTypeAccepted;

    public MijnFileFilter(String s){
        fileTypeAccepted = s;
    }

    @Override
    public boolean accept(File f) {
        //deze methode geeft true terug indien de extensie overeenkomt met de filter waarde
        //indien true teruggestuurd voor een bepaald bestand --> bestand wordt getoond bij actieve filter
        //mappen moeten altijd worden weergegeven --> altijd true teruggeven
        if(f.isDirectory()) return true;

        String naam = f.getName();

        //opzoeken van de plaats van de punt = start van de extensie in de bestandsnaam
        int extensionStart = naam.lastIndexOf(".");

        //geen punt gevonden --> geen bestandstype --> niet weergeven in filter
        if(extensionStart==-1||extensionStart==naam.length()) return false;
        else {
            //de extensie uit de bestandsnaam halen vanaf het punt tot het einde van de bestandsnaam
            String extension = naam.substring(extensionStart+1,naam.length());
            //enkel indien de gevonden extensie overeenkomt met het gewenste type --> bestanden weergeven in filter
            if(extension.equals(fileTypeAccepted)) return true;
        }
        return false;
    }

    @Override
    public String getDescription() {
        return fileTypeAccepted+"-bestanden (*."+fileTypeAccepted+")";
    }
}
