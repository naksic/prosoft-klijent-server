/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Kupac;
import domen.Mesto;
import domen.Oprema;
import domen.Prodavac;
import domen.Racun;
import domen.StrSprema;
import java.util.List;
import operacija.kupci.ObrisiKupcaSO;
import operacija.kupci.PromeniKupcaSO;
import operacija.kupci.UbaciKupcaSO;
import operacija.kupci.UcitajKupceSO;
import operacija.login.LoginOperacija;
import operacija.mesto.UcitajMestaSO;
import operacija.oprema.UcitajOpremuSO;
import operacija.prodavac.UcitajProdavceSO;
import operacija.racun.KreirajRacunSO;
import operacija.strsprema.UbaciStrSpremaSO;

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
    
    public void ubaciStrucnuSpremu(StrSprema ss) throws Exception {
        UbaciStrSpremaSO operacija = new UbaciStrSpremaSO();
        operacija.izvrsi(ss, null);
    }
    
    public List<Prodavac> ucitajProdavce() throws Exception {
        UcitajProdavceSO operacija = new UcitajProdavceSO();
        operacija.izvrsi(null, null);
        return operacija.getListaProdavaca();
    }

    public List<Oprema> ucitajOpremu() throws Exception {
        UcitajOpremuSO operacija = new UcitajOpremuSO();
        operacija.izvrsi(null, null);
        return operacija.getListaOpreme();
    }
    
    public void kreirajRacun(Racun r) throws Exception {
        KreirajRacunSO operacija = new KreirajRacunSO();
        operacija.izvrsi(r, null);
    }
}
