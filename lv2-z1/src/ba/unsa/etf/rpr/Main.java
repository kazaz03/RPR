package ba.unsa.etf.rpr;
import java.util.Scanner;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        int broj=Integer.parseInt(args[0]);
        double sinus=Kalkulator.Sinus(broj);
        System.out.println("Sinus od broja "+broj+" je "+sinus);
        int f=Kalkulator.Faktorijel(broj);
        System.out.println("Faktorijel od broja "+broj+" je "+f);
    }
}
