package com.ZakAdv;

import com.ZakAdv.hra.Hra;
import com.ZakAdv.main.AdventuraZaklad;
import com.ZakAdv.zakladniInterfaces.InterHra;
import com.ZakAdv.zakladniInterfaces.TextoveRozhrani;

public class ZaklinacAdv {


    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            AdventuraZaklad.launchGame();
        } else {
            if (args[0].equals("-text")) {
                InterHra hra = new Hra();
                TextoveRozhrani ui = new TextoveRozhrani(hra);
                ui.hraj();
            } else {
                System.out.println("Neznamy parametr");
            }
        }
    }
}