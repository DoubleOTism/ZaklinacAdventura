package com.ZakAdv.main;

import com.ZakAdv.gui.*;
import com.ZakAdv.hra.Hra;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;


public class AdventuraZaklad extends Application {


    public static void main(String[] args) {
        Application.launch(args);
    }

    
    @Override
    public void start(Stage primaryStage) throws IOException {

        // setup JavaFX
        BorderPane borderPane = new BorderPane();
        Scene scene = new Scene(borderPane);
        HBox hBoxTop = new HBox();
        HBox hBoxBottom = new HBox();
        VBox vBoxRight = new VBox();
        primaryStage.setScene(scene);
        primaryStage.setWidth(1080.0);
        primaryStage.setHeight(720.0);





        // vytvoreni full-duplex komunikace mezi pocitacem a uzivatelem
        // 1. smer komunikace: od pocitace k uzivateli

        TextArea textArea = new TextArea();
        textArea.setEditable(false);
        Hra hra = new Hra();




        // 2. smer komunikace: od uzivatele k pocitaci

        // nachystani grafickych prvku
        TextField uzivatelskyVstup = new TextField(); // lisi se od TextArea zejména svými eventy, konkrétně zpracováním klávesy Enter.
        Label label = new Label("Zadej příkaz: ");
        label.setFont(Font.font("Arial", FontWeight.BLACK, 14.0));

        FlowPane flowPane = new FlowPane();
        flowPane.getChildren().addAll(label, uzivatelskyVstup);
        flowPane.setAlignment(Pos.CENTER);

        // zajisteni zpracovani prikazu
        uzivatelskyVstup.setOnAction(event -> {
            String lokalniPromennaObsahujiciUzivateluvVstup = uzivatelskyVstup.getText();
            String lokalniPromennaObsahujiciOdpovedProgramu = hra.zpracujPrikaz(lokalniPromennaObsahujiciUzivateluvVstup);
            textArea.appendText("\n" + lokalniPromennaObsahujiciOdpovedProgramu + "\n");
                uzivatelskyVstup.clear();

            // DU: Udělat, aby nešlo zadávat příkazy po skončení hry.
        });


        // Pridani HraciPlochy
        HraciPlocha hraciPlocha = new HraciPlocha(hra);
        AnchorPane anchorPane = hraciPlocha.getAnchorPane();

        // Pridani PaneluVychodu
        PanelVychodu panelVychodu = new PanelVychodu(hra);

        // Pridani PaneluBatohu
        PanelBatohu panelBatohu = new PanelBatohu(hra);

        // Pridani PaneluPenez
        PanelPenez panelPenez = new PanelPenez(hra);

        //Pridani PaneluPostav
        PanelPostav panelPostav = new PanelPostav(hra);

        //Pridani PaneluVeci
        PanelVeci panelVeci = new PanelVeci(hra);



        //Vrchol okna - Batoh, HraciPlocha, Mistnosti
        hBoxTop.getChildren().addAll(panelBatohu.getBatohScroll(), anchorPane, panelVychodu.getVychodyScroll());
        hBoxTop.setAlignment(Pos.TOP_LEFT);
        borderPane.setTop(hBoxTop);

        //Stred Okna - Vypis hry
        textArea.setMaxWidth(780);
        borderPane.setCenter(textArea);

        //Spodni cast okna - vstup
        Label labelVeci = new Label("Věci v tvém okolí:");
        labelVeci.setFont(new Font("Arial", 15));
        hBoxBottom.getChildren().addAll(flowPane);
        hBoxBottom.setAlignment(Pos.CENTER);
        borderPane.setBottom(hBoxBottom);
        //Pravá cast okna - vypisy postav a predmetu
        Label popisekPenize = new Label("V měšci máš: " );
        popisekPenize.setFont(new Font("Arial", 15));
        vBoxRight.getChildren().addAll(panelPostav.getScrollPostavy(), panelVeci.getScrollVeci(), popisekPenize, panelPenez.getTextPenize());
        borderPane.setRight(vBoxRight);






        // Pridani menu
        MenuBar menuBar = new MenuBar();
        VBox vBox = new VBox();
        vBox.getChildren().addAll(menuBar,borderPane);
        Menu menu1 = new Menu("Hra");
        Menu menu2 = new Menu("Help");
        menuBar.getMenus().addAll(menu1, menu2);
        MenuItem menuItem1 = new MenuItem("Restart hry / Nová hra");
        MenuItem menuItem2 = new MenuItem("Ukončit hru");
        MenuItem menuItem3 = new MenuItem("Nápověda");
        MenuItem menuItem4 = new MenuItem("O aplikaci");

             //Menu: Ukoncit hru
        menuItem2.setOnAction((ActionEvent e) -> System.exit(0));

            // Menu: O Aplikaci
        Alert about = new Alert(Alert.AlertType.INFORMATION);
        EventHandler<ActionEvent> aboutAction = e -> {

                // Text, jmeno okna
            Label labelAbout = new Label("Adventura Zaklínač.\nPůvodně navrženo na 4IT101.\nUpraveno a doplněno o GUI na hodinách 4IT115.\nVelké díky Lukásovi B. a Markovi V. za předané informace o jazyku Java.\nErich Pross, 2021-2022");
            labelAbout.setWrapText(true);
            about.getDialogPane().setContent(labelAbout);
            about.setTitle("O Aplikaci");
            about.show();
        };

        menuItem4.setOnAction(aboutAction);
            //Menu: Nova hra
        menuItem1.setOnAction((ActionEvent e) -> {
            Alert restart = new Alert(Alert.AlertType.WARNING);
            Label labelRestart = new Label("Restartuji hru, držte si klobouk!");
            labelRestart.setWrapText(true);
            restart.getDialogPane().setContent(labelRestart);
            restart.setTitle("Restart hry");
            restart.show();
            Alert restarted =new Alert(Alert.AlertType.INFORMATION);
            Label labelRestarted = new Label("Hra úspěšně restartována");
            restarted.getDialogPane().setContent(labelRestarted);
            restarted.setTitle("Hra restartována");
                primaryStage.close();
                Platform.runLater( () -> {
                    try {
                        new AdventuraZaklad().start( new Stage() );
                        restart.close();
                        restarted.show();
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                });
        });
            //Menu: Nápověda
                //Musi zde byt webview







        menu1.getItems().addAll(menuItem1, menuItem2);
        menu2.getItems().addAll(menuItem3, menuItem4);


        scene.setRoot(vBox);



        uzivatelskyVstup.requestFocus();
        textArea.appendText(hra.vratUvitani());
        primaryStage.show();
    }
public static void launchGame(String... args){
        launch();
}
}

