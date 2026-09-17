/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package domen;

import java.io.Serializable;
import java.util.List;
import java.sql.ResultSet;

/**
 *
 * @author Korisnik
 */
public interface ApstraktniDomenskiObjekat extends Serializable {
    public String vratiNazivTabele();
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception;
    public String vratiKoloneZaUbacivanje();
    public String vratiVrednostZaUbacivanje();
    public String vratiPrimarniKljuc();
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception;
    public String vratiVrednostZaIzmenu();
    
    
}
