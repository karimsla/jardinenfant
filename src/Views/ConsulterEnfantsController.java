/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Entities.Activite;
import Entities.Enfant;
import Utils.ConnexionBD;
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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author FERID
 */
public class ConsulterEnfantsController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private TableView<Enfant> afficher;
    @FXML
    private TableColumn<Enfant, String> nom;
    @FXML
    private TableColumn<Enfant, String> prenom;
    @FXML
    private Button aff;
    
    public ObservableList<Enfant> data = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    private void Afficher(){

        try{
            Connection con = (Connection) ConnexionBD.getInstance().getCnx();
            String res="SELECT nom,prenom FROM `enfant" ;
          
            Statement statement = con.createStatement();
          
            ResultSet rs =  statement.executeQuery(res);
            while(rs.next()){
                 Enfant p = new Enfant();
                 p.setNom(rs.getString("nom"));
                 p.setPrenom(rs.getString("prenom"));
               
                
                data.add(p);
            }
        } catch (SQLException ex) {
             Logger.getLogger(views.ConsulterActiviteController.class.getName()).log(Level.SEVERE, null, ex);
         }
        
        nom.setCellValueFactory(new PropertyValueFactory<Enfant,String>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<Enfant,String>("prenom"));
       
        afficher.setItems(data);
        
        
        
        
       
    }
    
    
}
