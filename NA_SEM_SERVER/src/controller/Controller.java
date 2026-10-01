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
import operacija.kupci.PretraziKupceSO;
import operacija.kupci.PromeniKupcaSO;
import operacija.kupci.KreirajKupcaSO;
import operacija.kupci.UcitajJednogKupcaSO;
import operacija.kupci.VratiListuSviKupacSO;
import operacija.login.PrijaviProdavacSO;
import operacija.mesto.VratiListuSviMestoSO;
import operacija.oprema.VratiListuSviOpremaSO;
import operacija.prodavac.VratiListuSviProdavacSO;
import operacija.racun.KreirajRacunSO;
import operacija.racun.PretraziRacunSO;
import operacija.racun.PromeniRacunSO;
import operacija.racun.UcitajJedanRacunSO;
import operacija.racun.UcitajRacuneSO;
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
        PrijaviProdavacSO operacija = new PrijaviProdavacSO();
        operacija.izvrsi(p, null);
        return operacija.getProdavac();
    }

    public List<Kupac> ucitajKupce() throws Exception {
        VratiListuSviKupacSO operacija = new VratiListuSviKupacSO();
        operacija.izvrsi(null, null);
        return operacija.getKupci();
    }
    
    public List<Kupac> pretraziKupce(Kupac kriterijum) throws Exception {
        PretraziKupceSO operacija = new PretraziKupceSO();
        operacija.izvrsi(kriterijum, null);
        return operacija.getKupci();
    }
    
    public void obrisiKupca(Kupac k) throws Exception {
        ObrisiKupcaSO operacija = new ObrisiKupcaSO();
        operacija.izvrsi(k, null);
    }

    public List<Mesto> ucitajMesta() throws Exception {
        VratiListuSviMestoSO operacija = new VratiListuSviMestoSO();
        operacija.izvrsi(null, null);
        return operacija.getListaMesta();
    }

    public void ubaciKupca(Kupac kupacSaForme) throws Exception {
        KreirajKupcaSO operacija = new KreirajKupcaSO();
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
        VratiListuSviProdavacSO operacija = new VratiListuSviProdavacSO();
        operacija.izvrsi(null, null);
        return operacija.getListaProdavaca();
    }

    public List<Oprema> ucitajOpremu() throws Exception {
        VratiListuSviOpremaSO operacija = new VratiListuSviOpremaSO();
        operacija.izvrsi(null, null);
        return operacija.getListaOpreme();
    }
    
    public void kreirajRacun(Racun r) throws Exception {
        KreirajRacunSO operacija = new KreirajRacunSO();
        operacija.izvrsi(r, null);
    }
    
    public List<Racun> pretraziRacune(Racun kriterijum) throws Exception {
        PretraziRacunSO operacija = new PretraziRacunSO();
        operacija.izvrsi(kriterijum, null);
        return operacija.getRacuni();
    }
    
    public void promeniRacun(Racun r) throws Exception {
        PromeniRacunSO operacija = new PromeniRacunSO();
        operacija.izvrsi(r, null);
    }
    
    public Kupac ucitajJednogKupca(Kupac kriterijum) throws Exception {
        UcitajJednogKupcaSO operacija = new UcitajJednogKupcaSO();
        operacija.izvrsi(kriterijum, null);
        return operacija.getKupac();
    }
    
    public Racun ucitajJedanRacun(Racun kriterijum) throws Exception {
        UcitajJedanRacunSO operacija = new UcitajJedanRacunSO();
        operacija.izvrsi(kriterijum, null);
        return operacija.getRacun();
    }
    
    public List<Racun> ucitajRacune() throws Exception {
        UcitajRacuneSO operacija = new UcitajRacuneSO();
        operacija.izvrsi(null, null);
        return operacija.getRacuni();
    }
}
