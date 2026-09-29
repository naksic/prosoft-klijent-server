/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.StavkaRacuna;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Korisnik
 */
public class ModelTabeleStavki extends AbstractTableModel {
    private List<StavkaRacuna> lista;
    private String[] kolone = {"oprema", "kolicina", "cena", "iznos"};

    public ModelTabeleStavki(List<StavkaRacuna> lista) {
        this.lista = lista;
    }

    public List<StavkaRacuna> getLista() {
        return lista;
    }

    public void setLista(List<StavkaRacuna> lista) {
        this.lista = lista;
        fireTableDataChanged();
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
    public String getColumnName(int column) {
        return kolone[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StavkaRacuna s = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return s.getOprema().getNaziv();
            case 1:
                return s.getKolicina();
            case 2:
                return String.format("%.2f", s.getCena());
            case 3:
                return String.format("%.2f", s.getIznos());
            default:
                return "N/A";
        }
    }
}
