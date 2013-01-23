/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pakovankilasta;

/**
 *
 * @author Olli
 */

import java.util.Scanner;

public class PakoVankilasta {

    /**
     * @param args the command line arguments
     */
    
    private static Scanner lukija = new Scanner(System.in);
    
    public static void main(String[] args) {
        
        int koko; //pelaajat
        //char vinkit;
        Pelilauta lauta;
        
        System.out.println("Pako vankilasta\n" + 
                           "===============\n\n");
        System.out.println("Anna pelilaudan koko. Koko on pariton kokonaisluku väliltä 5-15.");
        koko = kokonaisluku();
        
        lauta = new Pelilauta(koko);
        System.out.println(lauta);
        
    }
    
    private static int kokonaisluku(){

        boolean syote;
        int luku = 0;

        System.out.println("Anna kokonaisluku.");
        do {
            syote = lukija.hasNextInt();
            if (syote) {
                luku = lukija.nextInt();
            }
            else {
                String virheellinen = lukija.next();
                System.out.println("Et syöttänyt kokonaislukua! Yritä uudelleen.\n");
            }
        } while(!syote);

        return luku;
    }    
    
}
