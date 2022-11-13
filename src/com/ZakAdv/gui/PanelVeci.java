package com.ZakAdv.gui;

import com.ZakAdv.hra.HerniPlan;
import com.ZakAdv.hra.Hra;
import com.ZakAdv.observer.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;


// Class tvorici vypis postav


public class PanelVeci implements Observer {


    private ObservableList<String> observableList = FXCollections.observableArrayList();
    private HerniPlan herniPlan;
    private ScrollPane scrollPane = new ScrollPane();
    private ListView listView = new ListView();
    private VBox vBox = new VBox();


    public PanelVeci(Hra hra) {

        herniPlan = hra.getHerniPlan();
        herniPlan.register(this);

        nastavVeci();
        listView.setMaxHeight(150);
        listView.setMaxWidth(240);
        listView.setPrefWidth(243);
        listView.setPrefHeight(100);
        scrollPane.setPrefHeight(150);



    }
    private void nastavVeci() {
        update();
    }

    public ScrollPane getScrollVeci() {
        return scrollPane;
    }

    @Override
    public void update() {
        String stringVeci = herniPlan.getAktualniProstor().popisVeci();
        String[] splited = stringVeci.split("\\s+");
        ObservableList<String> strList = FXCollections.observableArrayList(splited);
        ListView<String> listView = new ListView<>(strList);
        listView.getItems().remove(0);
        Label popisek = new Label("Předměty v okolí: ");
        popisek.setFont(new Font("Arial", 15));
        vBox.getChildren().addAll(popisek, listView);
        scrollPane.setContent(vBox);
    }

}
