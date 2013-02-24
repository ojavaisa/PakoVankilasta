package kayttoliittyma;

import java.util.Random;
import java.util.Scanner;
import pakovankilasta.Pelaaja;
import pakovankilasta.Pelilauta;
import pakovankilasta.Pelinappula;
import pakovankilasta.Ruutu;
import pakovankilasta.Vanki;

/**
 * Peli-luokka on pelin käyttöliittymän tärkein luokka. Se yhdistää
 * käyttöliittymän ja pelilogiikan, pelaajien vuorottelusta, nappuloiden
 * liikuttelusta, siirtojen tarkastuksista, ym.
 *
 * @author $Olli Väisänen
 */
public class Peli {

    /**
     * Peli tuntee pelilaudan
     */
    private Pelilauta lauta;
    /**
     * Peli tuntee pelaajat
     */
    private Pelaaja[] pelaajat;
    /**
     * Peli pitää kirjaa vuorossa olevasta pelaajasta sekä pelaajan valitsemasta
     * vangista.
     */
    private Pelaaja vuorossa;
    private int pelaajaNro;
    /**
     * Peli pitää kirjaa vuorossa olevan pelaajan valitsemasta vangista.
     */
    protected Vanki valittu;
    private static Scanner lukija = new Scanner(System.in);
    private static Random noppa = new Random();
    protected boolean kesken = true;

    public Peli() {

        System.out.println("Pako vankilasta\n"
                + "===============\n");

        System.out.println("Anna pelilaudan koko.");
        this.lauta = new Pelilauta(laudanKoko());

        luoPelaajat();

        System.out.println("Pelaaja " + (pelaajaNro + 1));

    }

    /**
     * luoPelaajat on konstruktorin käyttämä metodi joka luo pelin pelaajat
     * (lukumäärä kysytään erillisellä metodilla käyttäjältä) sekä asettaa
     * ensimmäisen pelaajan vuoroon.
     */
    private void luoPelaajat() {

        this.pelaajat = new Pelaaja[pelaajienLkm()];
        for (int i = 0; i < pelaajat.length; i++) {
            this.pelaajat[i] = new Pelaaja(i);
            for (int j = 0; j < pelaajat[i].getJaljella(); j++) {
                this.pelaajat[i].getVanki(j).setPelaaja(this.pelaajat[i]);
            }
        }
        this.pelaajaNro = 0;
        this.vuorossa = pelaajat[this.pelaajaNro];
        this.valittu = null;
    }

    /**
     * vuoro() on Hiirenkuuntelija-luokan käyttämä metodi. Kun Vanki on
     * onnistuneesti valittu, suoritetaan vuoro. Rivi ja sarake saadaan
     * Hiirenkuuntelijalta. Jos vuoro onnistuu, palautetaan arvona true. Jos
     * Hiirenkuuntelija saa takaisin arvon false, seuraava hiiren klikkaus
     * suorittaa metodin uudelleen.
     *
     * @param rivi Rivinumero
     * @param sarake Sarakenumero
     * @return Totuusarvo, onnistuiko vuoro
     */
    public boolean vuoro(int rivi, int sarake) {

        if (rivi == 99) {
            if (tarkistaVeneSiirto()) {
                this.kesken = veneSiirto();
                this.valittu = null;
                if (this.kesken == true) {
                    halytys();
                    seuraavaPelaaja();
                }
                return true;
            } else {
                return false;
            }

        } else {

            Ruutu kohde = this.lauta.getRivi(rivi).getRuutu(sarake);
            if (tarkistaSiirto(kohde)) {
                siirto(kohde);
                this.valittu = null;
                seuraavaPelaaja();
                return true;
            } else {
                return false;
            }
        }

    }

    /**
     * Jos vuoro onnistuu, vuorossa olevaa pelaajaa vaihdetaan.
     */
    private void seuraavaPelaaja() {
        pelaajaNro++;
        if (pelaajaNro >= this.pelaajat.length) {
            pelaajaNro = 0;
        }
        this.vuorossa = this.pelaajat[pelaajaNro];
        System.out.println("Pelaaja " + (pelaajaNro + 1));
    }

