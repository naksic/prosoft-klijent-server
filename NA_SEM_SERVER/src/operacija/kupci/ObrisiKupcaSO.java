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
public class ObrisiKupcaSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param == null || !(param instanceof Kupac))
            throw new Exception("Sistem ne može da nađe kupca");
        
        Kupac k = (Kupac) param;
        if (k.getIdKupca() <= 0)
            throw new Exception("Sistem ne može da nađe kupca");
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        try {
            broker.delete((Kupac) param);
        } catch (java.sql.SQLIntegrityConstraintViolationException e) {
            throw new Exception("Sistem ne može da obriše kupca. Razlog: kupac poseduje račun.");
        }
    }
    
}
