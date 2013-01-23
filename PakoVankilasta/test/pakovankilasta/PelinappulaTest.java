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
public class PelinappulaTest {
    
    Pelinappula nappula1, nappula2;
    Ruutu ruutu1, ruutu2;
    
    public PelinappulaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        nappula1 = new Pelinappula();
        nappula2 = new Pelinappula();
        ruutu1 = new Ruutu();
        ruutu2 = new Ruutu();
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    
    /**
     * Pelinappula-luokan getSijainti-metodin testaus ruuduttomalla nappulalla
     */
    @Test
    public void testGetSijaintiNull() {
        Ruutu oikea = null;
        Ruutu tulos = nappula1.getSijainti();
        assertEquals(oikea, tulos);
    }
    
    /**
     * Pelinappula-luokan getSijainti-metodin testaus ruudullisella nappulalla
     */
    @Test
    public void testGetSijaintiRuutu() {
        nappula1.liiku(ruutu1);
        
        Ruutu oikea = ruutu1;
        Ruutu tulos = nappula1.getSijainti();
        assertEquals(oikea, tulos);
    }
    
    /**
     * Pelinappula-luokan liiku-metodin testaus ruudusta toiseen
     * Tarkistetaan, että Pelinappulan tuntema Ruutu on oikea JA että vanha Ruutu
     * on tyhjä.
     */
    @Test
    public void testLiikuRuudustaToiseen() { 
        nappula1.liiku(ruutu1);
        nappula1.liiku(ruutu2);
        
        Ruutu oikea1 = ruutu2;
        Ruutu tulos1 = nappula1.getSijainti();
        assertEquals(oikea1, tulos1);
        
        Pelinappula oikea2 = null;
        Pelinappula tulos2 = ruutu1.getNappula();
        assertEquals(oikea2, tulos2);

    }
    
    /**
     * Pelinappula-luokan liiku-metodin testaus aloitussiirrolle (null->Ruutu)
     * 
     */
    @Test
    public void testLiikuAloitus() {         
        nappula1.liiku(ruutu1);
        
        Ruutu oikea1 = ruutu1;
        Ruutu tulos1 = nappula1.getSijainti();
        assertEquals(oikea1, tulos1);
        
        Pelinappula oikea2 = nappula1;
        Pelinappula tulos2 = ruutu1.getNappula();
        assertEquals(oikea2, tulos2);

    }
    
    /**
     * Pelinappula-luokan liiku-metodin testaus. Yritetään liikkua täyteen Ruutuun.
     * 
     */
    @Test
    public void testLiikuTayteen() { 
        nappula1.liiku(ruutu1);
        nappula2.liiku(ruutu1);
        
        Ruutu oikea1 = ruutu1;
        Ruutu tulos1 = nappula1.getSijainti();
        assertEquals(oikea1, tulos1);
        
        Pelinappula oikea2 = nappula1;
        Pelinappula tulos2 = ruutu1.getNappula();
        assertEquals(oikea2, tulos2);
        
        Ruutu oikea3 = null;
        Ruutu tulos3 = nappula2.getSijainti();
        assertEquals(oikea3, tulos3);
    }

    
}
