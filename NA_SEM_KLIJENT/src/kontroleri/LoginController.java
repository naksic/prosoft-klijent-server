/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domen.Prodavac;
import forme.LoginForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import kordinator.Kordinator;

/**
 *
 * @author Korisnik
 */
public class LoginController {
    private final LoginForma lf;

    public LoginController(LoginForma lf) {
        this.lf = lf;
        addActionListeners();
    }

    private void addActionListeners() {
        lf.loginAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prijava(e);
            }

            private void prijava(ActionEvent e) {
                String ki = lf.getjTextFieldKorisnickoIme().getText().trim();
                String pass = String.valueOf(lf.getjPasswordField1().getPassword());
                
                if(ki.isEmpty() || pass.isEmpty()) {
                    JOptionPane.showMessageDialog(lf, "Morate uneti korisničko ime i šifru.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                Komunikacija.getInstance().konekcija();
                Prodavac ulogovani = Komunikacija.getInstance().login(ki, pass);
                if(ulogovani == null) {
                    JOptionPane.showMessageDialog(lf, "Korisničko ime i šifra nisu ispravni.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
                } else {
                    Kordinator.getInstance().setUlogovani(ulogovani);
                    JOptionPane.showMessageDialog(lf, "Korisničko ime i šifra su ispravni.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                    Kordinator.getInstance().otvoriGlavnuFormu();
                    lf.dispose();
                }
            }
            
        });
    }

    public void otvoriFormu() {
        lf.setVisible(true);
    }
    
    
}
