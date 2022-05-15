package com.ZakAdv.postavy;

import com.ZakAdv.hra.HerniPlan;
import com.ZakAdv.vec.Vec;


/**
 * Class MrtvolaZlodeje - popisuje chovani Mrtvoly zloděje v postranní uličce
 *
 * @author Erich Pross
 * @version 2021/2022
 */

public class MrtvolaZlodeje extends Postava {

    private Boolean prohledan = false;
    private HerniPlan plan;

    public MrtvolaZlodeje(HerniPlan plan, String jmeno, String herniJmeno, Vec coChce, Vec coMa, String diaPred, String diaPo, String diaNechce, String diaChce, String diaAxii) {
        super(jmeno, herniJmeno, coChce, coMa, diaPred, diaPo, diaNechce, diaChce, diaAxii);
        this.plan = plan;
    }


    /**
     * metoda slouzici pro prohledání mrtvoly, dává hráči do invetáře zásilku pro bylinkáře, runu Heliotrop a 40 orénů
     *
     * @return text, jestli se povedla interakce
     */

    @Override
    public String prohledat() {
        if (!prohledan) {
            plan.getInventar().pricistPenize(40);
            plan.getInventar().vlozVec(new Vec("zasilka", "zásilka", true));
            plan.getInventar().vlozVec(new Vec("runa_heliotrop", "runa_heliotrop", true));
            prohledan = true;
            return "Prohledal jsi brašnu zloděje, uvnitř byla zásilka, runa_helitrop a 40 orénů";
        } else {
            return "Zloděj u sebe již nic nemá.";
        }
    }
}