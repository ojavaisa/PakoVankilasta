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
public class RiviTest {
    
    Rivi rivi0, rivi1, rivi9;
    int koko = 9;
    
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
        rivi0 = new Rivi(koko, 0);
        rivi0.asetaVartija();
        rivi1 = new Rivi(koko, 1);
        rivi1.asetaVartija();
        rivi9 = new Rivi(koko, 9);
        rivi9.asetaVartija();
    }
    
    @After
    public void tearDown() {
    }

    
//    @Test
//    public void testLuoRuudut() {
//        
//    }
    
    @Test
    public void testAsetaVartija() {
        
        int tulos = -1;
        for(int i=0; i < rivi0.getKoko(); i++){
            if(rivi0.getRuutu(i).getNappula() != null){
                tulos = i;
            }
        }
        int oikea = -1;
        assertEquals(oikea, tulos);
        
        for(int i=0; i < rivi1.getKoko(); i++){
            if(rivi1.getRuutu(i).getNappula() != null){
                tulos = i;
            }
        }
        oikea = rivi1.getKoko() - 1;
        assertEquals(oikea, tulos);
        
        for(int i=0; i < rivi9.getKoko(); i++){
            if(rivi9.getRuutu(i).getNappula() != null){
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
    
    
}
