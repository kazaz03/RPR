// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import java.util.*;
public class Main {
    public static void main(String[] args) {
        InformacijeOStudentu student=new InformacijeOStudentu("Amina","Kazazovic","druga",
                "19364");
        InformacijeOStudentu student1=new InformacijeOStudentu("Sedin","Kazazovic","druga",
                "19365");
        System.out.print(student.toString());
        Predmet predmet=new Predmet("Diskretna matematika","Tesko haos");
        ArrayList<Predstavi> lista=new ArrayList<Predstavi>();
        lista.add(0,student);
        lista.add(1,predmet);
        KolekcijaPoruka kolekcija=new KolekcijaPoruka(lista);
        ArrayList<String> k=kolekcija.getPoruke();
        for(int i=0; i<k.size(); i++)
            System.out.println(k.get(i));
        InformacijeONastavniku nastavnik=new InformacijeONastavniku("Zeljko","Juric","dr.mr");
        try
        {
            nastavnik.ocijeniNastavnika(student,7);
            nastavnik.ocijeniNastavnika(student1,8);
        }catch(NevalidnaOcjena e)
        {
            System.out.println(e.getMessage());
        }
        nastavnik.utisci();
        try
        {
            predmet.ocijeniPredmet(student1,2);
        }catch(NevalidnaOcjena e)
        {
            System.out.println(e.getMessage());
        }
        predmet.utisci();
    }
}