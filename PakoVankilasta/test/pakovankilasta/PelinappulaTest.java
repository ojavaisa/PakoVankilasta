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
     * Pelinappula-luokan getSijainti-metodin testaus ruudulisella nappulalla
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
     */
    @Test
    public void testLiikuRuudustaToiseen() { 
        nappula1.liiku(ruutu1);
        nappula1.liiku(ruutu2);
        Ruutu oikea = ruutu2;
        Ruutu tulos = nappula1.getSijainti();
        assertEquals(oikea, tulos);

    }

    
}
