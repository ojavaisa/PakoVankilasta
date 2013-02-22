package pakovankilasta;

/**
 * Pelilauta-luokka muodostaa pelin laudan. Pelilauta koostuu Riveistä.
 * Pelilaudan leveyden määrittää käyttäjän antama parametri koko. 
 *
 * @see pakovankilasta.Rivi
 *
 * @author $Olli Väisänen
 */

public class Pelilauta {

    private int leveys;
    
    /**
     * Pelilaudan muodostava Rivien taulukko.
     */
    private Rivi[] rivit;
    
    /*
     * Nämä luvut liittyvät Hiirenkuuntelija-luokalta saatujen koordinaattien muuttamiseen laudan
     * rivien ja sarakkeiden numeroiksi.
     */
    private static int ruudunKoko = 20;
    private static int vasenReuna = 58;  //Linuxilla 51?
    private static int alaReuna = 450;   //Linuxilla 448?

    /**
     * Konstruktori luo Pelilaudan eli taulukon Rivejä. Pelilaudan leveys on 
     * pelaajan antama pariton luku väliltä 5..15. Pelilaudan korkeus on yhtä suurempi,
     * sillä laudan alin rivi (kauimpana venettä) on Vartijaton, "turvallinen" Rivi.
     * 
     * @param koko Pelaajan antama laudan leveys
     */
    public Pelilauta(int koko) {

        this.leveys = koko;

        this.rivit = new Rivi[this.leveys + 1]; 
        luoRivit(rivit);
        
    }

    /**
     * luoRivit-metodi on konstruktorin käyttämä metodi, joka luo
     * rivit-muuttujassa olevat Rivit ja asettaa niihin Vartijat
     * (Rivi-luokan metodilla asetaVartija).
     *
     * @see pakovankilasta.Rivi
     *
     * @param rivit Pelilaudan muodostava Rivien taulukko
     */
    private void luoRivit(Rivi[] rivit) {

        for (int i = 0; i < rivit.length; i++) {
            rivit[i] = new Rivi(this.leveys, i);
        }
        for (int i = 0; i < rivit.length; i++) {
            rivit[i].asetaVartija();
        }
    }
    
    

