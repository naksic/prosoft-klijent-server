/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domen.StrSprema;
import forme.DodajStrSpremuForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;

/**
 *
 * @author Korisnik
 */
public class DodajStrSpremuController {
    private final DodajStrSpremuForma dssf;

    public DodajStrSpremuController(DodajStrSpremuForma dssf) {
        this.dssf = dssf;
        addActionListener();
    }

    public void otvoriFormu() {
        dssf.setVisible(true);
    }

    private void addActionListener() {
        dssf.dodajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String zvanje = dssf.getjTextFieldZvanje().getText().trim();
                String stepenText = dssf.getjTextFieldStepen().getText().trim();

                if (zvanje.isEmpty() || stepenText.isEmpty()) {
                    JOptionPane.showMessageDialog(dssf, "Sistem ne može da zapamti stručnu spremu.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int stepen;
                try {
                    stepen = Integer.parseInt(stepenText);
                } catch (NumberFormatException exc) {
                    JOptionPane.showMessageDialog(dssf, "Stepen mora biti ceo broj.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (stepen <= 0) {
                    JOptionPane.showMessageDialog(dssf, "Sistem ne može da zapamti stručnu spremu.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                StrSprema ss = new StrSprema(-1, zvanje, stepen);
                try {
                    Komunikacija.getInstance().ubaciStrucnuSpremu(ss);
                    JOptionPane.showMessageDialog(dssf, "Sistem je zapamtio stručnu spremu.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    dssf.getjTextFieldZvanje().setText("");
                    dssf.getjTextFieldStepen().setText("");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dssf, "Sistem ne može da zapamti stručnu spremu.", "Greška", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
