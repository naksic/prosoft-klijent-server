/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.kupci;

import domen.Kupac;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Korisnik
 */
public class KreirajKupcaSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Kupac)) {
            throw new Exception("Sistem ne može da zapamti kupca.");
        }
        Kupac k = (Kupac) param;
        if (k.getIme() == null || k.getIme().trim().isEmpty() || k.getPrezime() == null || k.getPrezime().trim().isEmpty()
                || k.getKontakt() == null || k.getKontakt().trim().isEmpty() || k.getDatumRodjenja() == null || k.getMesto() == null) {
            throw new Exception("Sistem ne može da zapamti kupca.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        try {
            broker.add((Kupac) param);
        } catch (java.sql.SQLIntegrityConstraintViolationException e) {
            if (e.getMessage() != null && e.getMessage().contains("uq_kupac_brojLoyaltyKartice")) {
                throw new Exception("Sistem ne može da zapamti kupca. Razlog: broj loyalty kartice već postoji.");
            }
            throw new Exception("Sistem ne može da zapamti kupca.");
        }
    }
    
}
