/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Club;

import Entities.Activite;
import Entities.Club;
import Utils.ConnexionBD;
import Views.ConsulterActiviteController;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Dorra Kerrou
 */
public class ConsulterClubController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private TableView<Club> club_liste ;
    @FXML
    private TableColumn<Club,Image> Photo;
    @FXML
    private TableColumn<Club, String> nom_Club;
    @FXML
    private TableColumn<Club, String> Description;
    
    
    
    public ObservableList<Club> data = FXCollections.observableArrayList();
    @FXML
    private Button Ajouter;
    @FXML
    private Button Modifier;
    @FXML
    private Button Annuler;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Afficher();
        // TODO
    }    
    
    
     private void Afficher(){
        

        
      
        try{
            Connection con = (Connection) ConnexionBD.getInstance().getCnx();
            String res="SELECT Name, Description , photo FROM Club" ;
          
            Statement statement = con.createStatement();
          
            ResultSet rs =  statement.executeQuery(res);
            while(rs.next()){
                 Club C = new Club();
                 C.setName(rs.getString("Name"));
                 C.setDescription(rs.getString("Description"));
                 C.setPhoto(rs.getString("photo"));
                
                data.add(C);
            }
        } catch (SQLException ex) {
             Logger.getLogger(ConsulterActiviteController.class.getName()).log(Level.SEVERE, null, ex);
         }
        
        
        nom_Club.setCellValueFactory(new PropertyValueFactory<Club,String>("Name"));
        Description.setCellValueFactory(new PropertyValueFactory<Club,String>("Description"));
        Photo.setCellValueFactory(new PropertyValueFactory<Club,Image>("photo"));
        club_liste.setItems(data);
}

    @FXML
    private void Ajouter(ActionEvent event) throws IOException {
         if(event.getSource() == Ajouter){
            AnchorPane pane = FXMLLoader.load(getClass().getResource("AjoutClub.fxml"));
            root.getChildren().setAll(pane);
        }
    }

    @FXML
    private void Modifier(ActionEvent event) {
    }

    @FXML
    private void Annuler(ActionEvent event) {
    }


}