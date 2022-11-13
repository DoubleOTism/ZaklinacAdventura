package com.ZakAdv.hra;

import com.ZakAdv.postavy.Postava;
import com.ZakAdv.vec.Vec;

import java.util.*;
import java.util.stream.Collectors;

/**
 *  Trida Prostor - popisuje jednotlivou místnost ve hře
 *
 *  Tato třída je součástí jednoduché textové hry.
 *
 *  "Prostor" reprezentuje jedno místo (místnost, prostor, ..) ve scénáři
 *  hry. Místnost může mít sousední místnosti připojené přes východy. Pro
 *  každý východ si místnost ukládá odkaz na sousedící místnost (instanci
 *  třídy Mistnost).
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, upravil Erich Pross
 *@version    3.1
 *@created    květen 2005/2021/2022
 */

public class Prostor {

    private String herniNazev;
    private String nazev;
    private String popis;
    private Set<Prostor> vychody;   // obsahuje sousední místnosti
    private Map<String, Vec> veci = new HashMap<>();
    private Map<String, Postava> postavy = new HashMap<>();
    private Double x;
    private Double y;

    /**
     * Vytvoření prostoru se zadaným popisem
     */
    public Prostor(String nazev, String screenNazev, String popis, Double x, Double y) {
        this.x = x;
        this.y = y;
        this.herniNazev = screenNazev;
        this.popis = popis;
        this.nazev = nazev;
        vychody = new HashSet<>();
    }

    /**
     * Definuje východ z prostoru
     */
    public void setVychod(Prostor vedlejsi) {
        vychody.add(vedlejsi);
    }

    /**
     * Vrací název prostoru
     * @return název a název zobrazený uživateli
     */
    public String getNazev() {
        return nazev;
    }

    public String getScreenNazev() {
        return herniNazev;
    }

    /**
     * Vrací "dlouhý" popis prostoru.
     * @return aktuální prostor, věci v prostoru a postavy
     */
    public String dlouhyPopis() {
        return "\nNacházíš se: " + popis + "\n" + popisVychodu() + "\n" + popisVeci() + "\n" + popisPostavy();
    }

    /**
     * Vrací textový řetězec, který popisuje napojené prostory
     * @return výpis východů z aktuálního prostoru
     */
    private String popisVychodu() {
        String vracenyText = "východy: ";
        for (Prostor sousedni : vychody) {
            vracenyText += sousedni.getScreenNazev() + ", ";
        }
        vracenyText = vracenyText.substring(0, vracenyText.length() - 2);
        return vracenyText;
    }

    /**
     * Vrací popis věcí v prostoru
     * @return vrací předměty v aktuální místnosti
     */
    public String popisVeci() {
        String vracenyText = "Vidíš: ";
        if (veci.size() > 0) {
            for (Map.Entry<String, Vec> vec : veci.entrySet()) {
                vracenyText += vec.getValue().getScreenNazev() + ", ";
            }
            return vracenyText.substring(0, vracenyText.length() - 2);
        } else {
            return vracenyText += "nic";
        }
    }

    /**
     * vrati popis postav v prostoru
     *
     * @return jména postav
     */
    public String popisPostavy() {
        String vracenyText = "Postavy: ";
        if (postavy.size() > 0) {
            for (Map.Entry<String, Postava> postava : postavy.entrySet()) {
                vracenyText += postava.getValue().getHerniJmeno().substring(0, 1).toUpperCase() + postava.getValue().getHerniJmeno().substring(1) + ", ";
            }
            return vracenyText.substring(0, vracenyText.length() - 2);
        } else {
            return vracenyText += "nikdo";
        }
    }

    /**
     * Vrací prostor, který sousedí s aktuálním prostorem a jehož název je zadán
     * jako parametr. Pokud prostor s udaným jménem nesousedí s aktuálním
     * prostorem, vrací se hodnota null.
     *
     * @param nazevSouseda Jméno sousedního prostoru (východu)
     * @return Prostor, který se nachází za příslušným východem, nebo hodnota
     * null, pokud prostor zadaného jména není sousedem.
     */
    public Prostor vratSousedniProstor(String nazevSouseda) {
        List<Prostor> hledaneProstory = vychody.stream().filter(sousedni -> sousedni.getNazev().equals(nazevSouseda)).collect(Collectors.toList());
        if (hledaneProstory.isEmpty()) {
            return null;
        } else {
            return hledaneProstory.get(0);
        }
    }

    /**
     * Vrací kolekci obsahující prostory, se kterými tento prostor sousedí.
     * Takto získaný seznam sousedních prostor nelze upravovat (přidávat,
     * odebírat východy) protože z hlediska správného návrhu je to plně
     * záležitostí třídy Prostor.
     *
     * @return Nemodifikovatelná kolekce prostorů (východů), se kterými tento
     * prostor sousedí.
     */
    public Collection<Prostor> getVychody() {
        return Collections.unmodifiableCollection(vychody);
    }

    /**
     * zjisti, jestli se v prostoru nachazi dana vec
     *
     * @param nazev vec, kterou chceme najit
     * @return true pokud se nachazi, false pokud se nenachayi
     */
    public boolean obsahujeVec(String nazev) {
        return veci.containsKey(nazev);
    }

    /**
     * zjisti, jestli se v prostoru nachazi dana postava
     *
     * @param nazev jmeno postavy, kterou chceme nalezt
     * @return text jmena postavy
     */
    public boolean obsahujePostavu(String nazev) {
        return postavy.containsKey(nazev);
    }

    /**
     * vrati postavu z prostoru
     *
     * @param nazev jmeno postavy, kterou chceme vratit
     * @return vracena postava
     */
    public Postava vratPostavu(String nazev) {
        return postavy.get(nazev);
    }

    /**
     * odebere postavu z prostoru
     *
     * @param nazev jmeno postavy, kterou chceme odebrat
     */
    public void odeberPostavu(String nazev) {
        postavy.remove(nazev);
    }

    /**
     * vrati vec z prostoru
     *
     * @param nazev nazev veci, kterou chceme vratit
     * @return vracena vec
     */
    public Vec vratVec(String nazev) {
        return veci.get(nazev);
    }

    /**
     * vlozi vec do prostoru
     *
     * @param vec nazev vec, kterou chceme do prostoru vlozit
     * @return vlozena vec
     */
    public Vec vlozVec(Vec vec) {
        veci.put(vec.getNazev(), vec);
        if (veci.containsKey(vec.getNazev())) {
            return vec;
        }
        return null;
    }

    /**
     * vlozi postavu do prostoru
     *
     * @param postava jmeno postavy, kterou chceme vlozit
     * @return postava, ktera se vlozila
     */
    public Postava vlozPostavu(Postava postava) {
        postavy.put(postava.getJmeno(), postava);
        if (veci.containsKey(postava.getJmeno())) {
            return postava;
        }
        return null;
    }

    /**
     * odebere vec z prostoru
     *
     * @param nazev nazev veci, kterou chceme odebrat
     * @return odebrana vec
     */
    public Vec odeberVec(String nazev) {
        return veci.remove(nazev);
    }

    public Double getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

}
