package com.ZakAdv.inventar;

import com.ZakAdv.vec.Vec;

import java.util.HashMap;
import java.util.Map;

/**
 * Class Inventar - inventar, kde se uchovavani sebrane predmety
 * muzememe si ho kdykoliv zobrazit prikazem inventar
 * @author Erich Pross
 * @version 2021/2022
 */

public class Inventar {
    private Map<String, Vec> veci = new HashMap<>();
    private int penize = 50;

    /**
     * zkontroluje, jestli inventar neni plny a vlozi vec do inventare
     *
     * @param vec vec kterou vkladame do inventare
     */
    public Vec vlozVec(Vec vec) {
        if (!jePlny()) {
            veci.put(vec.getNazev(), vec);
            if (veci.containsKey(vec.getNazev())) return vec;
        }
        return null;
    }

    /**
     * vrací obsah inventáře
     *
     * @return obsah kožené brašny uživatele
     */
    public String zobrazitObsah() {

        String vracenyText = "Obsah kožené brašny: \n";
        if (veci.size() > 0) {
            for (Map.Entry<String, Vec> vec : veci.entrySet()) {
                vracenyText += vec.getKey() + ", ";
            }
            vracenyText = vracenyText.substring(0, vracenyText.length() - 2);
        } else {
            vracenyText += "nic nevlastníš";
        }

        return vracenyText + "\n" + "V měšci máš: " + this.penize + " orénů";
    }

    /**
     * vrací množství peněz v inventáři
     * @return množství peněz v měšci
     */
    public int getPenize() {
        return this.penize;
    }


    /**
     * odecte penize z inventare, true pokud máme dostatek peněz a byla částka odečtena, false pokud není dostatek peněz
     * @return true pokud máme dostatek peněz, false pokud nemáme dostatek peněz
     */
    public Boolean odecistPenize(int castka) {
        if (castka <= this.penize) {
            this.penize -= castka;
            return true;
        }
        return false;
    }

    /**
     * pricte penize do inventare
     *
     */
    public void pricistPenize(int castka) {
        this.penize += castka;
    }

    /**
     * vrací, zda-li se v inventáři nachází požadovaná věc
     * @return true pokud ano, false pokud ne
     */
    public Boolean obsahujeVec(String nazev) {
        return veci.containsKey(nazev);
    }


    /**
     * odebere věc z inventáře
     * @return vec která byla odebrána
     */
    public Vec odeberVec(String nazev) {
        Vec vec = veci.get(nazev);
        veci.remove(nazev);
        return vec;
    }

    /**
     * vrací, zda-li je inventář plný
     * @return true pokud ano, false pokud ne
     */
    public Boolean jePlny() {
        if (veci.size() > 6) {
            return true;
        }
        return false;
    }
}