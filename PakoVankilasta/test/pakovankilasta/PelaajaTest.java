/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class PelaajaTest {
    
    Pelaaja pelaaja;
    Ruutu ruutu1, ruutu2, ruutu3, ruutu4;
    
    public PelaajaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        pelaaja = new Pelaaja(0);
        ruutu1 = new Ruutu(0, 0, false);
        ruutu2 = new Ruutu(0, 1, false);
        ruutu3 = new Ruutu(0, 2, false);
        ruutu4 = new Ruutu(0, 3, false);
        
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testJaljella() {
        int oikea = 4;
        int tulos;
        
        tulos = pelaaja.getJaljella();
        assertEquals(oikea, tulos);
        
        Vanki vanki1 = pelaaja.getVanki(0);
        pelaaja.siirraVeneeseen(vanki1);
        oikea = 3;
        tulos = pelaaja.getJaljella();
        assertEquals(oikea, tulos);        
    }
    
    @Test
    public void testSellissa() {
        int oikea = 4;
        int tulos;
        
        tulos = pelaaja.getSellissa();
        assertEquals(oikea, tulos);
        
        pelaaja.getVanki(0).liiku(ruutu1);
        oikea = 3;
        tulos = pelaaja.getSellissa();
        assertEquals(oikea, tulos);
    }
    
    @Test
    public void testSiirraVeneeseen() {
        
        int oikea, tulos;
        
        Vanki vanki1 = pelaaja.getVanki(0);
        pelaaja.siirraVeneeseen(vanki1);
        oikea = 3;
        tulos = pelaaja.getJaljella();
        assertEquals(oikea, tulos); 
        
        //Jos metodi toimii, uudessa taulukossa vangin 0 pitäisi olla sellissä
        vanki1 = pelaaja.getVanki(0);
        pelaaja.siirraVeneeseen(vanki1);
        oikea = 2;
        tulos = pelaaja.getJaljella();
        assertEquals(oikea, tulos); 
        
        //Jos metodi toimii, uudessa taulukossa vangin 0 pitäisi olla sellissä
        vanki1 = pelaaja.getVanki(0);
        pelaaja.siirraVeneeseen(vanki1);
        oikea = 1;
        tulos = pelaaja.getJaljella();
        assertEquals(oikea, tulos); 
    }
    
    @Test
    public void testGetVankiSellista() {
        Vanki oikea;
        Vanki tulos;
        
        oikea = pelaaja.getVanki(0);
        tulos = pelaaja.getVankiSellista();
        assertEquals(oikea, tulos);
        
        tulos.liiku(ruutu1);
        oikea = pelaaja.getVanki(1);
        tulos = pelaaja.getVankiSellista();
        assertEquals(oikea, tulos);
        
        tulos.liiku(ruutu2);
        oikea = pelaaja.getVanki(2);
        tulos = pelaaja.getVankiSellista();
        assertEquals(oikea, tulos);
        
        tulos.liiku(ruutu3);
        oikea = pelaaja.getVanki(3);
        tulos = pelaaja.getVankiSellista();
        assertEquals(oikea, tulos);
        
        tulos.liiku(ruutu4);
        oikea = null;
        tulos = pelaaja.getVankiSellista();
        assertEquals(oikea, tulos);
    }
}
