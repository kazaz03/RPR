import java.util.*;
abstract public class LicneInformacije implements Predstavi,MozeOcijeniti{
    protected String ime, prezime;
    public
    LicneInformacije()
    {
        ime=null;
        prezime=null;
    }
    LicneInformacije(String ime, String prezime)
    {
        this.ime=ime;
        this.prezime=prezime;
    }
    abstract String getIme();
    abstract String getPrezime();
    abstract void setIme(String ime);
    abstract void setPrezime(String prezime);
    @Override
    public abstract String toString();
    @Override
    public abstract String predstavi();
    @Override
    public Ocjena ocijeni(int x) throws NevalidnaOcjena
    {
        if(x<=0 || x>10) throw new NevalidnaOcjena("Ne valja ocjena");
        Ocjena o=new Ocjena(this,x);
        return o;
    }
}
