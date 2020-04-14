/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Emna;
import Entities.Categorie;
import IServices.CategorieService;
import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;



/**
 * FXML Controller class
 *
 * @author Emna
 */
public class AjoutCategorieController implements Initializable {
    
    @FXML
    private AnchorPane root;
    @FXML
    private TextField libelle_cat;
    @FXML
    private Button ajout_btn;
    
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
  /**  @FXML
    private void Ajouter(ActionEvent event) throws IOException
    {
      
        String libelle = libelle_cat.getText();
        ZoneId defaultZoneId = ZoneId.systemDefault();
     
        Categorie C= new Categorie (libelle);
        CategorieService CS = new CategorieService();
        CS.ajouter(C);
         AnchorPane pane = FXMLLoader.load(getClass().getResource("AjoutCategorie.fxml"));
            root.getChildren().setAll(pane);
        
        
}*/
}
