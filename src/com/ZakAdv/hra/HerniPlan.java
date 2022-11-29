package com.ZakAdv.hra;

import com.ZakAdv.inventar.Inventar;
import com.ZakAdv.observer.Observer;
import com.ZakAdv.observer.SubjectOfChange;
import com.ZakAdv.postavy.*;
import com.ZakAdv.vec.Vec;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Class HerniPlan - třída představující mapu a stav adventury.
 *
 * Tato třída inicializuje prvky ze kterých se hra skládá:
 * vytváří všechny prostory,
 * propojuje je vzájemně pomocí východů
 * a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 * @author Erich Pross
 * @version 2021/2022
 */
public class HerniPlan implements SubjectOfChange {
    private final Hra hra;
    private final Inventar inventar = new Inventar();
    private final Map<String, Prostor> prostory = new HashMap<>();
    private final Map<String, Postava> postavy = new HashMap<>();
    private Prostor aktualniProstor;
    private Postava spolecnik;

    private Set<Observer> seznamPozorovatelu = new HashSet<>();

    /**
     * zakládá hru
     * @param hra
     */
    public HerniPlan(Hra hra) {
        this.hra = hra;
        zalozHru();

    }

    /**
     * Vytváří jednotlivé prostory
     */
    public void zalozHru() {
        Prostor chlupatyMedved = new Prostor("hospoda", "hospoda", "v jedné z nejlepších hospod v Chudinské čtvrti Wyzimy, v hospodě u Chlupatého Medvěda", 453.0, 140.0);
        Prostor predHospodou = new Prostor("pred_hospodou", "před_hospodou", "ve špinavé, ale klidné uličce před hospodou", 415.0, 155.0);
        Prostor ulicePredBranou = new Prostor("ulice_pred_branou", "ulice_před_branou", " ve dlouhé ulici táhnoucí se až k branám do Obchodní čtvrti Wyzimy", 183.0, 188.0);
        Prostor brana = new Prostor("brana", "brána", "před mohutnou okovanou bránou do Obchodní čtvrti Wyzimy, je hlídaná stráží.", 163.0, 250.0);
        Prostor temnaUlicka = new Prostor("temna_ulicka", "temna_ulička", "v temné uličce, kam by obyčejný člověk jen tak nešel.", 435.0, 100.0);
        Prostor namesti = new Prostor("namesti", "náměstí", "na menším náměstí, nyní zde moc lidí není.", 307.0, 152.0);
        Prostor dumBylinkare = new Prostor("stanek_bylinkare", "stánek_bylinkáře", "u menšího stánku, je kolem něj cítit krásná vůně bylin", 330.0, 230.0);
        Prostor ulickaZasilka = new Prostor("postranni_ulicka", "postranní_ulička", "v zakrvácené uličce, o zeď je opřený mrtvý zloděj, svírající svoji tašku.", 280.0, 270.0);
        Prostor dumKalkstein = new Prostor ("dum_kalksteina", "dum_kalksteina", "v domě alchymisty a dobrého přítele kalksteina", 270.0, 85.0);
        Prostor dumSavolla = new Prostor ("dum_savolly", "dum_savolly", "v domě překupníka Savolly", 400.0, 230.0);


        /**
         * Nastavuje spojení mezi prostory
         */
        chlupatyMedved.setVychod(predHospodou);
        predHospodou.setVychod(temnaUlicka);
        predHospodou.setVychod(namesti);
        predHospodou.setVychod(chlupatyMedved);
        temnaUlicka.setVychod(predHospodou);
        namesti.setVychod(predHospodou);
        namesti.setVychod(ulicePredBranou);
        namesti.setVychod(dumBylinkare);
        dumBylinkare.setVychod(namesti);
        dumBylinkare.setVychod(ulickaZasilka);
        ulickaZasilka.setVychod(dumBylinkare);
        ulicePredBranou.setVychod(namesti);
        ulicePredBranou.setVychod(brana);
        brana.setVychod(ulicePredBranou);
        dumSavolla.setVychod(namesti);
        dumKalkstein.setVychod(namesti);
        namesti.setVychod(dumSavolla);
        namesti.setVychod(dumKalkstein);


        /**
         * Definuje věci, které se ve hře vyskytují
         */
        Vec bilyracek = new Vec("bily_racek", "bily_racek", true);
        Vec fisstech = new Vec("krabicka_s_fisstechem", "krabicka_s_fisstechem", true);
        Vec pasta = new Vec("magicka_pasta", "magicka_pasta", false);
        Vec glejt = new Vec("glejt", "glejt", false);
        Vec stary_glyf = new Vec("stary_glyf", "stary_glyf", true);
        Vec heliotrop = new Vec("runa_heliotrop", "runa_heliotrop", true);
        Vec zasilka = new Vec("zasilka", "zasilka", true);
        Vec korbel_piva = new Vec("rozlité_korbely_piva","rozlite_korbely_piva", false);
        Vec popelnice = new Vec("popelnice", "popelnice", false);
        pasta.setCena(500);
        heliotrop.setCena(250);
        stary_glyf.setCena(60);
        fisstech.setCena(50);
        bilyracek.setCena(45);
        zasilka.setCena(100);
        temnaUlicka.vlozVec(fisstech);
        chlupatyMedved.vlozVec(korbel_piva);
        ulickaZasilka.vlozVec(popelnice);
        ulicePredBranou.vlozVec(stary_glyf);
        dumSavolla.vlozVec(pasta);



        /**
         * Interakce s dealerem fisstechu
         */
        ProdavacFisstechu prodavacFisstechu = new ProdavacFisstechu("dealer_fisstechu", "dealer_fisstechu",fisstech, null, "Hej, ty, co chceš? \n" + "Geralt: To jsem si mohl myslet, dealer fisstechu. Lidé, jako jsi ty, jsou ten důvod, proč nosím dva meče \n" + "Dealer_fisstechu: Ale notak, máš přeci lidem pomáhat, ne? \n Dealer_fisstecgu: Pomoz mi - ztratil jsem někde po městě krabičku fisstechu, když mi jí přineseš, dostaneš 100 orénů. \n Geralt: Kdybych ty peníze nepotřeboval... no dobrá, uvidím co zmůžu.","Děkuji mockrát, dobrodinče","Kdybys nebyl zaklínač, tak bych tě bral za blázna","To si s radostí vezmu, zde je tvých 100 orénů","Proč se mi točí celej svět?");

        /**
         * Interakce s Kalksteinem
         */
        Kalkstein kalkstein = new Kalkstein("kalkstein", "Kalkstein", new Vec("magicka_pasta","magicka_pasta", true), glejt, "Nigredo? Ano, tím to bude! Ah, Geralte, co bys rád?\n" + "Geralt: Zdravím Kalksteine, nemáš nápad jak se dostat do Wyzimy? \n" + "Kalkstein: Samozřejmě, mám zde tento glejt, který zaručuje bezpečný průchod kolem stráží. \n" + "Geralt: Hádám, že to nebude zadarmo. Co bys za něj chtěl? \n" + "Kalkstein: Potřeboval bych jednu dávku magické pasty na svůj nejnovější experiment, kámen živlů se sám nevytvoří. \n" + "Geralt: Dobře tedy, přinesu ti magickou pastu a dostanu glejt, to je dobrý obchod.\n" + "Geralt: Máš nápad, kde tu pastu seženu? \n Kalkstein: Zkusil bych Savollu, ten má všechno, ale vždyť vy se vlastně znáte, tak co tu povídám... \n Geralt: No právě, uvidím co s ním zmůžu", "Díky za pomoc, Geralte, hodní štěstí na stezce.", "Geralte, tohle není správná chvíle na vtipy.", "Výborně, Geralte, to je přesně to, co potřebuji! \n" + "*Kalkstein vytahuje z koženého tubusu glejt*\n" + "Kalkstein: Díky za pomoc s experimenty zaklínači, zde máš glejt, doufám že se bude hodit. \n" +"Nyní máš glejt. Jdi k bráně a promluv si se stráží", "Přestaň s tím, nebo zavolám stráže!");


        /**
         * Interakce s hospodským
         */
        Hospodsky hospodsky = new Hospodsky(this.getInventar(), "hospodsky", "hospodsky", stary_glyf, bilyracek, "Zdravím poutníku, co bys rád? \n" + "Geralt: Zdravím hospodský, potřeboval bych silný alkohol, na zlepšení večera \n" + "Hospodský: Samozřejmě, procházel zde nějaký zaklínač, Berengar myslím, nechal zde tuto flašku silného alkoholu bily_racek \n" + "Hospodský: Pokud bys chtěl, můžeš mi najít stary_glyf, dám ti za něj alkohol a 60 orénů \n" + "Hospodsky: Jo a taky jsem slyšel o osobě, která hledá nějaký předmět, pohybuje v uličkách kolem hospody. \n" + "Geralt: Děkuji, zatím.", "Myslím, že jsi toho dneska měl už dost, celá flaška je dost i pro zaklínače. \n", "Tohle nechci.", "Děkuji, to se mi bude hodit do sbírky, tady máš 60 orénů a alkohol.", "Slyšel jsem, že pro průchod bránou budeš potřebovat Kalksteina.");

        /**
         * Interakce s Vesemirem
         */
        Vesemir vesemir = new Vesemir(this,"vesemir", "vesemir", bilyracek, null, "Geralte, co děláš ve Wyzimě?. \n" + "Geralt: Zdravím Vesemire, spíše co ty tady děláš? myslel jsem, že budeš v Redanii. \n" + "Vesemir: Ah, Geralte, v Redanii jsem zabil Baziliška, tak jsem se šel před zimou podívat do Wyzimy, nemohl bys mi pomoci? \n" + "Geralt: Povídej. \n" + "Vesemir: Potřeboval bych silný alchemistický základ na výrobu elixírů, nějaký dobře refinovaný alkohol. \n" + "Geralt: Hmm, to by chtělo Bílého racka, zkusím ti ho sehnat. \n" + "Vesemir: Díky, teď ti za něj nic dát nemohu, ale až zde vše doděláš, tak můžeme spolu na Kaer Morhen \n" + "Geralt: Domluveno, nechci takhle na zimu cestovat sám. zkusím ho sehnat. \n" + "Geralt: Sbohem.", "Vesemir: Ještě jednou díky, hodně štěstí na Stezce.", "To není ten správný základ, na Kaer Morhen tě budu muset naučit jak se dělají elixíry.", "Vesemir: To je ono Geralte, díky. Tak pojďme, jdu s tebou.", "To už nedělej, Axii udělá v hlavě člověka paseku.");

        /**
         * Interakce s pochybným jedincem
         */
        PochybnyJedinec pochybnyJedinec = new PochybnyJedinec(this.getInventar(), "pochybny_jedinec", "pochybny_jedinec", heliotrop, null, "C-c-co chcete, b-bo-bojovníku? \n" + "Geralt: Neboj se, slyšel jsem že hledáš runu Heliotrop. Je to tak? \n" + "Pochybný jedinec: A-a-ano, je to tak, nabízím za ní 250 orénů. Až jí budeš mít, přijď za mnou, rád jí koupím. \n Pochybný jedinec: Zdejší trpasličí kovář jí potřebuje na výrobu magické zbroje. \n" + "Geralt: Dobře, poohlédnu se po ní. \n" + "Geralt: Sbohem", "Děkuji zaklínači, nyní už raději jdi, ať nás nikdo nevidí.", "Jsem seriózní obchodník, budu dělat jakoby se nic nestalo.", "Děkuji, zde je 250 orénů, nyní už musím jít..", "Slyšel jsem, že Savolla prodává magickou pastu.");

        /**
         * Interakce se stráží
         */
        Straz straz = new Straz(this.hra, "straz", "straz", null, null, "Nech mě na pokoji.", null, "Ještě chvíli to vydržím, pak tě zavřu do žaláře.", null, "Na mě ty tvoje čarovnický triky nefungujou, mám dimeritovou helmu... \n" + "Pokusil jsi se omámit stráž. Byl jsi zavřen do žaláře a svůj úkol jsi nesplnil.");

        /**
         * Interakce se Savollou
         */

        Savolla savolla = new Savolla(this.hra ,this.getInventar(), "savolla", "Savolla", null, new Vec("magicka_pasta", "magicka_pasta", true), "Zaklínači, co ty zde děláš? \n" + "Geralt: Jmenuji se... \n" + "Savolla: Vím kdo jsi, Bílý Vlku, zná tě každý. Nechme toho představování a pojďme řešit obchodní záležitosti.\n" + "Savolla: Prodávám magické zboží z černého trhu, nemáš zájem? \n" + "Geralt: Potřeboval bych magickou pastu, nemáš jí na prodej? \n" + "Savolla: Čirou náhodou mám, vědmáku. Pro tebe za 500 orénů", "Nemáš zač, Geralte, obchody s tebou jsou vždy radost. \n" + "Nyní vlastníš magicka_pasta. Vrať se za Kalksteinem pro glejt.", "To nepůjde, zaklínači.", null, "A dost, tady žádný kouzelnický triky zkoušet nebudeš. Nic ti neprodám, zmiz.");

        /**
         * Interakce s prodavačem bylin
         */

        ProdejceBylin prodejceBylin = new ProdejceBylin(this.getInventar(), "bylinkar", "bylinkar", zasilka, null, "Kdo jste? neubližujte mi! \n" + "Geralt: Uklidněte se. Jmenuji se Geralt z Rivie. Máte nějaké potíže? \n" + "Bylinkar: Ano, někdo mi před chvilkou ukradl zásilku bylin. Popral jsem se s ním a pak bodnul nožem. \n Bylinkar: Utekl pak do uličky vedle, ale daleko dle mého neutekl.\n" + "Geralt: Dobře, podívám se po něm. Zůstaňte tady. \n" + "Bylinkar: Dobrá, počkám, ale pospěšte. Mám tady z toho v noci hrůzu.", "Díky za pomoc, cizinče.", null, "Aaaa, můj balíček, ale kde je ten zloděj? \n" + "Geralt: V té uličce, je mrtvý. Rána zasáhla plíci \n" + "Bylinkar: Bože, tomu se dalo předejít. \n" + "Geralt: To už nezměníte, neměl se vás pokoušet okrást. \n" + "Bylinkar: Máte pravdu, cizinče. Zde je 100 orénů za snahu.", "Ahh, moje hlava, to bude nějaká kletba.");

        /**
         * Mrtvola zloděje, bráno jako postava, se kterou lze provádět interakci prohledat a promluvit (bráno jako zjištění stavu)
         */
        MrtvolaZlodeje mrtvolaZlodeje = new MrtvolaZlodeje(this, "mrtvola_zlodeje", "mrtvola_zlodeje", null, zasilka, "*tělo je ještě teplé* \n" + "Geralt: Hmm, daleko nedošel, podívám se jestli nemá tu ukradenou zásilku u sebe. \n" + "Prohledej mrtvolu pomocí příkazu prohledat", null, null, null, "*nic* \n" + "Geralt: Hmm, nepředstírá to, to jsem si mohl myslet \n" + "Geralt: Musím ho prohledat");

        /**
         * Wyzimska stráž, postava, která ukončuje hru.
         */

        WyzimskaStraz wyzimskaStraz = new WyzimskaStraz(hra, "wyzimska_straz", "wyzimska_straz", null, null, null, null, "Kliď se mi z očí, mutante.", null, "Tak a dost!");


        /**
         * Vkládá postavy do daných prostorů
         */
        dumKalkstein.vlozPostavu(kalkstein);
        predHospodou.vlozPostavu(vesemir);
        ulicePredBranou.vlozPostavu(straz);
        temnaUlicka.vlozPostavu(pochybnyJedinec);
        brana.vlozPostavu(wyzimskaStraz);
        chlupatyMedved.vlozPostavu(hospodsky);
        ulickaZasilka.vlozPostavu(mrtvolaZlodeje);
        dumBylinkare.vlozPostavu(prodejceBylin);
        namesti.vlozPostavu(prodavacFisstechu);
        dumSavolla.vlozPostavu(savolla);
        chlupatyMedved.vlozPostavu(straz);

        /**
         * Nastavuje hospodu jako výchozí prostor
         */
        aktualniProstor = chlupatyMedved;
    }

