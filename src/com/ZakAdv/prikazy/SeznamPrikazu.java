package com.ZakAdv.prikazy;


import com.ZakAdv.zakladniInterfaces.InterPrikaz;

import java.util.HashMap;
import java.util.Map;

/**
 *  Class SeznamPrikazu - obsahuje seznam přípustných příkazů adventury.
 *  Používá se pro rozpoznávání příkazů.
 *
 *  Tato třída je součástí jednoduché textové hry.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, upravil Erich Pross
 *@version    3.1
 *@created    květen 2005/2021/2022
 */
public class SeznamPrikazu {
    // mapa pro uložení přípustných příkazů
    private final Map<String, InterPrikaz> mapaSPrikazy;


    /**
     * Konstruktor
     */
    public SeznamPrikazu() {
        mapaSPrikazy = new HashMap<>();
    }


    /**
     * Vkládá nový příkaz.
     *
     * @param prikaz Instance třídy implementující rozhraní IPrikaz
     */
    public void vlozPrikaz(InterPrikaz prikaz) {
        mapaSPrikazy.put(prikaz.getNazev(), prikaz);
    }


    /**
     * Vrací odkaz na instanci třídy implementující rozhraní IPrikaz,
     * která provádí příkaz uvedený jako parametr.
     *
     * @param retezec klíčové slovo příkazu, který chce hráč zavolat
     * @return instance třídy, která provede požadovaný příkaz
     */
    public InterPrikaz vratPrikaz(String retezec) {
        return mapaSPrikazy.getOrDefault(retezec, null);
    }


    /**
     * Kontroluje, zda zadaný řetězec je přípustný příkaz.
     *
     * @return Vrací hodnotu true, pokud je zadaný
     * řetězec přípustný příkaz
     */
    public boolean jePlatnyPrikaz(String retezec) {
        return mapaSPrikazy.containsKey(retezec);
    }


    /**
     * Vrací seznam přípustných příkazů, jednotlivé příkazy jsou odděleny mezerou.
     *
     */
    public String vratNazvyPrikazu() {
        String seznam = "";
        for (String slovoPrikazu : mapaSPrikazy.keySet()) {
            seznam += slovoPrikazu + ", ";
        }
        seznam = seznam.substring(0, seznam.length() - 2);
        return seznam;
    }

}