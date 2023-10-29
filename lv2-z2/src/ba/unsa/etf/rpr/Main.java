package ba.unsa.etf.rpr;
import java.util.*;
import java.util.ArrayList;
import java.util.Scanner;

class Osoba
{
    private
    String ime;
    String prezime;
    public
    Osoba(String ime, String prezime)
    {
        this.ime=ime;
        this.prezime=prezime;
    }
    @Override
    public String toString()
    {
        return ime+" "+prezime+"\n";
    }
    String getIme(){return ime;}
    String getPrezime(){return prezime;}
    void setIme(String ime){this.ime=ime;}
    void setPrezime(String prezime){this.prezime=prezime;}
}

class Uposlenik extends Osoba
{
    Uposlenik(String ime, String prezime){
        super(ime,prezime);
    }
}

class Korisnik extends Osoba
{
    protected
    Racun racun;
    public
    Korisnik(String ime, String prezime)
    {
        super(ime,prezime);
    }
    public void dodajRacun(Racun racun)
    {
        this.racun=racun;
    }
    Racun getRacun(){return racun;}
}

class Racun
{
    protected
    long brojRacuna;
    Osoba korisnikRacuna;
    boolean odobrenjePrekoracenja;
    double stanjeRacuna;
    private
    void provjeriOdobrenjePrekoracenja(double a)
    {
        if (a > stanjeRacuna && !odobrenjePrekoracenja) {
            throw new IllegalArgumentException("Nemate dozvoljen prekoračen račun.");
        }
    }
    public
    Racun(long broj, Osoba o) {
        brojRacuna = broj;
        korisnikRacuna = o;
    }
    boolean izvrsiUplatu(double uplata) {
        if(uplata<0) throw new IllegalArgumentException("Iznos uplate mora bit veci od nula");
        stanjeRacuna=stanjeRacuna+uplata;
        return true;
    }
    boolean izvrsiIsplatu(double isplata)
    {
        provjeriOdobrenjePrekoracenja(isplata);
        if(isplata<0) throw new IllegalArgumentException("Iznos isplate mora bit veci od nula");
        stanjeRacuna=stanjeRacuna-isplata;
        return true;
    }
    void odobriPrekoracenje()
    {
        odobrenjePrekoracenja=true;
    }
    @Override
    public String toString()
    {
        return "Broj racuna: "+brojRacuna+"\nKorisnik racuna: "+korisnikRacuna.toString()+
                "Ima li odobrenje: "+odobrenjePrekoracenja+"\nTrenutno stanje na racunu: "+stanjeRacuna;
    }
}

class Banka
{
    private
    long brojRacuna;
    protected
    List<Korisnik> korisnici=new ArrayList<>();
    List<Uposlenik> uposlenici=new ArrayList<>();
    public
    Banka(){}
    Korisnik kreirajNovogKorisnika(String ime, String prezime)
    {
        Korisnik korisnik=new Korisnik(ime,prezime);
        korisnici.add(korisnik);
        kreirajRacunZaKorisnika(korisnik);
        return korisnik;
    }
    Uposlenik kreirajNovogUposlenika(String ime, String prezime)
    {
        Uposlenik uposlenik=new Uposlenik(ime,prezime);
        uposlenici.add(uposlenik);
        return uposlenik;
    }
    Racun kreirajRacunZaKorisnika(Korisnik korisnik)
    {
        Random random=new Random();
        long broj=1000000000L + random.nextLong();
        if(broj<0) broj*=-1;
        Racun racun=new Racun(broj,korisnik);
        korisnik.dodajRacun(racun);
        return racun;
    }
    Korisnik getKorisnikaizBanke(int indeks)
    {
        if(indeks<0 || indeks>korisnici.size())
            throw new IllegalArgumentException("Indeks van opsega");
        return korisnici.get(indeks);
    }
    Uposlenik getUposlenikaizBanke(int indeks)
    {
        if(indeks<0 || indeks>korisnici.size())
            throw new IllegalArgumentException("Indeks van opsega");
        return uposlenici.get(indeks);
    }
}

public class Main {

    public static void main(String[] args) {
        Banka banka=new Banka();
        banka.kreirajNovogKorisnika("Amina","Kazazovic");
        banka.kreirajNovogKorisnika("Sabina","Kazazovic");
        Korisnik k1=banka.getKorisnikaizBanke(0);
        Korisnik k2=banka.getKorisnikaizBanke(1);
        k1.getRacun().izvrsiUplatu(12000);
        k2.getRacun().izvrsiUplatu(2000);
        k1.getRacun().odobriPrekoracenje();
        k2.getRacun().izvrsiIsplatu(20);
        for(int i=0; i<banka.korisnici.size(); i++)
        {
            System.out.println((i+1)+". Korisnik :");
            System.out.println(banka.getKorisnikaizBanke(i).getRacun());
        }
        banka.kreirajNovogUposlenika("Sedin","Kazazovic");
        banka.kreirajNovogUposlenika("Ehlimana","Krupalija");
        for(int i=0; i<banka.uposlenici.size(); i++)
        {
            System.out.print((i+1)+". Uposlenik: ");
            System.out.print(banka.getUposlenikaizBanke(i).toString());
        }
    }
}


