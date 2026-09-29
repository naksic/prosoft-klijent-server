/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domen.Kupac;
import forme.DetaljiKupcaForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Korisnik
 */
public class DetaljiKupcaController {
    private final DetaljiKupcaForma dkf;

    public DetaljiKupcaController(DetaljiKupcaForma dkf) {
        this.dkf = dkf;
        addActionListener();
    }

    public void otvoriFormu(Kupac k) {
        dkf.popuniPodatke(k);
        dkf.setVisible(true);
    }

    private void addActionListener() {
        dkf.zatvoriAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dkf.dispose();
            }
        });
    }
}
