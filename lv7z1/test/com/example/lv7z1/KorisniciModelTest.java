package com.example.lv7z1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KorisniciModelTest {

    @Test
    void getTrenutniKorisnik() {
        KorisniciModel model=new KorisniciModel();
        model.napuni();
        assertNull(model.getTrenutniKorisnik());
    }

    @Test
    void setTrenutnikorisnik() {
        KorisniciModel model=new KorisniciModel();
        Korisnik k=new Korisnik();
        model.dodajkorisnika(k);
        model.setTrenutnikorisnik(k);
        assertEquals("",model.getTrenutniKorisnik().getIme());
    }

    @Test
    void getKorisnici() {
        KorisniciModel model=new KorisniciModel();
        ObservableList<Korisnik> k=FXCollections.observableArrayList();
        k=model.getKorisnici();
        assertEquals(k,model.getKorisnici());
    }

    @Test
    void napuni() {
        KorisniciModel model=new KorisniciModel();
        model.napuni();
        assertEquals("Amina + Sedin",model.getKorisnici().get(0).getIme()+" + "+model.getKorisnici().get(1).getIme());
        assertNull(model.getTrenutniKorisnik());
    }

    @Test
    void dodajkorisnika() {
        KorisniciModel model=new KorisniciModel();
        Korisnik k=new Korisnik("Sabina","Kazazovic","sabka@gmail.com","sabka89","akskoa89as8");
        model.dodajkorisnika(k);
        assertEquals(k,model.getKorisnici().get(0));
    }
}