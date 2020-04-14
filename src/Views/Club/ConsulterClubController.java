/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Club;

import Entities.Club;
import IServices.ClubServices;
import Utils.ConnexionBD;
import Views.ConsulterActiviteController;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Dorra Kerrou
 */
public class ConsulterClubController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private TableView<Club> club_liste;
    @FXML
    private TableColumn<Club, Image> Photo;
    @FXML
    private TableColumn<Club, String> nom_Club;
    @FXML
    private TableColumn<Club, String> Description;

    public ObservableList<Club> data = FXCollections.observableArrayList();

    public ObservableList<Club> data1 = FXCollections.observableArrayList();

    @FXML
    private Button Ajouter;
    @FXML
    private Button Modifier;
    private Button Annuler;
    @FXML
    private TextField mod_club;
    @FXML
    private TextArea mod_descr;
    @FXML
    private Button Refresh;

    private String id;
    @FXML
    private Button Supprimer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Afficher();
        // TODO

        club_liste.setOnMouseClicked((MouseEvent event) -> {

            if (event.getClickCount() > 1) {

                onEdit();

            }
        });
        
     

    }

    public void onEdit() {
        // check the table's selected item and get selected item
        if (club_liste.getSelectionModel().getSelectedItem() != null) {
            Club selectedOne = club_liste.getSelectionModel().getSelectedItem();
            id = Integer.toString(selectedOne.getId());

            mod_club.setText(selectedOne.getName());
            mod_descr.setText(selectedOne.getDescription());

        }
    }

    private void Afficher() {

        data.clear();
        try {
            Connection con = (Connection) ConnexionBD.getInstance().getCnx();
            String res = "SELECT id,Name, Description , photo FROM Club";

            Statement statement = con.createStatement();

            ResultSet rs = statement.executeQuery(res);
            while (rs.next()) {
                Club C = new Club();

                C.setId(rs.getInt("id"));
                C.setName(rs.getString("Name"));
                C.setDescription(rs.getString("Description"));
                C.setPhoto(rs.getString("photo"));

                data.add(C);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsulterActiviteController.class.getName()).log(Level.SEVERE, null, ex);
        }

        nom_Club.setCellValueFactory(new PropertyValueFactory<Club, String>("Name"));
        Description.setCellValueFactory(new PropertyValueFactory<Club, String>("Description"));
        Photo.setCellValueFactory(new PropertyValueFactory<Club, Image>("photo"));
        club_liste.setItems(data);
    }

    @FXML
    private void Ajouter(ActionEvent event) throws IOException {
        if (event.getSource() == Ajouter) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("AjoutClub.fxml"));
            root.getChildren().setAll(pane);
        }
    }

    @FXML
    private void Modifier(ActionEvent event) {

        String club_nom = mod_club.getText();

        String descp = mod_descr.getText();
        int i = Integer.parseInt(id);
        ClubServices S = new ClubServices();
        int AS = S.modifier(club_nom, descp, i);
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

    }

    private void Annuler(ActionEvent event) throws IOException {
        if (event.getSource() == Annuler) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("Views/Accueil.fxml"));
            root.getChildren().setAll(pane);
        }
    }

    @FXML
    private void Refresh(ActionEvent event) {

        Afficher();
    }

    @FXML
    private void DELETE(ActionEvent event) {
         int i = Integer.parseInt(id);
        ClubServices S = new ClubServices();
        int AS = S.Delete(i);
        if (AS > 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Suppression est faite");
            alert.setHeaderText("INFO");
            alert.showAndWait();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("erroooooor");
            alert.showAndWait();
        }
    }

}
