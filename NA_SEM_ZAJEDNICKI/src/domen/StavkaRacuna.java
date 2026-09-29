/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Korisnik
 */
public class StavkaRacuna implements ApstraktniDomenskiObjekat{
    private Racun racun;
    private int rb;
    private int kolicina;
    private double cena;
    private double iznos;
    private Oprema oprema;
    private StatusStavke status;

    public StavkaRacuna() {
    }

    public StavkaRacuna(Racun racun, int rb, int kolicina, double cena, double iznos, Oprema oprema) {
        this.racun = racun;
        this.rb = rb;
        this.kolicina = kolicina;
        this.cena = cena;
        this.iznos = iznos;
        this.oprema = oprema;
    }

    public Racun getRacun() {
        return racun;
    }

    public void setRacun(Racun racun) {
        this.racun = racun;
    }
    
    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public double getIznos() {
        return iznos;
    }

    public void setIznos(double iznos) {
        this.iznos = iznos;
    }

    public Oprema getOprema() {
        return oprema;
    }

    public void setOprema(Oprema oprema) {
        this.oprema = oprema;
    }
    
    public StatusStavke getStatus() {
        return status;
    }

    public void setStatus(StatusStavke status) {
        this.status = status;
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
        final StavkaRacuna other = (StavkaRacuna) obj;
        if (this.rb != other.rb) {
            return false;
        }
        if (Double.doubleToLongBits(this.iznos) != Double.doubleToLongBits(other.iznos)) {
            return false;
        }
        if (!Objects.equals(this.racun, other.racun)) {
            return false;
        }
        return Objects.equals(this.oprema, other.oprema);
    }

    @Override
    public String toString() {
        return rb + " " + iznos;
    }

    @Override
    public String vratiNazivTabele() {
        return "stavkaracuna";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "racun,rb,kolicina,cena,iznos,oprema";
    }

    @Override
    public String vratiVrednostZaUbacivanje() {
        return racun.getIdRacun() + "," + rb + "," + kolicina + "," + cena + "," + iznos + "," + oprema.getIdOpreme();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "racun = " + racun.getIdRacun() + " AND rb = " + rb;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        return "kolicina = " + kolicina + ", cena = " + cena + ", iznos = " + iznos;
    }
    
    
}
