package WC7Oefening;

public class BoekNietInMandjeException extends Exception{
    private Boek boek;

    public BoekNietInMandjeException(){
        super("Boek kan niet teruggevonden worden in het boodschappenmandje!");
    }

    public BoekNietInMandjeException(Boek b){
        this();
        boek = b;
    }
}
