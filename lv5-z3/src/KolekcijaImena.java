import java.util.*;
public class KolekcijaImena implements Kolekcije{
    private ArrayList<String> imena=new ArrayList<String>();
    public
    KolekcijaImena(ArrayList<String> lista)
    {
        this.imena=lista;
    }
    void dodajime(String ime)
    {
        imena.add(imena.size(),ime);
    }
    @Override
    public String getNajduzeIme()
    {
        String ime=new String();
        int maxduzina=0;
        for(int i=0; i<imena.size(); i++)
        {
            int brojac=0;
            while(brojac<imena.get(i).length())
            {
                if(imena.get(i).charAt(brojac)==' ') break;
                brojac++;
            }
            if(brojac>maxduzina)
            {
                ime=imena.get(i).substring(0,brojac);
                maxduzina=brojac;
            }
        }
        return ime;
    }
    public String getPrezimeodNajudzegImena()
    {
        String ime=new String();
        String prezime=new String();
        int maxduzina=0;
        int indeks=0;
        for(int i=0; i<imena.size(); i++)
        {
            int brojac=0;
            while(brojac<imena.get(i).length())
            {
                if(imena.get(i).charAt(brojac)==' ') break;
                brojac++;
            }
            if(brojac>maxduzina)
            {
                ime=imena.get(i).substring(0,brojac);
                maxduzina=brojac;
                indeks=i;
                prezime=imena.get(i).substring(brojac,imena.get(i).length());
            }
        }
        return prezime;
    }
}
