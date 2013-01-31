package pakovankilasta;

/**
 *
 * @author Olli Väisänen
 */

import java.util.Scanner;

public class PakoVankilasta {

    /**
     * @param args the command line arguments
     */
    
    private static Scanner lukija = new Scanner(System.in);
    private static Pelilauta lauta;
    
    public static void main(String[] args) {
        
        int lkm; 
        //char vinkit;
        Pelaaja[] pelaajat;
        boolean kesken = true;
        
        System.out.println("Pako vankilasta\n" + 
                           "===============\n\n");
        
        //koko = laudanKoko();
        lauta = new Pelilauta(laudanKoko());
        
        lkm = pelaajienLkm();
        pelaajat = new Pelaaja[lkm];
        for(int i=0; i<lkm; i++){
            pelaajat[i] = new Pelaaja();
        }
        
//        do {
//            for(int i=0; i<lkm; i++){
//                System.out.println(lauta);
//                if(pelaajat[i].laudalla() == 0) {
//                    System.out.println("Anna ruutu johon siirrät uuden vangin.\n");
//                }
//            }
//            
//            
//            
//        } while(kesken);
        
        
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
    
    private static int laudanKoko() {
        
        int koko;
        
        System.out.println("Anna pelilaudan koko. Koko on pariton kokonaisluku väliltä 5-15.");
        koko = kokonaisluku();
        
        if(koko<5 || koko>15){ 
            System.out.println("Annoit virheellisen laudan koon. \n" +
                    "Käytetään oletuslautaa (9x10).\n");
            koko = 9;
        } else if((koko%2)==0){
            koko = koko - 1;
            System.out.println("Annoit parillisen laudan koon.\n" +
                    "Käytetään yhtä pienempää laudan kokoa (" +koko+ "x" +(koko+1)+ ").\n");
        } else {
            System.out.println("Laudan koko on (" +koko+ "x" +(koko+1)+ ").\n");
        }
        return koko;
    }
    
    private static int pelaajienLkm() {
        
        int luku;
        
        do {
            System.out.println("Anna pelaajien lukumäärä 2-4.");
            luku = kokonaisluku();
            if(luku < 2 || luku > 4){
                System.out.println("Syötit virheellisen pelaajien lukumäärän. Yritä uudelleen.\n");
            } 
        } while(luku < 2 || luku > 4);
        
        return luku;
 
    }
    
    private static boolean siirto(Vanki vanki, int sarake, int rivi) {
        
        if(lauta.siirtoSallittu(vanki, sarake, rivi)) {
            if(lauta.reittiVapaa(vanki, sarake, rivi)) {
                if(rivi>0){
                    if(lauta.getRivi(rivi).vartijaEiSyo(vanki, sarake)){
                        vanki.liiku(lauta.getRivi(rivi).getRuutu(sarake));
                        lauta.getRivi(rivi).liikutaVartijaa(vanki, sarake);
                        return true;
                    } else {
                        System.out.println("Siirto ei onnistu, vankisi jäisi kiinni! Yritä uudelleen.\n");
                        return false;
                    }
                } else { //rivi==0
                    vanki.liiku(lauta.getRivi(rivi).getRuutu(sarake));
                    return true;
                }
                
            } else {
                System.out.println("Siirto ei onnistu, tiellä on vartijoita! Yritä uudelleen.\n");
                return false;
            }
        
        } else {
            System.out.println("Siirto ei ole vaaka- tai pystysuoraan! Yritä uudelleen.\n");
            return false;
        }
        
    }
    
}
