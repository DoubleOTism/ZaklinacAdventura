package com.ZakAdv.prikazy;

import com.ZakAdv.hra.HerniPlan;
import com.ZakAdv.vec.Vec;

/**
 * Class PrikazKoupit - dedi z tridy Prikaz
 * Dovoluje hráči zakoupit předměty, které nelze sebrat
 * @author Erich Pross
 * @version 2021/2022
 */
public class PrikazKoupit extends Prikaz {


    public PrikazKoupit(HerniPlan plan) {
        super("zakoupit", plan);
    }

    @Override
    public String provedPrikaz(String... parametry) {

        if (this.getHerniPlan().getAktualniProstor().getNazev() == "dum_savolly") {
            if (parametry.length == 0) {
                return "Co bych měl koupit? Specifikuj předmět.";
            } else if (parametry.length == 1 && this.getHerniPlan().getAktualniProstor().obsahujeVec(parametry[0])) {
                Vec vec = this.getHerniPlan().getAktualniProstor().vratVec(parametry[0]);
                if (this.getHerniPlan().getInventar().odecistPenize(vec.getCena())) {
                    this.getHerniPlan().getInventar().vlozVec(this.getHerniPlan().getAktualniProstor().odeberVec(parametry[0]));
                    return "zakoupil jsi " + parametry[0] + ".";
                }
                return "Nemáš dost orénů, zkus udělat nějaký úkol.";

            } else {
                return "Tohle zde nekoupíš.";
            }
        } else {
            return "Zde se nic neprodává.";
        }

    }
}


