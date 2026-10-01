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
import forme.PrikazRacunaForma;
import forme.model.ModelTabeleRacun;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import kordinator.Kordinator;

/**
 *
 * @author Korisnik
 */
public class PrikazRacunaController {
    private final PrikazRacunaForma prf;

    public PrikazRacunaController(PrikazRacunaForma prf) {
        this.prf = prf;
        addActionListener();
    }

    public void otvoriFormu() {
        pripremiFormu();
        prf.setVisible(true);
    }

    private void pripremiFormu() {
        prf.getjComboBoxKupac().removeAllItems();
        prf.getjComboBoxProdavac().removeAllItems();
        prf.getjComboBoxOprema().removeAllItems();
        prf.getjComboBoxNacinPlacanja().removeAllItems();
        prf.getjTextFieldDatum().setText("");

        List<Kupac> listaKupaca = Komunikacija.getInstance().ucitajKupce();
        for (Kupac k : listaKupaca) {
            prf.getjComboBoxKupac().addItem(k);
        }
        prf.getjComboBoxKupac().setSelectedIndex(-1);

        List<Prodavac> listaProdavaca = Komunikacija.getInstance().vratiProdavce();
        for (Prodavac p : listaProdavaca) {
            prf.getjComboBoxProdavac().addItem(p);
        }
        prf.getjComboBoxProdavac().setSelectedIndex(-1);

        List<Oprema> listaOpreme = Komunikacija.getInstance().vratiOpremu();
        for (Oprema o : listaOpreme) {
            prf.getjComboBoxOprema().addItem(o);
        }
        prf.getjComboBoxOprema().setSelectedIndex(-1);

        for (NacinPlacanja np : NacinPlacanja.values()) {
            prf.getjComboBoxNacinPlacanja().addItem(np);
        }
        prf.getjComboBoxNacinPlacanja().setSelectedIndex(-1);

        List<Racun> sviRacuni = Komunikacija.getInstance().ucitajRacune();
        ModelTabeleRacun mtr = new ModelTabeleRacun();
        mtr.setLista(sviRacuni);
        prf.getjTableRacuni().setModel(mtr);
    }

    private void addActionListener() {
        prf.addBtnPretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Kupac k = (Kupac) prf.getjComboBoxKupac().getSelectedItem();
                Prodavac p = (Prodavac) prf.getjComboBoxProdavac().getSelectedItem();
                Oprema o = (Oprema) prf.getjComboBoxOprema().getSelectedItem();
                NacinPlacanja np = (NacinPlacanja) prf.getjComboBoxNacinPlacanja().getSelectedItem();
                String datumText = prf.getjTextFieldDatum().getText().trim();

                if (k == null && p == null && o == null && np == null && datumText.isEmpty()) {
                    JOptionPane.showMessageDialog(prf, "Sistem ne može da nađe račune po zadatim kriterijumima.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Date datum = null;
                if (!datumText.isEmpty()) {
                    try {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                        sdf.setLenient(false);
                        datum = sdf.parse(datumText);
                    } catch (ParseException exc) {
                        JOptionPane.showMessageDialog(prf, "Datum mora biti u formatu dd.MM.yyyy (npr. 23.08.2026)", "Greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                Racun kriterijum = new Racun();
                kriterijum.setKupac(k);
                kriterijum.setProdavac(p);
                kriterijum.setNacinPlacanja(np);
                kriterijum.setDatumIzdavanja(datum);
                if (o != null) {
                    List<StavkaRacuna> stavkeKriterijum = new ArrayList<>();
                    StavkaRacuna sk = new StavkaRacuna();
                    sk.setOprema(o);
                    stavkeKriterijum.add(sk);
                    kriterijum.setStavke(stavkeKriterijum);
                }

                try {
                    List<Racun> rezultat = Komunikacija.getInstance().pretraziRacune(kriterijum);
                    ModelTabeleRacun mtr = (ModelTabeleRacun) prf.getjTableRacuni().getModel();
                    mtr.setLista(rezultat);

                    if (rezultat.isEmpty()) {
                        JOptionPane.showMessageDialog(prf, "Sistem ne može da nađe račune po zadatim kriterijumima.", "Greška", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(prf, "Sistem je našao račune po zadatim kriterijumima.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (Exception ex) {
                    String poruka = ex.getMessage();
                    if (poruka == null || poruka.isEmpty()) {
                        poruka = "Sistem ne može da nađe račune po zadatim kriterijumima.";
                    }
                    JOptionPane.showMessageDialog(prf, poruka, "Greška", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        prf.addBtnRestartujPretraguActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pripremiFormu();
            }
        });

        prf.addBtnAzurirajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = prf.getjTableRacuni().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(prf, "Sistem ne može da nađe račun.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                ModelTabeleRacun mtr = (ModelTabeleRacun) prf.getjTableRacuni().getModel();
                Racun r = mtr.vratiRacunNaIndeksu(red);

                try {
                    Racun pronadjenRacun = Komunikacija.getInstance().ucitajJedanRacun(r);
                    JOptionPane.showMessageDialog(prf, "Sistem je našao račun.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    Kordinator.getInstance().dodajParam("racun", pronadjenRacun);
                    Kordinator.getInstance().otvoriPromeniRacunaFormu();
                } catch (Exception ex) {
                    String poruka = ex.getMessage();
                    if (poruka == null || poruka.isEmpty()) {
                        poruka = "Sistem ne može da nađe račun.";
                    }
                    JOptionPane.showMessageDialog(prf, poruka, "Greška", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        prf.addBtnDetaljiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = prf.getjTableRacuni().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(prf, "Sistem ne može da nađe račun.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                ModelTabeleRacun mtr = (ModelTabeleRacun) prf.getjTableRacuni().getModel();
                Racun r = mtr.vratiRacunNaIndeksu(red);
                try {
                    Racun pronadjenRacun = Komunikacija.getInstance().ucitajJedanRacun(r);
                    JOptionPane.showMessageDialog(prf, "Sistem je našao račun.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    Kordinator.getInstance().otvoriDetaljeRacunaFormu(pronadjenRacun);
                } catch (Exception ex) {
                    String poruka = ex.getMessage();
                    if (poruka == null || poruka.isEmpty()) {
                        poruka = "Sistem ne može da nađe račun.";
                    }
                    JOptionPane.showMessageDialog(prf, poruka, "Greška", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    
    public void osveziFormu() {
        pripremiFormu();
    }
}
