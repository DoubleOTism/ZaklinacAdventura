package com.ZakAdv.prikazy;


import com.ZakAdv.hra.HerniPlan;
import com.ZakAdv.hra.Prostor;
import com.ZakAdv.inventar.Inventar;
import com.ZakAdv.postavy.Postava;
import com.ZakAdv.vec.Vec;

import java.util.Map;

/**
 * Class PrikazDat - dědí z třídy Příkaz
 *
 * @author Erich Pross
 * @version 2021/2022
 */
public class PrikazDat extends Prikaz {
    public PrikazDat(HerniPlan plan) {
        super("dat", plan);
    }

    /**
     * provede vymenu predmetu
     *
     * @param parametry počet parametrů závisí na konkrétním příkazu.
     */
    public String provedPrikaz(String... parametry) {

        Inventar inventar = this.getHerniPlan().getInventar();
        Prostor aktualniProstor = this.getHerniPlan().getAktualniProstor();
        if (parametry.length == 0) {
            // pokud chybí první parametr (předmět)
            return "Co mám někomu dát? Musíš specifikovat předmět!";
        }
        //pokud chybí druhý parametr (osobu)
        else if (parametry.length == 1) {
            return "Komu to mám dát? Musíš specifikovat osobu!";
        } else if (parametry.length == 2 && inventar.obsahujeVec(parametry[0])) {
            if (aktualniProstor.obsahujePostavu(parametry[1])) {
                Postava postava = aktualniProstor.vratPostavu(parametry[1]);
                Vec vecChce = postava.getCoChce();
                Vec vecMa = postava.getCoMa();
                if (vecChce != null && postava.getJizMluvil()) {
                    if (vecChce.getNazev().equals(parametry[0])) {
                        Map<String, Vec> tradeVeci = postava.vymenitVec(vecChce);
                        if (tradeVeci.get("odeber") != null) {
                            inventar.odeberVec(tradeVeci.get("odeber").getNazev());
                        }
                        if (tradeVeci.get("pridej") != null) {
                            inventar.vlozVec(tradeVeci.get("pridej"));
                        }
                        return postava.chceOdpoved();
                    } else {
                        return postava.nechceOdpoved();
                    }
                } else if (postava.getJizMluvil()) {
                    return postava.getHerniJmeno().substring(0, 1).toUpperCase() + postava.getHerniJmeno().substring(1) + ": " + postava.getDiaNechce();
                } else {
                    return "*" + postava.getHerniJmeno().substring(0, 1).toUpperCase() + postava.getHerniJmeno().substring(1) + " na tebe nechápavě kouká*";
                }
            } else {
                return "Tato osoba v této oblasti není.";
            }

        } else if (parametry.length == 2) {
            return "Tohle nevlastníš.";
        } else {
            return "Moc parametrů, zkontroluj příkaz.";
        }
    }
}