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
        Prodavac uneti = (Prodavac) param;
        String uslov = " WHERE korisnickoIme = '" + uneti.getKorisnickoIme() + "'";

        List<Prodavac> pronadjeni = broker.getAll(new Prodavac(), uslov);
        System.out.println("KLASA: LoginOperacija - " + pronadjeni);

        if (!pronadjeni.isEmpty() && pronadjeni.get(0).equals(uneti)) {
            prodavac = pronadjeni.get(0);
        } else {
            prodavac = null;
        }
    }
    
}
