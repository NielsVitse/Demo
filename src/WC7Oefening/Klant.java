package WC7Oefening;

import java.io.*;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeSet;

public class Klant implements Serializable {
    @Serial
    private static final long serialVersionUID = -8572816346529309528L;
    private String naam;
    private HashMap<Boek, LocalDate> boodschappenMandje = null;
    private static TreeSet<Klant> klantenLijst = new TreeSet<>((Comparator.comparing(o -> o.naam)));

    public Klant(String naam){
        this.naam = naam;
        Klant.klantenLijst.add(this);
    }

    public void voegBoekToe(Boek b){
        boodschappenMandje.put(b,LocalDate.now());
    }

    public void verwijderUitMandje(Boek b) throws BoekNietInMandjeException {
        if(boodschappenMandje.containsKey(b)){
            boodschappenMandje.remove(b);
            System.out.println("Boek "+b.getTitel()+" werd verwijderd uit de boodschappenlijst.");
        }
        else {
            throw new BoekNietInMandjeException(b);
        }
    }

    public void saveKlant(){
        try(ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(naam+".obj",true))) {
            output.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Klant readObject(String naam){
        Klant k = null;
        try(ObjectInputStream input = new ObjectInputStream(new FileInputStream(naam+".obj"))) {
            k= (Klant) input.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return k;
    }

}
