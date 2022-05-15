package com.ZakAdv.prikazy;

import com.ZakAdv.hra.HerniPlan;

/**
 * Class PrikazAxii - dědí z třídy příkaz
 *
 * @author Erich Pross
 * @version 2021/2022
 */
public class PrikazAxii extends Prikaz {


    public PrikazAxii(HerniPlan plan) {
        super("axii", plan);
    }

    /**
     * metoda použije znamení Axii na danou postavu
     *
     * @param parametry počet parametrů závisí na konkrétním příkazu.
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Na koho mám použít znamení Axii?";
        } else if (parametry.length == 1 && this.getHerniPlan().getAktualniProstor().obsahujePostavu(parametry[0])) {
            return this.getHerniPlan().getAktualniProstor().vratPostavu(parametry[0]).axii();

        }

        return "Na toho použít Axii nemohu, není tu!";
    }
}
