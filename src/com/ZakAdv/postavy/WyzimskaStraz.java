package com.ZakAdv.postavy;

import com.ZakAdv.hra.Hra;
import com.ZakAdv.vec.Vec;

/**
 * Class WyzimskaStraz - popisuje chovani wyzimske stráže
 *
 * @author Erich Pross
 * @version 2021/2022
 */
public class WyzimskaStraz extends Postava {

    private Hra hra;

    public WyzimskaStraz(Hra hra, String jmeno, String herniJmeno, Vec coChce, Vec coMa, String diaPred, String diaPo, String diaNechce, String diaChce, String diaAxii) {
        super(jmeno, herniJmeno, coChce, coMa, diaPred, diaPo, diaNechce, diaChce, diaAxii);
        ;
        this.hra = hra;
    }

    /**
     * Promluvení s wyzimskou stráží - vrací konec hry
     *
     * @return konec hry
     */
    @Override
    public String mluvit() {
        return hra.getHerniPlan().ukoncitHru();
    }

    /**
     * Axii na wyzimskou stráž - vypne hru
     */
    @Override
    public String axii() {
        hra.setKonecHry(true);
        return super.axii();
    }
}
