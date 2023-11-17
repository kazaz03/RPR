import java.util.ArrayList;

public class KolekcijaImenaIPrezimena implements Kolekcije{
    private
    ArrayList<String> imena=new ArrayList<String>();
    ArrayList<String> prezimena=new ArrayList<String>();
    public
    KolekcijaImenaIPrezimena(ArrayList<String> ime, ArrayList<String> prezime)
    {
        imena=ime;
        prezimena=prezime;
    }
    @Override
    public String getNajduzeIme()
    {
        String ime=new String();
        int duzina=0;
        for(int i=0; i<imena.size(); i++)
        {
            if(imena.get(i).length()>duzina)
            {
                duzina=imena.get(i).length();
                ime=imena.get(i);
            }
        }
        return ime;
    }
    @Override
    public String getPrezimeodNajudzegImena()
    {
        String ime=imena.get(0);
        int duzina=0;
        int ink=0;
        for(int i=0; i<imena.size(); i++)
        {
            if(imena.get(i).length()>duzina)
            {
                duzina=imena.get(i).length();
                ime=imena.get(i);
                ink=i;
            }
        }
        return prezimena.get(ink);
    }
    int getIndexNajduzegPara()
    {
        int najduzi=0;
        int indeks=0;
        for(int i=0; i<imena.size(); i++)
        {
            if(imena.get(i).length()+prezimena.get(i).length()>najduzi)
            {
                najduzi=imena.get(i).length()+prezimena.get(i).length();
                indeks=i;
            }
        }
        return indeks;
    }
    String getImeIPrezime()
    {
        String k=new String(" ");
        for(int i=0; i<imena.size(); i++)
        {
            k+=imena.get(i)+" "+prezimena.get(i)+"\n";
        }
        return k;
    }
}
