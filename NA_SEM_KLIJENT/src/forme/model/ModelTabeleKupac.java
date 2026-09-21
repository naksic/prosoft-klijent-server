/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.Kupac;
import domen.Mesto;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Korisnik
 */
public class ModelTabeleKupac extends AbstractTableModel {
    List<Kupac> lista;
    String[] kolone = {"id", "ime", "prezime", "brojLoyaltyKartice", "kontakt", "starost", "mesto"};

    public ModelTabeleKupac(List<Kupac> lista) {
        this.lista = lista;
    }

    public List<Kupac> getLista() {
        return lista;
    }

    public void setLista(List<Kupac> lista) {
        this.lista = lista;
    }
    
    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Kupac k = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return k.getIdKupca(); 
            case 1:
                return k.getIme();
            case 2:
                return k.getPrezime();
            case 3:
                return k.getBrojLoyaltyKartice();
            case 4:
                return k.getKontakt();
            case 5:
                return k.getStarost();
            case 6:
                return k.getMesto().getNaziv();
            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<Kupac> pretrazi(String ime, String prezime, String brojLK, String kontakt, int starostInt, Mesto mesto) {
        List<Kupac> filtriranaLista = this.lista.stream()
            .filter(k -> (ime == null || ime.isEmpty() || k.getIme().toLowerCase().contains(ime.toLowerCase())))
            .filter(k -> (prezime == null || prezime.isEmpty() || k.getPrezime().toLowerCase().contains(prezime.toLowerCase())))
            .filter(k -> (brojLK == null || brojLK.isEmpty() || (k.getBrojLoyaltyKartice() != null && k.getBrojLoyaltyKartice().contains(brojLK))))
            .filter(k -> (kontakt == null || kontakt.isEmpty() || k.getKontakt().toLowerCase().contains(kontakt.toLowerCase())))
            .filter(k -> (starostInt <= 0 || k.getStarost() == starostInt))
            .filter(k -> (mesto == null || k.getMesto().equals(mesto))).collect(Collectors.toList());

        this.lista = filtriranaLista;
        fireTableDataChanged();
        return filtriranaLista;
    }
    
    
}
