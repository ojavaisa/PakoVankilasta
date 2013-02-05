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
    Vanki vanki;

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
        vanki = new Vanki();
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

    /**
     * siirtoSallittu-metodin testaus kielletyille siirroille
     */
    @Test
    public void testSiirtoSallittuFalse() {

        boolean oikea = false;
        boolean tulos;

        //Liikutaan aluksi nollarivin keskelle
        vanki.liiku(lauta.getRivi(0).getRuutu(4));

        //Yritetään liikkua muualle kuin samalle riville tai sarakkeelle
        //Liikkuminen samaan ruutuun???
        for (int i = 1; i < lauta.getKoko(); i++) {
            for (int j = 0; j < lauta.getKoko() - 1; j++) {
                if (j != 4) {
                    tulos = lauta.siirtoSallittu(vanki, lauta.getRivi(i).getRuutu(j));
                    assertEquals(oikea, tulos);
                }
            }
        }
    }

    /**
     * siirtoSallittu-metodin testaus sallituille siirroille
     */
    @Test
    public void testSiirtoSallittuTrue() {

        boolean oikea = true;
        boolean tulos;

        //Aloitussiirrolle (nappulan sijainti aluksi null) kaikki siirrot ovat "sallittuja"
        for (int i = 1; i < lauta.getKoko(); i++) {
            for (int j = 0; j < lauta.getKoko() - 1; j++) {
                tulos = lauta.siirtoSallittu(vanki, lauta.getRivi(i).getRuutu(j));
                assertEquals(oikea, tulos);
            }
        }

        //Liikutaan nollarivin keskelle
        vanki.liiku(lauta.getRivi(0).getRuutu(4));

        //Nyt sallittuja siirtoja ovat siirrot samalla rivillä tai sarakkeella
        for (int i = 1; i < lauta.getKoko(); i++) {
            for (int j = 0; j < lauta.getKoko() - 1; j++) {
                if (j == 4 || i == 0) {
                    tulos = lauta.siirtoSallittu(vanki, lauta.getRivi(i).getRuutu(j));
                    assertEquals(oikea, tulos);
                }
            }
        }
    }

    /**
     * reittiVapaa-metodin testaus Ruuduille, joihin ei voi liikkua
     */
    @Test
    public void testReittiVapaaFalse() {

        boolean oikea = false;
        boolean tulos;

        //Viimeiselle riville ei voi liikkua mihinkään ruutuun aloitustilanteessa
        for (int i = 0; i < lauta.getKoko() - 1; i++) {
            tulos = lauta.reittiVapaa(vanki, lauta.getRivi(9).getRuutu(i));
            assertEquals(oikea, tulos);
        }

        //Testataan metodin toimita myös siirrolle, joka ei ole aloitussiirto
        //Viimeiselle riville ei voi myöskään liikkua miltään ensimmäisen rivin ruudulta
        for (int i = 0; i < lauta.getKoko() - 1; i++) {
            vanki.liiku(lauta.getRivi(0).getRuutu(i));
            tulos = lauta.reittiVapaa(vanki, lauta.getRivi(9).getRuutu(i));
            assertEquals(oikea, tulos);
        }
    }

    /**
     * reittiVapaa-metodin testaus Ruuduille, joihin voi liikkua
     */
    @Test
    public void testReittiVapaaTrue() {

        boolean oikea = true;
        boolean tulos;

        //Keskimmäiselle sarakkeelle liikkuminen on sallittua kaikkiin Ruutuihin paitsi venettä lähimpään
        for (int i = 0; i < lauta.getKoko() - 2; i++) {
            tulos = lauta.reittiVapaa(vanki, lauta.getRivi(i).getRuutu(4));
            assertEquals(oikea, tulos);
        }

        vanki.liiku(lauta.getRivi(0).getRuutu(4));
        for (int i = 1; i < lauta.getKoko() - 2; i++) {
            tulos = lauta.reittiVapaa(vanki, lauta.getRivi(i).getRuutu(4));
            assertEquals(oikea, tulos);
        }
    }
}
