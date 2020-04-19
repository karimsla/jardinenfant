/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Club;

import Entities.Activite;
import Entities.Club;
import IServices.ClubServices;
import Utils.ConnexionBD;
import Views.ConsulterActiviteController;
import java.io.File;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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

    private String id;
    @FXML
    private Button Supprimer;
    @FXML
    private TextField nom_text;
    @FXML
    private Button search;
    @FXML
    private Button afficher;

    private String nom;
    private String description;
    private String url;
    
    
    private Image image;

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
            nom = selectedOne.getName();
            description = selectedOne.getDescription();
            url = selectedOne.getPhoto();
            

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
                // C.setPhoto(rs.getString("photo"));

                data.add(C);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsulterActiviteController.class.getName()).log(Level.SEVERE, null, ex);
        }

        nom_Club.setCellValueFactory(new PropertyValueFactory<Club, String>("Name"));
        Description.setCellValueFactory(new PropertyValueFactory<Club, String>("Description"));
        //  Photo.setCellValueFactory(new PropertyValueFactory<Club, Image>("photo"));
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
    private void Modifier(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierClub.fxml"));

        Parent root = (Parent) loader.load();

        ModifierClubController mc = loader.getController();
        
        
      

        mc.GetData(nom, description, url,image);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
    /*  private void Annuler(ActionEvent event) throws IOException {
        if (event.getSource() == Annuler) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("Views/Accueil.fxml"));
            root.getChildren().setAll(pane);
        }*/
    

    @FXML
    private void DELETE(ActionEvent event
    ) {
        int i = Integer.parseInt(id);
        ClubServices S = new ClubServices();
        int AS = S.Delete(i);
        if (AS > 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Suppression est faite");
            alert.setHeaderText("INFO");
            alert.showAndWait();
            Afficher();
            mod_club.clear();
            mod_descr.clear();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("erroooooor");
            alert.showAndWait();
        }
    }

    @FXML
    private void web(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/Views/WebView.fxml"));
        root.getChildren().setAll(pane);
    }

    @FXML
    private void Search() {

        data.clear();
        String nom = nom_text.getText();

        try {
            Connection con = (Connection) ConnexionBD.getInstance().getCnx();
            String res = "Select * from Club where name Like '%" + nom + "%' ";

            Statement statement = con.createStatement();

            ResultSet rs = statement.executeQuery(res);
            while (rs.next()) {
                Club p = new Club();
                p.setName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                // p.setPhoto(rs.get("photo"));

                data.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsulterActiviteController.class.getName()).log(Level.SEVERE, null, ex);
        }

        nom_Club.setCellValueFactory(new PropertyValueFactory<Club, String>("name"));
        Description.setCellValueFactory(new PropertyValueFactory<Club, String>("description"));
        Photo.setCellValueFactory(new PropertyValueFactory<Club, Image>("photo"));
        club_liste.setItems(data);

    }

    @FXML
    private void TakeMeToAffichage(ActionEvent event) throws IOException {
        if (event.getSource() == afficher) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("Affichage.fxml"));
            root.getChildren().setAll(pane);
        }
    }

}
