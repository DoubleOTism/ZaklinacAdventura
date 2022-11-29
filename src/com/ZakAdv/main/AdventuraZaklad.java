package com.ZakAdv.main;

import com.ZakAdv.gui.*;
import com.ZakAdv.hra.HerniPlan;
import com.ZakAdv.hra.Hra;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.web.PopupFeatures;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.net.URL;


public class AdventuraZaklad extends Application  {



    public static void main(String[] args) {
        Application.launch(args);
    }
    Hra hra = new Hra();
    HerniPlan herniPlan = new HerniPlan(hra);
    PanelVychodu panelVychodu = new PanelVychodu(hra);
    PanelPostav panelPostav = new PanelPostav(hra);

    PanelVeci panelVeci = new PanelVeci(hra);
    ObservableList observableList = panelVychodu.getObservableList();
    ObservableList observableListPostavy = panelPostav.getObservableListPostav();
    ObservableList observableListVeci = panelVeci.getObservableListVeci();
    private ListView<String> listVychody = new ListView<>(observableList);
    private ListView<String> listPostavy = new ListView<>(observableListPostavy);
    private ListView<String> listVeci = new ListView<>(observableListVeci);
    private TextArea textArea = new TextArea();
    String vec = herniPlan.getAktualniProstor().popisVeci();


    
    @Override
    public void start(Stage primaryStage) throws Exception {


        // setup JavaFX
        BorderPane borderPane = new BorderPane();
        Scene scene = new Scene(borderPane);
        HBox hBoxTop = new HBox();
        HBox hBoxBottom = new HBox();
        VBox vBoxRight = new VBox();
        primaryStage.setScene(scene);
        primaryStage.setWidth(1080.0);
        primaryStage.setHeight(620.0);
        scene.getRoot().setStyle("-fx-base:black;" + "-fx-text-inner-color: #ffffff;");



        // vytvoreni full-duplex komunikace mezi pocitacem a uzivatelem
        // 1. smer komunikace: od pocitace k uzivateli


        textArea.setEditable(false);
        textArea.setFont(Font.font("Arial", FontWeight.BOLD, 12));





                // 2. smer komunikace: od uzivatele k pocitaci

        // nachystani grafickych prvku
        TextField uzivatelskyVstup = new TextField(); // lisi se od TextArea zejména svými eventy, konkrétně zpracováním klávesy Enter.
        Label label = new Label("Zadej příkaz: ");
        label.setFont(Font.font("Arial", FontWeight.BLACK, 14.0));
        uzivatelskyVstup.setFont(Font.font("Arial", FontWeight.BOLD, 14 ));


        FlowPane flowPane = new FlowPane();
        flowPane.getChildren().addAll(label, uzivatelskyVstup);
        flowPane.setAlignment(Pos.CENTER);

        // zajisteni zpracovani prikazu + ukončení pomocí příkazu ukončit
        uzivatelskyVstup.setOnAction(event -> {
            String lokalniPromennaObsahujiciUzivateluvVstup = uzivatelskyVstup.getText();
            String lokalniPromennaObsahujiciOdpovedProgramu = hra.zpracujPrikaz(lokalniPromennaObsahujiciUzivateluvVstup);
                uzivatelskyVstup.clear();
                if (lokalniPromennaObsahujiciUzivateluvVstup.equals("ukoncit")) {
                ukoncitPrikaz();
                borderPane.setDisable(true);
                } else { textArea.appendText("\n" + lokalniPromennaObsahujiciOdpovedProgramu + "\n");};
                if (lokalniPromennaObsahujiciUzivateluvVstup.equals("axii straz")) {
                    ukoncitStraz();
                    borderPane.setDisable(true);
            }
                if(lokalniPromennaObsahujiciUzivateluvVstup.equals("IDKFA")) {
                    hra.getHerniPlan().IDKFA();
                    textArea.appendText("Shame.");
                }


        });


        // Pridani HraciPlochy
        HraciPlocha hraciPlocha = new HraciPlocha(hra);
        AnchorPane anchorPane = hraciPlocha.getAnchorPane();


        // Pridani PaneluBatohu
        PanelBatohu panelBatohu = new PanelBatohu(hra);

        // Pridani PaneluPenez
        PanelPenez panelPenez = new PanelPenez(hra);

        //Pridani PaneluVeci
        PanelVeci panelVeci = new PanelVeci(hra);


        //Pridani funkce pro proklikavani se do jinych prostoru
        listVychody.setMaxHeight(125);
        listVychody.setCellFactory(stringListView -> new ListCell<String>() {
            @Override
            protected void updateItem(String nazev, boolean empty) {
                super.updateItem(nazev, empty);
                if(!empty) {
                    setText(nazev);
                    setStyle("-fx-text-fill: #ffffff;");
                    setFont(Font.font("Arial", FontWeight.BOLD, 12));
                } else {
                    setText(null);
                }
            }
        });
        listVychody.setOnMouseClicked(event -> {
            String selected = listVychody.getSelectionModel().getSelectedItem();
            if (selected == null) {
            } else {
                hra.zpracujPrikaz("jdi " + selected);
                textArea.appendText("\n----------------------------------------------------------------------------------------------");
                textArea.appendText(hra.getHerniPlan().getAktualniProstor().dlouhyPopis());
                textArea.appendText("\n----------------------------------------------------------------------------------------------");
            }

        });

        //Pridani funkce pro proklikavani se dialogy a nastavení konce
        listPostavy.setMaxHeight(125);
        listPostavy.setCellFactory(stringListView -> new ListCell<String>() {
            @Override
            protected void updateItem(String nazev, boolean empty) {
                super.updateItem(nazev, empty);
                if(!empty) {
                    setText(nazev);
                    setStyle("-fx-text-fill: #ffffff;");
                    setFont(Font.font("Arial", FontWeight.BOLD, 12));
                } else {
                    setText(null);
                }
            }
        });
        listPostavy.setOnMouseClicked(event -> {
            String selected = listPostavy.getSelectionModel().getSelectedItem();
            if (selected == null) {
            } else {
                String selectedPostava = listPostavy.getSelectionModel().getSelectedItem().toLowerCase();
                hra.zpracujPrikaz("promluvit " + selectedPostava);
                if (selectedPostava.equals("wyzimska_straz")) {
                    textArea.appendText("\n----------------------------------------------------------------------------------------------\n");
                    String textPostavy = hra.getHerniPlan().getAktualniProstor().vratPostavu(selectedPostava).mluvit();
                    textArea.appendText(textPostavy);
                    textArea.appendText("\n----------------------------------------------------------------------------------------------");
                    flowPane.setDisable(true);
                    listVychody.setDisable(true);
                    listPostavy.setDisable(true);
                    listVeci.setDisable(true);
                }else {
                    textArea.appendText("\n----------------------------------------------------------------------------------------------\n");
                    String textPostavy = hra.getHerniPlan().getAktualniProstor().vratPostavu(selectedPostava).mluvit();
                    textArea.appendText(textPostavy);
                    textArea.appendText("\n----------------------------------------------------------------------------------------------");
                }
            }

        });

        //Pridani funkce pro proklikavani se skrze veci
        listVeci.setMaxHeight(125);
        listVeci.setCellFactory(stringListView -> new ListCell<String>() {
            @Override
            protected void updateItem(String nazev, boolean empty) {
                super.updateItem(nazev, empty);
                if(!empty) {
                    setText(nazev);
                    setStyle("-fx-text-fill: #ffffff;");
                    setFont(Font.font("Arial", FontWeight.BOLD, 12));
                } else {
                    setText(null);
                }
            }
        });
        listVeci.setOnMouseClicked(event -> {
            String selected = listVeci.getSelectionModel().getSelectedItem();
            if (selected == null) {
                textArea.appendText("Vyber věc k sebrání z listu.");
            } else {
                switch (selected) {
                    case "nic":
                        textArea.appendText("\nGeralt: V okolí není předmět, co by se mi mohl hodit");
                        break;
                    case "popelnice":
                        textArea.appendText("\nGeralt: Popelnici si do brašny nevložím.");
                        break;
                    case "rozlite_korbely_piva":
                        textArea.appendText("\nGeralt: To si radši brát nebudu.");
                        break;
                    case "glejt":
                        textArea.appendText("\nGeralt: To si radši koupím, nebudu obírat starého kamaráda.");
                        break;
                    case "magicka_pasta":
                        textArea.appendText("\nGeralt: Se Savollou si radši zahrávat nebudu, vím čeho je schopný. Radši se ho zeptám, jestli by to neprodal.");
                        break;
                    default:
                        hra.zpracujPrikaz("seber " + selected );
                        textArea.appendText("\nVložil jsi: " + selected + " do brašny.");
                        hra.getHerniPlan().getAktualniProstor().odeberVec(selected);
                        panelVeci.nastavVeci();
                        observableListVeci.clear();
                        panelVeci.update();
                        String stringVeci = hra.getHerniPlan().getAktualniProstor().popisVeci();
                        String[] splited = stringVeci.split("\\s+");
                        observableListVeci.addAll(splited);
                        observableListVeci.remove(0);
                        listVeci.setItems(observableListVeci);



                }
            }

        });






        //Vrchol okna - Batoh, HraciPlocha, Mistnosti
        Label popisekPredmety = new Label("Předměty v okolí (klikni):");
        Label popisekPostavy = new Label("Postavy v okolí (klikni): " );
        Label popisekProstory = new Label("Dostupné prostory (klikni): " );
        popisekPredmety.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        popisekPostavy.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        popisekProstory.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        VBox vBoxLeftTop = new VBox(popisekProstory , listVychody, popisekPostavy, listPostavy, popisekPredmety, listVeci);
        vBoxLeftTop.setMaxHeight(400);
        hBoxTop.getChildren().addAll(panelBatohu.getBatohScroll(), anchorPane, vBoxLeftTop);
        hBoxTop.setAlignment(Pos.CENTER);
        borderPane.setTop(hBoxTop);

        //Stred Okna - Vypis hry
        textArea.setMaxWidth(850);
        borderPane.setCenter(textArea);

        //Spodni cast okna - vstup
        hBoxBottom.getChildren().addAll(flowPane);
        hBoxBottom.setAlignment(Pos.CENTER);
        borderPane.setBottom(hBoxBottom);
        //Pravá cast okna - vypisy postav a predmetu
        Label popisekPenize = new Label("V měšci máš: " );
        popisekPenize.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        panelPenez.getTextPenize().setFont(Font.font("Arial", FontWeight.BOLD, 12));
        vBoxRight.getChildren().addAll(popisekPenize, panelPenez.getTextPenize());
        borderPane.setRight(vBoxRight);


        // Pridani menu
        MenuBar menuBar = new MenuBar();
        VBox vBox = new VBox();
        vBox.getChildren().addAll(menuBar,borderPane);
        Menu menu1 = new Menu("Hra");
        Menu menu2 = new Menu("Help");
        Menu menu3 = new Menu("Teleport");
        menuBar.getMenus().addAll(menu1, menu2, menu3);
        MenuItem menuItem1 = new MenuItem("Restart hry / Nová hra");
        MenuItem menuItem2 = new MenuItem("Ukončit hru");
        MenuItem menuItem3 = new MenuItem("Příručka");
        MenuItem menuItem4 = new MenuItem("O aplikaci");
        MenuItem menuItem5 = new MenuItem("Hospoda");
        MenuItem menuItem6 = new MenuItem("Před hospodou");
        MenuItem menuItem7 = new MenuItem("Temna Ulicka");
        MenuItem menuItem8 = new MenuItem("Namesti");
        MenuItem menuItem9 = new MenuItem("Stanek Bylinkare");
        MenuItem menuItem10 = new MenuItem("Postranní Ulička");
        MenuItem menuItem11 = new MenuItem("Dum kalksteina");
        MenuItem menuItem12 = new MenuItem("Dum Savolly");
        MenuItem menuItem13 = new MenuItem("Ulice Před Bránou");
        MenuItem menuItem14 = new MenuItem("Brána");

        menuItem1.setAccelerator(new KeyCodeCombination(KeyCode.R, KeyCombination.CONTROL_DOWN));
        menuItem2.setAccelerator(new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_DOWN));
        menuItem3.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN));
        menuItem4.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN));

             //Menu: Ukoncit hru
        EventHandler<ActionEvent> endAction = e -> {
            ukoncitMenu();
            borderPane.setDisable(true);
        };
        menuItem2.setOnAction(endAction);

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
            //Menu: Dokumentace
        menuItem3.setOnAction((ActionEvent e) -> {
            URL url = this.getClass().getResource("/dokumentace.htm");
            BorderPane webBorderPane = new BorderPane();
            Stage webStage = new Stage();
            WebView wv = new WebView();
            wv.getEngine().load(url.toString());
            webBorderPane.setCenter(wv);
            Scene webScene = new Scene(webBorderPane);
            webStage.setScene(webScene);
            webScene.getRoot().setStyle("-fx-base:black;" + "-fx-text-inner-color: #ffffff;");
            webStage.show();
        });




        menu1.getItems().addAll(menuItem1, menuItem2);
        menu2.getItems().addAll(menuItem3, menuItem4);
        menu3.getItems().addAll(menuItem5,menuItem6,menuItem7,menuItem8,menuItem9,menuItem10, menuItem11, menuItem12, menuItem13, menuItem14);


        scene.setRoot(vBox);


        scene.getRoot().setStyle("-fx-base:black;" + "-fx-text-inner-color: #ffffff;");

        uzivatelskyVstup.requestFocus();
        primaryStage.show();
        textArea.appendText(hra.vratUvitani());





    }
