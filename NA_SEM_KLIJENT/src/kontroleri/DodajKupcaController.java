/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domen.Kupac;
import domen.Mesto;
import forme.DodajKupcaForma;
import forme.FormaTip;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import kordinator.Kordinator;

/**
 *
 * @author Korisnik
 */
public class DodajKupcaController {
    private final DodajKupcaForma dkf;

    public DodajKupcaController(DodajKupcaForma dkf) {
        this.dkf = dkf;
        addActionListener();
    }

    private void addActionListener() {
        dkf.kreirajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ime = dkf.getjTextFieldIme().getText().trim();
                String prezime = dkf.getjTextFieldPrezime().getText().trim();
                String brojLK = dkf.getjTextFieldBrojLK().getText().trim();
                String kontakt = dkf.getjTextFieldKontakt().getText().trim();
                String datumRodjenjaText = dkf.getjTextFieldStarost().getText().trim();
                Mesto m = (Mesto) dkf.getjComboBoxMesto().getSelectedItem();

                if (ime.isEmpty() || prezime.isEmpty() || kontakt.isEmpty() || datumRodjenjaText.isEmpty() || m == null) {
                    JOptionPane.showMessageDialog(dkf, "Sistem ne može da zapamti kupca", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                java.util.Date datumRodjenja;
                try {
                    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd.MM.yyyy");
                    sdf.setLenient(false);
                    datumRodjenja = sdf.parse(datumRodjenjaText);
                } catch (java.text.ParseException exc) {
                    JOptionPane.showMessageDialog(dkf, "Datum rođenja mora biti u formatu dd.MM.yyyy.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (datumRodjenja.after(new java.util.Date())) {
                    JOptionPane.showMessageDialog(dkf, "Datum rođenja ne može biti u budućnosti.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Kupac k = new Kupac(-1, ime, prezime, brojLK, kontakt, datumRodjenja, m);
                try {
                    Komunikacija.getInstance().ubaciKupca(k);
                    JOptionPane.showMessageDialog(dkf, "Sistem je zapamtio kupca.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    dkf.dispose();
                } catch (Exception ex) {
                    String poruka = ex.getMessage();
                    if (poruka == null || poruka.isEmpty()) {
                        poruka = "Sistem ne može da zapamti kupca.";
                    }
                    JOptionPane.showMessageDialog(dkf, poruka, "Greška", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        dkf.promeniAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(dkf.getjTextFieldID().getText().trim());
                String ime = dkf.getjTextFieldIme().getText().trim();
                String prezime = dkf.getjTextFieldPrezime().getText().trim();
                String brojLK = dkf.getjTextFieldBrojLK().getText().trim();
                String kontakt = dkf.getjTextFieldKontakt().getText().trim();
                String datumRodjenjaText = dkf.getjTextFieldStarost().getText().trim();
                Mesto m = (Mesto) dkf.getjComboBoxMesto().getSelectedItem();

                if (ime.isEmpty() || prezime.isEmpty() || kontakt.isEmpty() || datumRodjenjaText.isEmpty() || m == null) {
                    JOptionPane.showMessageDialog(dkf, "Sistem ne može da zapamti kupca", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                java.util.Date datumRodjenja;
                try {
                    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd.MM.yyyy");
                    sdf.setLenient(false);
                    datumRodjenja = sdf.parse(datumRodjenjaText);
                } catch (java.text.ParseException exc) {
                    JOptionPane.showMessageDialog(dkf, "Datum rođenja mora biti u formatu dd.MM.yyyy.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (datumRodjenja.after(new java.util.Date())) {
                    JOptionPane.showMessageDialog(dkf, "Datum rođenja ne može biti u budućnosti.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                Kupac k = new Kupac(id, ime, prezime, brojLK, kontakt, datumRodjenja, m);
                try {
                    Komunikacija.getInstance().promeniKupca(k);
                    JOptionPane.showMessageDialog(dkf, "Sistem je zapamtio kupca.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    Kordinator.getInstance().osveziFormuKupaca();
                    dkf.dispose();
                } catch (Exception ex) {
                    String poruka = ex.getMessage();
                    if (poruka == null || poruka.isEmpty()) {
                        poruka = "Sistem ne može da zapamti kupca.";
                    }
                    JOptionPane.showMessageDialog(dkf, poruka, "Greška", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    
    public void otvoriFormu(FormaTip tip) {
        pripremiFormu(tip);
        dkf.setVisible(true);
    }

    private void pripremiFormu(FormaTip tip) {
        dkf.getjComboBoxMesto().removeAllItems();
        dkf.getjButtonPromeni().setVisible(false);
        dkf.getjButtonDodaj().setVisible(true);
        dkf.getjButtonPromeni().setEnabled(true);
        
        List<Mesto> listaMesta = Komunikacija.getInstance().vratiMesta();
        for (Mesto m : listaMesta) {
            dkf.getjComboBoxMesto().addItem(m);
        }
        
        switch (tip) {
            case KREIRAJ:
                pripremiKreirajFormu();
                break;
            case PROMENI:
                pripremiPromeniFormu();
                break;
            default:
                throw new AssertionError();
        }
    }
    
    public void pripremiKreirajFormu() {
        dkf.getjTextFieldID().setVisible(false);
        dkf.getjLabelID().setVisible(false);
        dkf.getjComboBoxMesto().setSelectedIndex(-1);
    }

    private void pripremiPromeniFormu() {
        dkf.getjTextFieldID().setVisible(true);
        dkf.getjTextFieldID().setEditable(false);
        dkf.getjLabelID().setVisible(true);
        dkf.getjButtonDodaj().setVisible(false);
        dkf.getjButtonPromeni().setVisible(true);
        dkf.getjButtonPromeni().setEnabled(true);
        
        Kupac k = (Kupac) Kordinator.getInstance().vratiParam("kupac");
        dkf.getjTextFieldID().setText(k.getIdKupca() + "");
        dkf.getjTextFieldIme().setText(k.getIme());
        dkf.getjTextFieldPrezime().setText(k.getPrezime());
        dkf.getjTextFieldBrojLK().setText(k.getBrojLoyaltyKartice());
        dkf.getjTextFieldKontakt().setText(k.getKontakt());
        java.text.SimpleDateFormat sdfPrikaz = new java.text.SimpleDateFormat("dd.MM.yyyy");
        dkf.getjTextFieldStarost().setText(k.getDatumRodjenja() != null ? sdfPrikaz.format(k.getDatumRodjenja()) : "");
        dkf.getjComboBoxMesto().setSelectedItem(k.getMesto());
    }
}
