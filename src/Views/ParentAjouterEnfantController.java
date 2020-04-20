/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import IServices.EnfantService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author FERID
 */
public class ParentAjouterEnfantController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private Button btn_ajt;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private DatePicker dat;
    @FXML
    private ComboBox<String> sex;
    int ids=6;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        sex.getItems().addAll("Homme","Femme");
      
    }    

    @FXML
    private void ajouter(ActionEvent event) {
        
        EnfantService en = new EnfantService();
        en.ajouterEnfant(ids, nom.getText(), prenom.getText(), dat.getValue().toString(), sex.getSelectionModel().getSelectedItem());
    }
    
}
