/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import controller.Controller;
import domen.Kupac;
import domen.Mesto;
import domen.Oprema;
import domen.Prodavac;
import domen.Racun;
import domen.StrSprema;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import komunikacija.Odgovor;
import komunikacija.Posiljalac;
import komunikacija.Primalac;
import komunikacija.Zahtev;

/**
 *
 * @author Korisnik
 */
public class ObradaKlijentskihZahteva extends Thread {
    Socket socket;
    Posiljalac posiljalac;
    Primalac primalac;

    public ObradaKlijentskihZahteva(Socket socket) {
        this.socket = socket;
        posiljalac = new Posiljalac(socket);
        primalac = new Primalac(socket);
    }
    
    

    @Override
    public void run() {
        while(true) {
            try {
                Zahtev zahtev = (Zahtev) primalac.primi();
                
                if (zahtev == null) {
                    try {
                        socket.close();
                    } catch (Exception ignore) {
                    }
                    return;
                }
                
                Odgovor odgovor = new Odgovor();

                try {
                    switch (zahtev.getOperacija()) {
                        case LOGIN:
                            Prodavac p = (Prodavac) zahtev.getParametar();
                            p = Controller.getInstance().login(p);
                            odgovor.setOdgovor(p);
                            break;
                        case UCITAJ_KUPCE:
                            List<Kupac> kupci = Controller.getInstance().ucitajKupce();
                            odgovor.setOdgovor(kupci);
                            break;
                        case PRETRAZI_KUPCE:
                            Kupac kriterijumKupac = (Kupac) zahtev.getParametar();
                            List<Kupac> filtriraniKupci = Controller.getInstance().pretraziKupce(kriterijumKupac);
                            odgovor.setOdgovor(filtriraniKupci);
                            break;
                        case OBRISI_KUPCA:
                            Kupac kZaBrisanje = (Kupac) zahtev.getParametar();
                            Controller.getInstance().obrisiKupca(kZaBrisanje);
                            odgovor.setOdgovor(null);
                            break;
                        case UCITAJ_MESTA:
                            List<Mesto> listaMesta = Controller.getInstance().ucitajMesta();
                            odgovor.setOdgovor(listaMesta);
                            break;
                        case UBACI_KUPCA:
                            Kupac kupacSaForme = (Kupac) zahtev.getParametar();
                            Controller.getInstance().ubaciKupca(kupacSaForme);
                            odgovor.setOdgovor(null);
                            break;
                        case PROMENI_KUPCA:
                            Kupac kZaIzmenu = (Kupac) zahtev.getParametar();
                            Controller.getInstance().promeniKupca(kZaIzmenu);
                            odgovor.setOdgovor(null);
                            break;
                        case UBACI_STRSPREMA:
                            StrSprema ssSaForme = (StrSprema) zahtev.getParametar();
                            Controller.getInstance().ubaciStrucnuSpremu(ssSaForme);
                            odgovor.setOdgovor(null);
                            break;
                        case UCITAJ_PRODAVCE:
                            List<Prodavac> prodavci = Controller.getInstance().ucitajProdavce();
                            odgovor.setOdgovor(prodavci);
                            break;
                        case UCITAJ_OPREMU:
                            List<Oprema> oprema = Controller.getInstance().ucitajOpremu();
                            odgovor.setOdgovor(oprema);
                            break;
                        case KREIRAJ_RACUN:
                            Racun racunZaKreiranje = (Racun) zahtev.getParametar();
                            Controller.getInstance().kreirajRacun(racunZaKreiranje);
                            odgovor.setOdgovor(null);
                            break;
                        case PRETRAZI_RACUN:
                            Racun kriterijumRacun = (Racun) zahtev.getParametar();
                            List<Racun> filtriraniRacuni = Controller.getInstance().pretraziRacune(kriterijumRacun);
                            odgovor.setOdgovor(filtriraniRacuni);
                            break;
                        case PROMENI_RACUN:
                            Racun racunZaIzmenu = (Racun) zahtev.getParametar();
                            Controller.getInstance().promeniRacun(racunZaIzmenu);
                            odgovor.setOdgovor(null);
                            break;
                        case UCITAJ_JEDNOG_KUPCA:
                            Kupac kupacZaDetalje = (Kupac) zahtev.getParametar();
                            Kupac pronadjenKupac = Controller.getInstance().ucitajJednogKupca(kupacZaDetalje);
                            odgovor.setOdgovor(pronadjenKupac);
                            break;
                        case UCITAJ_JEDAN_RACUN:
                            Racun racunZaDetalje = (Racun) zahtev.getParametar();
                            Racun pronadjenRacun = Controller.getInstance().ucitajJedanRacun(racunZaDetalje);
                            odgovor.setOdgovor(pronadjenRacun);
                            break;
                        case UCITAJ_RACUNE:
                            List<Racun> sviRacuni = Controller.getInstance().ucitajRacune();
                            odgovor.setOdgovor(sviRacuni);
                            break;
                        default:
                            System.out.println("GREŠKA, OPERACIJA NE POSTOJI");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    odgovor.setOdgovor(new Exception(e.getMessage()));
                }

                posiljalac.posalji(odgovor);

            } catch (Exception ex) {
                Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
}