    /**
     * Jos pelaaja siirtää vangin veneeseen, tapahtuu hälytys ja satunnaisen
     * rivin Vartija liikkuu Rivinsä laitaan.
     */
    private void halytys() {
        int rivi = noppa.nextInt(this.lauta.getKoko() - 2) + 1; //arvotaan rivinumero väliltä 1-viimeinen rivi
        boolean suunta = noppa.nextBoolean();
        int pituus;

        if (suunta) {
            pituus = this.lauta.getRivi(rivi).getKoko() - this.lauta.getRivi(rivi).getVartija().getSijainti().getSarake() - 1;
        } else {
            pituus = this.lauta.getRivi(rivi).getVartija().getSijainti().getSarake();
        }
        this.lauta.getRivi(rivi).liikutaVartijaa(suunta, pituus);
        System.out.println("Vanki aiheutti hälytyksen! Rivin " + (rivi + 1) + " vartija liikkui!");

    }

    /**
     * tarkistaSiirto()-metodi tarkistaa että siirto on kaikin puolin sallittu.
     *
     * @param kohde Siirron kohteena oleva Ruutu
     * @return Totuusarvo, onko siirto sallittu.
     */
    private boolean tarkistaSiirto(Ruutu kohde) {

        if (this.valittu.getSijainti() == kohde) { //tarkistetaan että pelaaja ei yritä liikkua samaan ruutuun jossa jo on
            return false;
        } else if (!this.lauta.siirtoSallittu(this.valittu, kohde)) {
            return false;
        } else if (!this.lauta.reittiVapaa(this.valittu, kohde)) {
            return false;
        } else {
            return vartijaEiSyo(kohde);
        }
    }

