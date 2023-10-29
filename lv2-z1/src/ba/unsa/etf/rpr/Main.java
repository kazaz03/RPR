package ba.unsa.etf.rpr;
import java.util.Scanner;
import java.util.*;

public class Main {
    static class Kalkulator
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
    public static void main(String[] args) {
        int broj=Integer.parseInt(args[0]);
        double sinus=Kalkulator.Sinus(broj);
        System.out.println("Sinus od broja "+broj+" je "+sinus);
        int f=Kalkulator.Faktorijel(broj);
        System.out.println("Faktorijel od broja "+broj+" je "+f);
    }
}
