package pakovankilasta;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Olli
 */
public class RiviTest {

    Pelilauta lauta;
    Rivi rivi0, rivi1, rivi9;
    int koko = 9;
    Pelaaja pelaaja;
    Vanki vanki1, vanki2;

    public RiviTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        lauta = new Pelilauta(koko);
        //Osa testeista kirjoitettu aiemmin, jolloin käytettiin vain näitä kolmea riviä...
        rivi0 = lauta.getRivi(0);
        rivi1 = lauta.getRivi(1);
        rivi9 = lauta.getRivi(9);
        pelaaja = new Pelaaja(0);
        //Seuraava tehdään itse ohjelmassa luokassa Peli, tehdään nyt erikseen
        for (int j = 0; j < pelaaja.getJaljella(); j++) {
                pelaaja.getVanki(j).setPelaaja(pelaaja);
            }
        vanki1 = pelaaja.getVanki(0);
        vanki2 = pelaaja.getVanki(1);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testLuoRuudut() {

        boolean oikea, tulos;

        for (int i = 0; i < koko; i++) {
            oikea = false;
            tulos = rivi0.getRuutu(i).onkoPako();
            assertEquals(oikea, tulos);
        }

        for (int i = 0; i < koko; i++) {
            if (i > 1 && i < 7) {
                oikea = true;
            } else {
                oikea = false;
            }
            tulos = rivi9.getRuutu(i).onkoPako();
            assertEquals(oikea, tulos);
        }
    }

    @Test
    public void testAsetaVartija() {

        int tulos = -1;
        for (int i = 0; i < rivi0.getKoko(); i++) {
            if (rivi0.getRuutu(i).getNappula() != null) {
                tulos = i;
            }
        }
        int oikea = -1;
        assertEquals(oikea, tulos);

        for (int i = 0; i < rivi1.getKoko(); i++) {
            if (rivi1.getRuutu(i).getNappula() != null) {
                tulos = i;
            }
        }
        oikea = rivi1.getKoko() - 1;
        assertEquals(oikea, tulos);

        for (int i = 0; i < rivi9.getKoko(); i++) {
            if (rivi9.getRuutu(i).getNappula() != null) {
                tulos = i;
            }
        }
        oikea = rivi9.getKoko() / 2;
        assertEquals(oikea, tulos);
    }

    @Test
    public void testGetRiviNro() {
        int oikea = 0;
        int tulos = rivi0.getRiviNro();
        assertEquals(oikea, tulos);
        oikea = 1;
        tulos = rivi1.getRiviNro();
        assertEquals(oikea, tulos);
        oikea = 9;
        tulos = rivi9.getRiviNro();
        assertEquals(oikea, tulos);
    }
    
    @Test
    public void testVartijaEiSyoAloitusFalse() {
        
        boolean oikea = false;
        boolean tulos;
        
        //Riville 1 ei voi siirtyä aloitussiirrolla ruutuihin 6 ja 7
        tulos = rivi1.vartijaEiSyo(vanki1, 6);
        assertEquals(oikea, tulos);
        tulos = rivi1.vartijaEiSyo(vanki1, 7);
        assertEquals(oikea, tulos);
        
        //Riville 9 ei voi siirtyä mihinkään ruutuun. (Keskimmäinen ruutu, jossa on vartija, tarkistetaan muualla???)
        for(int i=0; i<rivi9.getKoko(); i++) {
            if(i!=4) {
                tulos = rivi9.vartijaEiSyo(vanki1, i);
                assertEquals(oikea, tulos);                       
            }
        }
    }
    
    @Test
    public void testVartijaEiSyoAloitusTrue() {
        
        boolean oikea = true;
        boolean tulos;
        
        //Rivillä 0 ei ole vartijaa eli kaikkiin ruutuihin voi liikkua
        for(int i=0; i<rivi0.getKoko(); i++){
            tulos = rivi0.vartijaEiSyo(vanki1, i);
            assertEquals(oikea, tulos);
        }
        
        //Riville 1 voi siirtyä aloitussiirrolla ruutuihin 0..5
        for(int i=0; i<6; i++){
            tulos = rivi1.vartijaEiSyo(vanki1, i);
            assertEquals(oikea, tulos);
        }
    }
    
