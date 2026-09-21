/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kordinator;

import domen.Prodavac;
import forme.DodajKupcaForma;
import forme.DodajRacunForma;
import forme.DodajStrSpremuForma;
import forme.FormaTip;
import forme.GlavnaForma;
import forme.LoginForma;
import forme.PrikazKupacaForma;
import java.util.HashMap;
import java.util.Map;
import kontroleri.DodajKupcaController;
import kontroleri.DodajRacunController;
import kontroleri.DodajStrSpremuController;
import kontroleri.GlavnaFormaController;
import kontroleri.LoginController;
import kontroleri.PrikazKupacaController;



/**
 *
 * @author Korisnik
 */
public class Kordinator {
    private static Kordinator instance;
    private Prodavac ulogovani;
    private LoginController loginController;
    private GlavnaFormaController glavnaFormaController;
    private PrikazKupacaController prikazKupacaController;
    private DodajKupcaController dodajKupcaController;
    private DodajStrSpremuController dodajStrSpremuController;
    private DodajRacunController dodajRacunController;
    private Map<String, Object> parametri;
    
    private Kordinator() {
        parametri = new HashMap<>();
    }

    public static Kordinator getInstance() {
        if(instance == null)
            instance = new Kordinator();
        return instance;
    }

    public Prodavac getUlogovani() {
        return ulogovani;
    }

    public void setUlogovani(Prodavac ulogovani) {
        this.ulogovani = ulogovani;
    }

    public void otvoriLoginFormu() {
        loginController = new LoginController(new LoginForma());
        loginController.otvoriFormu();
    }

    public void otvoriGlavnuFormu() {
        glavnaFormaController = new GlavnaFormaController(new GlavnaForma());
        glavnaFormaController.otvoriFormu();
    }

    public void otvoriPrikazKupacaFormu() {
        prikazKupacaController = new PrikazKupacaController(new PrikazKupacaForma());
        prikazKupacaController.otvoriFormu();
    }
    
    public void otvoriKreirajKupcaFormu() {
        dodajKupcaController = new DodajKupcaController(new DodajKupcaForma());
        dodajKupcaController.otvoriFormu(FormaTip.KREIRAJ);
    }
    
    public void dodajParam(String s, Object o) {
        parametri.put(s, o);
    }

    public Object vratiParam(String s) {
        return parametri.get(s);
    }

    public void otvoriPromeniKupcaFormu() {
        dodajKupcaController = new DodajKupcaController(new DodajKupcaForma());
        dodajKupcaController.otvoriFormu(FormaTip.PROMENI);
    }

    public void osveziFormuKupaca() {
        prikazKupacaController.osveziFormu();
    }

    public void otvoriDodajStrSpremuFormu() {
        dodajStrSpremuController = new DodajStrSpremuController(new DodajStrSpremuForma());
        dodajStrSpremuController.otvoriFormu();
    }
    
    public void otvoriDodajRacunFormu() {
        dodajRacunController = new DodajRacunController(new DodajRacunForma());
        dodajRacunController.otvoriFormu();
    }
}
