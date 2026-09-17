/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Korisnik
 */
public class PrSS implements ApstraktniDomenskiObjekat {
    private Prodavac prodavac;
    private StrSprema strucnaSprema;
    private Date datumSticanja;
    private String brojDiplome;

    public PrSS() {
    }

    public PrSS(Prodavac prodavac, StrSprema strucnaSprema, Date datumSticanja, String brojDiplome) {
        this.prodavac = prodavac;
        this.strucnaSprema = strucnaSprema;
        this.datumSticanja = datumSticanja;
        this.brojDiplome = brojDiplome;
    }

    public Prodavac getProdavac() {
        return prodavac;
    }

    public void setProdavac(Prodavac prodavac) {
        this.prodavac = prodavac;
    }

    public StrSprema getStrucnaSprema() {
        return strucnaSprema;
    }

    public void setStrucnaSprema(StrSprema strucnaSprema) {
        this.strucnaSprema = strucnaSprema;
    }

    public Date getDatumSticanja() {
        return datumSticanja;
    }

    public void setDatumSticanja(Date datumSticanja) {
        this.datumSticanja = datumSticanja;
    }

    public String getBrojDiplome() {
        return brojDiplome;
    }

    public void setBrojDiplome(String brojDiplome) {
        this.brojDiplome = brojDiplome;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final PrSS other = (PrSS) obj;
        if (!Objects.equals(this.brojDiplome, other.brojDiplome)) {
            return false;
        }
        if (!Objects.equals(this.prodavac, other.prodavac)) {
            return false;
        }
        if (!Objects.equals(this.strucnaSprema, other.strucnaSprema)) {
            return false;
        }
        return Objects.equals(this.datumSticanja, other.datumSticanja);
    }

    @Override
    public String toString() {
        return datumSticanja + " " + brojDiplome;
    }

    @Override
    public String vratiNazivTabele() {
        return "prss";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "prodavac,strucnaSprema,datumSticanja,brojDiplome";
    }

    @Override
    public String vratiVrednostZaUbacivanje() {
        return prodavac.getIdProdavac() + "," + strucnaSprema.getIdStrucnaSprema() + ",'" + datumSticanja + "','" + brojDiplome + "'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "prss.prodavac=" + prodavac.getIdProdavac() + " AND prss.strucnaSprema=" + strucnaSprema.getIdStrucnaSprema();
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        return "datumSticanja = '" + datumSticanja + "', brojDiplome = '" + brojDiplome + "'";
    }
    
    
}
