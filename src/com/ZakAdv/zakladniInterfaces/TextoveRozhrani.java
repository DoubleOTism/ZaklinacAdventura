package com.ZakAdv.zakladniInterfaces;

import java.text.Normalizer;
import java.util.Scanner;

/**
 * Class TextoveRozhrani
 *
 * Toto je uživatelského rozhraní aplikace Adventura
 * Tato třída vytváří instanci třídy Hra, která představuje logiku aplikace.
 * Čte vstup zadaný uživatelem a předává tento řetězec logice a vypisuje odpověď logiky na konzoli.
 *
 * @version    3.1
 * @created    květen 2005/2021/2022
 */

public class TextoveRozhrani {
    private final InterHra hra;

    /**
     * Vytváří hru.
     */
    public TextoveRozhrani(InterHra hra) {
        this.hra = hra;
    }

    /**
     * Hlavní metoda hry. Vypíše úvodní text a pak opakuje čtení a zpracování
     * příkazu od hráče do konce hry (dokud metoda konecHry() z logiky nevrátí
     * hodnotu true). Nakonec vypíše text epilogu.
     */
    public void hraj() {
        System.out.println(hra.vratUvitani());

        while (!hra.konecHry()) {
            String radek = prectiString();
            String test = stripAccents(radek.toLowerCase());
            System.out.println(hra.zpracujPrikaz(stripAccents(radek.toLowerCase())));
        }

        System.out.println(hra.vratEpilog());
    }

    /**
     * Metoda přečte příkaz z příkazového řádku
     */
    private String prectiString() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("> ");
        return scanner.nextLine();
    }

    private String stripAccents(String s) {
        s = Normalizer.normalize(s, Normalizer.Form.NFD);
        s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return s;
    }

}