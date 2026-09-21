/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.oprema;

import domen.Oprema;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Korisnik
 */
public class UcitajOpremuSO extends ApstraktnaGenerickaOperacija {
    List<Oprema> listaOpreme;

    public List<Oprema> getListaOpreme() {
        return listaOpreme;
    }

    @Override
    protected void preduslovi(Object param) throws Exception {
        
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        listaOpreme = broker.getAll(new Oprema(), null);
    }
}