    /**
     * siirtoSallittu tarkistaa, että pelaajan yrittämä siirto on joko vaakasuoraan tai
     * pystysuoraan
     *
     * @param vanki Vanki-nappula, jota halutaan siirtää
     * @param kohde kohteena oleva Ruutu, johon ollaan siirtymässä
     * @return totuusarvo, siitä onko siirto sallittu
     */
    public boolean siirtoSallittu(Vanki vanki, Ruutu kohde) {
        
        int rivi = kohde.getRiviNro();
        int sarake = kohde.getSarake();

        if (rivi == 0 || vanki.getSijainti() == null) {
            return true;
        } else if (vanki.getSijainti().getRiviNro() == rivi) {
            return true;
        } else if (vanki.getSijainti().getSarake() == sarake) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * reittiVapaa-metodi tarkistaa että kohteena oleva Ruutu on vapaa ja ettei siirron
     * tiellä ole Vartijoita. Edessä olevat Vanki-nappulat eivät estä siirtoa (paitsi
     * kohteena olevassa Ruudussa). Tarkistus tapahtuu siirron tyypistä riippuvalla
     * alimetodilla.
     *
     * @param vanki Vanki, jota halutaan liikuttaa
     * @param kohde kohteena oleva Ruutu, johon ollaan siirtymässä
     * @return totuusarvo true, jos edessä ei ole vartijoita
     */
    public boolean reittiVapaa(Vanki vanki, Ruutu kohde) {

        int rivi = kohde.getRiviNro();
        int sarake = kohde.getSarake();
        
        if (this.rivit[rivi].getRuutu(sarake).getNappula() != null) {
            return false;
        } else {
            if (vanki.getSijainti() == null) {
                return aloitusSiirto(kohde);
            } else if (vanki.getSijainti().getSarake() == sarake) {
                return sarakeSiirto(vanki, rivi);
            } else if (vanki.getSijainti().getRiviNro() == rivi) {
                return riviSiirto(vanki, sarake);
            } else {
                //Tänne ei pitäisi missään tilanteessa päästä
                return false;
            }
        }

    }

    /**
     * aloitusSiirto-metodi on reittiVapaa-metodin käyttämä tarkistus siirrolle,
     * joka lähtee sellistä (liikutettava Vanki ei ole laudalla).
     * 
     * @param kohde Siirron kohteena oleva Ruutu
     * @return totuusarvo siitä, onko reitti kohderuutuun vapaa
     */
    private boolean aloitusSiirto(Ruutu kohde) {

        int rivi = kohde.getRiviNro();
        int sarake = kohde.getSarake();
        
        return tarkistaSarake(sarake, 1, rivi); //riviä 0 ei tarvitse tarkistaa!

    }

    /**
     * sarakeSiirto-metodi on reittiVapaa-metodin käyttämä tarkistus siirrolle,
     * joka tehdään pitkin saraketta (pystysuora siirto). Itse sarakkeen tarkistus 
     * tehdään erillisellä metodilla, sarakeSiirto()-metodi tarkistaa rivien suuruusjärjestyksen.
     * 
     * @param vanki Vanki-nappula jota ollaan liikuttamassa
     * @param rivi kohderivi, jolle ollaan menossa
     * @return totuusarvo siitä, onko reitti kohderuutuun vapaa
     */
    private boolean sarakeSiirto(Vanki vanki, int rivi) {

        int lahtoRivi = vanki.getSijainti().getRiviNro();
        int sarake = vanki.getSijainti().getSarake();

        if (lahtoRivi < rivi) {
            if(lahtoRivi == 0) {
                lahtoRivi++; //Riviä 0 ei tarvitse tarkistaa
            }
            return tarkistaSarake(sarake, lahtoRivi, rivi);
        } else { //lahtoRivi > rivi 
            if(rivi == 0) {
                rivi++; //Riviä 0 ei tarvitse tarkistaa
            }
            return tarkistaSarake(sarake, rivi, lahtoRivi);
        }
    }
    
    /**
     * tarkistaSarake() on sarakeSiirto()-metodin käyttämä alimetodi.
     * 
     * @param sarake Tarkistettavan sarakkeen numero
     * @param rivi1 Lähtö- ja kohderivistä alempi
     * @param rivi2 Lähtö- ja kohderivistä ylempi
     * @return Totuusarvo onko sarakesiirto sallittu
     */
    private boolean tarkistaSarake(int sarake, int rivi1, int rivi2) {
        int vartijanSarake;
        
        for(int i=rivi1; i<=rivi2; i++){
            vartijanSarake = rivit[i].getVartija().getSijainti().getSarake();
            if(vartijanSarake == sarake){
                return false;
            }
        }
        return true;
    }

    /**
     * riviSiirto-metodi on reittiVapaa-metodin käyttämä tarkistus siirrolle,
     * joka tehdään pitkin riviä (vaakasuora siirto).
     * 
     * @param vanki Vanki-nappula jota ollaan liikuttamassa
     * @param sarake kohdesarake, jolle ollaan menossa
     * @return totuusarvo siitä, onko reitti kohderuutuun vapaa
     */
    private boolean riviSiirto(Vanki vanki, int sarake) {

        int lahtoSarake = vanki.getSijainti().getSarake();
        int rivi = vanki.getSijainti().getRiviNro();
        
        if(rivi == 0){
            return true;
        }
        
        int vartijanSarake = rivit[rivi].getVartija().getSijainti().getSarake();

        if (lahtoSarake < sarake) {
            for (int i = lahtoSarake; i <= sarake; i++) {
                if (vartijanSarake == i) {
                    return false;
                }
            }
            return true;
        } else { //lahtoSarake > sarake
            for (int i = lahtoSarake; i >= sarake; i--) {
                if (vartijanSarake == i) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * getRiviNro antaa parametrina annetun Rivin
     *
     * @param rivi halutun Rivin numero (alkaen vankilan pihasta)
     * @return pyydetty Rivi
     */
    public Rivi getRivi(int rivi) {
        return rivit[rivi];
    }

    /**
     * getKoko-metodi palauttaa pelilaudan korkeuden eli Rivien lukumäärä. Huom!
     * korkeus = leveys + 1
     *
     * @return Pelilaudan korkeus eli Rivien lukumäärä.
     */
    public int getKoko() {
        return this.rivit.length;
    }

    /**
     * muunnaX() muuttaa pikseleinä annetun x-koordinaatin laudan sarakenumeroksi.
     * Jos klikkaus on laudan ulkopuolella vaakasuunnassa, palautetaan arvo -1.
     * 
     * @param x Annettu x-koordinaatti pikseleinä
     * @return (Ruudun) sarakenumero
     */
    public int muunnaX(int x) {
        
        if(x<vasenReuna || x>(this.leveys*ruudunKoko+vasenReuna)) { 
            return -1;
        } else {
            return (int) ((x - vasenReuna) / ruudunKoko);
        }
    }
    
    /**
     * muunnaY() muuttaa pikseleinä annetun y-koordinaatin laudan rivinumeroksi.
     * Jos klikkaus on laudan alapuolella (selli), palautetaan arvo -1.
     * Jos klikkaus on laudan yläpuolella (pakovene), palautetaan arvo 99.
     * 
     * @param y Annettu y-koordinaatti pikseleinä
     * @return (Ruudun) rivinumero
     */
    public int muunnaY(int y) {
        
        if(y>alaReuna){
            return -1;
        } else if (y<(alaReuna-(this.getKoko())*ruudunKoko)) {
            return 99;
        } else {
            return (int) ((alaReuna - y) / ruudunKoko);
        }
    }

    @Override
    public String toString() {

        String lauta = "    ";

        for (int i = 0; i < this.leveys; i++) {
            int kirjain = 'A';
            kirjain += i;
            lauta = lauta + (char) kirjain + " ";
        }
        lauta = lauta + "\n";

        for (int i = rivit.length - 1; i >= 0; i--) {
            lauta = lauta + rivit[i] + "\n";
        }
        return lauta;
    }
    
}
