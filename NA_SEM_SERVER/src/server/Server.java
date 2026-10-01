/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import konfiguracija.Konfiguracija;
import niti.ObradaKlijentskihZahteva;

/**
 *
 * @author Korisnik
 */
public class Server extends Thread {
    boolean kraj = false;
    ServerSocket serverSoket;

    @Override
    public void run() {
        try {
            serverSoket = new ServerSocket(vratiPort());
            while(!kraj) {
                Socket s = serverSoket.accept();
                System.out.println("Klijent povezan");
                
                ObradaKlijentskihZahteva okz = new ObradaKlijentskihZahteva(s);
                okz.start();
            }
        } catch (IOException ex) {
            if (!kraj) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }        
    }
    
    private int vratiPort() {
        try {
            return Integer.parseInt(Konfiguracija.getInstance().getProperty("port"));
        } catch (NumberFormatException nfe) {
            return 9000;
        }
    }
    
    public void zaustaviServer() {
        kraj = true;
        try {
            serverSoket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
