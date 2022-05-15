package com.ZakAdv.postavy;

import com.ZakAdv.inventar.Inventar;
import com.ZakAdv.vec.Vec;

import java.util.Map;


/**
 * Class Hospodsky - popisuje chovani hospodského
 *
 * @author Erich Pross
 * @version 2021/2022
 */

public class Hospodsky extends Postava {

    private final Inventar inventar;

    public Hospodsky(Inventar inventar, String jmeno, String screenJmeno, Vec coChce, Vec coMa, String mluvPred, String mluvPo, String recNechce, String recChce, String diaAxii) {
        super(jmeno, screenJmeno, coChce, coMa, mluvPred, mluvPo, recNechce, recChce, diaAxii);
        this.inventar = inventar;
    }

    /**
     * da hospodskému co chce a ten následně dá hráči penize
     */
    public Map<String, Vec> vymenitVec(Vec vec) {

        inventar.pricistPenize(vec.getCena());
        return super.vymenitVec(vec);
    }

}
