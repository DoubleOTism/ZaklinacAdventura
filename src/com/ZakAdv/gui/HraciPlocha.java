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

    private Image hrac = new Image(HraciPlocha.class.getResourceAsStream("/hrac.png"), 30, 30, false, false);
    private ImageView imageViewHrac = new ImageView(hrac);
    private HerniPlan herniPlan;




    public HraciPlocha(Hra hra) {
        herniPlan = hra.getHerniPlan();
        Prostor aktualniProstor = herniPlan.getAktualniProstor();

        herniPlan.register(this);

        update();

        Image mapa = new Image(HraciPlocha.class.getResourceAsStream("/mapa.jpg"), 600.0, 400.0, false, false);
        ImageView imageViewMapa = new ImageView(mapa);

        anchorPane.getChildren().addAll(imageViewMapa, imageViewHrac);

    }

    public AnchorPane getAnchorPane() {
        return this.anchorPane;
    }

    @Override
    public void update() {
        Prostor aktualniProstor = herniPlan.getAktualniProstor();
        AnchorPane.setLeftAnchor(imageViewHrac, aktualniProstor.getX());
        AnchorPane.setTopAnchor(imageViewHrac, aktualniProstor.getY());

    }


}
