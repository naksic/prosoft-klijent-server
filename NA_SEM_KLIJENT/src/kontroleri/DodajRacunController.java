/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domen.Kupac;
import domen.NacinPlacanja;
import domen.Oprema;
import domen.Prodavac;
import domen.Racun;
import domen.StavkaRacuna;
import forme.DodajRacunForma;
import forme.model.ModelTabeleStavki;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author Korisnik
 */
public class DodajRacunController {
    private final DodajRacunForma drf;
    private List<StavkaRacuna> stavke;

    public DodajRacunController(DodajRacunForma drf) {
        this.drf = drf;
        addActionListener();
    }

    public void otvoriFormu() {
        pripremiFormu();
        drf.setVisible(true);
    }

    private void pripremiFormu() {
        stavke = new ArrayList<>();
        drf.getjComboBoxKupac().removeAllItems();
        drf.getjComboBoxProdavac().removeAllItems();
        drf.getjComboBoxNacinPlacanja().removeAllItems();
        drf.getjComboBoxOprema().removeAllItems();
        drf.getjTextFieldNapomena().setText("");
        drf.getjTextFieldKolicina().setText("");
        drf.getjLabelUkupanIznos().setText("0.0");
        drf.getjTextFieldDatum().setText("");

        List<Kupac> listaKupaca = Komunikacija.getInstance().ucitajKupce();
        for (Kupac k : listaKupaca) {
            drf.getjComboBoxKupac().addItem(k);
        }
        drf.getjComboBoxKupac().setSelectedIndex(-1);

        List<Prodavac> listaProdavaca = Komunikacija.getInstance().vratiProdavce();
        for (Prodavac p : listaProdavaca) {
            drf.getjComboBoxProdavac().addItem(p);
        }
        drf.getjComboBoxProdavac().setSelectedIndex(-1);

        for (NacinPlacanja np : NacinPlacanja.values()) {
            drf.getjComboBoxNacinPlacanja().addItem(np);
        }
        drf.getjComboBoxNacinPlacanja().setSelectedIndex(-1);

        List<Oprema> listaOpreme = Komunikacija.getInstance().vratiOpremu();
        for (Oprema o : listaOpreme) {
            drf.getjComboBoxOprema().addItem(o);
        }
        drf.getjComboBoxOprema().setSelectedIndex(-1);

        ModelTabeleStavki mts = new ModelTabeleStavki(stavke);
        drf.getjTableStavke().setModel(mts);
    }

    private void addActionListener() {
        drf.dodajStavkuAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Oprema o = (Oprema) drf.getjComboBoxOprema().getSelectedItem();
                String kolicinaText = drf.getjTextFieldKolicina().getText().trim();

                if (o == null || kolicinaText.isEmpty()) {
                    JOptionPane.showMessageDialog(drf, "Sistem ne može da doda stavku.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int kolicina;
                try {
                    kolicina = Integer.parseInt(kolicinaText);
                } catch (NumberFormatException exc) {
                    JOptionPane.showMessageDialog(drf, "Količina mora biti ceo broj.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (kolicina <= 0) {
                    JOptionPane.showMessageDialog(drf, "Sistem ne može da doda stavku.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                StavkaRacuna s = new StavkaRacuna(null, stavke.size() + 1, kolicina, o.getTrenutnaCena(), kolicina * o.getTrenutnaCena(), o);
                stavke.add(s);

                ModelTabeleStavki mts = (ModelTabeleStavki) drf.getjTableStavke().getModel();
                mts.setLista(stavke);

                drf.getjTextFieldKolicina().setText("");
                drf.getjComboBoxOprema().setSelectedIndex(-1);

                osveziUkupanIznos();
            }
        });

        drf.ukloniStavkuAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = drf.getjTableStavke().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(drf, "Sistem ne može da nađe stavku.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                stavke.remove(red);
                ModelTabeleStavki mts = (ModelTabeleStavki) drf.getjTableStavke().getModel();
                mts.setLista(stavke);

                osveziUkupanIznos();
            }
        });

        drf.kreirajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Kupac k = (Kupac) drf.getjComboBoxKupac().getSelectedItem();
                Prodavac p = (Prodavac) drf.getjComboBoxProdavac().getSelectedItem();
                NacinPlacanja np = (NacinPlacanja) drf.getjComboBoxNacinPlacanja().getSelectedItem();
                String napomena = drf.getjTextFieldNapomena().getText().trim();
                String datumText = drf.getjTextFieldDatum().getText().trim();

                if (k == null || p == null || np == null || stavke.isEmpty() || datumText.isEmpty()) {
                    JOptionPane.showMessageDialog(drf, "Sistem ne može da kreira račun.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Date datum;
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
                    sdf.setLenient(false);
                    datum = sdf.parse(datumText);
                } catch (ParseException exc) {
                    JOptionPane.showMessageDialog(drf, "Datum mora biti u formatu dd.MM.yyyy. (npr. 23.08.2026.)", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Racun r = new Racun(-1, datum, np, napomena, 0, 0, p, k);
                r.setStavke(stavke);

                try {
                    Komunikacija.getInstance().kreirajRacun(r);
                    JOptionPane.showMessageDialog(drf, "Sistem je zapamtio račun.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    drf.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(drf, "Sistem ne može da zapamti račun.", "Greška", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        drf.getjComboBoxKupac().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                osveziUkupanIznos();
            }
        });

        drf.getjComboBoxNacinPlacanja().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                osveziUkupanIznos();
            }
        });
    }

    private void osveziUkupanIznos() {
        double ukupno = 0;
        for (StavkaRacuna s : stavke) {
            ukupno += s.getIznos();
        }

        double popust = izracunajPopust();
        double saPopustom = (1 - popust) * ukupno;

        drf.getjLabelUkupanIznos().setText(String.valueOf(saPopustom));
    }

    private double izracunajPopust() {
        double popust = 0;
        NacinPlacanja np = (NacinPlacanja) drf.getjComboBoxNacinPlacanja().getSelectedItem();
        Kupac k = (Kupac) drf.getjComboBoxKupac().getSelectedItem();

        if (np == NacinPlacanja.KARTICA) {
            popust = Math.max(popust, 0.05);
        }
        if (k != null) {
            if (k.getStarost() <= 27) {
                popust = Math.max(popust, 0.2);
            }
            if (k.getStarost() >= 64) {
                popust = Math.max(popust, 0.1);
            }
        }
        return popust;
    }
}
