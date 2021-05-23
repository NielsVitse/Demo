package WC7Oefening;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public abstract class Boek implements Serializable {

    @Serial
    private static final long serialVersionUID = -8094469143033920109L;
    private String auteur;
    private String titel;
    private String isbn;
    private final int id;
    private static int aantalBoeken = 0;

    public Boek(String auteur, String titel) {
        aantalBoeken++;
        this.auteur = auteur;
        this.titel = titel;
        this.id = aantalBoeken;
    }

    public abstract String geefISBN();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Boek boek = (Boek) o;
        return id == boek.id && Objects.equals(titel, boek.titel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titel, id);
    }

    public String getAuteur() {
        return auteur;
    }

    public String getTitel() {
        return titel;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getId() {
        return id;
    }
}