    /**
     * vartijaEiSyo tarkistaa että kohteena olevan rivin vartija ei syö pelaajan
     * nappuloita. Tarkistus tehdään Rivi-luokan metodilla.
     *
     * @param kohde Siirron kohteena oleva Ruutu
     * @return Totuusarvo siitä onko siirto sallittu
     */
    private boolean vartijaEiSyo(Ruutu kohde) {

        if (kohde.getRiviNro() > 0) {
            int rivi = kohde.getRiviNro();
            int sarake = kohde.getSarake();
            if (!this.lauta.getRivi(rivi).vartijaEiSyo(this.valittu, sarake)) {
                System.out.println("Siirto ei onnistu. Vartija saisi vankisi kiinni!");
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    /**
     * Venesiirto tarkistetaan tavallisesta siirrosta poikkeavasti.
     *
     * @return Totuusarvo, onko siirto sallittu.
     */
    private boolean tarkistaVeneSiirto() {

        int sarake = this.valittu.getSijainti().getSarake();
        int rivi = this.lauta.getKoko() - 1;

        if (!this.valittu.getSijainti().onkoPako()) {
            return false;
        } else if (this.valittu.getSijainti().getRiviNro() == rivi) {
            return true;
        } else if (!this.lauta.reittiVapaa(this.valittu, this.lauta.getRivi(rivi).getRuutu(sarake))) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Itse vangin siirto tapahtuu tällä metodilla.
     *
     * @param kohde Siirron kohteena oleva Ruutu.
     */
    private void siirto(Ruutu kohde) {

        int rivi = kohde.getRiviNro();
        int sarake = kohde.getSarake();
        int pituus = this.lauta.getRivi(rivi).siirronPituus(this.valittu, sarake);

        if (rivi != 0) {
            boolean suunta;
            if (sarake > this.lauta.getRivi(rivi).getVartija().getSijainti().getSarake()) {
                suunta = true; //true tarkoittaa oikeaa laitaa
            } else {
                suunta = false; //false tarkoittaa vasenta laitaa
            }
            this.valittu.liiku(kohde);
            this.lauta.getRivi(rivi).liikutaVartijaa(suunta, pituus);
        } else { //rivi==0
            this.valittu.liiku(kohde);
        }
    }

    /**
     * Venesiirto poikkeaa tavallisesta siirrosta.
     *
     * @return Totuusarvo siitä, jatkuuko peli edelleen.
     */
    private boolean veneSiirto() {
        this.valittu.getSijainti().setNappulaNull();
        this.vuorossa.siirraVeneeseen(this.valittu);
        //halytys()
        if (vuorossa.getJaljella() == 1) {
            System.out.println("Pelaaja " + (pelaajaNro + 1) + " voitti!");
            return false;
        } else {
            return true;
        }

    }

    /**
     * valitseVanki suorittaa siirrettävän Vangin valinnan. Metodin attribuutit
     * tulevat sitä kutsuvalta Hiirenkuuntelija-luokalta.
     *
     * @param rivi Valittavan vangin rivinumero
     * @param sarake Valittavan vangin sarake
     * @return Totuusarvo siitä onnistuiko valinta
     */
    protected boolean valitseVanki(int rivi, int sarake) {

        //this.valittu = null; //tarpeellinen? / aiheuttaako ongelmia?
        if (rivi == -1) {
            //valitaan sellissä oleva vanki!
            if (this.vuorossa.getSellissa() > 0) {
                this.valittu = this.vuorossa.getVankiSellista();
                return true;
            } else {
                return false;
            }
        } else if (rivi >= 0 && rivi < 99 && sarake >= 0) {
            return valitseLaudalta(rivi, sarake);
        } else {
            return false;
        }
    }

    /**
     * valitseLaudalta on valitseVanki-metodin käyttämä alimetodi. Metodi palauttaa
     * totuusarvon onnistuuko Vangin valinta.
     * 
     * @param rivi Valittavan Vangin rivinumero.
     * @param sarake Valittavan Vangin sarake.
     * @return Totuusarvo onnistuuko Vangin valinta
     */
    private boolean valitseLaudalta(int rivi, int sarake) {

        Pelinappula nappula = this.lauta.getRivi(rivi).getRuutu(sarake).getNappula();
        if (nappula == null) {
            return false;
        } else {
            for (int i = 0; i < vuorossa.getJaljella(); i++) {
                if (vuorossa.getVanki(i) == nappula) {
                    this.valittu = vuorossa.getVanki(i);
                    return true;
                }
            }
            return false;
        }
    }

    public Pelaaja[] getPelaajat() {
        return this.pelaajat;
    }

    public Pelaaja getPelaaja(int i) {
        return this.pelaajat[i];
    }

    public Pelilauta getLauta() {
        return this.lauta;
    }

    /**
     * laudanKoko-kysyy käyttäjältä pelilaudan koon (leveyden).
     * Syötteen oikeellisuus tarkistetaan erillisellä metodilla.
     * 
     * @return Käyttäjältä kysytty laudan koko 
     */
    private static int laudanKoko() {

        int koko;

        System.out.println("Koko on pariton kokonaisluku väliltä 5-15.");
        koko = kokonaisluku();

        if (koko < 5 || koko > 15) {
            System.out.println("Annoit virheellisen laudan koon. \n");
            koko = laudanKoko();
        } else if ((koko % 2) == 0) {
            koko = koko - 1;
            System.out.println("Annoit parillisen laudan koon.\n"
                    + "Käytetään yhtä pienempää laudan kokoa (" + koko + "x" + (koko + 1) + ").\n");
        } else {
            System.out.println("Laudan koko on (" + koko + "x" + (koko + 1) + ").\n");
        }
        return koko;
    }

    /**
     * kokonaisluku-metodi tarkistaa käyttäjän syötteen.
     * 
     * @return Käyttäjän antama luku, jos se on kokonaisluku.
     */
    private static int kokonaisluku() {

        boolean syote;
        int luku = 0;

        System.out.println("Anna kokonaisluku.");
        do {
            syote = lukija.hasNextInt();
            if (syote) {
                luku = lukija.nextInt();
            } else {
                String virheellinen = lukija.next();
                System.out.println("Et syöttänyt kokonaislukua! Yritä uudelleen.\n");
            }
        } while (!syote);

        return luku;
    }

    /**
     * pelaajienLkm-kysyy käyttäjältä pelaajien lukumäärän.
     * Syötteen oikeellisuus tarkistetaan erillisellä metodilla.
     * 
     * @return Käyttäjältä kysytty pelaajien lukumäärä.
     */
    private static int pelaajienLkm() {

        int luku;

        do {
            System.out.println("Anna pelaajien lukumäärä 2-4.");
            luku = kokonaisluku();
            if (luku < 2 || luku > 4) {
                System.out.println("Syötit virheellisen pelaajien lukumäärän. Yritä uudelleen.\n");
            }
        } while (luku < 2 || luku > 4);

        return luku;

    }
}
