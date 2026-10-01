/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.prodavac;

import domen.Prodavac;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Korisnik
 */
public class VratiListuSviProdavacSO extends ApstraktnaGenerickaOperacija {
    List<Prodavac> listaProdavaca;

    public List<Prodavac> getListaProdavaca() {
        return listaProdavaca;
    }
    
    @Override
    protected void preduslovi(Object param) throws Exception {
        
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        listaProdavaca = broker.getAll(new Prodavac(), null);
    }
    
}
