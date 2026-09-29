/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.Racun;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Korisnik
 */
public class ModelTabeleRacun extends AbstractTableModel {
    private List<Racun> lista = new ArrayList<>();
    private String[] kolone = {"Datum izdavanja", "Kupac", "Prodavac", "Način plaćanja", "Ukupan iznos"};

    public List<Racun> getLista() {
        return lista;
    }

    public void setLista(List<Racun> lista) {
        this.lista = lista;
        fireTableDataChanged();
    }

    public Racun vratiRacunNaIndeksu(int red) {
        return lista.get(red);
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
        Racun r = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd.MM.yyyy");
                return sdf.format(r.getDatumIzdavanja());
            case 1:
                return r.getKupac().getIme() + " " + r.getKupac().getPrezime();
            case 2:
                return r.getProdavac().getIme() + " " + r.getProdavac().getPrezime();
            case 3:
                return r.getNacinPlacanja();
            case 4:
                return String.format("%.2f", r.getUkupanIznos());
            default:
                return null;
        }
    }
}
