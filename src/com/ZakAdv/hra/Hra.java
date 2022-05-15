package com.ZakAdv.hra;


import com.ZakAdv.prikazy.*;
import com.ZakAdv.zakladniInterfaces.InterHra;
import com.ZakAdv.zakladniInterfaces.InterPrikaz;

/**
 *  Class Hra - třída představující logiku adventury.
 *
 *  Toto je hlavní třída  logiky aplikace.  Tato třída inicializuje ostatní třídy:
 *  vytváří všechny místnosti (třída Mistnost), vytváří seznam platných příkazů.
 *  Též vyhodnocuje jednotlivé příkazy zadané uživatelem.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, upravil Erich Pross
 *@version    3.1
 *@created    květen 2005/2021/2022
 */

public class Hra implements InterHra {
    private final SeznamPrikazu platnePrikazy;    // obsahuje seznam přípustných příkazů
    private final HerniPlan herniPlan;
    private boolean konecHry = false;

    /**
     * Vytváří hru a inicializuje HerniPlan
     */
    public Hra() {
        herniPlan = new HerniPlan(this);
        platnePrikazy = new SeznamPrikazu();
        platnePrikazy.vlozPrikaz(new PrikazNapoveda(platnePrikazy));
        platnePrikazy.vlozPrikaz(new PrikazJdi(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazKonec(this));
        platnePrikazy.vlozPrikaz(new PrikazSeber(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazZobrazitInventar(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazMluvit(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazKoupit(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazDat(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazProhledat(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazAxii(herniPlan));
    }

    /**
     * Vrací uvítání pro hráče
     * @return uvítání hráče
     */
    public String vratUvitani() {
        return "Je rok 1270, nacházíš se v okolí Wyzimy, královského města Temerie.\n" + "Hraješ za Geralta z Rivie, zaklínače. Nyní jsi v Hospodě u Chlupatého Medvěda.\n" + "Dnes večer musíš podniknout důležitý krok - dostat se do hradu v centru Wyzimy.\n" + "V hradě v tuto chvíli přebývá král Foltest. Musíš ho varovat o plánovaném útoku na něj. \n"+ "---------------------------------------------------------------------------------------------- \n" + "Cílem hry je dostat se skrze brány do Wyzimy. \n" + "Aby tě strážný pustil skrze bránu, musíš vlastnit glejt. Tvým úkolem je ho získat.\n" + "Až vše budeš mít, promluv si s wyzimským strážným u brány.\n" + "Ve hře je možné získat i společníka. \n" + "Pokud nebudeš vědět, použij příkaz napověda \n" + "----------------------------------------------------------------------------------------------"+ herniPlan.getAktualniProstor().dlouhyPopis();
    }

    /**
     * Vrací závěrečnou zprávu.
     * @return závěrečná zpráva
     */
    public String vratEpilog() {
        return "Toto je konec hry! Děkuji za zahrání. Pokud nemáte všechny konce, doporučuji si zahrát znovu.";
    }

    /**
     * Vrací true, pokud hra skončila.
     */
    public boolean konecHry() {
        return konecHry;
    }

    /**
     * Zpracuje řetězec jako parametr, rozdělí ho na slovo příkazu a jeho parametry, poté otestuje jestli obsahuje klíčové slovo.
     * pokud ano, spustí příkaz.
     */
    public String zpracujPrikaz(String radek) {
        String[] slova = radek.split("[ \t]+");
        String slovoPrikazu = slova[0];
        String[] parametry = new String[slova.length - 1];
        for (int i = 0; i < parametry.length; i++) {
            parametry[i] = slova[i + 1];
        }
        String textKVypsani = " .... ";
        if (platnePrikazy.jePlatnyPrikaz(slovoPrikazu)) {
            InterPrikaz prikaz = platnePrikazy.vratPrikaz(slovoPrikazu);
            textKVypsani = prikaz.provedPrikaz(parametry);
        } else {
            textKVypsani = "Nevím co tím myslíš. Tento příkaz neznám. ";
        }
        return textKVypsani;
    }


    /**
     * Nastaví, že je konec hry, metodu využívá třída PrikazKonec,
     * false = konec hry, true = hra je spuštěná
     */
    public void setKonecHry(boolean konecHry) {
        this.konecHry = konecHry;
    }

    /**
     * Metoda vrátí odkaz na herní plán.
     * @return odkaz na herní plán
     */
    public HerniPlan getHerniPlan() {
        return herniPlan;
    }

}