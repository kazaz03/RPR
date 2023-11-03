package ba.unsa.etf.rpr;
import java.util.Set;

import org.junit.Test;

import static org.junit.Assert.*;

public class ImenikTest {

    @Test
    public void dajBroj() {
        FiksniBroj f=new FiksniBroj(Grad.SARAJEVO,"241-331");
        Imenik i=new Imenik();
        i.dodaj("Amina Kazazovic",f);
        assertEquals("071/241-331",f.ispisi());
    }

    @Test
    public void dajIme() {
        FiksniBroj f=new FiksniBroj(Grad.SARAJEVO,"241-331");
        Imenik i=new Imenik();
        i.dodaj("Amina Kazazovic",f);
        Set<String> tel=i.izGrada(Grad.SARAJEVO); String e="";
        for(String el: tel)
            e=el;
        assertEquals("Amina Kazazovic",e);
    }

    @Test
    public void naSlovo() {
        try
        {
            Imenik i=new Imenik();
            String s=i.naSlovo('a');
            assertEquals("","");
        }catch(MojIzuzetak e)
        {
            System.out.println(e.getDodatnaPoruka());
        }
    }
}