// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Laptop laptop1=new Laptop("EliteBook","HP",234.33,16,500,120,"Intel core I5","Nvidia",15.0);
        try
        {
            LaptopDao dao1 = null;
            dao1.dodajLaptopUFile(laptop1);
            dao1.getLaptop("AMD");
        }catch(NeodgovarajuciProcesorException izuzetak)
        {
            System.out.println(izuzetak.getPoruka());
        }
        return;
    }
}