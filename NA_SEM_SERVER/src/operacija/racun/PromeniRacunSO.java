/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.racun;

import domen.NacinPlacanja;
import domen.Racun;
import domen.StatusStavke;
import domen.StavkaRacuna;
import operacija.ApstraktnaGenerickaOperacija;
import repository.db.DbConnectionFactory;
import java.sql.*;

/**
 *
 * @author Korisnik
 */
public class PromeniRacunSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Racun)) {
            throw new Exception("Sistem ne može da zapamti račun");
        }
        Racun r = (Racun) param;
        if (r.getIdRacun() <= 0 || r.getProdavac() == null || r.getKupac() == null 
                || r.getDatumIzdavanja() == null || r.getNacinPlacanja() == null || r.getStavke() == null || r.getStavke().isEmpty()) {
            throw new Exception("Sistem ne može da zapamti račun");
        }
        for (StavkaRacuna s : r.getStavke()) {
            if (s.getOprema() == null || s.getKolicina() <= 0) {
                throw new Exception("Sistem ne može da zapamti račun");
            }
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        Racun racun = (Racun) param;

        racun.setPopust(izracunajPopust(racun));

        double ukupno = 0;
        int sledeciRb = sledeciSlobodniRb(racun.getIdRacun());

        for (StavkaRacuna s : racun.getStavke()) {
            if (s.getStatus() == StatusStavke.OBRISANA) {
                continue;
            }
            if (s.getStatus() == StatusStavke.NOVA || s.getStatus() == StatusStavke.IZMENJENA) {
                s.setCena(s.getOprema().getTrenutnaCena());
                s.setIznos(zaokruzi(s.getKolicina() * s.getCena()));
            }
            if (s.getStatus() == StatusStavke.NOVA) {
                s.setRb(sledeciRb);
                sledeciRb++;
            }
            ukupno += s.getIznos();
        }
        racun.setUkupanIznos(zaokruzi((1 - racun.getPopust()) * ukupno));

        broker.edit(racun);

        for (StavkaRacuna s : racun.getStavke()) {
            s.setRacun(racun);
            switch (s.getStatus()) {
                case NOVA:
                    broker.add(s);
                    break;
                case IZMENJENA:
                    broker.edit(s);
                    break;
                case OBRISANA:
                    broker.delete(s);
                    break;
                case NEPROMENJENA:
                default:
                    break;
            }
        }
    }
    
    private double izracunajPopust(Racun racun) {
        double popust = 0;
        if (racun.getNacinPlacanja() == NacinPlacanja.KARTICA) {
            popust = Math.max(popust, 0.05);
        }
        int starost = racun.getKupac().getStarost();
        if (starost <= 27) {
            popust = Math.max(popust, 0.2);
        }
        if (starost >= 64) {
            popust = Math.max(popust, 0.1);
        }
        return popust;
    }

    private double zaokruzi(double vrednost) {
        return Math.round(vrednost * 100.0) / 100.0;
    }
    
    private int sledeciSlobodniRb(int idRacuna) throws Exception {
        Statement st = DbConnectionFactory.getInstance().getConnection().createStatement();
        ResultSet rs = st.executeQuery("SELECT MAX(rb) AS maxRb FROM stavkaracuna WHERE racun = " + idRacuna);
        int maxRb = 0;
        if (rs.next()) {
            maxRb = rs.getInt("maxRb");
        }
        rs.close();
        st.close();
        return maxRb + 1;
    }
}
