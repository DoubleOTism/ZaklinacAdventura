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


public class PanelPostav implements Observer {



    private ObservableList<String> observableListPostavy = FXCollections.observableArrayList();
    private TextArea textPostavy = new TextArea();
    private HerniPlan herniPlan;
    private VBox vBox = new VBox();
    private ScrollPane scrollPane = new ScrollPane();
    private TextArea postavy = new TextArea();

    public PanelPostav(Hra hra) {


        herniPlan = hra.getHerniPlan();
        herniPlan.register(this);



        nastavPostavy();
        textPostavy.setMaxHeight(30);
        textPostavy.setMaxWidth(245);
        textPostavy.setPrefWidth(243);
        textPostavy.setPrefHeight(20);
        textPostavy.setEditable(false);
        scrollPane.setMaxHeight(150);
        scrollPane.setPrefHeight(150);
    }
    private void nastavPostavy() {
        update();
    }

    public ScrollPane getScrollPostavy() {
        return scrollPane;
    }

    @Override
    public void update() {
        String stringPostavy = herniPlan.getAktualniProstor().popisPostavy();
        String[] splited = stringPostavy.split("\\s+");
        ObservableList<String> strList = FXCollections.observableArrayList(splited);
        ListView<String> listView = new ListView<>(strList);
        listView.getItems().remove(0);
        Label popisek = new Label("Postavy v okolí: ");
        popisek.setFont(new Font("Arial", 15));
        vBox.getChildren().addAll(popisek, listView);
        scrollPane.setContent(vBox);



    }

}
