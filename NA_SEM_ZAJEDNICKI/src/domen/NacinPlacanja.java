/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package domen;

/**
 *
 * @author Korisnik
 */
public enum NacinPlacanja {
    KES, KARTICA;
    
    @Override
    public String toString() {
        if (this == KES) {
            return "KEŠ";
        }
        return name();
    }
}
