package com.ZakAdv.inventar;

import com.ZakAdv.hra.Hra;
import com.ZakAdv.vec.Vec;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Testuje: vkládání věcí, kapacitu inventáře, přičítání a odečítání peněz
 * @author: Erich Pross
 * @version 2021/2022
 */
public class InventarTest {

    Inventar inventar = new Inventar();
    Vec vlozitelna = new Vec("vlozitelna", "vlozitelna", true);
    Vec vlozitelna1 = new Vec("vlozitelna1", "vlozitelna1", true);
    Vec vlozitelna2 = new Vec("vlozitelna2", "vlozitelna2", true);
    Vec vlozitelna3 = new Vec("vlozitelna3", "vlozitelna3", true);
    Vec vlozitelna4 = new Vec("vlozitelna4", "vlozitelna4", true);
    Vec vlozitelna5 = new Vec("vlozitelna5", "vlozitelna5", true);
    Vec vlozitelna6 = new Vec("vlozitelna5", "vlozitelna6", true);

    @Before
    public void setUp() {
        inventar.pricistPenize(5);
        inventar.vlozVec(new Vec("test", "test", true));
        inventar.vlozVec(new Vec("test2", "test2", true));
        inventar.odeberVec("test2");

    }

    /**
     * Ověřuje kapacitu inventáře (max 6 předmětů, 0-5)
     */
    @Test
    public void vlozVec() {
        assertEquals(vlozitelna, inventar.vlozVec(vlozitelna));
        assertEquals(vlozitelna1, inventar.vlozVec(vlozitelna1));
        assertEquals(vlozitelna2, inventar.vlozVec(vlozitelna2));
        assertEquals(vlozitelna3, inventar.vlozVec(vlozitelna3));
        assertEquals(vlozitelna4, inventar.vlozVec(vlozitelna4));
        assertEquals(vlozitelna5, inventar.vlozVec(vlozitelna5));
        assertEquals(null, inventar.vlozVec(vlozitelna6));
    }

    /**
     * Testuje přičtení peněz
     */
    @Test
    public void getPenize1() {
        assertEquals(55, inventar.getPenize());
    }

    /**
     * Testuje přičtení peněz
     */
    @Test
    public void getPenize2() {
        assertNotEquals(10, inventar.getPenize());
    }

    /**
     * Testuje, zda byly odečteny peníze.
     */
    @Test
    public void odecistPenize() {
        assertEquals(true, inventar.odecistPenize(10));
        assertEquals(false, inventar.odecistPenize(100));
    }

    /**
     * Testuje, zda byl předmět vložen do inventáře
     */
    @Test
    public void obsahujeVec() {
        assertEquals(true, inventar.obsahujeVec("test"));
        assertEquals(false, inventar.obsahujeVec("neobsahuje"));
    }

    /**
     * Testuje, zda byl předmět odebrán z inventáře
     */
    @Test
    public void obsahujeVec2() {
        assertEquals(false, inventar.obsahujeVec("test2"));
    }
}