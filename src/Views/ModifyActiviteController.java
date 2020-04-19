/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Entities.Activite;
import Entities.Club;
import IServices.ActiviteServices;
import Utils.ConnexionBD;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Dorra Kerrou
 */
public class ModifyActiviteController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private TextField act_label;
    @FXML
    private TextArea description_label;
    @FXML
    private TextField date_picker;
    @FXML
    private Button mod_btn;
    @FXML
    private Button vider_btn;
    @FXML
    private ComboBox<String> Recherche;

    public ObservableList data = FXCollections.observableArrayList();
    @FXML
    private Button supp_btn;
    @FXML
    private Button annuler_btn;
    @FXML
    private ComboBox<String> club_box;

    public ObservableList nom = FXCollections.observableArrayList();

    HashMap<String, Integer> map;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        map = new HashMap<>();
        LoadData();
        for (int i = 0; i < data.size(); i++) {
            Recherche.setValue((String) data.get(i));
        }
        Recherche.setItems(data);

        for (int i = 0; i < nom.size(); i++) {
            club_box.setValue((String) nom.get(i));
        }
        club_box.setItems(nom);

    }

    @FXML
    private void Modifier(ActionEvent event) throws IOException, ParseException {
        String nom = Recherche.getSelectionModel().getSelectedItem();
        String club = club_box.getSelectionModel().getSelectedItem();
        int id = map.get(club);
        // int id = Integer.parseInt(nom);
        if (club_box.getSelectionModel().getSelectedIndex() != -1) {
            if (act_label.getText().matches("[a-zA-Z]*")) {
                if (!act_label.getText().equals("")) {
                    if (!description_label.getText().equals("")) {
                        if (!date_picker.getText().equals("")) {
                            String activite = act_label.getText();
                            String descp = description_label.getText();
                            String date = date_picker.getText();
                            Activite A = new Activite();
                            // A.setId(id);
                            A.setTypeact(activite);
                            A.setDetailles(descp);
                            A.setDate(date);
                            Club c = new Club();
                            c.setId(id);
                            A.setClub(c);
                            int AS;
                            AS = ActiviteServices.modifier(A);
                            if (AS > 0) {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("modification est faite");
                                alert.setHeaderText("INFO");
                                alert.showAndWait();

                            } else {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("nop");
                                alert.showAndWait();
                            }
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("A FIELD IS MISSING");
                            alert.setContentText("LA DATE YA MADAME");

                            alert.showAndWait();
                        }
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("A FIELD IS MISSING");
                        alert.setContentText("LA DESCRIPTION YA MADAME");
                        alert.showAndWait();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("A FIELD IS MISSING");
                    alert.setContentText("WOH TITRE DE L'ACIVITE");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);

                alert.setTitle("hja okhraa");
                alert.setContentText("LE TITRE :) :)  ");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("A FIELD IS MISSING");
            alert.setContentText("L CLUUB IS MISSING");
            alert.showAndWait();
        }

    }

    @FXML
    private void Trouver(ActionEvent event
    ) {

        String AID = Recherche.getSelectionModel().getSelectedItem();

        Activite A = ActiviteServices.findActivite(AID);

        act_label.setText(A.getTypeact());
        description_label.setText(A.getDetailles());
        date_picker.setText(A.getDate());
        int idclub = A.getClub().getId();
        Club C = ActiviteServices.findNomClub(idclub);
        String nomClub = C.getName();
        club_box.setValue(nomClub);
    }

    @FXML
    private void Vider(ActionEvent event
    ) {

        act_label.clear();
        description_label.clear();
        date_picker.clear();
    }

    private void LoadData() {

        try {

            String res = "SELECT a.typeact , c.id , c.Name   FROM Activite a , Club AS c ";
            Connection con = (Connection) ConnexionBD.getInstance().getCnx();
            Statement statement = con.prepareStatement(res);
            ResultSet rs = statement.executeQuery(res);
            while (rs.next()) {
                data.add(rs.getString("typeact"));
                nom.add(rs.getString("Name"));
                map.put(rs.getString("Name"), rs.getInt("id"));
            }

        } catch (SQLException ex) {
            System.err.println(ex);;
        }

    }

    @FXML
    private void Supprimer(ActionEvent event) {
        String nom = Recherche.getSelectionModel().getSelectedItem();
        int AS;
        AS = ActiviteServices.Delete(nom);
        if (AS > 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Supprimer");
            alert.setHeaderText("Suppression est faite");
            alert.showAndWait();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("nop not yet");
            alert.showAndWait();
        }

    }

    @FXML
    private void Retour(ActionEvent event) throws IOException {
        if (event.getSource() == annuler_btn) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("ConsulterActivite.fxml"));
            root.getChildren().setAll(pane);
        }

    }
//Date d=Date.valueOf(dateToConvert);
}
