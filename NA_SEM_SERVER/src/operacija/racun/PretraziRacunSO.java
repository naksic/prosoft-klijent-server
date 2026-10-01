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
import repository.db.DbConnectionFactory;
import java.sql.*;

/**
 *
 * @author Korisnik
 */
public class PretraziRacunSO extends ApstraktnaGenerickaOperacija {
    List<Racun> racuni;

    public List<Racun> getRacuni() {
        return racuni;
    }
    
    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Racun)) {
            throw new Exception("Sistem ne može da nađe račune po zadatim kriterijumima");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        Racun kriterijum = (Racun) param;
        StringBuilder uslov = new StringBuilder(
            " JOIN prodavac ON racun.prodavac = prodavac.idProdavac"
          + " JOIN kupac ON racun.kupac = kupac.idKupca"
          + " JOIN mesto ON kupac.mesto = mesto.idMesto"
          + " WHERE 1=1"
        );

        if (kriterijum.getDatumIzdavanja() != null) {
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
            uslov.append(" AND racun.datumIzdavanja = '").append(sdf.format(kriterijum.getDatumIzdavanja())).append("'");
        }
        if (kriterijum.getNacinPlacanja() != null) {
            uslov.append(" AND racun.nacinPlacanja = '").append(kriterijum.getNacinPlacanja().name()).append("'");
        }
        if (kriterijum.getProdavac() != null) {
            uslov.append(" AND racun.prodavac = ").append(kriterijum.getProdavac().getIdProdavac());
        }
        if (kriterijum.getKupac() != null) {
            uslov.append(" AND racun.kupac = ").append(kriterijum.getKupac().getIdKupca());
        }
        if (kriterijum.getStavke() != null && !kriterijum.getStavke().isEmpty() && kriterijum.getStavke().get(0).getOprema() != null) {
            int idOpreme = kriterijum.getStavke().get(0).getOprema().getIdOpreme();
            uslov.append(" AND racun.idRacun IN (SELECT stavkaracuna.racun FROM stavkaracuna WHERE stavkaracuna.oprema = ").append(idOpreme).append(")");
        }

        uslov.append(" ORDER BY racun.idRacun ASC");

        racuni = broker.getAll(new Racun(), uslov.toString());

        for (Racun r : racuni) {
            r.setStavke(ucitajStavke(r.getIdRacun()));
        }
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
