package WC7Oefening;

public class Fantasy extends Fictie implements Hardcover{
    public Fantasy(String auteur, String titel) {
        super(auteur, titel);
    }

    @Override
    public String geefISBN() {
        return null;
    }

    @Override
    public int geefMeerprijs() {
        return 10;
    }
}
