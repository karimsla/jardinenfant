/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Entities.Club;
import Utils.ConnexionBD;
import Views.Club.ModifierClubController;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dorra Kerrou
 */
public class ParentClubController implements Initializable {

    @FXML
    private TableView<Club> Table_club;
    @FXML
    private TextField Recherche;

    public ObservableList<Club> data = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Club, String> nom_club;
    @FXML
    private TableColumn<Club, String> description_club;
    @FXML
    private Button Afficher;

    private String nom;
    private String description;
    private String url;
    private String id ;

    @FXML
    private Button hezni;
    @FXML
    private AnchorPane root;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        LoadData();

        Table_club.setOnMouseClicked((MouseEvent event) -> {

            if (event.getClickCount() > 1) {

                TakeMe();

            }
        });
    }

    public void TakeMe() {
        // check the table's selected item and get selected item
        if (Table_club.getSelectionModel().getSelectedItem() != null) {
            Club selectedOne = Table_club.getSelectionModel().getSelectedItem();
            id = Integer.toString(selectedOne.getId());
            nom = selectedOne.getName();
            description = selectedOne.getDescription();
            url = selectedOne.getPhoto();

        }
    }

    @FXML
    private void Recherche(KeyEvent event) {
        data.clear();
        String nom = Recherche.getText();

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

        nom_club.setCellValueFactory(new PropertyValueFactory<Club, String>("name"));
        description_club.setCellValueFactory(new PropertyValueFactory<Club, String>("description"));
        // Photo.setCellValueFactory(new PropertyValueFactory<Club, Image>("photo"));
        Table_club.setItems(data);
    }

    @FXML
    private void TakeMeToAfficher(ActionEvent event) throws IOException {

            
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherclubParent.fxml"));

        Parent root = (Parent) loader.load();

        AfficherclubParentController mc = loader.getController();

        mc.GetData(nom, description, url, id);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void LoadData() {

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

        nom_club.setCellValueFactory(new PropertyValueFactory<Club, String>("Name"));
        description_club.setCellValueFactory(new PropertyValueFactory<Club, String>("Description"));
        //  Photo.setCellValueFactory(new PropertyValueFactory<Club, Image>("photo"));
        Table_club.setItems(data);
    }

    @FXML
    private void TakeMeAfficherClub(ActionEvent event) {
    }

  

}