public static void launchGame(){
        launch();
}
public static void ukoncitPrikaz () {
    Alert ukoncit = new Alert(Alert.AlertType.INFORMATION);
    Label labelUkoncit = new Label("Hra byla vypnuta pomocí příkazu ukončit \nPokud chceš hrát znovu, jdi do menu Hra a vyber možnost Restart hry / Nová hra ");
    labelUkoncit.setWrapText(true);
    ukoncit.getDialogPane().setContent(labelUkoncit);
    ukoncit.setTitle("Konec");
    ukoncit.show();

}
public static void ukoncitMenu () {
        Alert ukoncit = new Alert(Alert.AlertType.INFORMATION);
        Label labelUkoncit = new Label("Hra byla vypnuta pomocí tlačítka Ukončit hru v menu Hra \nPokud chceš hrát znovu, jdi do menu Hra a vyber možnost Restart hry / Nová hra ");
        labelUkoncit.setWrapText(true);
        ukoncit.getDialogPane().setContent(labelUkoncit);
        ukoncit.setTitle("O Aplikaci");
        ukoncit.show();
    }
public static  void ukoncitStraz () {
        Alert strazKonec = new Alert(Alert.AlertType.INFORMATION);
        Label labelStraz = new Label("Použil jsi Axii na stráž a ta si to nenechala líbit. Byl jsi zavřen do vězení. Krále jsi nevaroval a byl zabit.");
        labelStraz.setWrapText(true);
        strazKonec.getDialogPane().setContent(labelStraz);
        strazKonec.setTitle("Konec");
        strazKonec.show();
}
}


