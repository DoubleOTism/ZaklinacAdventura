package com.ZakAdv.hra;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/*******************************************************************************
 * Třída HraTest funguje jako testovací třída, zkouší způsoby jak hru ukončit.
 * @author Erich Pross
 * @version 2021/2022
 */
public class HraTest {
    private Hra hra1;
    @Before
    public void setUp() {
        hra1 = new Hra();
    }
    @After
    public void tearDown() {
    }

    @Test
    public void testPrubehHryProhra() {
        hra1.zpracujPrikaz("jdi pred_hospodou");
        hra1.zpracujPrikaz("jdi namesti");
        hra1.zpracujPrikaz("jdi ulice_pred_branou");
        hra1.zpracujPrikaz("jdi brana");
        hra1.zpracujPrikaz("promluvit wyzimska_straz");


    }

    @Test
    public void testStrazPredBranouAxii() {
        hra1.zpracujPrikaz("jdi pred_hospodou");
        hra1.zpracujPrikaz("jdi namesti");
        hra1.zpracujPrikaz("jdi ulice_pred_branou");
        hra1.zpracujPrikaz("axii straz");
        assertEquals(true, hra1.konecHry());
    }



    @Test
    public void testHospodaAxii() {
        hra1.zpracujPrikaz("axii straz");
        assertEquals(true, hra1.konecHry());
    }

    @Test
    public void testWyzimskaStrazDialog() {
        hra1.zpracujPrikaz("jdi pred_hospodou");
        hra1.zpracujPrikaz("jdi namesti");
        hra1.zpracujPrikaz("jdi ulice_pred_branou");
        hra1.zpracujPrikaz("jdi brana");
        hra1.zpracujPrikaz("promluvit wyzimska_straz");
        assertEquals(true, hra1.konecHry());
    }

    @Test
    public void testWyzimskaStrazAxii() {
        hra1.zpracujPrikaz("jdi pred_hospodou");
        hra1.zpracujPrikaz("jdi namesti");
        hra1.zpracujPrikaz("jdi ulice_pred_branou");
        hra1.zpracujPrikaz("jdi brana");
        hra1.zpracujPrikaz("axii wyzimska_straz");
        assertEquals(true, hra1.konecHry());
    }
    @Test
    public void testSavollaEnding() {
        hra1.zpracujPrikaz("jdi pred_hospodou");
        hra1.zpracujPrikaz("jdi namesti");
        hra1.zpracujPrikaz("jdi dum_savolly");
        hra1.zpracujPrikaz("axii savolla");
        assertEquals(true, hra1.konecHry());
    }

    @Test
    public void testVyhra() {
        hra1.zpracujPrikaz("jdi pred_hospodou");
        hra1.zpracujPrikaz("jdi namesti");
        hra1.zpracujPrikaz("jdi stanek_bylinkare");
        hra1.zpracujPrikaz("jdi postranni_ulicka");
        hra1.zpracujPrikaz("prohledat mrtvola_zlodeje");
        hra1.zpracujPrikaz("jdi stanek_bylinkare");
        hra1.zpracujPrikaz("promluvit bylinkar");
        hra1.zpracujPrikaz("dat zasilka bylinkar");
        hra1.zpracujPrikaz("jdi namesti");
        hra1.zpracujPrikaz("jdi ulice_pred_branou");
        hra1.zpracujPrikaz("seber stary_glyf");
        hra1.zpracujPrikaz("jdi namesti");
        hra1.zpracujPrikaz("jdi pred_hospodou");
        hra1.zpracujPrikaz("jdi temna_ulicka");
        hra1.zpracujPrikaz("seber krabicka_s_fisstechem");
        hra1.zpracujPrikaz("promluvit pochybny_jedinec");
        hra1.zpracujPrikaz("jdi pred_hospodou");
        hra1.zpracujPrikaz("jdi hospoda");
        hra1.zpracujPrikaz("promluvit hospodsky");
        hra1.zpracujPrikaz("dat stary_glyf hospodsky");
        hra1.zpracujPrikaz("jdi pred_hospodou");
        hra1.zpracujPrikaz("promluvit vesemir");
        hra1.zpracujPrikaz("dat bily_racek vesemir");
        hra1.zpracujPrikaz("jdi namesti");
        hra1.zpracujPrikaz("promluvit dealer_fisstechu");
        hra1.zpracujPrikaz("dat krabicka_s_fisstechem dealer_fisstechu");
        hra1.zpracujPrikaz("jdi dum_savolly");
        hra1.zpracujPrikaz("promluvit Savolla");
        hra1.zpracujPrikaz("zakoupit magicka_pasta");
        hra1.zpracujPrikaz("jdi namesti");
        hra1.zpracujPrikaz("jdi dum_kalksteina");
        hra1.zpracujPrikaz("promluvit kalkstein");
        hra1.zpracujPrikaz("dat magicka_pasta kalkstein");
        hra1.zpracujPrikaz("jdi namesti");
        hra1.zpracujPrikaz("jdi ulice_pred_branou");
        hra1.zpracujPrikaz("jdi brana");
        hra1.zpracujPrikaz("promluvit wyzimska_straz");
        assertNotEquals(null, hra1.konecHry());
    }
}
