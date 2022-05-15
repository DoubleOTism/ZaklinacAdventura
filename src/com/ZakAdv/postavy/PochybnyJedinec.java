package com.ZakAdv.postavy;

import com.ZakAdv.inventar.Inventar;
import com.ZakAdv.vec.Vec;

import java.util.Map;

/**
 * Class PochybnyJedinec - popisuje chovani pochybného jedince za hospodou
 *
 * @author Erich Pross
 * @version 2021/2022
 */
public class PochybnyJedinec extends Postava {

    private Inventar inventar;

    public PochybnyJedinec(Inventar inventar, String jmeno, String herniJmeno, Vec coChce, Vec coMa, String diaPred, String diaPo, String diaNechce, String diaChce, String diaAxii) {
        super(jmeno, herniJmeno, coChce, coMa, diaPred, diaPo, diaNechce, diaChce, diaAxii);
        this.inventar = inventar;
    }

    /**
     * Dá pochybnému jedinci co chce a ten nasledně hráči dá peníze
     */
    public Map<String, Vec> vymenitVec(Vec vec) {

        inventar.pricistPenize(vec.getCena());
        return super.vymenitVec(vec);
    }

}
