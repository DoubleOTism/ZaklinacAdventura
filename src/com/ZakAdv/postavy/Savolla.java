package com.ZakAdv.postavy;

import com.ZakAdv.hra.Hra;
import com.ZakAdv.inventar.Inventar;
import com.ZakAdv.vec.Vec;

import java.util.Map;

/**
 * Class Savolla - popisuje chovani Savolly
 *
 * @author Erich Pross
 * @version 2021/2022
 */
public class Savolla extends Postava {

    private Inventar inventar;
    private Hra hra;

    public Savolla(Hra hra, Inventar inventar, String jmeno, String herniJmeno, Vec coChce, Vec coMa, String diaPred, String diaPo, String diaNechce, String diaChce, String diaAxii) {
        super(jmeno, herniJmeno, coChce, coMa, diaPred, diaPo, diaNechce, diaChce, diaAxii);
        ;
        this.hra = hra;
        this.inventar = inventar;
    }

    /**
     * da Savollovi co chce a ten nám následně dá peníze
     */
    public Map<String, Vec> vymenitVec(Vec vec) {

        inventar.pricistPenize(vec.getCena());
        return super.vymenitVec(vec);
    }
    /**
     * Použití Axii na Savollu ukončí hru
     *
     * @return konec hry
     */
    @Override
    public String axii() {
        hra.setKonecHry(true);
        return super.axii();
    }
}
