package com.ZakAdv.prikazy;

import com.ZakAdv.hra.HerniPlan;
import com.ZakAdv.zakladniInterfaces.InterPrikaz;

/**
 * Class Prikaz - zaklad pro ostatni prikazy
 *
 * @author Erich Pross
 * @version 2021/2022
 */
public class Prikaz implements InterPrikaz {

    private String NAME;
    private HerniPlan plan;

    public Prikaz(String name, HerniPlan plan) {
        this.NAME = name;
        this.plan = plan;
    }

    /**
     * @param parametry počet parametrů závisí na konkrétním příkazu.
     * @return
     */
    @Override
    public String provedPrikaz(String... parametry) {
        return null;
    }


    /**
     * vrati nazev prikazu
     *
     * @return text nazvu prikazu
     */
    @Override
    public String getNazev() {
        return this.NAME;
    }

    /**
     * vrati herni plan
     *
     * @return herni plan;
     */
    public HerniPlan getHerniPlan() {
        return this.plan;
    }
}
