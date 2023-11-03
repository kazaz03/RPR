package ba.unsa.etf.rpr;

public class MojIzuzetak extends Exception{
    private String poruka;
    public
    MojIzuzetak(String poruka)
    {
        this.poruka=poruka;
    }
    String getDodatnaPoruka()
    {
        return poruka;
    }
}
