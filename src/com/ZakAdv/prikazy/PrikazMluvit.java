package com.ZakAdv.prikazy;

import com.ZakAdv.hra.HerniPlan;

public class PrikazMluvit extends Prikaz {

    /**
     * Class PrikazMluvit - dedi ze tridy Prikaz
     *
     * @author Erich Pross
     * @version 2021/2022
     */

    public PrikazMluvit(HerniPlan plan) {
        super("promluvit", plan);
    }

    /**
     * metoda vrací dialog postavy
     *
     * @param parametry počet parametrů závisí na konkrétním příkazu.
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo, tak ....
            return "S kým mám mluvit?";
        } else if (parametry.length == 1 && this.getHerniPlan().getAktualniProstor().obsahujePostavu(parametry[0])) {
            // pokud je druhe slovo takové, které lze vložit do brasny
            return this.getHerniPlan().getAktualniProstor().vratPostavu(parametry[0]).mluvit();

        }

        return "Tato osoba zde není!";
    }

}
