/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import Entities.AbonEnf;
import Entities.Activite;
import Entities.Enfant;
import Entities.Parents;
import Utils.ConnexionBD;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Dorra Kerrou
 */
public class ConsulterEnfantController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
     @FXML
    private AnchorPane root;
    @FXML
    private TableView<AbonEnf> afficher;
    @FXML
    private TableColumn<AbonEnf, String> nom;
    @FXML
    private TableColumn<AbonEnf, String> prenom;
    @FXML
    private Button aff;
    
    public ObservableList<AbonEnf> data = FXCollections.observableArrayList();
    @FXML
    private TableColumn<AbonEnf, Date> date;
    @FXML
    private Button ajou;
    @FXML
    private TableColumn<AbonEnf, String> etat;
    @FXML
    private TableColumn<AbonEnf, String> type;
    @FXML
    private TableColumn<AbonEnf, Date> dateex;
    String noms="attente";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void Afficher(){
      
        try{
            Connection con = (Connection) ConnexionBD.getInstance().getCnx();
            String res="SELECT ab.etat,ab.type,ab.date FROM abonnement AS ab WHERE ab.etat LIKE '%"+noms+"%'   " ;
          
            Statement statement = con.createStatement();
          
            ResultSet rs =  statement.executeQuery(res);
            while(rs.next()){
                 AbonEnf p = new AbonEnf();
                 p.setNom(rs.getString("nom"));
                 p.setPrenom(rs.getString("prenom"));
                  p.setDatenaiss(rs.getDate("datenaiss"));
                  p.setEtat(rs.getString("etat"));
                  p.setType(rs.getString("type"));
                  p.setDate(rs.getDate("date"));
                  
                 
               
                
                data.add(p);
            }
        } catch (SQLException ex) {
           
         }
        
        nom.setCellValueFactory(new PropertyValueFactory<AbonEnf,String>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<AbonEnf,String>("prenom"));
        date.setCellValueFactory(new PropertyValueFactory<AbonEnf,Date>("datenaiss"));
         etat.setCellValueFactory(new PropertyValueFactory<AbonEnf,String>("etat"));
         type.setCellValueFactory(new PropertyValueFactory<AbonEnf,String>("type"));
        dateex.setCellValueFactory(new PropertyValueFactory<AbonEnf,Date>("date"));
       
        afficher.setItems(data);
        
        
        
        
       
    }

    @FXML
    private void redirect(ActionEvent event) throws IOException {
        if(event.getSource() == ajou){
            AnchorPane pane = FXMLLoader.load(getClass().getResource("AjouterEnfant.fxml"));
            root.getChildren().setAll(pane);
        }
    }

}