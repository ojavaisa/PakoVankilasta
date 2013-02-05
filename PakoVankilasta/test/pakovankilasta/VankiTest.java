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
public class VankiTest {
    
    Vanki vanki1, vanki2;
    Ruutu ruutu1, ruutu2, ruutuVartija;
    Vartija vartija;
    Pelinappula nappula;
    
    public VankiTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        vanki1 = new Vanki();
        vanki2 = new Vanki();
        ruutu1 = new Ruutu(1, 1, false);
        ruutu2 = new Ruutu(2, 1, false);
        ruutuVartija = new Ruutu(3, 1, false);
        vartija = new Vartija(ruutuVartija);
        nappula = new Pelinappula();
        
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetSijainti(){
        
        vanki1.liiku(ruutu1);
        
        Ruutu oikea = ruutu1;
        Ruutu tulos = vanki1.getSijainti();
        assertEquals(oikea, tulos);
    }
    
    @Test
    public void testGetSijaintiNull(){
        
        Ruutu oikea = null;
        Ruutu tulos = vanki1.getSijainti();
        assertEquals(oikea, tulos);
    }
    
    /**
     * Vanki-luokan liiku-metodin (peritty) testaus ruudusta toiseen
     * Tarkistetaan, että Vangin tuntema Ruutu on oikea JA että vanha Ruutu
     * on tyhjä.
     */
    @Test
    public void testLiikuRuudustaToiseen() { 
        vanki1.liiku(ruutu1);
        vanki1.liiku(ruutu2);
        
        Ruutu oikea1 = ruutu2;
        Ruutu tulos1 = vanki1.getSijainti();
        assertEquals(oikea1, tulos1);
        
        Vanki oikea2 = null;
        Pelinappula tulos2 = ruutu1.getNappula();
        assertEquals(oikea2, tulos2);

    }
    
    /**
     * Vanki-luokan liiku-metodin testaus aloitussiirrolle (null->Ruutu)
     * 
     */
    @Test
    public void testLiikuAloitus() {         
        vanki1.liiku(ruutu1);
        
        Ruutu oikea1 = ruutu1;
        Ruutu tulos1 = vanki1.getSijainti();
        assertEquals(oikea1, tulos1);
        
        Vanki oikea2 = vanki1;
        Pelinappula tulos2 = ruutu1.getNappula();
        assertEquals(oikea2, tulos2);

    }
    
    /**
     * Vanki-luokan liiku-metodin testaus. Yritetään liikkua Ruutuun, jossa on jo Pelinappula.
     * 
     */
    @Test
    public void testLiikuNappulaan() { 
        nappula.liiku(ruutu1);
        vanki1.liiku(ruutu1);
        
        Ruutu oikea1 = null;
        Ruutu tulos1 = vanki1.getSijainti();
        assertEquals(oikea1, tulos1);
        
        Pelinappula oikea2 = nappula;
        Pelinappula tulos2 = ruutu1.getNappula();
        assertEquals(oikea2, tulos2);

    }    

    /**
     * Vanki-luokan liiku-metodin testaus. Yritetään liikkua Ruutuun, jossa on jo Vanki.
     * 
     */
    @Test
    public void testLiikuVankiin() { 
        vanki1.liiku(ruutu1);
        vanki2.liiku(ruutu1);
        
        Ruutu oikea1 = ruutu1;
        Ruutu tulos1 = vanki1.getSijainti();
        assertEquals(oikea1, tulos1);
        
        Pelinappula oikea2 = vanki1;
        Pelinappula tulos2 = ruutu1.getNappula();
        assertEquals(oikea2, tulos2);
        
        oikea1 = null;
        tulos1 = vanki2.getSijainti();
        assertEquals(oikea1, tulos1);
    }    
    
    /**
     * Vanki-luokan liiku-metodin testaus. Yritetään liikkua Ruutuun, jossa on jo Vartija.
     * 
     */
    @Test
    public void testLiikuVartijaan() { 
        vanki1.liiku(ruutuVartija);
        
        Ruutu oikea1 = null;
        Ruutu tulos1 = vanki1.getSijainti();
        assertEquals(oikea1, tulos1);
        
        Pelinappula oikea2 = vartija;
        Pelinappula tulos2 = ruutuVartija.getNappula();
        assertEquals(oikea2, tulos2);

    }    
    
//    @Test
//    public void testTakaisinSelliin(){
//        
//        vanki1.liiku(ruutu1);
//        vanki1.takaisinSelliin();
//        
//        Ruutu oikea1 = null;
//        Ruutu tulos1 = vanki1.getSijainti();
//        assertEquals(oikea1, tulos1);
//        
//        Pelinappula oikea2 = null;
//        Pelinappula tulos2 = ruutu1.getNappula();
//        assertEquals(oikea2, tulos2);
//    }
}
