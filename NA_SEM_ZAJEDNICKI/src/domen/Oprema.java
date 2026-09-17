/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Korisnik
 */
public class Oprema implements ApstraktniDomenskiObjekat {
    private int idOpreme;
    private String naziv;
    private String napomena;
    private double trenutnaCena;

    public Oprema() {
    }

    public Oprema(int idOpreme, String naziv, String napomena, double trenutnaCena) {
        this.idOpreme = idOpreme;
        this.naziv = naziv;
        this.napomena = napomena;
        this.trenutnaCena = trenutnaCena;
    }

    

    public int getIdOpreme() {
        return idOpreme;
    }

    public void setIdOpreme(int idOpreme) {
        this.idOpreme = idOpreme;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public double getTrenutnaCena() {
        return trenutnaCena;
    }

    public void setTrenutnaCena(double trenutnaCena) {
        this.trenutnaCena = trenutnaCena;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Oprema other = (Oprema) obj;
        if (Double.doubleToLongBits(this.trenutnaCena) != Double.doubleToLongBits(other.trenutnaCena)) {
            return false;
        }
        return Objects.equals(this.naziv, other.naziv);
    }

    @Override
    public String toString() {
        return naziv + " " + trenutnaCena;
    }

    @Override
    public String vratiNazivTabele() {
        return "oprema";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while(rs.next()) {
            int idOpreme = rs.getInt("oprema.idOpreme");
            String naziv = rs.getString("oprema.naziv");
            String napomena = rs.getString("oprema.napomena");
            double trenutnaCena = rs.getDouble("oprema.trenutnaCena");
        
            Oprema o = new Oprema(idOpreme, naziv, napomena, trenutnaCena);
            lista.add(o);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "naziv,napomena,trenutnaCena";
    }

    @Override
    public String vratiVrednostZaUbacivanje() {
        return "'" + naziv + "','" + napomena + "'," + trenutnaCena;
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "oprema.idOpreme=" + idOpreme;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        return "naziv = '" + naziv + "', napomena = '" + napomena + "', trenutnaCena = " + trenutnaCena;
    }
    
    
}
