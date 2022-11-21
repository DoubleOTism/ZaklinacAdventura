package com.ZakAdv.gui;

import com.ZakAdv.hra.HerniPlan;
import com.ZakAdv.hra.Hra;
import com.ZakAdv.observer.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import javax.swing.*;
import java.util.List;


// Class tvorici vypis postav


public class PanelVeci implements Observer {



    private TextArea textVeci = new TextArea();

    private ListView listView = new ListView();
    private HerniPlan herniPlan;
    private ObservableList<String> observableList = FXCollections.observableArrayList();



    public PanelVeci(Hra hra) {

        herniPlan = hra.getHerniPlan();
        herniPlan.register(this);

        nastavVeci();
        textVeci.setMaxHeight(30);
        textVeci.setMaxWidth(245);
        textVeci.setPrefWidth(243);
        textVeci.setPrefHeight(20);
        textVeci.setEditable(false);



    }
    public void nastavVeci() {
        update();
    }
    public ObservableList getObservableListVeci() {
        return observableList;
    }

    @Override
    public void update() {
        observableList.clear();
        observableList.removeAll();
        String stringVeci = herniPlan.getAktualniProstor().popisVeci();
        textVeci.setText(stringVeci);
        String[] splited = stringVeci.split("\\s+");
        observableList.addAll(splited);
        observableList.remove(0);
        listView.setItems(observableList);


    }

}
