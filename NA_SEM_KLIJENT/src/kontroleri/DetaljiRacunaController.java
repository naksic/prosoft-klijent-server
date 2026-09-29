/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domen.Racun;
import forme.DetaljiRacunaForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Korisnik
 */
public class DetaljiRacunaController {
    private final DetaljiRacunaForma drf;

    public DetaljiRacunaController(DetaljiRacunaForma drf) {
        this.drf = drf;
        addActionListener();
    }

    public void otvoriFormu(Racun r) {
        drf.popuniPodatke(r);
        drf.setVisible(true);
    }

    private void addActionListener() {
        drf.zatvoriAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drf.dispose();
            }
        });
    }
}
