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

public class DrzavaController {
    private GeografijaDAO geografijaDAO;
    @FXML
    private ChoiceBox<Grad> choiceGrad;
    @FXML
    private Button btnOk;
    @FXML
    private Button btnCancel;
    @FXML
    private TextField fieldNaziv;
    @FXML
    private Label upozorenjeLabel;
    private boolean validno(String tekst)
    {
        if(tekst.isEmpty()) return false;
        return true;
    }
    @FXML
    public void initialize()
    {
        upozorenjeLabel.setVisible(false);
        geografijaDAO=GeografijaDAO.getInstance();
        ObservableList<Grad> gradovi=geografijaDAO.dajGlavneGradove();
        choiceGrad.setItems(gradovi);
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
    }
    public void dodajDrzavu(ActionEvent actionEvent) {
        String naziv=fieldNaziv.getText();
        Grad grad=choiceGrad.getValue();
        Drzava postoji=geografijaDAO.nadjiDrzavu(naziv);
        if(postoji!=null)
        {
            upozorenjeLabel.setText("Vec postoji!");
            upozorenjeLabel.setVisible(true);
            return;
        }
        int id=geografijaDAO.daj_id_grada(grad.getNaziv());
        Drzava drzava=new Drzava(naziv);
        geografijaDAO.dodajDrzave(drzava);
        geografijaDAO.postavi_Glavnigrad(id,naziv);
    }

    public void ugasiProzor(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }
}
