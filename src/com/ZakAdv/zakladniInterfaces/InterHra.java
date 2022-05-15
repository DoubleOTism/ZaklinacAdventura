package com.ZakAdv.zakladniInterfaces;

import com.ZakAdv.hra.HerniPlan;

/**
 * Rozhraní které musí implementovat hra, je na ně navázáno uživatelské rozhraní
 *
 * @author Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 * @version    3.1
 * @created    květen 2005/2021/2022
 */
public interface InterHra {
    //== VEŘEJNÉ KONSTANTY =====================================================
    //== DEKLAROVANÉ METODY ====================================================

    /**
     * Vrátí úvodní zprávu pro hráče.
     *
     * @return vrací se řetězec, který se má vypsat na obrazovku
     */
    String vratUvitani();

    /**
     * Vrátí závěrečnou zprávu pro hráče.
     *
     * @return vrací se řetězec, který se má vypsat na obrazovku
     */
    String vratEpilog();

    /**
     * Vrací informaci o tom, zda hra již skončila, je jedno zda výhrou, prohrou nebo příkazem konec.
     *
     * @return vrací true, pokud hra skončila
     */
    boolean konecHry();

    /**
     * Metoda zpracuje řetězec uvedený jako parametr, rozdělí ho na slovo příkazu a další parametry.
     * Pak otestuje zda příkaz je klíčovým slovem  např. jdi.
     * Pokud ano spustí samotné provádění příkazu.
     *
     * @param radek text, který zadal uživatel jako příkaz do hry.
     * @return vrací se řetězec, který se má vypsat na obrazovku
     */
    String zpracujPrikaz(String radek);


    /**
     * Metoda vrátí odkaz na herní plán, je využita hlavně v testech,
     * kde se jejím prostřednictvím získává aktualní místnost hry.
     *
     * @return odkaz na herní plán
     */
    HerniPlan getHerniPlan();

    //== ZDĚDĚNÉ METODY ========================================================
    //== INTERNÍ DATOVÉ TYPY ===================================================
}