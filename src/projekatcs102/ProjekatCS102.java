/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekatcs102;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import DB.DB;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import avioKarte.AvioKarte;
import avioKarte.PrezumiKartu;
import exception.KlijentException;
import java.util.function.UnaryOperator;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import klijent.Klijent;

/**
 *
 * @author David
 */
public class ProjekatCS102 extends Application {

    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Prvo izaberti kartu, a posle unesite svoje podatke", ButtonType.NEXT);

    Label ime = new Label("Ime : ");
    TextField txIme = new TextField();
    Label prezime = new Label("Prezime : ");
    TextField txPrezime = new TextField();
    Label jmbg = new Label("JMBG : ");
    TextField txJmbg = new TextField();
    Label brPasosa = new Label("Broj pasosa : ");
    TextField txBrojPasosa = new TextField();

    Label odGRada = new Label("Od grada : ");
    TextField txOdGrada = new TextField();
    Label doGrada = new Label("Do grada : ");
    TextField txDoGrada = new TextField();
    Label smer = new Label("Klasa : ");
    TextField txSmer = new TextField();
    Label cena = new Label("Cena : ");
    TextField txCena = new TextField();

    GridPane centar = new GridPane();

    HBox hb = new HBox();

    Button btnRezervisi = new Button("Rezervisi");

    BorderPane root = new BorderPane();
    TableView tableView = new TableView();

