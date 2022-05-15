package com.ZakAdv.prikazy;

import com.ZakAdv.hra.HerniPlan;
import com.ZakAdv.hra.Prostor;

/**
 * Třída PrikazJit implementuje pro hru příkaz jít.
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author Jarmila Pavlickova, Luboš Pavlíček, upravil Erich Pross
 * @version    3.1
 * @created    květen 2005/2021/2022
 **/
public class PrikazJdi extends Prikaz {

    /**
     * Konstruktor třídy
     *
     * @param plan herní plán, ve kterém se bude ve hře "chodit"
     */
    public PrikazJdi(HerniPlan plan) {

        super("jdi", plan);
    }

    /**
     * Provádí příkaz "jdi". Zkouší se vyjít do zadaného prostoru. Pokud prostor
     * existuje, vstoupí se do nového prostoru. Pokud zadaný sousední prostor
     * (východ) není, vypíše se chybové hlášení.
     *
     * @param parametry - jako  parametr obsahuje jméno prostoru (východu),
     *                  do kterého se má jít.
     * @return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (sousední prostor), tak ....
            return "Kam mám jít? Musíš zadat jméno východu";
        }

        String smer = parametry[0];

        // zkoušíme přejít do sousedního prostoru
        Prostor sousedniProstor = this.getHerniPlan().getAktualniProstor().vratSousedniProstor(smer);

        if (sousedniProstor == null) {
            return "Tam se odsud jít nedá!";
        } else {
            this.getHerniPlan().setAktualniProstor(sousedniProstor);
            return sousedniProstor.dlouhyPopis();
        }
    }
}
