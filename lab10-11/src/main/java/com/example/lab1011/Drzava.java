package com.example.lab1011;

import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;

public class Drzava {
    private SimpleStringProperty naziv;
    public Drzava(String naziv)
    {
        this.naziv=new SimpleStringProperty(naziv);
    }
    public Drzava()
    {
        naziv=new SimpleStringProperty();
    }
    public void setNaziv(String naziv)
    {
        this.naziv.set(naziv);
    }
    public String getNaziv()
    {
        return naziv.get();
    }
    @Override
    public String toString()
    {
        return naziv.get();
    }
    public SimpleStringProperty nazivProperty()
    {
        return naziv;
    }
}

