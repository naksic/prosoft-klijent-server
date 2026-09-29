/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import domen.Kupac;
import domen.Mesto;
import domen.Oprema;
import domen.Prodavac;
import domen.Racun;
import domen.StrSprema;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

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

    public List<Kupac> pretraziKupce(Kupac kriterijum) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.PRETRAZI_KUPCE, kriterijum);
        posiljalac.posalji(zahtev);

        Odgovor odg = (Odgovor) primalac.primi();
        Object odgovor = odg.getOdgovor();
        if (odgovor instanceof Exception) {
            Exception serverGreska = (Exception) odgovor;
            serverGreska.printStackTrace();
            throw new Exception(serverGreska.getMessage());
        }
        return (List<Kupac>) odgovor;
    }
    
    public void obrisiKupca(Kupac k) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.OBRISI_KUPCA, k);
        posiljalac.posalji(zahtev);
        
        Odgovor odg = (Odgovor) primalac.primi();
        if(odg.getOdgovor() == null) {
            System.out.println("Uspeh");
        } else {
            System.out.println("Greska");
            Exception serverGreska = (Exception) odg.getOdgovor();
            serverGreska.printStackTrace();
            throw new Exception(serverGreska.getMessage());
        }
    }

    public List<Mesto> vratiMesta() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_MESTA, null);
        posiljalac.posalji(zahtev);
        
        Odgovor odg = (Odgovor) primalac.primi();
        List<Mesto> listaMesta = (List<Mesto>) odg.getOdgovor();
        return listaMesta;
    }

    public void ubaciKupca(Kupac k) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.UBACI_KUPCA, k);
        posiljalac.posalji(zahtev);

        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() != null) {
            Exception serverGreska = (Exception) odg.getOdgovor();
            serverGreska.printStackTrace();
            throw new Exception(serverGreska.getMessage());
        }
    }

    public void promeniKupca(Kupac k) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.PROMENI_KUPCA, k);
        posiljalac.posalji(zahtev);

        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() != null) {
            Exception serverGreska = (Exception) odg.getOdgovor();
            serverGreska.printStackTrace();
            throw new Exception(serverGreska.getMessage());
        }
    }

    public void ubaciStrucnuSpremu(StrSprema ss) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.UBACI_STRSPREMA, ss);
        posiljalac.posalji(zahtev);

        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() != null) {
            throw new Exception("Greska");
        }
    }
    
    public List<Prodavac> vratiProdavce() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_PRODAVCE, null);
        posiljalac.posalji(zahtev);

        Odgovor odg = (Odgovor) primalac.primi();
        List<Prodavac> listaProdavaca = (List<Prodavac>) odg.getOdgovor();
        return listaProdavaca;
    }

    public List<Oprema> vratiOpremu() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_OPREMU, null);
        posiljalac.posalji(zahtev);

        Odgovor odg = (Odgovor) primalac.primi();
        List<Oprema> listaOpreme = (List<Oprema>) odg.getOdgovor();
        return listaOpreme;
    }
    
    public void kreirajRacun(Racun r) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.KREIRAJ_RACUN, r);
        posiljalac.posalji(zahtev);

        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() != null) {
            throw new Exception("Greska");
        }
    }
    
    public List<Racun> pretraziRacune(Racun kriterijum) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.PRETRAZI_RACUN, kriterijum);
        posiljalac.posalji(zahtev);

        Odgovor odg = (Odgovor) primalac.primi();
        Object odgovor = odg.getOdgovor();
        if (odgovor instanceof Exception) {
            Exception serverGreska = (Exception) odgovor;
            serverGreska.printStackTrace();
            throw new Exception(serverGreska.getMessage());
        }
        return (List<Racun>) odgovor;
    }
    
    public void promeniRacun(Racun r) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.PROMENI_RACUN, r);
        posiljalac.posalji(zahtev);

        Odgovor odg = (Odgovor) primalac.primi();
        if (odg.getOdgovor() != null) {
            Exception serverGreska = (Exception) odg.getOdgovor();
            serverGreska.printStackTrace();
            throw new Exception(serverGreska.getMessage());
        }
    }
    
    public Kupac ucitajJednogKupca(Kupac k) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_JEDNOG_KUPCA, k);
        posiljalac.posalji(zahtev);

        Odgovor odg = (Odgovor) primalac.primi();
        Object odgovor = odg.getOdgovor();
        if (odgovor instanceof Exception) {
            Exception serverGreska = (Exception) odgovor;
            serverGreska.printStackTrace();
            throw new Exception(serverGreska.getMessage());
        }
        return (Kupac) odgovor;
    }
    
    public Racun ucitajJedanRacun(Racun r) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_JEDAN_RACUN, r);
        posiljalac.posalji(zahtev);

        Odgovor odg = (Odgovor) primalac.primi();
        Object odgovor = odg.getOdgovor();
        if (odgovor instanceof Exception) {
            Exception serverGreska = (Exception) odgovor;
            serverGreska.printStackTrace();
            throw new Exception(serverGreska.getMessage());
        }
        return (Racun) odgovor;
    }
}
