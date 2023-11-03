package ba.unsa.etf.rpr;
import java.util.*;

class Program {

    public static void main(String[] args) {
        FiksniBroj f=new FiksniBroj(Grad.SARAJEVO,"123-456");
        FiksniBroj f1=new FiksniBroj(Grad.SARAJEVO,"145-123");
        System.out.println(f.ispisi());
        MobilniBroj m=new MobilniBroj(60,"241-331");
        System.out.println(m.ispisi());
        MedunarodniBroj mb=new MedunarodniBroj("+387","62-241-331");
        System.out.println(mb.ispisi());
        System.out.println(mb.hashCode());
        Imenik imenik=new Imenik();
        try
        {
            System.out.println(imenik.naSlovo('A'));
        }catch(MojIzuzetak e)
        {
            System.out.println("Greska: "+e.getDodatnaPoruka());
        }
        imenik.dodaj("Zejnepa Kazazovic",f);
        imenik.dodaj("Sabina Kazazovic",m);
        imenik.dodaj("Samir Kazazovic",f1);
        Set<String> skup=imenik.izGrada(Grad.SARAJEVO);
        for(String el: skup)
            System.out.println(el);
        Set<TelefonskiBroj> skup2=imenik.izGradaBrojevi(Grad.SARAJEVO);
        for(TelefonskiBroj el: skup2)
            System.out.println(el.ispisi());
    }
}
