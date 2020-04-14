/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import Entities.AbonEnf;
import IServices.EnfantService;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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
    @FXML
    private Button ajou;
    @FXML
    private Button supp;
    String id="";
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try{
            Connection con = (Connection) ConnexionBD.getInstance().getCnx();
            String res="SELECT en.nom,en.prenom,en.datenaiss,ab.etat,ab.type,ab.date,ab.id FROM enfant en,abonnement AS ab WHERE ab.etat LIKE '%"+noms+"%'AND en.id=ab.enfant_id " ;
          
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
                  p.setId(rs.getInt("id"));
                  
                 
               
                
                data.add(p);
            }
        }
      
       
       catch (SQLException ex) {
           
         }
        
        nom.setCellValueFactory(new PropertyValueFactory<AbonEnf,String>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<AbonEnf,String>("prenom"));
        date.setCellValueFactory(new PropertyValueFactory<AbonEnf,Date>("datenaiss"));
         etat.setCellValueFactory(new PropertyValueFactory<AbonEnf,String>("etat"));
         type.setCellValueFactory(new PropertyValueFactory<AbonEnf,String>("type"));
        dateex.setCellValueFactory(new PropertyValueFactory<AbonEnf,Date>("date"));
       
        afficher.setItems(data);
        
          afficher.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() > 1) {
                onEdit();
            }
        });
        
    }    
    public void onEdit() {
        // check the table's selected item and get selected item
        if (afficher.getSelectionModel().getSelectedItem() != null) {
            AbonEnf selectedOne = afficher.getSelectionModel().getSelectedItem();
            id=Integer.toString(selectedOne.getId());
            
           
        }
    }

    @FXML
    private void modif(ActionEvent event) {
        EnfantService en= new EnfantService();
        int mo=Integer.parseInt(id);
      int al=en.modifier(mo);
      if (al>0){
          Alert ale= new Alert(Alert.AlertType.INFORMATION);
          ale.setTitle("INFORMATION");
          ale.setHeaderText("modification faite !");
          ale.showAndWait();
          data.clear();
          try{
            Connection con = (Connection) ConnexionBD.getInstance().getCnx();
            String res="SELECT en.nom,en.prenom,en.datenaiss,ab.etat,ab.type,ab.date,ab.id FROM enfant en,abonnement AS ab WHERE ab.etat LIKE '%"+noms+"%'AND en.id=ab.enfant_id " ;
          
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
                  p.setId(rs.getInt("id"));
                  
                 
               
                
                data.add(p);
            }
        }
      
       
       catch (SQLException ex) {
           
         }
        
        nom.setCellValueFactory(new PropertyValueFactory<AbonEnf,String>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<AbonEnf,String>("prenom"));
        date.setCellValueFactory(new PropertyValueFactory<AbonEnf,Date>("datenaiss"));
         etat.setCellValueFactory(new PropertyValueFactory<AbonEnf,String>("etat"));
         type.setCellValueFactory(new PropertyValueFactory<AbonEnf,String>("type"));
        dateex.setCellValueFactory(new PropertyValueFactory<AbonEnf,Date>("date"));
       
        afficher.setItems(data);
        
      }
      else {
           Alert aler= new Alert(Alert.AlertType.ERROR);
            aler.setTitle("erreur !");
            aler.setHeaderText("Erreur");
            aler.showAndWait();
          
      }
        
        
    }

    @FXML
    private void supprime(ActionEvent event) {
        
        EnfantService ens= new EnfantService();
        int mos=Integer.parseInt(id);
       int as= ens.supprimer(mos);
       if (as>0){
          Alert ale= new Alert(Alert.AlertType.INFORMATION);
          ale.setTitle("INFORMATION");
          ale.setHeaderText("suppression faite !");
          ale.showAndWait();
          data.clear();
          try{
            Connection con = (Connection) ConnexionBD.getInstance().getCnx();
            String res="SELECT en.nom,en.prenom,en.datenaiss,ab.etat,ab.type,ab.date,ab.id FROM enfant en,abonnement AS ab WHERE ab.etat LIKE '%"+noms+"%'AND en.id=ab.enfant_id " ;
          
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
                  p.setId(rs.getInt("id"));
                  
                 
               
                
                data.add(p);
            }
        }
      
       
       catch (SQLException ex) {
           
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
    
}
