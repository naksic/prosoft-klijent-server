/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.kupci;

import domen.Kupac;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Korisnik
 */
public class PretraziKupceSO extends ApstraktnaGenerickaOperacija {
    List<Kupac> kupci;

    public List<Kupac> getKupci() {
        return kupci;
    }
    
    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Kupac)) {
            throw new Exception("Sistem ne može da nađe kupce po zadatim kriterijumima");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        Kupac kriterijum = (Kupac) param;
        StringBuilder uslov = new StringBuilder(" JOIN mesto ON kupac.mesto = mesto.idMesto WHERE 1=1");

        if (kriterijum.getIme() != null && !kriterijum.getIme().trim().isEmpty()) {
            uslov.append(" AND LOWER(kupac.ime) LIKE LOWER('%").append(kriterijum.getIme().trim()).append("%')");
        }
        if (kriterijum.getPrezime() != null && !kriterijum.getPrezime().trim().isEmpty()) {
            uslov.append(" AND LOWER(kupac.prezime) LIKE LOWER('%").append(kriterijum.getPrezime().trim()).append("%')");
        }
        if (kriterijum.getBrojLoyaltyKartice() != null && !kriterijum.getBrojLoyaltyKartice().trim().isEmpty()) {
            uslov.append(" AND kupac.brojLoyaltyKartice LIKE '%").append(kriterijum.getBrojLoyaltyKartice().trim()).append("%'");
        }
        if (kriterijum.getKontakt() != null && !kriterijum.getKontakt().trim().isEmpty()) {
            uslov.append(" AND LOWER(kupac.kontakt) LIKE LOWER('%").append(kriterijum.getKontakt().trim()).append("%')");
        }
        if (kriterijum.getStarost() > 0) {
            uslov.append(" AND TIMESTAMPDIFF(YEAR, kupac.datumRodjenja, CURDATE()) = ").append(kriterijum.getStarost());
        }
        if (kriterijum.getMesto() != null) {
            uslov.append(" AND kupac.mesto = ").append(kriterijum.getMesto().getIdMesto());
        }

        uslov.append(" ORDER BY kupac.idKupca ASC");

        kupci = broker.getAll(new Kupac(), uslov.toString());
    }
    
}
