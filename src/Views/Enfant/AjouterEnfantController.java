/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Enfant;

import Entities.AbonEnf;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    public ObservableList<AbonEnf> recherc = FXCollections.observableArrayList();
    @FXML
    private Button ajou;
    @FXML
    private Button supp;
    String id="";
    @FXML
    private TextField txt_re;
    @FXML
    private ComboBox<String> cmb_rec;
    @FXML
    private Button btn_rc;
    @FXML
    private Button rtn;
    int as=0;
    int al=0;
    @FXML
    private TextField compt;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmb_rec.getItems().addAll("nom","prenom","etat","type");
        try{
            Connection cone = (Connection) ConnexionBD.getInstance().getCnx();
            String rese="SELECT Count(*) AS cou FROM  abonnement WHERE etat LIKE '%"+noms+"%' " ;
          
            Statement statement = cone.createStatement();
          
            ResultSet rse =  statement.executeQuery(rese);
            while(rse.next()){
                compt.setText(rse.getString("cou"));
                
            }}
            catch(SQLException e){}
       
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
        try{
        int mo=Integer.parseInt(id);
         al=en.modifier(mo);}
        catch(Exception e){}
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
            aler.setHeaderText("Veuillez selectionner une personne!");
            aler.showAndWait();
          
      }
        
        
    }

    @FXML
    private void supprime(ActionEvent event) {
        
        EnfantService ens= new EnfantService();
        try{
        int mos=Integer.parseInt(id);
        as= ens.supprimer(mos);}
         catch (Exception e){}
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
       else{
            Alert ale= new Alert(Alert.AlertType.ERROR);
          ale.setTitle("INFORMATION");
          ale.setHeaderText("Selectionnez une personne !");
          ale.showAndWait();
       }
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
     
          if(event.getSource() == rtn){
            AnchorPane pane = FXMLLoader.load(getClass().getResource("ConsulterEnfant.fxml"));
            root.getChildren().setAll(pane);
        }
    }

    @FXML
    private void rechercher(KeyEvent event) {
         recherc.clear();
       
         if(txt_re.getText().equals(null)){
                Alert ale= new Alert(Alert.AlertType.ERROR);
          ale.setTitle("INFORMATION");
          ale.setHeaderText("veuillez écrire quelque chose !");
          ale.showAndWait();
            }
            else{
                if (cmb_rec.getSelectionModel().getSelectedItem()==null){
                    Alert ale= new Alert(Alert.AlertType.ERROR);
          ale.setTitle("INFORMATION");
          ale.setHeaderText("veuillez choisir un mode !");
          ale.showAndWait();
          
                    
                }
                else{
         
           for (int i=0;i<data.size();i++){
               if (cmb_rec.getSelectionModel().getSelectedItem().equals("nom")){
            if (data.get(i).getNom().contains(txt_re.getText())){
                
                recherc.add(data.get(i));
               
                
            }}
                if (cmb_rec.getSelectionModel().getSelectedItem().equals("prenom")){
            if (data.get(i).getPrenom().contains(txt_re.getText())){
                recherc.add(data.get(i));
               
                
            }}
                 if (cmb_rec.getSelectionModel().getSelectedItem().equals("etat")){
            if (data.get(i).getEtat().contains(txt_re.getText())){
                recherc.add(data.get(i));
               
                
            }}
                 if (cmb_rec.getSelectionModel().getSelectedItem().equals("type")){
            if (data.get(i).getType().contains(txt_re.getText())){
                recherc.add(data.get(i));
               
                
            }}
               
        }
         
        
        nom.setCellValueFactory(new PropertyValueFactory<AbonEnf,String>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<AbonEnf,String>("prenom"));
        date.setCellValueFactory(new PropertyValueFactory<AbonEnf,Date>("datenaiss"));
         etat.setCellValueFactory(new PropertyValueFactory<AbonEnf,String>("etat"));
         type.setCellValueFactory(new PropertyValueFactory<AbonEnf,String>("type"));
        dateex.setCellValueFactory(new PropertyValueFactory<AbonEnf,Date>("date"));
       
        afficher.setItems(recherc);
        
        
        
        
        
        
        
        
                }}
        
    }

   
    

   
    
}
