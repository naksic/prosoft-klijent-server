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
public class StrSprema implements ApstraktniDomenskiObjekat {
    private int idStrucnaSprema;
    private String zvanje;
    private int stepen;

    public StrSprema() {
    }

    public StrSprema(int idStrucnaSprema, String zvanje, int stepen) {
        this.idStrucnaSprema = idStrucnaSprema;
        this.zvanje = zvanje;
        this.stepen = stepen;
    }

    public int getIdStrucnaSprema() {
        return idStrucnaSprema;
    }

    public void setIdStrucnaSprema(int idStrucnaSprema) {
        this.idStrucnaSprema = idStrucnaSprema;
    }

    public String getZvanje() {
        return zvanje;
    }

    public void setZvanje(String zvanje) {
        this.zvanje = zvanje;
    }

    public int getStepen() {
        return stepen;
    }

    public void setStepen(int stepen) {
        this.stepen = stepen;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final StrSprema other = (StrSprema) obj;
        if (this.stepen != other.stepen) {
            return false;
        }
        return Objects.equals(this.zvanje, other.zvanje);
    }

    @Override
    public String toString() {
        return zvanje + " " + stepen;
    }

    @Override
    public String vratiNazivTabele() {
        return "strsprema";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while(rs.next()) {
            int idStrucnaSprema = rs.getInt("strsprema.idStrucnaSprema");
            String zvanje = rs.getString("strsprema.zvanje");
            int stepen = rs.getInt("strsprema.stepen");

            StrSprema ss = new StrSprema(idStrucnaSprema, zvanje, stepen);
            lista.add(ss);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "zvanje,stepen";
    }

    @Override
    public String vratiVrednostZaUbacivanje() {
        return "'" + zvanje + "'," + stepen;
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "strsprema.idStrucnaSprema=" + idStrucnaSprema;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        return "zvanje = '" + zvanje + "', stepen = " + stepen;
    }
    
    
}
