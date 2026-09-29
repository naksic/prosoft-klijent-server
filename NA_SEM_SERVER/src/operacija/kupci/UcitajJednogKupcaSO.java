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
public class UcitajJednogKupcaSO extends ApstraktnaGenerickaOperacija {
    Kupac kupac;

    public Kupac getKupac() {
        return kupac;
    }
    
    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Kupac)) {
            throw new Exception("Sistem ne može da nađe kupca");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        Kupac kriterijum = (Kupac) param;
        String uslov = " JOIN mesto ON kupac.mesto = mesto.idMesto WHERE kupac.idKupca = " + kriterijum.getIdKupca();

        List<Kupac> rezultat = broker.getAll(new Kupac(), uslov);

        if (rezultat.isEmpty()) {
            throw new Exception("Sistem ne može da nađe kupca.");
        }

        kupac = rezultat.get(0);
    }
    
}
