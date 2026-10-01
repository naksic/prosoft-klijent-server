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
import java.text.SimpleDateFormat;

/**
 *
 * @author Korisnik
 */
public class Racun implements ApstraktniDomenskiObjekat {
    private int idRacun;
    private Date datumIzdavanja;
    private NacinPlacanja nacinPlacanja;
    private String napomena;
    private double popust;
    private double ukupanIznos;
    private Prodavac prodavac;
    private Kupac kupac;
    private List<StavkaRacuna> stavke = new ArrayList<>();

    public Racun() {
    }

    public Racun(int idRacun, Date datumIzdavanja, NacinPlacanja nacinPlacanja, String napomena, double popust, double ukupanIznos,
            Prodavac prodavac, Kupac kupac) {
        this.idRacun = idRacun;
        this.datumIzdavanja = datumIzdavanja;
        this.nacinPlacanja = nacinPlacanja;
        this.napomena = napomena;
        this.popust = popust;
        this.ukupanIznos = ukupanIznos;
        this.prodavac = prodavac;
        this.kupac = kupac;
    }

    public int getIdRacun() {
        return idRacun;
    }

    public void setIdRacun(int idRacun) {
        this.idRacun = idRacun;
    }

    public Date getDatumIzdavanja() {
        return datumIzdavanja;
    }

    public void setDatumIzdavanja(Date datumIzdavanja) {
        this.datumIzdavanja = datumIzdavanja;
    }

    public NacinPlacanja getNacinPlacanja() {
        return nacinPlacanja;
    }

    public void setNacinPlacanja(NacinPlacanja nacinPlacanja) {
        this.nacinPlacanja = nacinPlacanja;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    public double getPopust() {
        return popust;
    }

    public void setPopust(double popust) {
        this.popust = popust;
    }

    public double getUkupanIznos() {
        return ukupanIznos;
    }

    public void setUkupanIznos(double ukupanIznos) {
        this.ukupanIznos = ukupanIznos;
    }

    public Prodavac getProdavac() {
        return prodavac;
    }

    public void setProdavac(Prodavac prodavac) {
        this.prodavac = prodavac;
    }

    public Kupac getKupac() {
        return kupac;
    }

    public void setKupac(Kupac kupac) {
        this.kupac = kupac;
    }

    public List<StavkaRacuna> getStavke() {
        return stavke;
    }

    public void setStavke(List<StavkaRacuna> stavke) {
        this.stavke = stavke;
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
        final Racun other = (Racun) obj;
        if (Double.doubleToLongBits(this.ukupanIznos) != Double.doubleToLongBits(other.ukupanIznos)) {
            return false;
        }
        if (!Objects.equals(this.datumIzdavanja, other.datumIzdavanja)) {
            return false;
        }
        if (this.nacinPlacanja != other.nacinPlacanja) {
            return false;
        }
        if (!Objects.equals(this.prodavac, other.prodavac)) {
            return false;
        }
        return Objects.equals(this.kupac, other.kupac);
    }

    

    @Override
    public String toString() {
        return datumIzdavanja + " - " + nacinPlacanja + " - " + ukupanIznos;
    }

    @Override
    public String vratiNazivTabele() {
        return "racun";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int idRacun = rs.getInt("racun.idRacun");
            java.sql.Date datumIzdavanja = rs.getDate("racun.datumIzdavanja");
            NacinPlacanja nacinPlacanja = NacinPlacanja.valueOf(rs.getString("racun.nacinPlacanja"));
            String napomena = rs.getString("racun.napomena");
            double popust = rs.getDouble("racun.popust");
            double ukupanIznos = rs.getDouble("racun.ukupanIznos");

            int idProdavac = rs.getInt("prodavac.idProdavac");
            String emailP = rs.getString("prodavac.email");
            String imeP = rs.getString("prodavac.ime");
            String prezimeP = rs.getString("prodavac.prezime");
            java.sql.Date datumRodjenjaP = rs.getDate("prodavac.datumRodjenja");
            String telefonP = rs.getString("prodavac.telefon");
            String korisnickoIme = rs.getString("prodavac.korisnickoIme");
            String sifra = rs.getString("prodavac.sifra");
            Prodavac prodavac = new Prodavac(idProdavac, emailP, imeP, prezimeP, datumRodjenjaP, telefonP, korisnickoIme, sifra);

            int idKupca = rs.getInt("kupac.idKupca");
            String imeK = rs.getString("kupac.ime");
            String prezimeK = rs.getString("kupac.prezime");
            String brojLK = rs.getString("kupac.brojLoyaltyKartice");
            String kontakt = rs.getString("kupac.kontakt");
            java.sql.Date datumRodjenjaK = rs.getDate("kupac.datumRodjenja");

            int idMesto = rs.getInt("mesto.idMesto");
            String nazivMesta = rs.getString("mesto.naziv");
            int ptt = rs.getInt("mesto.postanskiBroj");
            Mesto mesto = new Mesto(idMesto, nazivMesta, ptt);

            Kupac kupac = new Kupac(idKupca, imeK, prezimeK, brojLK, kontakt, datumRodjenjaK, mesto);

            Racun racun = new Racun(idRacun, datumIzdavanja, nacinPlacanja, napomena, popust, ukupanIznos, prodavac, kupac);
            lista.add(racun);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "datumIzdavanja,nacinPlacanja,napomena,popust,ukupanIznos,prodavac,kupac";
    }

    @Override
    public String vratiVrednostZaUbacivanje() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return "'" + sdf.format(datumIzdavanja) + "','" + nacinPlacanja.name() + "','" + napomena + "'," + popust + "," + ukupanIznos + "," + prodavac.getIdProdavac()+ "," + kupac.getIdKupca();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "racun.idRacun=" + idRacun;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return "datumIzdavanja = '" + sdf.format(datumIzdavanja) + "', nacinPlacanja = '" + nacinPlacanja.name()
                + "', napomena = '" + napomena + "', popust = " + popust + ", ukupanIznos = " + ukupanIznos
                + ", prodavac = " + prodavac.getIdProdavac() + ", kupac = " + kupac.getIdKupca();
    }

}
