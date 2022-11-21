package com.ZakAdv.prikazy;

import com.ZakAdv.gui.PanelVeci;
import com.ZakAdv.hra.HerniPlan;
import com.ZakAdv.main.AdventuraZaklad;
import com.ZakAdv.vec.Vec;
import javafx.scene.layout.Pane;

/**
 * Class PrikazSeber - dedi ze tridy Prikaz
 *
 * @author Erich Pross
 * @version 2021/2022
 */
public class PrikazSeber extends Prikaz {


    public PrikazSeber(HerniPlan plan) {
        super("seber", plan);
    }

    /**
     * sebere predmet a vlozi ho do inventare
     *
     * @param parametry počet parametrů závisí na konkrétním příkazu
     */
    @Override
    public String provedPrikaz(String... parametry) {

        if (parametry.length == 0) {
            // pokud chybí druhé slovo, tak ....
            return "Co mam sebrat?";
        } else if (parametry.length == 1 && this.getHerniPlan().getAktualniProstor().obsahujeVec(parametry[0])) {
            // pokud je druhe slovo takové, které lze vložit do brašny
            Vec vec = this.getHerniPlan().getAktualniProstor().vratVec(parametry[0]);
            if (vec.getLzeVzit()) {

                this.getHerniPlan().getInventar().vlozVec(this.getHerniPlan().getAktualniProstor().odeberVec(parametry[0]));
                return "Vložil jsi: " + vec.getScreenNazev() + " do brašny.";
            } else {
                return "To nemůžu jen tak vzít, jedině že by mi to někdo prodal a nebo věnoval,";
            }

        }

        return "To zde není.";
    }


}