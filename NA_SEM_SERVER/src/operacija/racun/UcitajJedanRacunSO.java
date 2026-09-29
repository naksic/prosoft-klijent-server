/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.racun;

import domen.Oprema;
import domen.Racun;
import domen.StatusStavke;
import domen.StavkaRacuna;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;
import java.sql.*;
import repository.db.DbConnectionFactory;

/**
 *
 * @author Korisnik
 */
public class UcitajJedanRacunSO extends ApstraktnaGenerickaOperacija {
    Racun racun;

    public Racun getRacun() {
        return racun;
    }
    
    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Racun)) {
            throw new Exception("Sistem ne može da nađe račun");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        Racun kriterijum = (Racun) param;
        String uslov =
            " JOIN prodavac ON racun.prodavac = prodavac.idProdavac"
          + " JOIN kupac ON racun.kupac = kupac.idKupca"
          + " JOIN mesto ON kupac.mesto = mesto.idMesto"
          + " WHERE racun.idRacun = " + kriterijum.getIdRacun();

        List<Racun> rezultat = broker.getAll(new Racun(), uslov);

        if (rezultat.isEmpty()) {
            throw new Exception("Sistem ne može da nađe račun.");
        }

        racun = rezultat.get(0);
        racun.setStavke(ucitajStavke(racun.getIdRacun()));
    }
    
    private List<StavkaRacuna> ucitajStavke(int idRacuna) throws Exception {
        List<StavkaRacuna> stavke = new java.util.ArrayList<>();
        String upit = "SELECT stavkaracuna.rb, stavkaracuna.kolicina, stavkaracuna.cena, stavkaracuna.iznos, "
                + "oprema.idOpreme, oprema.naziv, oprema.napomena, oprema.trenutnaCena "
                + "FROM stavkaracuna JOIN oprema ON stavkaracuna.oprema = oprema.idOpreme "
                + "WHERE stavkaracuna.racun = " + idRacuna + " ORDER BY stavkaracuna.rb";

        Statement st = DbConnectionFactory.getInstance().getConnection().createStatement();
        ResultSet rs = st.executeQuery(upit);
        while (rs.next()) {
            Oprema o = new Oprema(rs.getInt("idOpreme"), rs.getString("naziv"), rs.getString("napomena"), rs.getDouble("trenutnaCena"));
            StavkaRacuna s = new StavkaRacuna(null, rs.getInt("rb"), rs.getInt("kolicina"), rs.getDouble("cena"), rs.getDouble("iznos"), o);
            s.setStatus(StatusStavke.NEPROMENJENA);
            stavke.add(s);
        }
        rs.close();
        st.close();
        return stavke;
    }
}
