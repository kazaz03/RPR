package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.*;

public class App 
{
    public static String ispisiGradove() throws SQLException {
        GeografijaDAO gd=GeografijaDAO.getInstance();
        ArrayList<Grad> gradovi=new ArrayList<>();
        String ispis="";
        gradovi=gd.gradovi();
        try {
            Connection conn= DriverManager.getConnection("jdbc:sqlite:src/main/resources/mojabaza.db");
            PreparedStatement upit0=conn.prepareStatement("select drzava from grad where naziv=?");
            PreparedStatement upit=conn.prepareStatement("select naziv from drzava where id=?");
            for(Grad grad:gradovi)
            {
                upit0.setString(1,grad.getNaziv());
                ResultSet rez1=upit0.executeQuery();
                int id=rez1.getInt(1);
                upit.setInt(1,id);
                ResultSet rez2=upit.executeQuery();
                String ime_drzave=rez2.getString(1);
                ispis+=grad.getNaziv()+" ("+ime_drzave+") "+"-"+grad.getBroj_stanovnika()+"\n";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ispis;
    }
    public static void glavniGrad()
    {
        Scanner unos=new Scanner(System.in);
        System.out.print("Unesite naziv drzave: ");
        String naziv=unos.nextLine();
        GeografijaDAO gd=GeografijaDAO.getInstance();
        Grad g=gd.glavniGrad(naziv);
        if(g==null)
        {
            System.out.println("Nepostojeca drzava");
            return;
        }
        System.out.println("Glavni grad "+naziv+" je "+g.getNaziv());
    }
    public static void main( String[] args ) throws SQLException {
        System.out.println(ispisiGradove());
        glavniGrad();
    }
}
