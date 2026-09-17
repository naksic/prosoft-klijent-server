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
                int starost = Integer.parseInt(dkf.getjTextFieldStarost().getText().trim());
                Mesto m = (Mesto) dkf.getjComboBoxMesto().getSelectedItem();
                
                if (ime.isEmpty() || prezime.isEmpty() || brojLK.isEmpty() || kontakt.isEmpty() || starost == 0 || m == null) {
                    JOptionPane.showMessageDialog(dkf,"Sistem ne može da zapamti kupca","Greška",JOptionPane.ERROR_MESSAGE );
                    return;
                }
                
                Kupac k = new Kupac(-1, ime, prezime, brojLK, kontakt, starost, m);
                try {
                    Komunikacija.getInstance().ubaciKupca(k);
                    JOptionPane.showMessageDialog(dkf, "Sistem je zapamtio kupca", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    dkf.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dkf, "Sistem ne može da zapamti kupca", "Greška", JOptionPane.ERROR_MESSAGE);
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
                int starost = Integer.parseInt(dkf.getjTextFieldStarost().getText().trim());
                Mesto m = (Mesto) dkf.getjComboBoxMesto().getSelectedItem();
                
                if (ime.isEmpty() || prezime.isEmpty() || brojLK.isEmpty() || kontakt.isEmpty() || starost == 0 || m == null) {
                    JOptionPane.showMessageDialog(dkf,"Sistem ne može da zapamti kupca","Greška",JOptionPane.ERROR_MESSAGE );
                    return;
                }
                
                Kupac k = new Kupac(id, ime, prezime, brojLK, kontakt, starost, m);
                try {
                    Komunikacija.getInstance().promeniKupca(k);
                    JOptionPane.showMessageDialog(dkf, "Sistem je zapamtio kupca", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    Kordinator.getInstance().osveziFormuKupaca();
                    dkf.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dkf, "Sistem ne može da zapamti kupca", "Greška", JOptionPane.ERROR_MESSAGE);
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
        dkf.getjTextFieldStarost().setText(k.getStarost() + "");
        dkf.getjComboBoxMesto().setSelectedItem(k.getMesto());
    }
}
