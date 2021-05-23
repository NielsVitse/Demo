package WC7Oefening;

public class Biografie extends NonFictie{
    public Biografie(String auteur, String titel) {
        super(auteur, titel);
    }

    @Override
    public String geefISBN() {
        return null;
    }
}
