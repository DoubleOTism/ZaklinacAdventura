package com.ZakAdv.prikazy;

import com.ZakAdv.hra.HerniPlan;

/**
 * Class PrikazProhledat - dedi ze tridy Prikaz
 *
 * @author Erich Pross
 * @version 2021/2022
 */
public class PrikazProhledat extends Prikaz {

    public PrikazProhledat(HerniPlan plan) {
        super("prohledat", plan);
    }

    /**
     * okrade danou postavu
     *
     * @param parametry počet parametrů závisí na konkrétním příkazu.
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo, tak ....
            return "Koho mám prohledat?";
        } else if (parametry.length == 1 && this.getHerniPlan().getAktualniProstor().obsahujePostavu(parametry[0])) {

            return this.getHerniPlan().getAktualniProstor().vratPostavu(parametry[0]).prohledat();

        }

        return "To dělat nebudu.";
    }
}
