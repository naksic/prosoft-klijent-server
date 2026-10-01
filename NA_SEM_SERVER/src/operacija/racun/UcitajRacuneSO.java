/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.racun;

import domen.Oprema;
import domen.Racun;
import domen.StatusStavke;
import domen.StavkaRacuna;
import java.util.ArrayList;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;
import java.sql.*;
import repository.db.DbConnectionFactory;
/**
 *
 * @author Korisnik
 */
public class UcitajRacuneSO extends ApstraktnaGenerickaOperacija {
    List<Racun> racuni;

    public List<Racun> getRacuni() {
        return racuni;
    }
    
    @Override
    protected void preduslovi(Object param) throws Exception {
        
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        String uslov = " JOIN prodavac ON racun.prodavac = prodavac.idProdavac"
                     + " JOIN kupac ON racun.kupac = kupac.idKupca"
                     + " JOIN mesto ON kupac.mesto = mesto.idMesto"
                     + " ORDER BY racun.idRacun ASC";

        racuni = broker.getAll(new Racun(), uslov);

        for (Racun r : racuni) {
            r.setStavke(ucitajStavke(r.getIdRacun()));
        }
    }
    
    private List<StavkaRacuna> ucitajStavke(int idRacuna) throws Exception {
        List<StavkaRacuna> stavke = new ArrayList<>();
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
