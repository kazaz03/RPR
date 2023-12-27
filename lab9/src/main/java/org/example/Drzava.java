package org.example;

public class Drzava {
    private String naziv;
    public Drzava(String naziv)
    {
        this.naziv=naziv;
    }
    public Drzava() {this("");}
    public void setNaziv(String naziv)
    {
        this.naziv=naziv;
    }
    public String getNaziv()
    {
        return naziv;
    }
    public String toString()
    {
        return naziv;
    }
}
