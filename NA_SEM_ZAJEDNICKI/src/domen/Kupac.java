/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Korisnik
 */
public class Kupac implements ApstraktniDomenskiObjekat {
    private int idKupca;
    private String ime;
    private String prezime;
    private String brojLoyaltyKartice;
    private String kontakt;
    private Date datumRodjenja;
    private int starost;
    private Mesto mesto;

    public Kupac() {
    }

    public Kupac(int idKupca, String ime, String prezime, String brojLoyaltyKartice, String kontakt, java.util.Date datumRodjenja, Mesto mesto) {
        this.idKupca = idKupca;
        this.ime = ime;
        this.prezime = prezime;
        this.brojLoyaltyKartice = brojLoyaltyKartice;
        this.kontakt = kontakt;
        this.mesto = mesto;
        setDatumRodjenja(datumRodjenja);
}

    public int getIdKupca() {
        return idKupca;
    }

    public void setIdKupca(int idKupca) {
        this.idKupca = idKupca;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getBrojLoyaltyKartice() {
        return brojLoyaltyKartice;
    }

    public void setBrojLoyaltyKartice(String brojLoyaltyKartice) {
        this.brojLoyaltyKartice = brojLoyaltyKartice;
    }

    public String getKontakt() {
        return kontakt;
    }

    public void setKontakt(String kontakt) {
        this.kontakt = kontakt;
    }

    public int getStarost() {
        return starost;
    }

    public void setStarost(int starost) {
        this.starost = starost;
    }

    public Mesto getMesto() {
        return mesto;
    }

    public void setMesto(Mesto mesto) {
        this.mesto = mesto;
    }
    
    public java.util.Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(java.util.Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
        this.starost = izracunajStarost(datumRodjenja);
    }

    private int izracunajStarost(java.util.Date datumRodjenja) {
        if (datumRodjenja == null) {
            return 0;
        }
        java.time.LocalDate danas = java.time.LocalDate.now();
        java.time.LocalDate rodjendan = new java.sql.Date(datumRodjenja.getTime()).toLocalDate();
        return java.time.Period.between(rodjendan, danas).getYears();
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
        final Kupac other = (Kupac) obj;
        if (!Objects.equals(this.ime, other.ime)) {
            return false;
        }
        if (!Objects.equals(this.prezime, other.prezime)) {
            return false;
        }
        return Objects.equals(this.brojLoyaltyKartice, other.brojLoyaltyKartice);
    }

    

    @Override
    public String toString() {
        return ime + " " + prezime + " - " + mesto.getNaziv();
    }

    @Override
    public String vratiNazivTabele() {
        return "kupac";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while(rs.next()) {
            int idKupca = rs.getInt("kupac.idKupca");
            String ime = rs.getString("kupac.ime");
            String prezime = rs.getString("kupac.prezime");
            String brojLK = rs.getString("kupac.brojLoyaltyKartice");
            String kontakt = rs.getString("kupac.kontakt");
            java.sql.Date datumRodjenja = rs.getDate("kupac.datumRodjenja");

            int idMesto = rs.getInt("mesto.idMesto");
            String nazivMesta= rs.getString("mesto.naziv");
            int ptt = rs.getInt("mesto.postanskiBroj"); 
            Mesto mesto = new Mesto(idMesto, nazivMesta, ptt);

            Kupac k = new Kupac(idKupca, ime, prezime, brojLK, kontakt, datumRodjenja, mesto);
            lista.add(k);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "ime,prezime,brojLoyaltyKartice,kontakt,starost,datumRodjenja,mesto";
    }

    @Override
    public String vratiVrednostZaUbacivanje() {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        return "'" + ime + "','" + prezime + "'," + brojLoyaltyKarticeZaUpit() + ",'" + kontakt + "'," + starost + ",'" + sdf.format(datumRodjenja) + "'," + mesto.getIdMesto();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "kupac.idKupca=" + idKupca;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        return "ime = '" + ime + "', prezime = '" + prezime + "', brojLoyaltyKartice = " + brojLoyaltyKarticeZaUpit() + ", kontakt = '" + kontakt + "', starost = " + starost + ", datumRodjenja = '" + sdf.format(datumRodjenja) + "', mesto = " + mesto.getIdMesto();
    }
    
    private String brojLoyaltyKarticeZaUpit() {
        if (brojLoyaltyKartice == null || brojLoyaltyKartice.trim().isEmpty()) {
            return "NULL";
        }
        return "'" + brojLoyaltyKartice + "'";
    }
}
