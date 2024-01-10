package com.example.lab1011;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class GradController {
    private GlavnaController glavniController;
    private GeografijaDAO geografijaDAO;
    @FXML
    private ChoiceBox<Drzava> choiceDrzava;
    @FXML
    private TextField fieldBrojStanovnika;
    @FXML
    private TextField fieldNaziv;
    @FXML
    private Button btnCancel;
    @FXML
    private Label upozorenjeLabel;
    public void setGlavniController(GlavnaController kontroler)
    {
        this.glavniController=kontroler;
    }
    private boolean validno(String tekst)
    {
        if(tekst.isEmpty()) return false;
        return true;
    }
    private boolean validan_broj(String tekst)
    {
        if(tekst.contains("-") || tekst.isEmpty()) return false;
        return true;
    }
    @FXML
    public void initialize()
    {
        upozorenjeLabel.setVisible(false);
        geografijaDAO=GeografijaDAO.getInstance();
        ObservableList<Drzava> drzave=geografijaDAO.dajDrzave();
        choiceDrzava.setItems(drzave);
        fieldNaziv.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if(validno(n))
                {
                    fieldNaziv.getStyleClass().removeAll("poljeNijeIspravno");
                    upozorenjeLabel.setVisible(false);
                }else
                {
                    fieldNaziv.getStyleClass().add("poljeNijeIspravno");
                    upozorenjeLabel.setText("Neispravan unos!");
                    upozorenjeLabel.setVisible(true);
                }
            }
        });
        fieldBrojStanovnika.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if(validan_broj(n))
                {
                    fieldBrojStanovnika.getStyleClass().removeAll("poljeNijeIspravno");
                    upozorenjeLabel.setVisible(false);
                }else
                {
                    fieldBrojStanovnika.getStyleClass().add("poljeNijeIspravno");
                    upozorenjeLabel.setText("Neispravan unos!");
                    upozorenjeLabel.setVisible(true);
                }
            }
        });
    }
    public void ugasiProzor(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }
    public void dodaj_ili_izmijeniGrad(ActionEvent actionEvent) throws SQLException {
        String naziv=fieldNaziv.getText();
        int broj = Integer.parseInt(fieldBrojStanovnika.getText());
        if(glavniController.getOdabraniGrad()!=null)
        {
            //ako je odabran neko u glavnom prozoru onda mijenjat
            glavniController.izmijenisadrzajGrada(naziv,broj,glavniController.getOdabraniGrad().getNaziv());
        }else
        {
            //ako nije odabran onda dodat u bazu i u tableview
            Drzava drzava=choiceDrzava.getSelectionModel().getSelectedItem();
            int id=glavniController.dajIdDrzave(drzava.getNaziv());
            glavniController.dodajGradTable(naziv,broj,id);
        }
    }
}
