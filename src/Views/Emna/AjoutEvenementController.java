/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Emna;

import Entities.Evenement;
import IServices.EvenementService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Emna
 */
public class AjoutEvenementController implements Initializable {
 
    @FXML
    private AnchorPane root;
    @FXML
    private TextField titre_event ;
    @FXML
    private DatePicker date_picker;
    @FXML
    private TextArea desc_event;
    @FXML
    private Button ajout_bt;
    
    @FXML
    private ListView list_cat;
    @FXML
    private ListView list_jardin;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
     @FXML
    private void Ajouter(ActionEvent event) throws IOException {
      
        String titre = titre_event.getText();
          LocalDate date = date_picker.getValue();
        ZoneId defaultZoneId = null;
        Date dateE = (Date) Date.from(date.atStartOfDay(defaultZoneId).toInstant());
        String description = desc_event.getText();
      
        
        Evenement E = new Evenement(titre,dateE,description,"");
        EvenementService ES = new EvenementService();
        ES.Ajouter(E);
         AnchorPane pane = FXMLLoader.load(getClass().getResource("AjoutEvenement.fxml"));
            root.getChildren().setAll(pane);
        
        
        
       
    }
    
}
