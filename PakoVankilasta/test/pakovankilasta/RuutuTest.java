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
public class RuutuTest {
    
    Ruutu ruutuTyhja, ruutuNappula;
    Pelinappula nappula1, nappula2;
    
    public RuutuTest() {
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
        ruutuTyhja = new Ruutu(1,1,false);
        ruutuNappula = new Ruutu(2,1,false);
        ruutuNappula.setNappula(nappula1);
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    
    /**
     * Ruutu-luokan getNappula-metodin testaus tyhjällä ruudulla
     */
    @Test
    public void testGetNappulaTyhja() {
        Pelinappula oikea = null;
        Pelinappula tulos = ruutuTyhja.getNappula();
        assertEquals(oikea, tulos);
    }
    
    /**
     * Ruutu-luokan getNappula-metodin testaus nappulalla
     */
    @Test
    public void testGetNappula() {
        Pelinappula oikea = nappula1;
        Pelinappula tulos = ruutuNappula.getNappula();
        assertEquals(oikea, tulos);
    }
    
    /**
     * Ruutu-luokan setNappula-metodin testaus, lisätään nappula tyhjään ruutuun
     */
    @Test
    public void testSetNappulaTyhjaan() {
        ruutuTyhja.setNappula(nappula2);
        Pelinappula oikea = nappula2;
        Pelinappula tulos = ruutuTyhja.getNappula();
        assertEquals(oikea, tulos);
    }
    
    /**
     * Ruutu-luokan setNappula-metodin testaus arvolla null
     */
    @Test
    public void testSetNappulaTyhjaksi() {
        ruutuNappula.setNappula(null);
        Pelinappula oikea = nappula1;
        Pelinappula tulos = ruutuNappula.getNappula();
        assertEquals(oikea, tulos);
    }
    
    /**
     * Ruutu-luokan setNappula-metodin testaus, nappulan lisääminen täyteen ruutuun
     */
    @Test
    public void testSetNappulaTayteen() {
        ruutuNappula.setNappula(nappula2);
        Pelinappula oikea = nappula1;
        Pelinappula tulos = ruutuNappula.getNappula();
        assertEquals(oikea, tulos);
    }
    
}
