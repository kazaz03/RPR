import java.util.*;
public class InformacijeOStudentu extends LicneInformacije{
    private String godinaStudija,brojIndexa;
    public
    InformacijeOStudentu(String ime,String prezime, String godinaStudija,String brojIndexa)
    {
        super(ime,prezime);
        this.godinaStudija=godinaStudija; this.brojIndexa=brojIndexa;
    }
    InformacijeOStudentu(){super(); godinaStudija=null; brojIndexa=null;}
    @Override
    public String getIme(){return ime;}
    @Override
    public String getPrezime(){return prezime;}
    String getGodinaStudija(){return godinaStudija;}
    String getBrojIndexa(){return brojIndexa;}
    @Override
    public void setIme(String ime){this.ime=ime;}
    @Override
    public void setPrezime(String prezime){this.prezime=prezime;}
    void setGodinaStudija(String godinaStudija){this.godinaStudija=godinaStudija;}
    void setBrojIndexa(String brojIndexa){this.brojIndexa=brojIndexa;}
    @Override
    public String toString(){
        return "Ime: " + getIme()+"\n Prezime: " + getPrezime()+"\n"+"Godina studija: "+ getGodinaStudija()+ "\n Broj indeksa: "+ getBrojIndexa()+"\n";
    }
    @Override
    public String predstavi()
    {
        return this.toString();
    }
}
