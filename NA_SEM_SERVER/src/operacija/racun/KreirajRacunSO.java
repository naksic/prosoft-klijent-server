/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.racun;

import domen.NacinPlacanja;
import domen.Racun;
import domen.StavkaRacuna;
import operacija.ApstraktnaGenerickaOperacija;
import repository.db.DbConnectionFactory;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Korisnik
 */
public class KreirajRacunSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Racun)) {
            throw new Exception("Sistem ne može da kreira račun");
        }
        Racun r = (Racun) param;
        if (r.getProdavac() == null || r.getKupac() == null || r.getDatumIzdavanja() == null
                || r.getNacinPlacanja() == null || r.getStavke() == null || r.getStavke().isEmpty()) {
            throw new Exception("Sistem ne može da kreira račun");
        }
        for (StavkaRacuna s : r.getStavke()) {
            if (s.getOprema() == null || s.getKolicina() <= 0) {
                throw new Exception("Sistem ne može da kreira račun");
            }
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        Racun racun = (Racun) param;

        racun.setPopust(izracunajPopust(racun));

        double ukupno = 0;
        for (StavkaRacuna s : racun.getStavke()) {
            s.setCena(s.getOprema().getTrenutnaCena());
            s.setIznos(s.getKolicina() * s.getCena());
            ukupno += s.getIznos();
        }
        racun.setUkupanIznos((1 - racun.getPopust()) * ukupno);

        broker.add(racun);

        int idRacuna = vratiPoslednjiGenerisaniId();
        racun.setIdRacun(idRacuna);

        int rb = 1;
        for (StavkaRacuna s : racun.getStavke()) {
            s.setRacun(racun);
            s.setRb(rb);
            broker.add(s);
            rb++;
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
    
    private int vratiPoslednjiGenerisaniId() throws Exception {
        Statement st = DbConnectionFactory.getInstance().getConnection().createStatement();
        ResultSet rs = st.executeQuery("SELECT LAST_INSERT_ID()");
        int id = -1;
        if (rs.next()) {
            id = rs.getInt(1);
        }
        rs.close();
        st.close();
        return id;
    }
}
