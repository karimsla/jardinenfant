/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import IServices.IserviceUser;
import IServices.ServiceUser;
import jardin.enfant.JardinEnfant;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Sami
 */
public class ResponsableController implements Initializable {
    @FXML
    AnchorPane body,all;
    @FXML
    private Pane chauffeurs, club, messages, evenements, abonnements, tuteurs;
    @FXML
    private Label jardin_nom;
    @FXML
    private Pane logout,retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        IserviceUser su = new ServiceUser();
        jardin_nom.setText(su.nomresp());
        // TODO
        load(club, "Club/ConsulterClub.fxml",body);
        load(chauffeurs, "Chauffeurs/Consulter.fxml",body);
        load(abonnements, "Enfant/ConsulterEnfant.fxml",body);
        load(evenements, "Emna/ConsulterListeEvenements.fxml",body);
        load(messages, "Messages/MessagesList.fxml",body);
        load(tuteurs, "Tuteur/Tuteur.fxml",body);
        load(retour, "Responsable.fxml", all);

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
}
