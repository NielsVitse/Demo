package WC7Oefening;

public class Detective extends Fictie{
    public Detective(String auteur, String titel) {
        super(auteur, titel);
    }

    @Override
    public String geefISBN() {
        return null;
    }
}
