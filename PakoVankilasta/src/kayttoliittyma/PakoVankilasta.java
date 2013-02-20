package kayttoliittyma;

/**
 *
 * @author Olli Väisänen
 */

import javax.swing.SwingUtilities;

public class PakoVankilasta {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        
        Kayttoliittyma kayttoliittyma; 
        
        kayttoliittyma = new Kayttoliittyma();
        SwingUtilities.invokeLater(kayttoliittyma);
             
    }
    
//    private static boolean siirto(Vanki vanki, Ruutu kohde) {
//        
//        int rivi = kohde.getRiviNro();
//        int sarake = kohde.getSarake();
//        if(lauta.siirtoSallittu(vanki, kohde)) {
//            if(lauta.reittiVapaa(vanki, kohde)) {
//                if(rivi>0){
//                    if(lauta.getRivi(rivi).vartijaEiSyo(vanki, sarake)){
//                        vanki.liiku(lauta.getRivi(rivi).getRuutu(sarake));
//                        lauta.getRivi(rivi).liikutaVartijaa(vanki, sarake);
//                        return true;
//                    } else {
//                        System.out.println("Siirto ei onnistu, vankisi jäisi kiinni! Yritä uudelleen.\n");
//                        return false;
//                    }
//                } else { //rivi==0
//                    vanki.liiku(lauta.getRivi(rivi).getRuutu(sarake));
//                    return true;
//                }
//                
//            } else {
//                System.out.println("Siirto ei onnistu, tiellä on vartijoita! Yritä uudelleen.\n");
//                return false;
//            }
//        
//        } else {
//            System.out.println("Siirto ei ole vaaka- tai pystysuoraan! Yritä uudelleen.\n");
//            return false;
//        }
//        
//    }
//    
//    private static void valinta(int pelaaja) {
//        
//        int laudalla=0;
//        int veneessa = pelaajat[pelaaja].veneessa();
//        for (int i = 0; i < 4; i++) {
//            if (pelaajat[pelaaja].laudalla(i)) {
//                laudalla++;
//            }
//        }
//        if(laudalla==0){ //Pelaajalla ei ole yhtään Vankia laudalla
//            //uusiVanki();
//        } else if(laudalla == (4-veneessa)){ //Kaikki jäljellä olevat Vangit laudalla
//            for(int i=1; i<=laudalla; i++){
//            System.out.println("Valitse '" + i + "' siirtääksesi ruudussa XX olevaa vankia.\n");
//            }
//            lueValinta(1, laudalla);
//        } else { //Vankeja sekä sellissä että laudalla
//            System.out.println("Valitse '0' siirtääksesi uuden vangin pihalle.\n");
//            for(int i=1; i<=laudalla; i++) {
//                System.out.println("Valitse '" + i + "' siirtääksesi ruudussa XX olevaa vankia.\n");
//            }
//            lueValinta(0, laudalla);
//        }
//    }
//    
//    private static void lueValinta(int min, int max) {
//        int valinta = kokonaisluku();
//        
//        while(valinta < min || valinta > max){
//            System.out.println("Virheellinen valinta. Yritä uudelleen.\n");
//            valinta = kokonaisluku();
//        }
//        
//        if(valinta == 0){
//            //uusiVanki();
//        } else {
//            for(int i=min; i<=valinta; i++) {
//                
//            }
//        }
//    }
    
}
