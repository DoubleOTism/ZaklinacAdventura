package com.ZakAdv.postavy;

import com.ZakAdv.hra.Hra;
import com.ZakAdv.vec.Vec;

/**
 * Class Straz - popisuje chovani stráže
 *
 * @author Erich Pross
 * @version 2021/2022
 */
public class Straz extends Postava {

    private Hra hra;

    public Straz(Hra hra, String jmeno, String herniJmeno, Vec coChce, Vec coMa, String diaPred, String diaPo, String diaNechce, String diaChce, String diaAxii) {
        super(jmeno, herniJmeno, coChce, coMa, diaPred, diaPo, diaNechce, diaChce, diaAxii);
        ;
        this.hra = hra;
    }

    /**
     * pokud použijeme Axii na stráž, hra končí
     *
     * @return text konec hry
     */
    @Override
    public String axii() {
        hra.setKonecHry(true);
        return super.axii();
    }

}