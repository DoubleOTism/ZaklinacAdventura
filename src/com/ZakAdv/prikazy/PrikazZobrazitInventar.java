package com.ZakAdv.prikazy;

import com.ZakAdv.hra.HerniPlan;

/**
 * Class PrikazZobrazitInventar - dedi ze tridy Prikaz
 *
 * @author Erich Pross
 * @version 2021/2022
 */
public class PrikazZobrazitInventar extends Prikaz {


    public PrikazZobrazitInventar(HerniPlan plan) {
        super("zobrazit_inventar", plan);
    }

    /**
     * zobrazi predmety v inventari
     *
     * @param parametry počet parametrů závisí na konkrétním příkazu.
     */
    @Override
    public String provedPrikaz(String... parametry) {
        return this.getHerniPlan().getInventar().zobrazitObsah();
    }
}
