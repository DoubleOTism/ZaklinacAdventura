package com.ZakAdv.vec;

/**
 * Class Vec - popisuje atributy a chovani veci
 *
 * @author Erich Pross
 * @version 2021/2022
 */
public class Vec {
    private final String nazev;
    private final String herniNazev;
    private final Boolean lzeVzit;
    private int cena = 0;

    public Vec(String nazev, String herniNazev, Boolean lzeVzit) {
        this.nazev = nazev;
        this.lzeVzit = lzeVzit;
        this.herniNazev = herniNazev;
    }

    /**
     * vrati nazev veci
     *
     * @return textu nazvu veci
     */
    public String getNazev() {
        return this.nazev;
    }

    /**
     * vrati screenNazev veci
     *
     * @return text screenNazev veci
     */
    public String getScreenNazev() {
        return this.herniNazev;
    }

    /**
     * vrati, zda lze predmet vzit
     *
     * @return true pokud jde vzit, false pokud nelze
     */
    public Boolean getLzeVzit() {
        return this.lzeVzit;
    }

    /**
     * vrati cenu veci
     *
     * @return cena veci
     */
    public int getCena() {
        return this.cena;
    }

    /**
     * nastavi cenu veci
     *
     * @param cena - cena, kterou vec bude mit
     */
    public void setCena(int cena) {
        this.cena = cena;
    }
}