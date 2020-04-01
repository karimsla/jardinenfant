/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Emna;

import Entities.Activite;
import Entities.Evenement;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;


/**
 * FXML Controller class
 *
 * @author Emna
 */
public class ConsulterListeEvenementsController implements Initializable {
    
    
    @FXML
    private Button leAdd_btn;
    @FXML 
    private AnchorPane root;
    @FXML
    private TableView<Evenement> TV_le;
    @FXML 
    private TableColumn<Evenement, String> col_titre;
    @FXML
    private TableColumn<Evenement, String> col_cat;
    @FXML
    private TableColumn<Evenement, Date> col_date;
    @FXML
    private Button mod_btn;
    @FXML
    private Button aff_btn;
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
