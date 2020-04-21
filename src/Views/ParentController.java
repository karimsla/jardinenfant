/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Entities.User;
import IServices.IserviceUser;
import IServices.ServiceUser;
import jardin.enfant.JardinEnfant;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import static jardin.enfant.JardinEnfant.authenticated;

/**
 * FXML Controller class
 *
 * @author Sami
 */
public class ParentController implements Initializable {

    @FXML
    AnchorPane body, all;
    @FXML
    private Pane trajets, activites, messages, evenements, jardins, enfants, retour, logout, profil;
    @FXML
    private Label nom;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        IserviceUser su = new ServiceUser();
        nom.setText(su.nompar());
        // TODO
        load(activites, "ConsulterActivite.fxml", body);
        load(trajets, "Chauffeurs/Consulter.fxml", body);
        load(jardins, "Jardin/AllJardin.fxml", body);
        load(evenements, "Emna/ConsulterListeEvenements.fxml", body);
        load(messages, "Messages/MessageParent.fxml", body);
        load(enfants, "Tuteur/Tuteur.fxml", body);
        load(retour, "Parent.fxml", all);

        logout();

        load(profil, "profil.fxml", body);
    }

    public void load(Pane p, String name, AnchorPane dest) {
        p.setOnMouseClicked((event) -> {
            try {
                dest.getChildren().clear();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/" + name));
                AnchorPane pane = loader.load();
                dest.getChildren().setAll(pane);
            } catch (Exception ee) {

            }
        });
    }

    public void logout() {
        logout.setOnMouseClicked((event) -> {
            try {
                //  authenticated=null;
                logout.getScene().getWindow().hide();
                new JardinEnfant().start(new Stage());
            } catch (IOException ex) {
                Logger.getLogger(ParentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
}
     
 

