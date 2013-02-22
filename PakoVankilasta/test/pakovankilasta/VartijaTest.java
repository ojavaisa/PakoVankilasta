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
public class VartijaTest {
    
    Vartija vartija;
    Ruutu ruutu1, ruutu2;
    Vanki vanki;
    Pelinappula nappula;
    
    public VartijaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ruutu1 = new Ruutu(1, 1, false);
        ruutu2 = new Ruutu(2, 1, false);
        vartija = new Vartija(ruutu1);
        vanki = new Vanki();
        nappula = new Pelinappula();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testKonstruktori() {
        Ruutu oikea1 = ruutu1;
        Ruutu tulos1 = vartija.getSijainti();
        assertEquals(oikea1, tulos1);
        
        Vartija oikea2 = vartija;
        Pelinappula tulos2 = ruutu1.getNappula();
        assertEquals(oikea2, tulos2);
    }

    @Test
    public void testLiikuTyhjaan() {
        
        vartija.liiku(ruutu2);
        
        Ruutu oikea1 = ruutu2;
        Ruutu tulos1 = vartija.getSijainti();
        assertEquals(oikea1, tulos1);
        
        Pelinappula oikea2 = vartija;
        Pelinappula tulos2 = ruutu2.getNappula();
        assertEquals(oikea2, tulos2);
        
    }
    
    public void testLiikuVankiin() {
        
        vanki.liiku(ruutu2);
        vartija.liiku(ruutu2);
        
        Ruutu oikea1 = ruutu2;
        Ruutu tulos1 = vartija.getSijainti();
        assertEquals(oikea1, tulos1);
        
        Pelinappula oikea2 = vartija;
        Pelinappula tulos2 = ruutu2.getNappula();
        assertEquals(oikea2, tulos2);
        
        oikea2 = null;
        tulos2 = ruutu1.getNappula();
        assertEquals(oikea2, tulos2);
    }
    
    public void testLiikuNappulaan() {
        
        nappula.liiku(ruutu2);
        vartija.liiku(ruutu2);
        
        Ruutu oikea1 = ruutu2;
        Ruutu tulos1 = vartija.getSijainti();
        assertEquals(oikea1, tulos1);
        
        Pelinappula oikea2 = vartija;
        Pelinappula tulos2 = ruutu2.getNappula();
        assertEquals(oikea2, tulos2);
        
        oikea2 = null;
        tulos2 = ruutu1.getNappula();
        assertEquals(oikea2, tulos2);
    }
}
