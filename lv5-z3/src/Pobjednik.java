import java.util.*;
public class Pobjednik {
    private
    String ime, prezime;
    int brojZnakova;
    KolekcijaImena kolekcijaImena;
    public
    Pobjednik(KolekcijaImena kolekcija)
    {
        kolekcijaImena=kolekcija;
        ime=kolekcija.getNajduzeIme();
        prezime=kolekcija.getPrezimeodNajudzegImena();
        brojZnakova=ime.length();
    }
    String getIme(){return ime;}
    String getPrezime(){return prezime;}
    int getBrojZnakova(){return brojZnakova;}
}
