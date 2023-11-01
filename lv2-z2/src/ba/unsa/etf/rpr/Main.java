package ba.unsa.etf.rpr;
import java.util.*;
import java.util.ArrayList;
import java.util.Scanner;

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


