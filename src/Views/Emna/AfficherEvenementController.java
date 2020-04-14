/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Emna;

import Entities.Evenement;
import Entities.Jardin;
import Utils.ConnexionBD;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Emna
 */
public class AfficherEvenementController implements Initializable {

   
    @FXML 
    private AnchorPane root;
    @FXML
    private TableView<Evenement> TV_detE;
    @FXML 
    private TableColumn<Evenement, String> col_titre;
    @FXML
    private TableColumn<Evenement, String> col_desc;
    @FXML
    private TableColumn<Evenement, Date> col_date;
    @FXML
    private TableColumn<Evenement, Jardin> col_jardin;
    
    public ObservableList<Evenement> data = FXCollections.observableArrayList();
   
    @FXML
    private ImageView IV_event;
    @FXML
    private Button supp_btn;
    
     @FXML
    private Button retour_btn;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         try{
            Connection con = (Connection) ConnexionBD.getInstance().getCnx();
            String res="SELECT titre FROM evenement " ;
          
            Statement statement = con.createStatement();
          
            ResultSet rs =  statement.executeQuery(res);
            while(rs.next()){
                 Evenement p = new Evenement();
                 p.setTitre(rs.getString("titre"));
                 
                  
                 
               
                
                data.add(p);
            }
        } catch (SQLException ex) {
           
         }
        
        col_titre.setCellValueFactory(new PropertyValueFactory<Evenement,String>("titre"));
        
       
        TV_detE.setItems(data);
  
    }    
    
    
    
    
}