    /**
     * Ukončí hru a zadá konec podle toho, jak si hráč vedl.
     *
     * @return text konce
     */
    public String ukoncitHru() {
        hra.setKonecHry(true);
        if(inventar.obsahujeVec("glejt")) {
            if(spolecnik==null) {
                return "Dostal jsi se ke králi Foltestovi a varoval ho před útokem. \n"+ "Odměnil se ti plným měšcem. Vycestoval jsi zpět do Kaer Morhen. \n" + "Gratuluji, máš 1. z 4 konců";
            }
            else if(spolecnik.getJmeno()=="vesemir") {
                return "Společně s Vesemirem jsi se dostal do hradu a varoval krále Foltesta \n" +
                        "Byli jste patřičně odměněni a získali tak možnost obnovit vaší zaklínačskou školu \n" +
                        "Oba dva jste se pak vydali na společnou cestu do Kaer Morhen \n" +
                        "Gratuluji, máš 2. z 4 konců";
                        }
        }
        else {
            if(spolecnik==null) {
                return  "Stráže tě zajali za pokus o průchod bránou bez glejtu. \n" +"Nikdo krále nevaroval a byl tak zabit. Máš 3. z 4 konců. \n";
            }
                else if(spolecnik.getJmeno()=="vesemir") {
                    return "Vesemir se dostal do hradu a varoval krále Foltesta \n" +
                            "Druhý den jsi se za brány dostal a společně s Vesemire jsi odcestoval do Kaer Morhen \n" +
                            "Gratuluji, máš 4. z 4 konců!";
            }
                }

        return null;



    }
    /**
     * metoda vraci jestli je Vesemir nastaven jako společník, pokud ne, tak vrací null
     * vraci jmeno spolecnika
     */
    public Postava getSpolecnik() {
        return spolecnik;
    }
    /**
     * metoda nastavi společníka
     */
    public void setSpolecnik(Postava spolecnik) {
        this.spolecnik = spolecnik;
    }

