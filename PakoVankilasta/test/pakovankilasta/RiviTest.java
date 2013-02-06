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
    Vanki vanki;

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
        vanki = new Vanki(0);
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
        tulos = rivi1.vartijaEiSyo(vanki, 6);
        assertEquals(oikea, tulos);
        tulos = rivi1.vartijaEiSyo(vanki, 7);
        assertEquals(oikea, tulos);
        
        //Riville 9 ei voi siirtyä mihinkään ruutuun. (Keskimmäinen ruutu, jossa on vartija, tarkistetaan muualla???)
        for(int i=0; i<rivi9.getKoko(); i++) {
            if(i!=4) {
                tulos = rivi9.vartijaEiSyo(vanki, i);
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
            tulos = rivi0.vartijaEiSyo(vanki, i);
            assertEquals(oikea, tulos);
        }
        
        //Riville 1 voi siirtyä aloitussiirrolla ruutuihin 0..5
        for(int i=0; i<6; i++){
            tulos = rivi1.vartijaEiSyo(vanki, i);
            assertEquals(oikea, tulos);
        }
    }
    
    //HUOMHUOM! Kirjoita vartijaEiSyo-metodille testejä myös riviltä toiselle siirryttäessä, sekä rivillä liikkuessa!!!

    //testLiikutaVartijaa, mutta tarkista ensin että metodi on oikein (Vangin siirtyminen jo tapahtunut?)
    
    //testSiirronPituus, siirronPituus on private?

}
