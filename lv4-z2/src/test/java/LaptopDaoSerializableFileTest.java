import junit.framework.TestCase;
import java.util.*;
import java.io.File;

public class LaptopDaoSerializableFileTest extends TestCase {

    public void testDodajLaptopUFile() {
        // Priprema testnih podataka
        Laptop testLaptop = new Laptop("BrandX", "ModelY", 2000.0,16,250,100,"Intel","AMD",15.3);

        // Kreiranje instance LaptopDao
        File testFile = new File("test.txt"); // Ime datoteke može biti različito
        LaptopDao laptopDao = new LaptopDaoSerializableFile(testFile);

        // Dodavanje laptopa u datoteku
        laptopDao.dodajLaptopUFile(testLaptop);

        // Čitanje podataka iz datoteke i provjera dodanog laptopa
        ArrayList<Laptop> laptopsFromFile = laptopDao.vratiPodatkeIzDatoteke();

        // Provjera da li je lista popunjena i da li sadrži dodani laptop
        assertNotNull(laptopsFromFile);
        assertEquals(1, laptopsFromFile.size()); // Provjera broja laptopa
        assertEquals("BrandX", laptopsFromFile.get(0).getBrend()); // Provjera brenda dodanog laptopa
        assertEquals("ModelY", laptopsFromFile.get(0).getModel()); // Provjera modela dodanog laptopa
        assertEquals(2000.0, laptopsFromFile.get(0).getCijena()); // Provjera cijene dodanog laptopa
    }

    public void testGetLaptop() {
        assertEquals("AMD","AAO");
    }

    public void testNapuniListu() {
        ArrayList<Laptop> testLaptops = new ArrayList<>();
        testLaptops.add(new Laptop("Brand1", "Model1", 1000.0,12,500,100,"AMD","Nvidia",20.2));
        testLaptops.add(new Laptop("Brand2", "Model2", 1500.0,12,500,100,"AMD","Nvidia",20.2));

        // Kreiranje instance LaptopDao
        LaptopDao laptopDao = new LaptopDaoSerializableFile(new File("test.txt"));

        // Testiranje metode napuniListu
        laptopDao.napuniListu(testLaptops);

        // Provjera da li je lista laptopa popunjena
        assertNotNull(laptopDao.vratiPodatkeIzDatoteke());
        assertEquals(2, laptopDao.vratiPodatkeIzDatoteke().size()); // Provjera da li ima 2 laptopa u listi
    }

    public void testVratiPodatkeIzDatoteke() {
        LaptopDao laptopDao = new LaptopDaoSerializableFile(new File("test.txt"));

        // Testiranje metode vratiPodatkeIzDatoteke
        ArrayList<Laptop> laptops = laptopDao.vratiPodatkeIzDatoteke();

        // Provjera da li je metoda vratila validnu listu
        assertNotNull(laptops);
    }
}