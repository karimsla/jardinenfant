/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Entities.Activite;
import IServices.ActiviteServices;
import Utils.ConnexionBD;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import static java.time.temporal.TemporalQueries.localDate;

import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Dorra Kerrou
 */
public class AjoutActiviteController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private TextField act_label;
    @FXML
    private TextArea description_label;
    @FXML
    private DatePicker date_picker;
    @FXML
    private Button ajout_btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Ajouter(ActionEvent event) throws IOException {
      
        String activite = act_label.getText();
        String description = description_label.getText();
        ZoneId defaultZoneId = ZoneId.systemDefault();
        LocalDate date = date_picker.getValue();
        Date dateA = (Date) Date.from(date.atStartOfDay(defaultZoneId).toInstant());
        
        Activite A = new Activite(activite,description, dateA);
        ActiviteServices AS = new ActiviteServices();
        AS.ajouter(A);
         AnchorPane pane = FXMLLoader.load(getClass().getResource("FXML.fxml"));
            root.getChildren().setAll(pane);
        
        
        
       
    }
    
}
