package WC7Oefening;

public class Kookboek extends NonFictie implements Hardcover{

    public Kookboek(String auteur, String titel) {
        super(auteur, titel);
    }

    @Override
    public String geefISBN() {
        return null;
    }

    @Override
    public int geefMeerprijs() {
        return 5;
    }
}
