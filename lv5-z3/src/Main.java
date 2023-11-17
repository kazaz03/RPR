import java.util.ArrayList;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        ArrayList<String> lista=new ArrayList<String>();
        lista.add(0,"Amina Kazazovic");
        lista.add(1,"Azra Kazazovic");
        lista.add(2,"Sabaheta Kaletic");
        KolekcijaImena kolekcija=new KolekcijaImena(lista);
        System.out.println(kolekcija.getPrezimeodNajudzegImena());
        Pobjednik p=new Pobjednik(kolekcija);
        ArrayList<String> l=new ArrayList<String>();
        l.add(0,"Amina");
        l.add(1,"Sabina");
        l.add(2,"Zulejha");
        ArrayList<String> l1=new ArrayList<String>();
        l1.add(0,"Kazazovic");
        l1.add(1,"Kaleta");
        l1.add(2,"Mujic");
        KolekcijaImenaIPrezimena kol=new KolekcijaImenaIPrezimena(l,l1);
        System.out.println(kol.getImeIPrezime());

    }
}