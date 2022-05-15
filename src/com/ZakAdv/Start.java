package com.ZakAdv;

import com.ZakAdv.hra.Hra;
import com.ZakAdv.zakladniInterfaces.InterHra;
import com.ZakAdv.zakladniInterfaces.TextoveRozhrani;


/*******************************************************************************
 * Třída  Start je hlavní třídou projektu,
 * který představuje jednoduchou textovou adventuru určenou k dalším úpravám a rozšiřování
 *
 * @author    Jarmila Pavlíčková
 * @version    3.1
 * @created    květen 2005/2021/2022
 */
public class Start
{
    /***************************************************************************
     * Metoda, prostřednictvím níž se spouští celá aplikace.
     *
     * @param args Parametry příkazového řádku
     */
    public static void main(String[] args)
    {

        InterHra hra = new Hra();
        TextoveRozhrani ui = new TextoveRozhrani(hra);
        ui.hraj();
    }
}