    @Test
    public void testVartijaEiSyoMuitaFalse() {
        //HUOM! vartijaEiSyo-metodi antaisi arvon true näille siirroille ilman vartijaEiSyoMuita-metodia
        boolean oikea = false;
        boolean tulos;
        
        //Sijoitetaan vartijan eteen pelaajan vanki (Pelinappulan liiku-metodilla Rivin Vartija ei reagoi)
        vanki1.liiku(rivi1.getRuutu(7));
        //ilman vartijaEiSyoMuita-metodia, seuraavat tarkastukset menisivät läpi ks. testVartijaEiSyoAloitusTrue
        for(int i=0; i<6; i++) {
            tulos = rivi1.vartijaEiSyo(vanki2, i);
            assertEquals(oikea, tulos);                       
        }
        
    }
    
    @Test
    public void testVartijaEiSyoRiviltaRivilleTrue() {
        
        boolean oikea = true;
        boolean tulos;
        
        //Testataan eri vaihtoehtoja
        vanki1.liiku(rivi0.getRuutu(4));
        tulos = rivi1.vartijaEiSyo(vanki1, 4);
        assertEquals(oikea, tulos);
        tulos = lauta.getRivi(2).vartijaEiSyo(vanki1, 4);
        assertEquals(oikea, tulos);
        
        vanki1.liiku(rivi0.getRuutu(1));
        tulos = rivi1.vartijaEiSyo(vanki1, 1);
        assertEquals(oikea, tulos);
        tulos = lauta.getRivi(3).vartijaEiSyo(vanki1, 1);
        assertEquals(oikea, tulos);
        
        vanki1.liiku(rivi0.getRuutu(6));
        tulos = lauta.getRivi(2).vartijaEiSyo(vanki1, 6);
        assertEquals(oikea, tulos);
        tulos = lauta.getRivi(4).vartijaEiSyo(vanki1, 6);
        assertEquals(oikea, tulos);
        
        vanki1.liiku(rivi9.getRuutu(8));
        tulos = lauta.getRivi(8).vartijaEiSyo(vanki1, 8);
        assertEquals(oikea, tulos);
        tulos = lauta.getRivi(7).vartijaEiSyo(vanki1, 8);
        assertEquals(oikea, tulos);
        tulos = lauta.getRivi(6).vartijaEiSyo(vanki1, 8);
        assertEquals(oikea, tulos);
        
        vanki1.liiku(rivi9.getRuutu(0));
        tulos = lauta.getRivi(8).vartijaEiSyo(vanki1, 0);
        assertEquals(oikea, tulos);
        tulos = lauta.getRivi(7).vartijaEiSyo(vanki1, 0);
        assertEquals(oikea, tulos);
        tulos = lauta.getRivi(5).vartijaEiSyo(vanki1, 0);
        assertEquals(oikea, tulos);
        tulos = lauta.getRivi(3).vartijaEiSyo(vanki1, 0);
        assertEquals(oikea, tulos);
    }
    
    @Test
    public void testVartijaEiSyoRiviltaRivilleFalse() {
        
        boolean oikea = false;
        boolean tulos;
        
        //Testataan eri vaihtoehtoja
        vanki1.liiku(rivi0.getRuutu(4));
        for (int i = 3; i < 9; i++) {
            tulos = lauta.getRivi(i).vartijaEiSyo(vanki1, 4);
            assertEquals(oikea, tulos);
        }
        
        vanki1.liiku(rivi0.getRuutu(1));
        tulos = lauta.getRivi(2).vartijaEiSyo(vanki1, 1);
        assertEquals(oikea, tulos);
        for (int i = 5; i < 9; i++) {
            tulos = lauta.getRivi(i).vartijaEiSyo(vanki1, 1);
            assertEquals(oikea, tulos);
        }
        
        vanki1.liiku(rivi0.getRuutu(6));
        tulos = lauta.getRivi(3).vartijaEiSyo(vanki1, 6);
        assertEquals(oikea, tulos);
        for (int i = 6; i < 9; i++) {
            tulos = lauta.getRivi(i).vartijaEiSyo(vanki1, 6);
            assertEquals(oikea, tulos);
        }
        
    }
    
