/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import Entities.AdmEnf;
import IServices.EnfantService;
import Utils.ConnexionBD;
import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author FERID
 */
public class ParentAfficherEnfController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private TableView<AdmEnf> enfcons;
    @FXML
    private TableColumn<AdmEnf, String> nom;
    @FXML
    private TableColumn<AdmEnf, String> prenom;
    @FXML
    private TableColumn<AdmEnf, Date> date;
    @FXML
    private TableColumn<AdmEnf, String> sexe;
    
    public ObservableList<AdmEnf> data = FXCollections.observableArrayList();
    public ObservableList<AdmEnf> recherc = FXCollections.observableArrayList();
    @FXML
    private Button btn_supp;
    String id="";
    int as=0;
    @FXML
    private TextField txt_r;
    @FXML
    private ComboBox<String> cmb_r;
    int ids=6;
    @FXML
    private Button ajouter;
    @FXML
    private Button modifier;
    int pid;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         cmb_r.getItems().addAll("nom","prenom");
        
        
        
         try{
            Connection con = (Connection) ConnexionBD.getInstance().getCnx();
            String res="SELECT en.nom,en.prenom,en.datenaiss,en.sexe,en.id,ab.nom,ab.prenom FROM enfant en,parent AS ab WHERE en.parent_id=ab.id AND ab.id="+ids  ;
          
            Statement statement = con.createStatement();
          //
            ResultSet rs =  statement.executeQuery(res);
            while(rs.next()){
                 AdmEnf p = new AdmEnf();
                 p.setNom(rs.getString("nom"));
                 p.setPrenom(rs.getString("prenom"));
                  p.setDate(rs.getDate("datenaiss"));
                  p.setSexe(rs.getString("sexe"));
                  p.setJardin(rs.getString("ab.nom")+" "+rs.getString("ab.prenom"));
                  p.setId(rs.getInt("id"));
                  
            
               
                
                data.add(p);
            }
        } catch (SQLException ex) {
           
         }
        
        nom.setCellValueFactory(new PropertyValueFactory<AdmEnf,String>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<AdmEnf,String>("prenom"));
        date.setCellValueFactory(new PropertyValueFactory<AdmEnf,Date>("date"));
         sexe.setCellValueFactory(new PropertyValueFactory<AdmEnf,String>("sexe"));
         
        
       
        enfcons.setItems(data);
        
        enfcons.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() > 1) {
                onEdit();
            }
        });
        
       
    }  
    
     public void onEdit() {
        // check the table's selected item and get selected item
        if (enfcons.getSelectionModel().getSelectedItem() != null) {
            AdmEnf selectedOne = enfcons.getSelectionModel().getSelectedItem();
            id=Integer.toString(selectedOne.getId());
            pid=selectedOne.getId();
            
            System.out.println(selectedOne.getId());
            
             ParentModifierEnfantController.getid(pid);
           // 
            
           
        }
    }
        

    @FXML
    private void supprimer(ActionEvent event) {
        
          EnfantService ens= new EnfantService();
         try{
        int mos=Integer.parseInt(id);
          as= ens.supprimerAdm(mos);
         }
         catch (Exception e){}
        
      
       if (as>0){
          Alert ale= new Alert(Alert.AlertType.INFORMATION);
          ale.setTitle("INFORMATION");
          ale.setHeaderText("suppression faite !");
          ale.showAndWait();
          
          
          data.clear();
          try{
            Connection con = (Connection) ConnexionBD.getInstance().getCnx();
            String res="SELECT en.nom,en.prenom,en.datenaiss,en.sexe,en.id,ab.nom,ab.prenom FROM enfant en,parent AS ab WHERE en.parent_id=ab.id AND ab.id="+ids  ;
          
            Statement statement = con.createStatement();
          
            ResultSet rs =  statement.executeQuery(res);
            while(rs.next()){
                 AdmEnf p = new AdmEnf();
                 p.setNom(rs.getString("nom"));
                 p.setPrenom(rs.getString("prenom"));
                  p.setDate(rs.getDate("datenaiss"));
                  p.setSexe(rs.getString("sexe"));
                  p.setJardin(rs.getString("ab.nom")+" "+rs.getString("ab.prenom"));
                  p.setId(rs.getInt("id"));
                  
            
               
                
                data.add(p);
            }
        } catch (SQLException ex) {
           
         }
        
        nom.setCellValueFactory(new PropertyValueFactory<AdmEnf,String>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<AdmEnf,String>("prenom"));
        date.setCellValueFactory(new PropertyValueFactory<AdmEnf,Date>("date"));
         sexe.setCellValueFactory(new PropertyValueFactory<AdmEnf,String>("sexe"));
        
        
       
        enfcons.setItems(data);
          
      }
        
       
        else{
            Alert ale= new Alert(Alert.AlertType.ERROR);
          ale.setTitle("INFORMATION");
          ale.setHeaderText("Selectionnez une personne !");
          ale.showAndWait();
       
       
       }
        
    }

    @FXML
    private void rechercher(KeyEvent event) {
        recherc.clear();
            if(txt_r.getText().equals(null)){
                Alert ale= new Alert(Alert.AlertType.ERROR);
          ale.setTitle("INFORMATION");
          ale.setHeaderText("veuillez écrire quelque chose !");
          ale.showAndWait();
            }
            else{
                if (cmb_r.getSelectionModel().getSelectedItem()==null){
                    Alert ale= new Alert(Alert.AlertType.ERROR);
          ale.setTitle("INFORMATION");
          ale.setHeaderText("veuillez choisir un mode !");
          ale.showAndWait();
          
                    
                }
                else {
           for (int i=0;i<data.size();i++){
               if (cmb_r.getSelectionModel().getSelectedItem().equals("nom")){
            if (data.get(i).getNom().contains(txt_r.getText())){
                
                recherc.add(data.get(i));
               
                
            }}
                if (cmb_r.getSelectionModel().getSelectedItem().equals("prenom")){
            if (data.get(i).getPrenom().contains(txt_r.getText())){
                recherc.add(data.get(i));
               
                
            }}
           
                 
            }
               
        
         
        
        nom.setCellValueFactory(new PropertyValueFactory<AdmEnf,String>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<AdmEnf,String>("prenom"));
        date.setCellValueFactory(new PropertyValueFactory<AdmEnf,Date>("datenaiss"));
        sexe.setCellValueFactory(new PropertyValueFactory<AdmEnf,String>("sexe"));
         
       
        enfcons.setItems(recherc);
                }
            }
        
        
        
        
        
        
    }

    @FXML
    private void redirect(ActionEvent event) throws IOException {
        if(event.getSource() == ajouter){
            AnchorPane pane = FXMLLoader.load(getClass().getResource("ParentAjouterEnfant.fxml"));
            root.getChildren().setAll(pane);
        }
        
    }
   

    @FXML
    private void modifredirect(ActionEvent event) throws IOException {
        
         if(event.getSource() == modifier){
             
             
            AnchorPane pane = FXMLLoader.load(getClass().getResource("ParentModifierEnfant.fxml"));
            root.getChildren().setAll(pane);
            
        }
    }
        
    
}
