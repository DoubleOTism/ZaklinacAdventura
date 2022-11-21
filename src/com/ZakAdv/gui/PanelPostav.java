package com.ZakAdv.gui;

import com.ZakAdv.hra.HerniPlan;
import com.ZakAdv.hra.Hra;
import com.ZakAdv.observer.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;

import java.util.List;


// Class tvorici vypis postav


public class PanelPostav implements Observer {




    private TextArea textPostavy = new TextArea();
    private HerniPlan herniPlan;
    private TextArea postavy = new TextArea();
    private ListView listView = new ListView();
    private ObservableList<String> observableList = FXCollections.observableArrayList();

    public PanelPostav(Hra hra) {


        herniPlan = hra.getHerniPlan();
        herniPlan.register(this);



        nastavPostavy();
        textPostavy.setMaxHeight(30);
        textPostavy.setMaxWidth(245);
        textPostavy.setPrefWidth(243);
        textPostavy.setPrefHeight(20);
        textPostavy.setEditable(false);
    }
    private void nastavPostavy() {
        update();
    }

    public TextArea getPostavy() {
        return textPostavy;
    }

    public ObservableList getObservableListPostav() {
        return observableList;
    }

    @Override
    public void update() {
        observableList.clear();
        observableList.removeAll();
        String stringPostavy = herniPlan.getAktualniProstor().popisPostavy();
        textPostavy.setText(stringPostavy);
        String[] splited = stringPostavy.split("\\s+");
        observableList.addAll(splited);
        observableList.remove(0);
        listView.setItems(observableList);




    }

}
