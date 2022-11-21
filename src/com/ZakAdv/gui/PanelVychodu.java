package com.ZakAdv.gui;

import com.ZakAdv.hra.HerniPlan;
import com.ZakAdv.hra.Hra;
import com.ZakAdv.hra.Prostor;
import com.ZakAdv.main.AdventuraZaklad;
import com.ZakAdv.observer.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import javax.jnlp.ClipboardService;
import java.util.Collection;

// Podpora pro panel vychodu tvorici se v AdventuraZaklad


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
        nastavVychody();
        vBox.getChildren().addAll(popisek, listView);
        vychodyScroll.setContent(vBox);




    }
    private void nastavVychody() {
        update();
    }

    public ObservableList getObservableList(){return observableList;}


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