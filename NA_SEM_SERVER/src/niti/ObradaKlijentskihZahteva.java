/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import controller.Controller;
import domen.Kupac;
import domen.Mesto;
import domen.Prodavac;
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
                Odgovor odgovor = new Odgovor();
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
                    case OBRISI_KUPCA:
                        try {
                            Kupac k = (Kupac) zahtev.getParametar();
                            Controller.getInstance().obrisiKupca(k);
                            odgovor.setOdgovor(null);
                        } catch(Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                    case UCITAJ_MESTA:
                        List<Mesto> listaMesta = controller.Controller.getInstance().ucitajMesta();
                        odgovor.setOdgovor(listaMesta);
                        break;
                    case UBACI_KUPCA:
                        Kupac kupacSaForme = (Kupac) zahtev.getParametar();
                        Controller.getInstance().ubaciKupca(kupacSaForme);
                        odgovor.setOdgovor(null);
                        break;
                    case PROMENI_KUPCA:
                        Kupac k = (Kupac) zahtev.getParametar();
                        Controller.getInstance().promeniKupca(k);
                        odgovor.setOdgovor(null);
                        break;
                    default:
                        System.out.println("GRESKA, OPERACIJA NE POSTOJI");
                }
                posiljalac.posalji(odgovor);
            }
            catch (Exception ex) {
                        Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                    }
        }
    }
    
    
}
