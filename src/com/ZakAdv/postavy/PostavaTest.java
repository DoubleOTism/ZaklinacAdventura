package com.ZakAdv.postavy;


import com.ZakAdv.hra.Hra;
import com.ZakAdv.vec.Vec;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Třída PostavaTest slouží ke komplexnímu otestování postav.
 *
 * @author Erich Pross
 * @version 2021/2022
 */

public class PostavaTest {
    private Hra hra1;

    @Before
    public void setUp() {
        hra1 = new Hra();
    }

    @After
    public void tearDown() {
    }


    @Test
    public void testPostavaPředVěcí() {
        Postava postava = new Postava("testovacipanak", "testovacípanák", new Vec("okurka", "okurka", true), new Vec("rajce", "rajce", true), "před", "po", "Toto je text v pripade odmitnuti", "Toto je text v případě přijmutí \n" + "Text v případě přijmutí druhá řádka \n" + "Text v případě přijmutí třetí řádka", "TestNaAxii");

        assertEquals("Testovacípanák: před", postava.mluvit());
    }

    @Test
    public void testPostavaDostalaCoChce() {
        Postava postava = new Postava("testovacipanak", "testovacípanák", new Vec("okurka", "okurka", true), new Vec("rajce", "rajce", true), "před", "po", "Toto je text v pripade odmitnuti", "Toto je text v případě přijmutí \n" + "Text v případě přijmutí druhá řádka \n" + "Text v případě přijmutí třetí řádka", "TestNaAxii");

        assertEquals("Testovacípanák: před", postava.mluvit());
        //postava dostala, co chce
        postava.vymenitVec(new Vec("okurka", "okurka", true));
        assertEquals("Testovacípanák: po", postava.mluvit());
    }

    @Test
    public void testPostavaAxii() {
        Postava postava = new Postava("testovacipanak", "testovacípanák", new Vec("okurka", "okurka", true), new Vec("rajce", "rajce", true), "před", "po", "Toto je text v pripade odmitnuti", "Toto je text v případě přijmutí \n" + "Text v případě přijmutí druhá řádka \n" + "Text v případě přijmutí třetí řádka", "TestNaAxii");
        assertEquals("TestNaAxii", postava.getDiaAxii());
    }
}