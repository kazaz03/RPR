package com.example.lab1011;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Grad implements Comparable<Grad>{
    private SimpleStringProperty naziv;
    private SimpleIntegerProperty broj_stanovnika;
    public Grad(String naziv,int broj_stanovnika)
    {
        this.naziv=new SimpleStringProperty(naziv);
        this.broj_stanovnika=new SimpleIntegerProperty(broj_stanovnika);
    }
    public Grad()
    {
        this.naziv=new SimpleStringProperty();
        broj_stanovnika=new SimpleIntegerProperty(0);
    }
    void setNaziv(String naziv)
    {
        this.naziv.set(naziv);
    }
    void setBroj_stanovnika(int broj_stanovnika)
    {
        this.broj_stanovnika.set(broj_stanovnika);
    }
    public String getNaziv()
    {
        return naziv.get();
    }
    public Integer getBroj_stanovnika()
    {
        return broj_stanovnika.get();
    }
    public SimpleIntegerProperty broj_stanovnikaProperty()
    {
        return broj_stanovnika;
    }
    @Override
    public String toString()
    {
        return naziv.get()+" "+broj_stanovnika.get();
    }
    @Override
    public int compareTo(Grad grad)
    {
        if(this.broj_stanovnika.get()>grad.broj_stanovnika.get()) return 1;
        if(this.broj_stanovnika.get()<grad.broj_stanovnika.get()) return -1;
        return 0;
    }
    public SimpleStringProperty nazivProperty()
    {
        return naziv;
    }
}