/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import domen.Kupac;
import domen.Mesto;
import domen.Prodavac;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Korisnik
 */
public class Komunikacija {
    private Socket soket;
    private Posiljalac posiljalac;
    private Primalac primalac;
    private static Komunikacija instance;

    private Komunikacija() {
    }

    public static Komunikacija getInstance() {
        if(instance == null)
            instance = new Komunikacija();
        return instance;
    }
    
    public void konekcija() {
        try {
            soket = new Socket("localhost", 9000);
            posiljalac = new Posiljalac(soket);
            primalac = new Primalac(soket);
        } catch (IOException ex) {
            System.out.println("Server nije povezan.");
        }
        
    }

    public Prodavac login(String ki, String pass) {
        Prodavac p = new Prodavac();
        p.setKorisnickoIme(ki);
        p.setSifra(pass);
        Zahtev zahtev = new Zahtev(Operacija.LOGIN, p);
        
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        p = (Prodavac) odg.getOdgovor();
        return p;
    }

    public List<Kupac> ucitajKupce() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_KUPCE, null);
        List<Kupac> kupci = new ArrayList<>();
        
        posiljalac.posalji(zahtev);
        
        Odgovor odg = (Odgovor) primalac.primi();
        kupci = (List<Kupac>) odg.getOdgovor();
        return kupci;
    }

    public void obrisiKupca(Kupac k) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.OBRISI_KUPCA, k);
        posiljalac.posalji(zahtev);
        
        Odgovor odg = (Odgovor) primalac.primi();
        if(odg.getOdgovor() == null) {
            System.out.println("Uspeh");
        } else {
            System.out.println("Greska");
            ((Exception)odg.getOdgovor()).printStackTrace();
            throw new Exception("Greska");
            //JOptionPane.showMessageDialog(pkf, "Sistem ne može da obriše kupca", "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<Mesto> vratiMesta() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_MESTA, null);
        posiljalac.posalji(zahtev);
        
        Odgovor odg = (Odgovor) primalac.primi();
        List<Mesto> listaMesta = (List<Mesto>) odg.getOdgovor();
        return listaMesta;
    }

    public void ubaciKupca(Kupac k) {
        Zahtev zahtev = new Zahtev(Operacija.UBACI_KUPCA, k);
        posiljalac.posalji(zahtev);
        
        Odgovor odg = (Odgovor) primalac.primi();
    }

    public void promeniKupca(Kupac k) {
        Zahtev zahtev = new Zahtev(Operacija.PROMENI_KUPCA, k);
        posiljalac.posalji(zahtev);
        
        Odgovor odg = (Odgovor) primalac.primi();
    }

    
}