    Image image = new Image("https://vaughanrockets.typepad.com/.a/6a00d8354d1e6469e201b8d14af050970c-pi");
    BackgroundImage bgImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);

    Background bg = new Background(bgImage);

    /**
     *
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {

        root.setBackground(bg);

        //Ogranicenje za TextField moguci unos samo brojeva
        UnaryOperator<Change> jmbgOgranicenje = change -> {
            String text = change.getText();
            if (text.matches("[0-9]*")) {
                return change;
            }
            return null;
        };
        TextFormatter<String> textFormatter1 = new TextFormatter<>(jmbgOgranicenje);
        txJmbg.setTextFormatter(textFormatter1);

        UnaryOperator<Change> brojPasosaOgranicenje = change -> {
            String text = change.getText();
            if (text.matches("[0-9]*")) {
                return change;
            }
            return null;
        };
        TextFormatter<String> textFormatter2 = new TextFormatter<>(brojPasosaOgranicenje);
        txBrojPasosa.setTextFormatter(textFormatter2);

        // Ogranicenje  TextField moguci unos samo slova
        UnaryOperator<Change> imeOgranicenje = change -> {
            String text = change.getText();
            if (text.matches("[a-zA-z]*")) {
                return change;
            }
            return null;
        };

        TextFormatter<String> textFormatter3 = new TextFormatter<>(imeOgranicenje);
        txIme.setTextFormatter(textFormatter3);

        UnaryOperator<Change> prezimeOgranicenje = change -> {
            String text = change.getText();
            if (text.matches("[a-zA-Z]*")) {
                return change;
            }
            return null;
        };
        TextFormatter<String> textFormatter4 = new TextFormatter<>(prezimeOgranicenje);
        txPrezime.setTextFormatter(textFormatter4);

        Scene scene = new Scene(root, 1200, 700);

        //Stilizovanje dugmeta
        txOdGrada.setEditable(false);
        txOdGrada.setStyle("-fx-background-color : lightgray");
        txDoGrada.setEditable(false);
        txDoGrada.setStyle("-fx-background-color : lightgray");
        txSmer.setEditable(false);
        txSmer.setStyle("-fx-background-color : lightgray");
        txCena.setEditable(false);
        txCena.setStyle("-fx-background-color : lightgray");

        //Podesavanje Grida
        centar.add(ime, 0, 0);
        centar.add(txIme, 1, 0);
        centar.add(prezime, 0, 1);
        centar.add(txPrezime, 1, 1);
        centar.add(jmbg, 0, 2);
        centar.add(txJmbg, 1, 2);
        centar.add(brPasosa, 0, 3);
        centar.add(txBrojPasosa, 1, 3);
        centar.add(odGRada, 3, 0);
        centar.add(txOdGrada, 4, 0);
        centar.add(doGrada, 3, 1);
        centar.add(txDoGrada, 4, 1);
        centar.add(smer, 3, 2);
        centar.add(txSmer, 4, 2);
        centar.add(cena, 3, 3);
        centar.add(txCena, 4, 3);
        centar.add(btnRezervisi, 5, 6);

        centar.setPadding(new Insets(10, 5, 10, 5));
        centar.setAlignment(Pos.CENTER);
        centar.setVgap(10);
        centar.setHgap(10);

        primaryStage.setTitle("Jeftine karte");
        primaryStage.setScene(scene);
        primaryStage.show();

        alert.show();

        try {

            // Pokretanje niti
            Thread t1 = new Thread(new PrezumiKartu());
            Thread t2 = new Thread(() -> {
                tableView.setEditable(true);
                DB.database.tabelKlijent();

                TableColumn id = new TableColumn("id");
                TableColumn odGrada = new TableColumn("Od grada");
                TableColumn doGrada = new TableColumn("Do grada");
                TableColumn klasa = new TableColumn("Klasa");
                TableColumn cena = new TableColumn("Cena");
                TableColumn mesta = new TableColumn("Mesta");

                //Podesavanje kolona
                id.setCellValueFactory(new PropertyValueFactory<AvioKarte, Integer>("id"));
                odGrada.setCellValueFactory(new PropertyValueFactory<AvioKarte, String>("odGrada"));
                doGrada.setCellValueFactory(new PropertyValueFactory<AvioKarte, String>("doGrada"));
                klasa.setCellValueFactory(new PropertyValueFactory<AvioKarte, String>("klasa"));
                cena.setCellValueFactory(new PropertyValueFactory<AvioKarte, String>("cena"));
                mesta.setCellValueFactory(new PropertyValueFactory<AvioKarte, Integer>("mesta"));
                tableView.setItems(DB.database.prikazBaze());
                tableView.getColumns().addAll(id, odGrada, doGrada, klasa, cena, mesta);

                //Citanje iz tabele i upis u polja 
                tableView.setOnMouseClicked((e) -> {
                    AvioKarte a = (AvioKarte) tableView.getSelectionModel().getSelectedItem();
                    txOdGrada.setText(a.getOdGrada());
                    txDoGrada.setText(a.getDoGrada());
                    txSmer.setText(a.getKlasa());
                    txCena.setText(a.getCena());

                });

                //Porvera da li su sva polja popunjena, a zatim upis u tabelu
                btnRezervisi.setOnAction((e) -> {
                    try {

                        if (txOdGrada.getText().isEmpty()) {

                            Alert alert2 = new Alert(Alert.AlertType.ERROR, "Morate selektovati kartu", ButtonType.OK);
                            alert2.show();

                        } else if (txIme.getText().isEmpty() || txPrezime.getText().isEmpty() || txJmbg.getText().isEmpty() || txBrojPasosa.getText().isEmpty()) {

                            Alert alert4 = new Alert(Alert.AlertType.ERROR, "Unesite svoje podatke", ButtonType.OK);
                            alert4.show();
                            
                          

                        } else {
                            AvioKarte a = (AvioKarte) tableView.getSelectionModel().getSelectedItem();
                            Klijent k = new Klijent();
                            k.setIme(txIme.getText());
                            k.setPrezime(txPrezime.getText());
                            k.setJmbg(txJmbg.getText());
                            k.setBrojPasosa(txBrojPasosa.getText());
                            k.setId_karte(a.getId());
                            DB.database.insertKlijent(k);
                            Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION, "Uspesno ste rezervisali kartu na ime "
                                    + txIme.getText() + "sa  brojem pasosa " + txBrojPasosa.getText(), ButtonType.OK);
                            alert1.show();
                            txIme.setText("");
                            txPrezime.setText("");
                            txJmbg.setText("");
                            txBrojPasosa.setText("");

                        }

                    } catch (KlijentException ex) {
                        ex.printStackTrace();
                    } finally {

                    }

                });

                //FX nit
                Platform.runLater(() -> {

                    root.setRight(tableView);
                    root.setCenter(centar);

                });

            });

            t1.start();
            t1.join();
            t2.start();

        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);

    }

}
