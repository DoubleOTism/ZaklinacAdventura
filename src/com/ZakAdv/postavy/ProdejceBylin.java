package com.ZakAdv.postavy;

import com.ZakAdv.inventar.Inventar;
import com.ZakAdv.vec.Vec;

import java.util.Map;

/**
 * Class ProdejceBylin - popis chování prodejce bylin
 * @author Erich Pross
 *    @version 2021/2022
 */
public class ProdejceBylin extends Postava {

    private Inventar inventar;

    public ProdejceBylin(Inventar inventar, String jmeno, String herniJmeno, Vec coChce, Vec coMa, String diaPred, String diaPo, String diaNechce, String diaChce, String diaAxii) {
        super(jmeno, herniJmeno, coChce, coMa, diaPred, diaPo, diaNechce, diaChce, diaAxii);
        this.inventar = inventar;
    }


    /**
     * da bylinkáři co chce a ta nám následně dá peníze
     */
    public Map<String, Vec> vymenitVec(Vec vec) {

        inventar.pricistPenize(vec.getCena());
        return super.vymenitVec(vec);
    }
}