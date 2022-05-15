package com.ZakAdv.postavy;

import com.ZakAdv.hra.HerniPlan;
import com.ZakAdv.vec.Vec;

import java.util.Map;


/**
 * Class Vesemir - popisuje chovani Vesemira
 *
 * @author Erich Pross
 * @version 2021/2022
 */

public class Vesemir extends Postava {
    private HerniPlan plan;


    public Vesemir(HerniPlan plan, String jmeno, String herniJmeno, Vec coChce, Vec coMa, String diaPred, String diaPo, String diaNechce, String diaChce, String diaAxii) {
        super(jmeno, herniJmeno, coChce, coMa, diaPred, diaPo, diaNechce, diaChce, diaAxii);
        this.plan = plan;
    }

    /**
     * nastavuje Vesemira jako spolecnika
     */
    @Override
    public Map<String, Vec> vymenitVec(Vec vec) {

        if(plan.getSpolecnik()==null) {
            this.plan.setSpolecnik(this);
            this.plan.getAktualniProstor().odeberPostavu(this.getJmeno());
        }
        else {
            this.setDiaPo("Díky, Geralte. Až si vše zařídíš, připojím se k tobě, já glejt mám. Kalkstein by ti ho mohl také zařídit");
        }
        return super.vymenitVec(vec);
    }
}