/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.login;

import domen.Kupac;
import domen.Prodavac;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Korisnik
 */
public class LoginOperacija extends ApstraktnaGenerickaOperacija {

    Prodavac prodavac;

    public Prodavac getProdavac() {
        return prodavac;
    }
    
    @Override
    protected void preduslovi(Object param) throws Exception {
        if(param == null || !(param instanceof Prodavac))
            throw new Exception("Sistem ne moze da nadje prodavca");
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        List<Prodavac> sviProdavci = broker.getAll((Prodavac) param, null);
        System.out.println("KLASA: LoginOperacija - " + sviProdavci);
        
        if(sviProdavci.contains((Prodavac) param)) {
            for (Prodavac p : sviProdavci) {
                if(p.equals((Prodavac) param)) {
                    prodavac = p;
                    return;
                }
            }
        } else {
            prodavac = null;
        }
    }
    
}
