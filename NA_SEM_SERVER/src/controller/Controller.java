/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Kupac;
import domen.Mesto;
import domen.Prodavac;
import java.util.List;
import operacija.kupci.ObrisiKupcaSO;
import operacija.kupci.PromeniKupcaSO;
import operacija.kupci.UbaciKupcaSO;
import operacija.kupci.UcitajKupceSO;
import operacija.login.LoginOperacija;
import operacija.mesto.UcitajMestaSO;

/**
 *
 * @author Korisnik
 */
public class Controller {
    private static Controller instance;

    private Controller() {
    }

    public static Controller getInstance() {
        if(instance == null)
            instance = new Controller();
        return instance;
    }

    public Prodavac login(Prodavac p) throws Exception {
        LoginOperacija operacija = new LoginOperacija();
        operacija.izvrsi(p, null);
        System.out.println("KLASA Controller - " + operacija.getProdavac());
        return operacija.getProdavac();
    }

    public List<Kupac> ucitajKupce() throws Exception {
        UcitajKupceSO operacija = new UcitajKupceSO();
        operacija.izvrsi(null, null);
        System.out.println("Klasa Controller - " + operacija.getKupci());
        return operacija.getKupci();
    }

    public void obrisiKupca(Kupac k) throws Exception {
        ObrisiKupcaSO operacija = new ObrisiKupcaSO();
        operacija.izvrsi(k, null);
    }

    public List<Mesto> ucitajMesta() throws Exception {
        UcitajMestaSO operacija = new UcitajMestaSO();
        operacija.izvrsi(null, null);
        return operacija.getListaMesta();
    }

    public void ubaciKupca(Kupac kupacSaForme) throws Exception {
        UbaciKupcaSO operacija = new UbaciKupcaSO();
        operacija.izvrsi(kupacSaForme, null);
    }

    public void promeniKupca(Kupac k) throws Exception {
        PromeniKupcaSO operacija = new PromeniKupcaSO();
        operacija.izvrsi(k, null);
    }
    
    
}
