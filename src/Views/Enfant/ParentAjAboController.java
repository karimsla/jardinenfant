/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Enfant;

import IServices.EnfantService;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author FERID
 */
public class ParentAjAboController implements Initializable {

    @FXML
    private ComboBox<String> enf;
    @FXML
    private ComboBox<String> type;
    @FXML
    private Button btn_ajt;
    @FXML
    private TextField montant;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouter(ActionEvent event) {
        
        
        EnfantService en = new EnfantService();
        en.ajouterAbonnement(1, 7, "2019-02-25", "bus", "attente", "555");
        
    }
    
}
