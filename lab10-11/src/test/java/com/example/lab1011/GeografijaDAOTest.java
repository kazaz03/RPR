package com.example.lab1011;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

class GeografijaDAOTest {

    @Test
    void dajIDDrzave() {
        //bez brisanja
        String upit="select id from drzava where naziv='Srbija'";
        int id;
        try {
            Connection konekcija=DriverManager.getConnection("jdbc:sqlite:src/main/resources/com/example/lab1011/mojabaza.db");
            Statement stmt=konekcija.createStatement();
            ResultSet set=stmt.executeQuery(upit);
            id=set.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        GeografijaDAO gd=GeografijaDAO.getInstance();
        assertEquals(gd.dajIDDrzave("Srbija"),2);
    }
    @Test
    void dajDrzave() {

    }

    @Test
    void daj_id_grada() {
        //bez brisanja
        String upit="select id from grad where naziv='Pariz'";
        int id;
        try {
            Connection konekcija=DriverManager.getConnection("jdbc:sqlite:src/main/resources/com/example/lab1011/mojabaza.db");
            Statement stmt=konekcija.createStatement();
            ResultSet set=stmt.executeQuery(upit);
            id=set.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        GeografijaDAO gd=GeografijaDAO.getInstance();
        assertEquals(gd.daj_id_grada("Pariz"),3);
    }

    @Test
    void postavi_Glavnigrad() {
        /*GeografijaDAO geografijaDAO=GeografijaDAO.getInstance();
        geografijaDAO.vratiNaDefault();
        geografijaDAO.dodajDrzave(new Drzava("Belgija"));
        geografijaDAO.postavi_Glavnigrad(1,"Belgija");
        assertEquals(geografijaDAO.glavniGrad("Belgija"));*/
    }

    @Test
    void dajGlavneGradove() {
        //bez brisanja
        GeografijaDAO gd=GeografijaDAO.getInstance();
        ObservableList<Grad> gradovi= FXCollections.observableArrayList();
        gradovi.add(new Grad("Sarajevo",300000));
        gradovi.add(new Grad("Beograd",500000));
        gradovi.add(new Grad("Pariz",1200000));
        gradovi.add(new Grad("Lond",3200000));
        assertTrue(gd.dajGlavneGradove().get(0).getNaziv().equals(gradovi.get(0).getNaziv()));
    }

    @Test
    void obrisiGrad() {
    }

    @Test
    void dodajGrad() {
    }

    @Test
    void obrisiDrzavu() {
    }

    @Test
    void nadjiDrzavu() {
        //bez brisanja
        String upit="select naziv from drzava where naziv='Amerika'";
        int id;
        try {
            Connection konekcija=DriverManager.getConnection("jdbc:sqlite:src/main/resources/com/example/lab1011/mojabaza.db");
            Statement stmt=konekcija.createStatement();
            ResultSet set=stmt.executeQuery(upit);
            id=set.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        GeografijaDAO gd=GeografijaDAO.getInstance();
        assertEquals(gd.nadjiDrzavu("Amerika"),null);
    }
    @Test
    void gradoviBaze() {
        //bez brisanja
        ObservableList<Grad> gradovi= FXCollections.observableArrayList();
        gradovi.add(new Grad("Sarajevo",300000));
        gradovi.add(new Grad("Beograd",500000));
        gradovi.add(new Grad("Pariz",1200000));
        gradovi.add(new Grad("Lond",3200000));
        GeografijaDAO gd=GeografijaDAO.getInstance();
        //assertEquals(gd.gradoviBaze().size(),gradovi.size());
        assertFalse(gd.gradoviBaze().equals(gradovi));
    }
}