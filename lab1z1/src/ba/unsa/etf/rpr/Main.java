package ba.unsa.etf.rpr;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	 Scanner ulaz=new Scanner(System.in);
     System.out.print("Unesi prvi broj: ");
     int a,b;
     a=ulaz.nextInt();
     System.out.print("Unesi drugi broj: ");
     b=ulaz.nextInt();
     System.out.print("Brojevi glase: "+a+" "+b);
    }
}
