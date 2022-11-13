package com.ZakAdv.gui;

import com.ZakAdv.hra.HerniPlan;
import com.ZakAdv.hra.Hra;
import com.ZakAdv.hra.Prostor;
import com.ZakAdv.observer.Observer;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;


public class HraciPlocha implements Observer {

    private AnchorPane anchorPane = new AnchorPane();
    private Circle poziceHrace = new Circle(10.0, Paint.valueOf("red"));
    private HerniPlan herniPlan;




    public HraciPlocha(Hra hra) {
        herniPlan = hra.getHerniPlan();
        Prostor aktualniProstor = herniPlan.getAktualniProstor();

        herniPlan.register(this);

        update();

        Image image = new Image(HraciPlocha.class.getResourceAsStream("/mapa.jpg"), 600.0, 400.0, false, false);
        ImageView imageView = new ImageView(image);

        anchorPane.getChildren().addAll(imageView, poziceHrace);

    }

    public AnchorPane getAnchorPane() {
        return this.anchorPane;
    }

    @Override
    public void update() {
        Prostor aktualniProstor = herniPlan.getAktualniProstor();
        AnchorPane.setLeftAnchor(poziceHrace, aktualniProstor.getX());
        AnchorPane.setTopAnchor(poziceHrace, aktualniProstor.getY());

    }


}
