package com.ZakAdv.gui;

import com.ZakAdv.hra.HerniPlan;
import com.ZakAdv.hra.Hra;
import com.ZakAdv.hra.Prostor;
import com.ZakAdv.observer.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.Collection;

// Class tvorici jak vypis vychodu, tak postav


public class PanelVychodu implements Observer {


    private ObservableList<String> observableList = FXCollections.observableArrayList();
    private ListView<String> listView = new ListView<>(observableList);

    private HerniPlan herniPlan;

    private VBox vBox = new VBox();
    private ScrollPane vychodyScroll = new ScrollPane();

    public PanelVychodu(Hra hra) {
        Label popisek = new Label("Možné východy: ");
        popisek.setFont(new Font("Arial", 15));


        herniPlan = hra.getHerniPlan();
        herniPlan.register(this);

        listView.setMaxWidth(240);
        listView.setMaxHeight(240);

        listView.setCellFactory(stringListView -> new ListCell<String>() {
            @Override
            protected void updateItem(String nazev, boolean empty) {
                super.updateItem(nazev, empty);
                if(!empty) {
                    setText(nazev);
                } else {
                    setText(null);
                    setGraphic(null);
                }
            }
        });

        nastavVychody();
        vBox.getChildren().addAll(popisek, listView);
        vychodyScroll.setContent(vBox);
    }
    private void nastavVychody() {
        update();
    }


    public ScrollPane getVychodyScroll() {
        return vychodyScroll;
    }


    @Override
    public void update() {
        observableList.clear();
        observableList.removeAll();
        Collection<Prostor> vychody = herniPlan.getAktualniProstor().getVychody();

        for (Prostor vychod : vychody) {
            observableList.add(vychod.getNazev());
        }


    }

}