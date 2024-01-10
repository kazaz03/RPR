package com.example.lab1011;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import javafx.stage.Stage;

public class GlavnaController {
    private GeografijaDAO geografijaDAO;
    private GradZaTableView odabraniGrad;
    @FXML
    private TableView<GradZaTableView> tableViewGradovi;
    @FXML
    private TableColumn<GradZaTableView,Integer> colGradId;
    @FXML
    private TableColumn<GradZaTableView,String> colGradNaziv;
    @FXML
    private TableColumn<GradZaTableView,Integer> colGradStanovnika;
    @FXML
    private TableColumn<GradZaTableView,String> colGradDrzava;
    @FXML
    private Button btnObrisiGrad;
    @FXML
    private Button btnIzmijeniGrad;
    @FXML
    private Button btnDodajDrzavu;
    @FXML
    public void initialize()
    {
        /* Rad s nitima
        //brisanje preko dvije niti
        geografijaDAO=GeografijaDAO.getInstance();
        Runnable obrisiGradove = () -> {
            synchronized (geografijaDAO) {
                geografijaDAO.obrisiGradove();
            }
        };

        Runnable obrisiDrzave = () -> {
            synchronized (geografijaDAO) {
                geografijaDAO.obrisiDrzave();
            }
        };
        Thread nit1=new Thread(obrisiGradove);
        Thread nit2=new Thread(obrisiDrzave);
        nit1.start();
        nit2.start();
        try {
            nit1.join();
            nit2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //unos par podataka u tabele preko dvije niti
        Runnable dodajGradove = () -> {
            synchronized (geografijaDAO) {
                geografijaDAO.dodajGradove();
            }
        };

        Runnable dodajDrzave = () -> {
            synchronized (geografijaDAO) {
                geografijaDAO.dodajDrzavee();
            }
        };
        Thread nit3=new Thread(dodajGradove);
        Thread nit4=new Thread(dodajDrzave);
        nit3.start();
        nit4.start();
        try {
            nit3.join();
            nit4.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
         */
        //unos u obje tabele istovremeno ne moze da se vrsi bez join metode
        geografijaDAO=GeografijaDAO.getInstance();
        btnObrisiGrad.setDisable(true);
        btnIzmijeniGrad.setDisable(true);
        colGradId.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        colGradNaziv.setCellValueFactory(cellData -> cellData.getValue().nazivProperty());
        colGradStanovnika.setCellValueFactory(cellData -> cellData.getValue().broj_stanovnikaProperty().asObject());
        colGradDrzava.setCellValueFactory(cellData -> cellData.getValue().drzavaProperty());
        ObservableList<Grad> gradovi=geografijaDAO.gradoviBaze();
        ObservableList<Integer> idovi=geografijaDAO.Id_gradova();
        ObservableList<Integer> idovidrzava=geografijaDAO.IdoviDrzavaOdGradova();
        ObservableList<GradZaTableView> lista=FXCollections.observableArrayList();
        for(int i=0; i<gradovi.size(); i++)
        {
            GradZaTableView g=new GradZaTableView(idovi.get(i),gradovi.get(i).getNaziv(),gradovi.get(i).getBroj_stanovnika(),geografijaDAO.naziv_drzave(idovidrzava.get(i)));
            lista.add(g);
        }
        tableViewGradovi.setItems(lista);
        //listener na tableview
        tableViewGradovi.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Ako je odabran redak omogućimo tipke
                btnObrisiGrad.setDisable(false);
                btnIzmijeniGrad.setDisable(false);
                odabraniGrad=newValue;
            } else {
                // ako nije odabran disable
                btnObrisiGrad.setDisable(true);
                btnIzmijeniGrad.setDisable(true);
                odabraniGrad=null;
            }
        });
    }
    public GradZaTableView getOdabraniGrad()
    {
        return odabraniGrad;
    }
    public void dodajGrad(ActionEvent actionEvent) {
        try {
            // Učitavanje nove FXML datoteke za novi prozor
            FXMLLoader loader = new FXMLLoader(getClass().getResource("grad.fxml"));
            Parent root = loader.load();
            //dohvacamo kontroler
            GradController gradController = loader.getController();
            gradController.setGlavniController(this);
            // Postavljanje scene
            Scene scene = new Scene(root);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("Style.css")).toExternalForm());
            Stage stage = new Stage();
            stage.setTitle("Grad");
            stage.setScene(scene);
            stage.show();

            Stage currentStage = (Stage) btnDodajDrzavu.getScene().getWindow();
            stage.setOnHiding(event -> currentStage.show());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void dodajDrzavu(ActionEvent actionEvent) {
        try {
            // Učitavanje nove FXML datoteke za novi prozor
            FXMLLoader loader = new FXMLLoader(getClass().getResource("drzava.fxml"));
            Parent root = loader.load();
            // Postavljanje scene
            Scene scene = new Scene(root);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("Style.css")).toExternalForm());
            Stage stage = new Stage();
            stage.setTitle("Država");
            stage.setScene(scene);
            stage.show();
            //zadrzimo referencu na prosli stage
            Stage currentStage = (Stage) btnDodajDrzavu.getScene().getWindow();
            stage.setOnHiding(event -> currentStage.show());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void izmijeniGrad(ActionEvent actionEvent) {
        try {
            // Učitavanje nove FXML datoteke za novi prozor
            FXMLLoader loader = new FXMLLoader(getClass().getResource("grad.fxml"));
            Parent root = loader.load();
            //dohvacamo kontroler
            GradController gradController = loader.getController();
            gradController.setGlavniController(this);
            // Postavljanje scene
            Scene scene = new Scene(root);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("Style.css")).toExternalForm());
            Stage stage = new Stage();
            stage.setTitle("Grad");
            stage.setScene(scene);
            stage.show();

            Stage currentStage = (Stage) btnDodajDrzavu.getScene().getWindow();
            stage.setOnHiding(event -> currentStage.show());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void obrisiGrad(ActionEvent actionEvent) {
        GradZaTableView selectedItem = tableViewGradovi.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            int selectedIndex = tableViewGradovi.getSelectionModel().getSelectedIndex();
            // metodu za brisanje iz baze podataka
            geografijaDAO.obrisiGrad(selectedItem.getId());
            // uklanjanje iz tableview
            tableViewGradovi.getItems().remove(selectedIndex);
        }
    }
    public void izmijenisadrzajGrada(String novi_naziv, Integer novi_broj_stanovnika,String stari_naziv)
    {
        GradZaTableView selectedItem = tableViewGradovi.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            int selectedIndex = tableViewGradovi.getSelectionModel().getSelectedIndex();
            geografijaDAO.izmijeniGradzaBazu(novi_naziv,novi_broj_stanovnika,selectedItem.getNaziv());
            odabraniGrad.setNaziv(novi_naziv);
            odabraniGrad.setBroj_stanovnika(novi_broj_stanovnika);
            tableViewGradovi.refresh();
        }
    }
    public void dodajGradTable(String naziv, int broj_stanovnika,int drzava) throws SQLException {
        GradZaTableView selectedItem = tableViewGradovi.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            int selectedIndex = tableViewGradovi.getSelectionModel().getSelectedIndex();
            geografijaDAO.dodajGrad(new Grad(naziv,broj_stanovnika));
            geografijaDAO.postaviDrzavuodGrada(drzava,naziv);
            GradZaTableView grad=new GradZaTableView(geografijaDAO.daj_id_grada(naziv),naziv,broj_stanovnika,geografijaDAO.dajDrzavuPrekoId(drzava));
            tableViewGradovi.getItems().add(grad);
            tableViewGradovi.refresh();
        }
    }
    public int dajIdDrzave(String naziv)
    {
        int a=geografijaDAO.dajIDDrzave(naziv);
        return a;
    }
}