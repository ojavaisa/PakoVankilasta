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
        pelaaja = new Pelaaja();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * laudalla-metodin testaus Vangeille, jotka eivät ole laudalla
     */
    @Test
    public void testLaudallaNull() {
        
        boolean oikea = false;
        boolean tulos;
        for(int i=0; i<4; i++){
            tulos = pelaaja.laudalla(i);
            assertEquals(oikea, tulos);
        }
    }
}
