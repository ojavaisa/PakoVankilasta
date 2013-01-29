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
public class PelilautaTest {
    
    Pelilauta lauta;
    int koko = 9;
    
    public PelilautaTest() {
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
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testGetKoko() {
        
        int oikea = koko + 1;
        int tulos = lauta.getKoko();
        assertEquals(oikea, tulos);
    }
    
    /**
     * Testataan Pelilaudan Rivit luovaa metodia.
     */
    @Test
    public void testLuoRivit() {

        int tulos;
        for (int i = 0; i < lauta.getKoko(); i++) {
            tulos = lauta.getRivi(i).getRiviNro();
            assertEquals(i, tulos);
        }
        //asetaVartija-metodin testaus RiviTest-luokassa?
    }
     
     //testGetRivi? 
}
