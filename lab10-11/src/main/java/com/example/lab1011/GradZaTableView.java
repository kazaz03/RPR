package com.example.lab1011;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class GradZaTableView {
    private SimpleIntegerProperty id;
    private SimpleStringProperty naziv;
    private SimpleIntegerProperty broj_stanovnika;
    private SimpleStringProperty drzava;

    public GradZaTableView(Integer id, String naziv,Integer brojStanovnika, String drzava) {
        this.id = new SimpleIntegerProperty(id);
        this.naziv = new SimpleStringProperty(naziv);
        broj_stanovnika = new SimpleIntegerProperty(brojStanovnika);
        this.drzava = new SimpleStringProperty(drzava);
    }
    public GradZaTableView()
    {
        this.id=new SimpleIntegerProperty();
        this.drzava=new SimpleStringProperty();
        this.naziv=new SimpleStringProperty();
        this.broj_stanovnika=new SimpleIntegerProperty();
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getNaziv() {
        return naziv.get();
    }

    public SimpleStringProperty nazivProperty() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv.set(naziv);
    }

    public int getBroj_stanovnika() {
        return broj_stanovnika.get();
    }

    public SimpleIntegerProperty broj_stanovnikaProperty() {
        return broj_stanovnika;
    }

    public void setBroj_stanovnika(int broj_stanovnika) {
        this.broj_stanovnika.set(broj_stanovnika);
    }

    public String getDrzava() {
        return drzava.get();
    }

    public SimpleStringProperty drzavaProperty() {
        return drzava;
    }

    public void setDrzava(String drzava) {
        this.drzava.set(drzava);
    }
}
