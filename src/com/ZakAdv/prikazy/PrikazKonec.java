package com.ZakAdv.prikazy;

import com.ZakAdv.hra.Hra;

/**
 * Třída PrikazKonec implementuje pro hru příkaz konec.
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author Jarmila Pavlickova
 *@version    3.1
 *@created    květen 2005/2021/2022
 */

public class PrikazKonec extends Prikaz {

    private Hra hra;

    /**
     * Konstruktor třídy
     *
     * @param hra odkaz na hru, která má být příkazem konec ukončena
     */
    public PrikazKonec(Hra hra) {
        super("ukoncit", hra.getHerniPlan());
        this.hra = hra;
    }

    /**
     * V případě, že příkaz má jen jedno slovo "konec" hra končí(volá se metoda setKonecHry(true))
     * jinak pokračuje např. při zadání "konec a".
     *
     * @return zpráva, kterou vypíše hra hráči
     */

    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length > 0) {
            return "Nelze ukončit jen tak něco, zadej příkaz bez parametru!.";
        } else {
            hra.setKonecHry(true);
            return "Hra ukončena příkazem ukončit.";
        }
    }
}