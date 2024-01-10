package com.example.lab1011;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import javax.xml.transform.Result;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.*;
import java.util.ArrayList;

public class GeografijaDAO{
    private static GeografijaDAO instance=null;
    private Connection conn;
    private PreparedStatement dajGradoveStatement,dajGlavniGradStatement,obrisiDrzavuStatement,nadjiDrzavuStatement;
    private PreparedStatement obrisiGradoveDrzaveStatement,dodajGradStatement,dodajDrzavuStatement,izmijeniGradStatement;
    private PreparedStatement izmijeniDrzavuStatement,drzava_od_gradaStatement,idGradaStatement,idDrzavaGradStatement;
    private PreparedStatement obrisiGradStatement,postaviGlavniGradDrzaveNaNull,dajDrzaveStatement;
    private PreparedStatement dajGlavneGradoveStatement,postaviGlavniGradDrzaveStatement,idGlavnogGradaStatement;
    private PreparedStatement izmjenizaTable,dajIdDrzaveStatement,postaviDrzavuodGrada,nadjiDrzavuPrekoId,obrisiGradove,obrisiDrzave;
    private GeografijaDAO()
    {
        try
        {
            conn= DriverManager.getConnection("jdbc:sqlite:src/main/resources/com/example/lab1011/mojabaza.db");
            dajGradoveStatement=conn.prepareStatement("select naziv,broj_stanovnika from grad");
            dajGlavniGradStatement=conn.prepareStatement("select t1.naziv, t1.broj_stanovnika from grad t1,drzava t2 where t1.id=t2.glavni_grad and t2.naziv=?");
            obrisiGradoveDrzaveStatement=conn.prepareStatement("delete from grad where drzava=(select id from drzava where naziv=?)");
            obrisiDrzavuStatement=conn.prepareStatement("delete from drzava where naziv=?");
            dodajGradStatement=conn.prepareStatement("insert into grad (id,naziv,broj_stanovnika,drzava) values(?,?,?,?)");
            dodajDrzavuStatement=conn.prepareStatement("insert into drzava(id,naziv,glavni_grad) values (?,?,?)");
            izmijeniGradStatement=conn.prepareStatement("update grad set naziv=?,broj_stanovnika=?,drzava=? where naziv=?");
            nadjiDrzavuStatement=conn.prepareStatement("select naziv from drzava where naziv=?");
            izmijeniDrzavuStatement=conn.prepareStatement("update drzava set naziv=?,glavni_grad=?");
            drzava_od_gradaStatement=conn.prepareStatement("select naziv from drzava where glavni_grad=?");
            idDrzavaGradStatement=conn.prepareStatement("select drzava from grad");
            idGradaStatement=conn.prepareStatement("select id from grad");
            obrisiGradStatement=conn.prepareStatement("delete from grad where id=?");
            postaviGlavniGradDrzaveNaNull=conn.prepareStatement("update drzava set glavni_grad=null where glavni_grad=?");
            dajGlavneGradoveStatement=conn.prepareStatement("select naziv,broj_stanovnika from grad where " +
                    "id in (select glavni_grad from drzava)");
            postaviGlavniGradDrzaveStatement=conn.prepareStatement("update drzava set glavni_grad=? where naziv=?");
            idGlavnogGradaStatement=conn.prepareStatement("select id from grad where naziv=?");
            dajDrzaveStatement=conn.prepareStatement("select naziv from drzava");
            izmjenizaTable=conn.prepareStatement("update grad set naziv=?, broj_stanovnika=? where naziv=?");
            dajIdDrzaveStatement=conn.prepareStatement("select id from drzava where naziv=?");
            postaviDrzavuodGrada=conn.prepareStatement("update grad set drzava=? where naziv=?");
            nadjiDrzavuPrekoId=conn.prepareStatement("select naziv from drzava where id=?");
            obrisiGradove=conn.prepareStatement("delete from grad");
            obrisiDrzave=conn.prepareStatement("delete from drzava");
        }catch(SQLException e)
        {
            regenerisiBazu();
            try
            {
                dajGradoveStatement=conn.prepareStatement("Select naziv,broj_stanovnika from grad");
                dajGlavniGradStatement=conn.prepareStatement("select t1.naziv, t1.broj_stanovnika from grad t1,drzava t2 where t1.id=t2.glavni_grad and t2.naziv=?");
                obrisiGradoveDrzaveStatement=conn.prepareStatement("delete from grad where drzava=(select id from drzava where naziv=?)");
                obrisiDrzavuStatement=conn.prepareStatement("delete from drzava where naziv=?");
                dodajGradStatement=conn.prepareStatement("insert into grad (id,naziv,broj_stanovnika,drzava) values(?,?,?,?)");
                dodajDrzavuStatement=conn.prepareStatement("insert into drzava(id,naziv,glavni_grad) values (?,?,?)");
                izmijeniGradStatement=conn.prepareStatement("update grad set naziv=?,broj_stanovnika=?,drzava=? where naziv=?");
                nadjiDrzavuStatement=conn.prepareStatement("select naziv from drzava where naziv=?");
                izmijeniDrzavuStatement=conn.prepareStatement("update drzava set naziv=?,glavni_grad=?");
                drzava_od_gradaStatement=conn.prepareStatement("select naziv from drzava where glavni_grad=?");
                idDrzavaGradStatement=conn.prepareStatement("select drzava from grad");
                idGradaStatement=conn.prepareStatement("select id from grad");
                obrisiGradStatement=conn.prepareStatement("delete from grad where id=?");
                dajGlavneGradoveStatement=conn.prepareStatement("select naziv,broj_stanovnika from grad where " +
                        "id in (select glavni_grad from drzava)");
                postaviGlavniGradDrzaveNaNull=conn.prepareStatement("update drzava set glavni_grad=null where glavni_grad=?");
                postaviGlavniGradDrzaveStatement=conn.prepareStatement("update drzava set glavni_grad=? where naziv=?");
                idGlavnogGradaStatement=conn.prepareStatement("select id from grad where naziv=?");
                dajDrzaveStatement=conn.prepareStatement("select naziv from drzava");
                izmjenizaTable=conn.prepareStatement("update grad set naziv=?, broj_stanovnika=? where naziv=?");
                dajIdDrzaveStatement=conn.prepareStatement("select id from drzava where naziv=?");
                postaviDrzavuodGrada=conn.prepareStatement("update grad set drzava=? where naziv=?");
                nadjiDrzavuPrekoId=conn.prepareStatement("select naziv from drzava where id=?");
                obrisiGradove=conn.prepareStatement("delete from grad");
                obrisiDrzave=conn.prepareStatement("delete from drzava");
            }catch(SQLException e1)
            {
                e1.printStackTrace();
            }
        }
    }
    public synchronized void obrisiGradove()
    {
        try {
            obrisiGradove.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public synchronized void obrisiDrzave()
    {
        try {
            obrisiDrzave.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public String dajDrzavuPrekoId(int id)
    {
        String naziv;
        try {
            nadjiDrzavuPrekoId.setInt(1,id);
            ResultSet s=nadjiDrzavuPrekoId.executeQuery();
            naziv=s.getString(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return naziv;
    }
    public void postaviDrzavuodGrada(int drzava, String naziv)
    {
        try {
            postaviDrzavuodGrada.setInt(1,drzava);
            postaviDrzavuodGrada.setString(2,naziv);
            postaviDrzavuodGrada.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public int dajIDDrzave(String naziv)
    {
        int id;
        try {
            dajIdDrzaveStatement.setString(1,naziv);
            ResultSet set=dajIdDrzaveStatement.executeQuery();
            id=set.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }
    public ObservableList<Drzava> dajDrzave()
    {
        ObservableList<Drzava> lista=FXCollections.observableArrayList();
        try {
            ResultSet set=dajDrzaveStatement.executeQuery();
            while(set.next())
            {
                lista.add(new Drzava(set.getString(1)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }
    public int daj_id_grada(String naziv)
    {
        int id;
        try {
            idGlavnogGradaStatement.setString(1,naziv);
            ResultSet set=idGlavnogGradaStatement.executeQuery();
            id=set.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }
    public void postavi_Glavnigrad(int id,String naziv)
    {
        try {
            postaviGlavniGradDrzaveStatement.setInt(1,id);
            postaviGlavniGradDrzaveStatement.setString(2,naziv);
            postaviGlavniGradDrzaveStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ObservableList<Grad> dajGlavneGradove()
    {
        ObservableList<Grad> gradovi=FXCollections.observableArrayList();
        try {
            ResultSet set=dajGlavneGradoveStatement.executeQuery();
            while(set.next())
            {
                gradovi.add(new Grad(set.getString(1),set.getInt(2)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return gradovi;
    }
    public void obrisiGrad(int id)
    {
        try {
            obrisiGradStatement.setInt(1,id);
            postaviGlavniGradDrzaveNaNull.setInt(1,id);
            obrisiGradStatement.executeUpdate();
            postaviGlavniGradDrzaveNaNull.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public String naziv_drzave(int id)
    {
        String naziv;
        try {
            PreparedStatement o=conn.prepareStatement("select naziv from drzava where id=?");
            o.setInt(1,id);
            ResultSet s=o.executeQuery();
            naziv=s.getString(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return naziv;
    }
    public ObservableList<Integer> IdoviDrzavaOdGradova()
    {
        ObservableList<Integer> lista=FXCollections.observableArrayList();
        try {
            ResultSet set=idDrzavaGradStatement.executeQuery();
            while(set.next())
            {
                lista.add(set.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }
    void dodajGrad(Grad grad) throws SQLException {
        Statement stmt = conn.createStatement();
        String upit_za_prebrojavanje="select count(*) from grad";
        ResultSet rez=stmt.executeQuery(upit_za_prebrojavanje);
        int a=rez.getInt(1);
        a++;
        dodajGradStatement.setInt(1,a);
        dodajGradStatement.setString(2,grad.getNaziv());
        dodajGradStatement.setInt(3,grad.getBroj_stanovnika());
        dodajGradStatement.setInt(4,0);
        dodajGradStatement.executeUpdate();
    }
    public void dodajGradove()
    {
        try {
            dodajGrad(new Grad("Sarajevo",340000));
            dodajGrad(new Grad("Zagreb",700000));
            dodajGrad(new Grad("Konjic",50000));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void regenerisiBazu() {
        Scanner ulaz = null;
        try {
            ulaz = new Scanner(new FileInputStream("src/main/resources/com/example/lab1011/mojabaza.sql"));
            String sqlUpit = "";
            while (ulaz.hasNext()) {
                sqlUpit += ulaz.nextLine();
                if ( sqlUpit.length() > 1 && sqlUpit.charAt( sqlUpit.length()-1 ) == ';') {
                    try {
                        Statement stmt = conn.createStatement();
                        stmt.execute(sqlUpit);
                        sqlUpit = "";
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            } // ...
            ulaz.close();
        } catch (FileNotFoundException e) {
            System.out.println("Ne postoji SQL datoteka... nastavljam sa praznom bazom");
        }
    }
    public Grad glavniGrad(String drzava)
    {
        try {
            dajGlavniGradStatement.setString(1,drzava);
            ResultSet rez=dajGlavniGradStatement.executeQuery();
            if(!rez.next()) return null;
            String naziv=rez.getString(1);
            int broj=rez.getInt(2);
            Grad grad=new Grad(naziv,broj);
            return grad;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void obrisiDrzavu(String drzava)
    {
        try {
            obrisiGradoveDrzaveStatement.setString(1,drzava);
            obrisiDrzavuStatement.setString(1,drzava);
            obrisiGradoveDrzaveStatement.executeUpdate();
            obrisiDrzavuStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void dodajDrzave(Drzava drzava)
    {
        try {
            Statement stmt=conn.createStatement();
            String upit="select count(*) from drzava";
            ResultSet rez=stmt.executeQuery(upit);
            int a=rez.getInt(1);
            a++;
            dodajDrzavuStatement.setInt(1,a);
            dodajDrzavuStatement.setString(2,drzava.getNaziv());
            dodajDrzavuStatement.setInt(3,0);
            dodajDrzavuStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void dodajDrzavee()
    {
        dodajDrzave(new Drzava("Bosna i Hercegovina"));
        dodajDrzave(new Drzava("Srbija"));
        dodajDrzave(new Drzava("Hrvatska"));
    }
    public void izmijeniGrad(Grad grad)
    {
        try {
            izmijeniGradStatement.setString(1,"Makarska");
            izmijeniGradStatement.setInt(2,6900);
            izmijeniGradStatement.setInt(3,0);
            izmijeniGradStatement.setString(4,grad.getNaziv());
            izmijeniGradStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void izmijeniGradzaBazu(String nazivgr, int broj,String stari)
    {
        try {
            izmjenizaTable.setString(1,nazivgr);
            izmjenizaTable.setInt(2,broj);
            izmjenizaTable.setString(3,stari);
            izmjenizaTable.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void izmijeniDrzavu(Drzava drzava)
    {
        try
        {
            izmijeniDrzavuStatement.setString(1,drzava.getNaziv());
            izmijeniDrzavuStatement.setString(2,glavniGrad(drzava.getNaziv()).getNaziv());
            izmijeniDrzavuStatement.executeUpdate();
        }catch(SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
    public Drzava nadjiDrzavu(String drzava)
    {
        try {
            nadjiDrzavuStatement.setString(1,drzava);
            ResultSet rez=nadjiDrzavuStatement.executeQuery();
            if(!rez.next()) return null;
            String naziv=rez.getString(1);
            Drzava drzava1=new Drzava(naziv);
            return drzava1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<Grad> gradovi()
    {
        ArrayList<Grad> gradovi=new ArrayList<>();
        try {
            ResultSet rezultat=dajGradoveStatement.executeQuery();
            while(rezultat.next())
            {
                String naziv=rezultat.getString(1);
                int broj=rezultat.getInt(2);
                Grad g=new Grad(naziv,broj);
                gradovi.add(g);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //sortirani po opadajucem redoslijedu
        for(int i=0; i<gradovi.size(); i++)
        {
            for(int j=0; j<gradovi.size()-1-i; j++)
            {
                if(gradovi.get(j).compareTo(gradovi.get(j+1))<0)
                {
                    Grad temp = new Grad(gradovi.get(j).getNaziv(), gradovi.get(j).getBroj_stanovnika());
                    gradovi.get(j).setNaziv(gradovi.get(j + 1).getNaziv());
                    gradovi.get(j).setBroj_stanovnika(gradovi.get(j + 1).getBroj_stanovnika());
                    gradovi.get(j + 1).setNaziv(temp.getNaziv());
                    gradovi.get(j + 1).setBroj_stanovnika(temp.getBroj_stanovnika());
                }
            }
        }
        return gradovi;
    }
    public static GeografijaDAO getInstance() {
        if (instance == null) instance = new GeografijaDAO();
        return instance;
    }
    public static void removeInstance() {
        if (instance != null) {
            try {
                instance.conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        instance = null;
    }
    public ObservableList<Grad> gradoviBaze()
    {
        ObservableList<Grad> lista= FXCollections.observableArrayList();
        try {
            ResultSet gradovi=dajGradoveStatement.executeQuery();
            while(gradovi.next())
            {
                lista.add(new Grad(gradovi.getString(1),gradovi.getInt(2)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }
    public void vratiNaDefault() {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM grad");
            stmt.executeUpdate("DELETE FROM drzava");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ObservableList<Integer> Id_gradova()
    {
        ObservableList<Integer> ids=FXCollections.observableArrayList();
        try
        {
            ResultSet set=idGradaStatement.executeQuery();
            while(set.next())
            {
                ids.add(set.getInt(1));
            }
        }catch(SQLException e)
        {
            throw new RuntimeException(e);
        }
        return ids;
    }
}