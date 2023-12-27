package org.example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.*;
import java.util.ArrayList;

public class GeografijaDAO {
    private static GeografijaDAO instance=null;
    private Connection conn;
    private PreparedStatement dajGradoveStatement,dajGlavniGradStatement,obrisiDrzavuStatement,nadjiDrzavuStatement;
    private PreparedStatement obrisiGradoveDrzaveStatement,dodajGradStatement,dodajDrzavuStatement,izmijeniGradStatement;
    private GeografijaDAO()
    {
        try
        {
            conn= DriverManager.getConnection("jdbc:sqlite:src/main/resources/mojabaza.db");
            dajGradoveStatement=conn.prepareStatement("select naziv,broj_stanovnika from grad");
            dajGlavniGradStatement=conn.prepareStatement("select t1.naziv, t1.broj_stanovnika from grad t1,drzava t2 where t1.id=t2.glavni_grad and t2.naziv=?");
            obrisiGradoveDrzaveStatement=conn.prepareStatement("delete from grad where drzava=(select id from drzava where naziv=?)");
            obrisiDrzavuStatement=conn.prepareStatement("delete from drzava where naziv=?");
            dodajGradStatement=conn.prepareStatement("insert into grad (id,naziv,broj_stanovnika,drzava) values(?,?,?,?)");
            dodajDrzavuStatement=conn.prepareStatement("insert into drzava(id,naziv,glavni_grad) values (?,?,?)");
            izmijeniGradStatement=conn.prepareStatement("update grad set naziv=?,broj_stanovnika=?,drzava=? where naziv=?");
            nadjiDrzavuStatement=conn.prepareStatement("select naziv from drzava where naziv=?");
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
            }catch(SQLException e1)
            {
                e1.printStackTrace();
            }
        }
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
    private void regenerisiBazu() {
        Scanner ulaz = null;
        try {
            ulaz = new Scanner(new FileInputStream("src/main/resources/mojabaza.sql"));
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
}
