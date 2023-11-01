package ba.unsa.etf.rpr;

public class Kalkulator
{
    public static double Sinus(int broj)
    {
        return Math.sin(broj);
    }
    public static int Faktorijel(int broj)
    {
        int f=1;
        for(int i=1; i<=broj; i++) f=f*i;
        return f;
    }
}