    /**
     * Metoda vrací odkaz na aktuální prostor, ve kterém se hráč právě nachází.
     * @return aktualni prostor
     */

    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }

    /**
     * Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     */
    public void setAktualniProstor(Prostor prostor) {
        aktualniProstor = prostor;
        notifyObservers();
    }

    Vec bilyracek1 = new Vec("bily_racek", "bily_racek", true);
    Vec fisstech1 = new Vec("krabicka_s_fisstechem", "krabicka_s_fisstechem", true);
    Vec pasta1 = new Vec("magicka_pasta", "magicka_pasta", false);
    Vec glejt1 = new Vec("glejt", "glejt", false);
    Vec stary_glyf1 = new Vec("stary_glyf", "stary_glyf", true);
    Vec heliotrop1 = new Vec("runa_heliotrop", "runa_heliotrop", true);
    Vec zasilka1 = new Vec("zasilka", "zasilka", true);
    public void IDKFA() {
        inventar.vlozVec(stary_glyf1);
        inventar.vlozVec(glejt1);
        inventar.vlozVec(bilyracek1);
        inventar.vlozVec(fisstech1);
        inventar.vlozVec(pasta1);
        inventar.vlozVec(heliotrop1);
        inventar.vlozVec(zasilka1);
    }

    /**
     * vrací všechny prostory
     * @return postavy
     */
    public Map<String, Prostor> getProstory() {
        return prostory;
    }

    /**
     * vrací všechny postavy
     * @return postavy
     */
    public Map<String, Postava> getPostavy() {
        return postavy;
    }

    /**
     * vrací inventář
     * @return inventar
     */
    public Inventar getInventar() {
        return this.inventar;
    }

    @Override
    public void register(Observer observer) {
        seznamPozorovatelu.add(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : seznamPozorovatelu) {
            observer.update();
        }
    }

    @Override
    public void unregister(Observer observer) {

    }
}