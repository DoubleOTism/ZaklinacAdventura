package com.ZakAdv.postavy;

import com.ZakAdv.vec.Vec;
import com.ZakAdv.zakladniInterfaces.InterPostava;

import java.util.HashMap;
import java.util.Map;

/**
 * Class Postava - popisuje obecné chování postav
 *
 * @author Erich Pross
 * @version 2021/2022
 */
public class Postava implements InterPostava {
    private String jmeno;
    private String herniJmeno;
    private Vec coChce;
    private Vec coMa;
    private String diaPred;
    private String diaPo;
    private String diaNechce;
    private String diaChce;
    private String diaAxii;
    private Boolean jizMluvil = false;
    private Boolean probehlaVymena = false;


    public Postava(String jmeno, String herniJmeno, Vec coChce, Vec coMa, String diaPred, String diaPo, String diaNechce, String diaChce, String diaAxii) {
        this.jmeno = jmeno;
        this.coChce = coChce;
        this.coMa = coMa;
        this.diaPred = diaPred;
        this.diaPo = diaPo;
        this.diaChce = diaChce;
        this.diaNechce = diaNechce;
        this.diaAxii = diaAxii;
        this.herniJmeno = herniJmeno;
    }

    /**
     * urcuje, co postava rekne v dialogu
     *
     * @return text, co postava rika
     */
    public String mluvit() {
        jizMluvil = true;
        if (!probehlaVymena) {
            return makeFirstLetterCapital(herniJmeno) + ": " + diaPred;
        } else {
            return makeFirstLetterCapital(herniJmeno) + ": " + diaPo;
        }
    }

    /**
     * metoda, která vrátí odpoveď při podání správného předmětu při výměně
     *
     * @return text kladne odpovedi
     */
    public String chceOdpoved() {
        return makeFirstLetterCapital(herniJmeno) + ": " + diaChce;
    }

    /**
     * metoda, která vrací odpověď při podání špatného předmětu při výměně
     *
     * @return text zaporne odpovedi
     */
    public String nechceOdpoved() {
        return makeFirstLetterCapital(herniJmeno) + ": " + diaNechce;
    }

    /**
     * metoda, která vymění předmět hráče za předmět postavy
     *
     * @param vec vec, kterou postava chce
     * @return list předmětů, které budeme následně přidávat/odebírat do/z inventáře.
     */
    public Map<String, Vec> vymenitVec(Vec vec) {
        probehlaVymena = true;
        Map<String, Vec> veci = new HashMap<>();
        veci.put("odeber", vec);
        veci.put("pridej", coMa);
        coChce = null;
        Vec puvVec = coMa;
        coMa = vec;
        return veci;
    }

    /**
     * vrátí reakci postavy, pokud se ji pokusime prohledat
     */
    public String prohledat() {
        return makeFirstLetterCapital(herniJmeno) + " nemůže být prohledán, všiml by si tě!";
    }

    /**
     * vrátí String s velkým prvnim pismenem
     *
     * @param text text, který chceme zpracovat
     */
    private String makeFirstLetterCapital(String text) {
        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }

    /**
     * vrati String s reakci na axii
     * @return reakce na axii
     */
    public String axii() {
        return makeFirstLetterCapital(herniJmeno) + ": " + diaAxii;
    }

    /**
     * zjistí predmet, ktery postava má
     * @return věc, co má
     */
    public Vec getCoMa() {
        return coMa;
    }

    /**
     * nastaví, jaký předmět postava chce.
     *
     * @param coMa vec kterou chceme, aby postava mela
     */
    public void setCoMa(Vec coMa) {
        this.coMa = coMa;
    }

    /**
     * vrati text, který postava říká před výměnou
     * @return dialog pred vymenou
     */
    public String getDiaPred() {
        return diaPred;
    }

    /**
     * vrátí text postavy po výměně
     * @return dialog po vymene
     */
    public String getDiaPo() {
        return diaPo;
    }

    /**
     * nastaví, co bude postava říkat po výměně
     */
    public void setDiaPo(String diaPo) {
        this.diaPo = diaPo;
    }

    /**
     * vrátí text postavy, když předmět nechce
     * @return dialog odmitnuti vymeny
     */
    public String getDiaNechce() {
        return diaNechce;
    }

    /**
     * vrátí text postavy, když postava předmět chce
     * @return dialog potvrzeni vymeny
     */
    public String getDiaChce() {
        return diaChce;
    }

    /**
     * nastaví, co postava řekne, pokud předmět chce
     *
     * @param diaChce text, co má postava říct
     */
    public void setDiaChce(String diaChce) {
        this.diaChce = diaChce;
    }

    /**
     * zjišťuje, zda proběhla výměna předmětů
     * @return true pokud proběhla, false pokud ne
     */
    public Boolean getProbehlaVymena() {
        return probehlaVymena;
    }

    /**
     * nastavi, zda již proběhla výměna, nebo nikoli
     */
    public void setProbehlaVymena(Boolean probehlaVymena) {
        this.probehlaVymena = probehlaVymena;
    }

    /**
     * zjistí jméno postavy
     * @return jmeno postavy
     */
    public String getJmeno() {
        return jmeno;
    }

    /**
     * zjisti předmět, co postava chce.
     * @return predmet, co postava chce
     */
    public Vec getCoChce() {
        return coChce;
    }

    /**
     * nastaví, jaký předmět postava chce
     *
     * @param coChce předmět, který postava chce
     */
    public void setCoChce(Vec coChce) {
        this.coChce = coChce;
    }

    /**
     * zjišťuje, za již postava mluvila
     * @return true pokud ano, false pokud ne
     */
    public Boolean getJizMluvil() {
        return jizMluvil;
    }

    /**
     * zjistí, co postava řekne, když se na ní použije znamení Axii
     * @return text při použití axii na postavu
     */
    public String getDiaAxii() {
        return diaAxii;
    }

    /**
     * zjisti herníJméno
     * @return herní jméno
     */
    public String getHerniJmeno() {
        return herniJmeno;
    }

}