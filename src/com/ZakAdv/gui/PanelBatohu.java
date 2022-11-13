package com.ZakAdv.gui;

import com.ZakAdv.hra.Hra;
import com.ZakAdv.observer.Observer;
import com.ZakAdv.inventar.Inventar;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class PanelBatohu implements Observer {

// Class tvorici panel batohu




    private VBox vBox = new VBox();
    private Inventar inventar;

    private FlowPane flowPane = new FlowPane();
    private ScrollPane batohScroll = new ScrollPane();
    private TextArea textPenize = new TextArea();


    private Label popisek = new Label("Ve své brašně máš:" );

    public PanelBatohu(Hra hra) {
        this.inventar = hra.getHerniPlan().getInventar();
        inventar.register(this);
        popisek.setFont(new Font("Arial", 15));

        vBox.getChildren().addAll(popisek, flowPane);
        vBox.setMaxWidth(200.0);
        vBox.setMaxHeight(600);
        batohScroll.setContent(vBox);
        batohScroll.setPrefHeight(400);
        batohScroll.setPrefWidth(220);
        textPenize.setMaxHeight(50);
        textPenize.setMaxWidth(240);
        textPenize.setEditable(false);
        int intPenize = inventar.getPenize();
        String stringPenize = Integer.toString(intPenize);
        textPenize.setText("V koženém měšci máš: \n" +stringPenize+ " orénů.");
    }
    public ScrollPane getBatohScroll() {
        return this.batohScroll;
    }



    @Override
    public void update() {
        flowPane.getChildren().clear();
        flowPane.getChildren().removeAll();
        for (String vec : inventar.getMnozinaNazvuVeciVInventari()) {
            ImageView imageView = new ImageView();
            Image image = new Image(PanelBatohu.class.getResourceAsStream("/" + vec + ".jpg"), 100,100, true, false);
            imageView.setImage(image);
            flowPane.getChildren().addAll(imageView);


        }
    }
}