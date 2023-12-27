package org.example;

public class Grad implements Comparable<Grad>{
    private String naziv;
    private int broj_stanovnika;
    public Grad(String naziv,int broj_stanovnika)
    {
        this.naziv=naziv;
        this.broj_stanovnika=broj_stanovnika;
    }
    public Grad()
    {
        this("",0);
    }
    void setNaziv(String naziv)
    {
        this.naziv=naziv;
    }
    void setBroj_stanovnika(int broj_stanovnika)
    {
        this.broj_stanovnika=broj_stanovnika;
    }
    String getNaziv()
    {
        return naziv;
    }
    int getBroj_stanovnika()
    {
        return broj_stanovnika;
    }
    public String toString()
    {
        return naziv+" "+broj_stanovnika;
    }
    @Override
    public int compareTo(Grad grad)
    {
        if(this.broj_stanovnika>grad.broj_stanovnika) return 1;
        if(this.broj_stanovnika<grad.broj_stanovnika) return -1;
        return 0;
    }
}

