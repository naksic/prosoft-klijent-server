/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domen.Kupac;
import domen.Mesto;
import forme.PrikazKupacaForma;
import forme.model.ModelTabeleKupac;
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
public class PrikazKupacaController {
    private final PrikazKupacaForma pkf;

    public PrikazKupacaController(PrikazKupacaForma pkf) {
        this.pkf = pkf;
        addActionListener();
    }

    public void otvoriFormu() {
        pripremiFormu();
        pkf.setVisible(true);
    }

    private void pripremiFormu() {
        pkf.getjComboBoxMesta().removeAllItems();
        pkf.getjTextFieldIme().setText("");
        pkf.getjTextFieldPrezime().setText("");
        pkf.getjTextFieldBrojLK().setText("");
        pkf.getjTextFieldKontakt().setText("");
        pkf.getjTextFieldStarost().setText("");
        
        List<Mesto> listaMesta = Komunikacija.getInstance().vratiMesta();
        for (Mesto mesto : listaMesta) {
            pkf.getjComboBoxMesta().addItem(mesto);
        }
        pkf.getjComboBoxMesta().setSelectedIndex(-1);
        
        List<Kupac> kupci = Komunikacija.getInstance().ucitajKupce();
        ModelTabeleKupac mtk = new ModelTabeleKupac(kupci);
        pkf.getjTableKupci().setModel(mtk);
    }

    private void addActionListener() {
        pkf.addBtnObrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pkf.getjTableKupci().getSelectedRow();
                if(red == -1) {
                    JOptionPane.showMessageDialog(pkf, "Greška, sistem ne može da obriše kupca", "Greska", JOptionPane.ERROR_MESSAGE);
                } else {
                    ModelTabeleKupac mtk = (ModelTabeleKupac) pkf.getjTableKupci().getModel();
                    Kupac k = mtk.getLista().get(red);
                    try {
                        Komunikacija.getInstance().obrisiKupca(k);
                        JOptionPane.showMessageDialog(pkf, "Sistem je obrisao kupca.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
                        pripremiFormu();
                    } catch(Exception ex) {
                        JOptionPane.showMessageDialog(pkf, "Greška, sistem ne može da obriše kupca", "Greska", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        pkf.addBtnAzurirajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pkf.getjTableKupci().getSelectedRow();
                if(red != -1) {
                    ModelTabeleKupac mtk = (ModelTabeleKupac) pkf.getjTableKupci().getModel();
                    Kupac k = mtk.getLista().get(red);
                    JOptionPane.showMessageDialog(pkf, "Sistem je nasao kupca!", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    
                    Kordinator.getInstance().dodajParam("kupac", k);
                    Kordinator.getInstance().otvoriPromeniKupcaFormu();
                            
                } else {
                    JOptionPane.showMessageDialog(pkf, "Sistem ne može da nađe kupca", "Greška", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        pkf.addBtnPretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ime = pkf.getjTextFieldIme().getText().trim();
                String prezime = pkf.getjTextFieldPrezime().getText().trim();
                String brojLK = pkf.getjTextFieldBrojLK().getText().trim();
                String kontakt = pkf.getjTextFieldKontakt().getText().trim();
                String starost = pkf.getjTextFieldStarost().getText().trim();
                Mesto mesto = (Mesto) pkf.getjComboBoxMesta().getSelectedItem();
                
                if(ime.isEmpty() && prezime.isEmpty() && brojLK.isEmpty() && kontakt.isEmpty() && starost.isEmpty() && mesto == null) {
                    JOptionPane.showMessageDialog(pkf, "Sistem ne može da nađe kupce po zadatim kriterijumima.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int starostInt = 0;
                if(!starost.isEmpty()) {
                    try {
                        starostInt = Integer.parseInt(starost.trim());
                    } catch (NumberFormatException exc) {
                        JOptionPane.showMessageDialog(pkf, "Starost mora biti ceo broj.", "Greška", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                ModelTabeleKupac mtk = (ModelTabeleKupac) pkf.getjTableKupci().getModel();
                List<Kupac> rezultat = mtk.pretrazi(ime, prezime, brojLK, kontakt, starostInt, mesto);
                
                if (rezultat.isEmpty()) {
                    JOptionPane.showMessageDialog(pkf,
                            "Sistem ne može da nađe kupce po zadatim kriterijumima.","Greška",JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(pkf,
                            "Sistem je našao kupce po zadatim kriterijumima.","Uspeh",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        pkf.addBtnRestartujPretraguActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pripremiFormu();
            }
        });
    }

    public void osveziFormu() {
        pripremiFormu();
    }
    
    
}
