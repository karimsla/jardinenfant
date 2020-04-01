/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import Entities.AbonEnf;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author FERID
 */
public class AjouterEnfantController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private TableView<AbonEnf> afficher;
    @FXML
    private TableColumn<AbonEnf, String> nom;
    @FXML
    private TableColumn<AbonEnf, String> prenom;
    @FXML
    private TableColumn<AbonEnf, Date> date;
    @FXML
    private TableColumn<AbonEnf, String> etat;
    @FXML
    private TableColumn<AbonEnf, String> type;
    @FXML
    private TableColumn<AbonEnf, Date> dateex;
    String noms="attente";
    public ObservableList<AbonEnf> data = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try{
            Connection con = (Connection) ConnexionBD.getInstance().getCnx();
            String res="SELECT en.nom,en.prenom,en.datenaiss,ab.etat,ab.type,ab.date FROM enfant en,abonnement AS ab WHERE ab.etat LIKE '%"+noms+"%'AND en.id=ab.enfant_id " ;
          
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
    
}