    @Test
    public void testVartijaEiSyoRivillaTrue() {
        
        boolean oikea = true;
        boolean tulos;
        
        //Sijoitetaan vanki vartija eteen
        vanki1.liiku(rivi1.getRuutu(7));
        //Kun rivillä siirrytään vartijasta poispäin, vartija seuraa perässä, mutta ei syö
        for(int i=0; i<6; i++) {
            tulos = rivi1.vartijaEiSyo(vanki1, i);
            assertEquals(oikea, tulos);                       
        }
        
        //Sijoitetaan vanki toiseen laitaan
        vanki1.liiku(rivi1.getRuutu(0));
        //Kun rivillä siirrytään vartijaa kohti, voidaan liikkua vain (alle) puoleenväliin etäisyydestä
        for(int i=0; i<3; i++) {
            tulos = rivi1.vartijaEiSyo(vanki1, i);
            assertEquals(oikea, tulos);                       
        }
    }
    
    @Test
    public void testVartijaEiSyoRivillaFalse() {
        
        boolean oikea = false;
        boolean tulos;
        
        //Sijoitetaan vanki toiseen laitaan
        vanki1.liiku(rivi1.getRuutu(0));
        //Kun rivillä siirrytään vartijaa kohti, voidaan liikkua vain (alle) puoleenväliin etäisyydestä
        for(int i=4; i<7; i++) {
            tulos = rivi1.vartijaEiSyo(vanki1, i);
            assertEquals(oikea, tulos);                       
        }
        
    }
    
    @Test
    public void testVartijaEiSyoMuitaRivillaFalse() {
        //HUOM Jos liikkuu rivillä vartija "perässään" ja hyppää oman vangin yli, 
        //vartijaEiSyo-metodi olisi 'true' ilman vartijaEiSyoMuita-metodia
        boolean oikea = false;
        boolean tulos;
        
        //Sijoitetaan vanki vartija eteen
        vanki1.liiku(rivi1.getRuutu(7));
        vanki2.liiku(rivi1.getRuutu(6));
        //ilman vartijaEiSyoMuita-metodia / toista vankia seuraava siirto olisi 
        //sallittu (ks. testVartijaEiSyoRivillaTrue ilman vankia 2)  
        tulos = rivi1.vartijaEiSyo(vanki1, 5);
        
        assertEquals(oikea, tulos); 
    }

    @Test
    public void testLiikutaVartijaaIlmanVankeja() {
        //HUOM Testaa samalla että siirronPituus-metodikin toimii oikein
        Ruutu oikea, tulos;
        
        rivi1.liikutaVartijaa(false, 3); //Kolme ruutua vasemmalle
        oikea = rivi1.getRuutu(5);
        tulos = rivi1.getVartija().getSijainti();
        
        assertEquals(oikea, tulos); 
        
    }
    
    @Test
    public void testLiikutaVartijaaSyoVankeja() {
        //HUOM Testaa samalla että siirronPituus-metodikin toimii oikein
        Ruutu oikea1, tulos1;
        Pelinappula oikea2, tulos2;
        
        vanki1.liiku(rivi1.getRuutu(6));
        oikea1 = rivi1.getRuutu(6);
        tulos1 = vanki1.getSijainti();
        assertEquals(oikea1, tulos1);
        oikea2 = vanki1;
        tulos2 = rivi1.getRuutu(6).getNappula();
        assertEquals(oikea2, tulos2);
        
        rivi1.liikutaVartijaa(false, 3); //Kolme ruutua vasemmalle, vanki syödään
        oikea1 = null;
        tulos1 = vanki1.getSijainti();
        assertEquals(oikea1, tulos1);
        oikea2 = null;
        tulos2 = rivi1.getRuutu(6).getNappula();
        assertEquals(oikea2, tulos2);
    }


}
