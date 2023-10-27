package ba.unsa.etf.rpr;
import java.util.Scanner;


public class Sat {

    private int sati,minute,sekunde;
    public Sat(int s,int m,int ss)
    {
        this.Postavi(s,m,ss);
    }
    public void Postavi(int s,int m, int ss)
    {
        this.minute=m; this.sekunde=ss; this.sati=s;
    }
    public void Sljedeci()
    {
        this.sekunde++;
        if (this.sekunde==60) { this.sekunde=0; this.minute++; }
        if (this.minute==60) { this.minute=0; this.sati++; }
        if (this.sati==24) this.sati=0;
    }
    public void Prethodni()
    {
        this.sekunde--;
        if (this.sekunde==-1) { this.sekunde=59; this.minute--; }
        if (this.minute==-1) { this.minute=59; this.sati--; }
        if (this.sati==-1) this.sati=23;
    }
    public void PomjeriZa(int pomak)
    {
        if (pomak>0) for (int i=0; i<pomak; i++) this.Sljedeci();
        else for (int i=0; i<-pomak; i++) this.Prethodni();
    }
    public int DajSate()
    {
        return this.sati;
    }
    public int DajMinute()
    {
        return this.minute;
    }
    public int DajSekunde()
    {
        return this.sekunde;
    }
    public void Ispisi() {
        System.out.println(sati + ":" + minute + ":" + sekunde);
    }
    public static void main(String[] args)
    {
        Sat s=new Sat(15,30,40);
        s.Ispisi();
        s.Sljedeci();
        s.Ispisi();
        s.PomjeriZa(-48);
        s.Ispisi();
        s.Postavi(0,0,0);
        s.Ispisi();
    }
}
