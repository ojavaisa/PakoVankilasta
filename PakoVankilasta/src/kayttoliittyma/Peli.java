package kayttoliittyma;

/**
 *
 * @author $Olli Väisänen
 */
import java.util.Scanner;
import pakovankilasta.Pelaaja;
import pakovankilasta.Pelilauta;
import pakovankilasta.Ruutu;
import pakovankilasta.Pelinappula;
import pakovankilasta.Vanki;

public class Peli {

    private Pelilauta lauta;
    private Pelaaja[] pelaajat;
    private Pelaaja vuorossa;
    private int pelaajaNro;
    protected Vanki valittu;
    private static Scanner lukija = new Scanner(System.in);
    protected boolean kesken = true;

    public Peli() {

        System.out.println("Pako vankilasta\n"
                + "===============\n");

        System.out.println("Anna pelilaudan koko.");
        this.lauta = new Pelilauta(laudanKoko());

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
        System.out.println("Pelaaja " + (pelaajaNro + 1));

    }

    public boolean vuoro(int rivi, int sarake) {

        if (rivi == 99) {
            if (tarkistaVeneSiirto()) {
                this.kesken = veneSiirto();
                this.valittu = null;
                if(this.kesken == true) {
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

    private void seuraavaPelaaja() {
        pelaajaNro++;
        if (pelaajaNro >= this.pelaajat.length) {
            pelaajaNro = 0;
        }
        this.vuorossa = this.pelaajat[pelaajaNro];
        System.out.println("Pelaaja " + (pelaajaNro + 1));
    }

    private boolean tarkistaSiirto(Ruutu kohde) {

        if (this.valittu.getSijainti() == kohde) { //tarkistetaan että pelaaja ei yritä liikkua samaan ruutuun jossa jo on
            return false;
        } else if (!this.lauta.siirtoSallittu(this.valittu, kohde)) {
            return false;
        } else if (!this.lauta.reittiVapaa(this.valittu, kohde)) {
            return false;
        } else {
            if (kohde.getRiviNro() > 0) {
                int rivi = kohde.getRiviNro();
                int sarake = kohde.getSarake();
                return this.lauta.getRivi(rivi).vartijaEiSyo(this.valittu, sarake);
            } else {
                return true;
            }
        }
    }

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

    private void siirto(Ruutu kohde) {

        int rivi = kohde.getRiviNro();
        int sarake = kohde.getSarake();
        int pituus = this.lauta.getRivi(rivi).siirronPituus(this.valittu, sarake);

        if (rivi != 0) {
            this.valittu.liiku(kohde);
            this.lauta.getRivi(rivi).liikutaVartijaa(valittu, pituus);
        } else { //rivi==0
            this.valittu.liiku(kohde);
        }
    }

    private boolean veneSiirto() {
        this.valittu.getSijainti().setNappulaNull();
        this.vuorossa.siirraVeneeseen(this.valittu);
        //halytys()
        if (vuorossa.getJaljella() == 1) {
            System.out.println("Pelaaja " + (pelaajaNro+1) + " voitti!");
            return false;
        } else {
            return true;
        }

    }

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
        } else {
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
