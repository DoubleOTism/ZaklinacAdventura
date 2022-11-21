package com.ZakAdv.gui;

import com.ZakAdv.hra.Hra;
import com.ZakAdv.observer.Observer;
import com.ZakAdv.inventar.Inventar;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;

public class PanelPenez implements Observer {

// Class tvorici panel penez




    private Inventar inventar;
    private FlowPane flowPane = new FlowPane();
    private TextArea textPenize = new TextArea();



    public PanelPenez(Hra hra) {
        this.inventar = hra.getHerniPlan().getInventar();
        inventar.register(this);

        textPenize.setMaxHeight(50);
        textPenize.setMaxWidth(244);
        textPenize.setPrefHeight(30);
        textPenize.setEditable(false);
        int intPenize = inventar.getPenize();
        String stringPenize = Integer.toString(intPenize);
        textPenize.setText(stringPenize+ " orénů.");
    }
    public TextArea getTextPenize() {
        return this.textPenize;
    }



    @Override
    public void update() {
        int intPenize = inventar.getPenize();
        String stringPenize = Integer.toString(intPenize);
        textPenize.setText(stringPenize+ " orénů.");


    }
}