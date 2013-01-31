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
    private static Pelaaja[] pelaajat;
    
    public static void main(String[] args) {
        
        int lkm; 
        //char vinkit;
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
        
        do {
            for(int i=0; i<lkm; i++){
                System.out.println(lauta);
                //valinta(i);
                
                
            }
            
            
            
        } while(kesken);
        
        
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
    
    private static boolean siirto(Vanki vanki, Ruutu kohde) {
        
        int rivi = kohde.getRiviNro();
        int sarake = kohde.getSarake();
        if(lauta.siirtoSallittu(vanki, kohde)) {
            if(lauta.reittiVapaa(vanki, kohde)) {
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
    
    private static void valinta(int pelaaja) {
        
        int laudalla=0;
        int veneessa = pelaajat[pelaaja].veneessa();
        for (int i = 0; i < 4; i++) {
            if (pelaajat[pelaaja].laudalla(i)) {
                laudalla++;
            }
        }
        if(laudalla==0){ //Pelaajalla ei ole yhtään Vankia laudalla
            uusiVanki();
        } else if(laudalla == (4-veneessa)){ //Kaikki jäljellä olevat Vangit laudalla
            for(int i=1; i<=laudalla; i++){
            System.out.println("Valitse '" + i + "' siirtääksesi ruudussa XX olevaa vankia.\n");
            }
            lueValinta(1, laudalla);
        } else { //Vankeja sekä sellissä että laudalla
            System.out.println("Valitse '0' siirtääksesi uuden vangin pihalle.\n");
            for(int i=1; i<=laudalla; i++) {
                System.out.println("Valitse '" + i + "' siirtääksesi ruudussa XX olevaa vankia.\n");
            }
            lueValinta(0, laudalla);
        }
    }
    
    private static void lueValinta(int min, int max) {
        int valinta = kokonaisluku();
        
        while(valinta < min || valinta > max){
            System.out.println("Virheellinen valinta. Yritä uudelleen.\n");
            valinta = kokonaisluku();
        }
        
        if(valinta == 0){
            uusiVanki();
        } else {
            for(int i=min; i<=valinta; i++) {
                
            }
        }
    }
    
}
