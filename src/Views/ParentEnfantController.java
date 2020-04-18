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
public class ParentEnfantController implements Initializable {

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
    @FXML
    private Button aff;
    @FXML
    private Button ajou;
    @FXML
    private ComboBox<String> list_rec;
    @FXML
    private TextField txt_rech;
    @FXML
    private Button supp;
    @FXML
    private TextField count;
     public ObservableList<AbonEnf> data = FXCollections.observableArrayList();
      public ObservableList<AbonEnf> datam = FXCollections.observableArrayList();
    public ObservableList<AbonEnf> recherc = FXCollections.observableArrayList();
    int ids=6;
    String id="";
    int as=0;
    String resm="";
    @FXML
    private Button modifier;
    @FXML
    private ComboBox<String> modi;
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        data.clear();
      recherc.clear();
      modi.setDisable(true);
        modifier.setDisable(true);
      list_rec.getItems().addAll("nom","prenom","etat","type");
      modi.getItems().addAll("bus","normal");
        try{
            Connection con = (Connection) ConnexionBD.getInstance().getCnx();
            String res="SELECT en.nom,en.prenom,en.datenaiss,ab.etat,ab.type,ab.date,ab.id,pa.numtel FROM enfant en, abonnement AS ab,parent AS pa WHERE en.id=ab.enfant_id AND en.parent_id=pa.id  AND pa.id="+ids ;
          
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
        } catch (SQLException ex) {
           
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
        
         try{
            Connection cone = (Connection) ConnexionBD.getInstance().getCnx();
            String rese="SELECT Count(*) AS cou FROM abonnement,enfant WHERE abonnement.enfant_id=enfant.id AND enfant.parent_id="+ids ;
          
            Statement statement = cone.createStatement();
          
            ResultSet rse =  statement.executeQuery(rese);
            while(rse.next()){
                count.setText(rse.getString("cou"));
                
            }}
            catch(SQLException e){}
        
        // TODO
        
       
    }
    
      public void onEdit() {
        // check the table's selected item and get selected item
        if (afficher.getSelectionModel().getSelectedItem() != null) {
            AbonEnf selectedOne = afficher.getSelectionModel().getSelectedItem();
            
            id=Integer.toString(selectedOne.getId());
             modi.setDisable(false);
           modifier.setDisable(false);
            modi.setValue(selectedOne.getType());
            
        
            
            
           
        }
    }
      

        
        
        
        
        
    

    @FXML
    private void Afficher(ActionEvent event) {
    }

    @FXML
    private void redirect(ActionEvent event) {
    }

    @FXML
    private void rechercher(KeyEvent event) {
         
         recherc.clear();
         
          if(txt_rech.getText() == null){
                Alert ale= new Alert(Alert.AlertType.ERROR);
          ale.setTitle("INFORMATION");
          ale.setHeaderText("veuillez écrire quelque chose !");
          ale.showAndWait();
            }
            else{
                if (list_rec.getSelectionModel().getSelectedItem()==null){
                    Alert ale= new Alert(Alert.AlertType.ERROR);
          ale.setTitle("INFORMATION");
          ale.setHeaderText("veuillez choisir un mode !");
          ale.showAndWait();
          
                    
                }
                else{
           for (int i=0;i<data.size();i++){
               if (list_rec.getSelectionModel().getSelectedItem().equals("nom")){
            if (data.get(i).getNom().contains(txt_rech.getText())){
                
                recherc.add(data.get(i));
               
                
            }}
                if (list_rec.getSelectionModel().getSelectedItem().equals("prenom")){
            if (data.get(i).getPrenom().contains(txt_rech.getText())){
                recherc.add(data.get(i));
               
                
            }}
                 if (list_rec.getSelectionModel().getSelectedItem().equals("etat")){
            if (data.get(i).getEtat().contains(txt_rech.getText())){
                recherc.add(data.get(i));
               
                
            }}
                 if (list_rec.getSelectionModel().getSelectedItem().equals("type")){
            if (data.get(i).getType().contains(txt_rech.getText())){
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
         afficher.setOnMouseClicked((MouseEvent events) -> {
            if (events.getClickCount() > 1) {
                onEdit();
            }
        });
         
        
        
      
        
        
        
                }
        
                }
    
        
        
    }

    @FXML
    private void supprimer(ActionEvent event) {
        
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
            String res="SELECT en.nom,en.prenom,en.datenaiss,ab.etat,ab.type,ab.date,ab.id,pa.numtel FROM enfant en, abonnement AS ab,parent AS pa WHERE en.id=ab.enfant_id AND en.parent_id=pa.id AND pa.id="+ids ;
          
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
       else{
           Alert ale= new Alert(Alert.AlertType.ERROR);
          ale.setTitle("INFORMATION");
          ale.setHeaderText("Selectionnez une personne !");
          ale.showAndWait();
       }
       
          try{
            Connection cone = (Connection) ConnexionBD.getInstance().getCnx();
            String rese="SELECT Count(*) AS cou FROM abonnement,enfant WHERE abonnement.enfant_id=enfant.id AND enfant.parent_id="+ids ;
          
            Statement statement = cone.createStatement();
          
            ResultSet rse =  statement.executeQuery(rese);
            while(rse.next()){
                count.setText(rse.getString("cou"));
                
            }}
            catch(SQLException e){}
    }

    @FXML
    private void modify(ActionEvent event) {
          int ms=Integer.parseInt(id);
        resm=modi.getSelectionModel().getSelectedItem();
        
        EnfantService en= new EnfantService();
       
       
       int al= en.modifiertype(ms,resm);
         if (al>0){
          Alert ale= new Alert(Alert.AlertType.INFORMATION);
          ale.setTitle("INFORMATION");
          ale.setHeaderText("Modification faite !");
          ale.showAndWait();
          data.clear();
           try{
            Connection con = (Connection) ConnexionBD.getInstance().getCnx();
            String res="SELECT en.nom,en.prenom,en.datenaiss,ab.etat,ab.type,ab.date,ab.id FROM enfant en, abonnement AS ab,parent AS pa WHERE en.id=ab.enfant_id AND en.parent_id=pa.id AND en.parent_id="+ids;
          
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
             
                  
                 
               
                
                datam.add(p);
            }
        } catch (SQLException ex) {
           
         }
        
        nom.setCellValueFactory(new PropertyValueFactory<AbonEnf,String>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<AbonEnf,String>("prenom"));
        date.setCellValueFactory(new PropertyValueFactory<AbonEnf,Date>("datenaiss"));
         etat.setCellValueFactory(new PropertyValueFactory<AbonEnf,String>("etat"));
         type.setCellValueFactory(new PropertyValueFactory<AbonEnf,String>("type"));
        dateex.setCellValueFactory(new PropertyValueFactory<AbonEnf,Date>("date"));
        
       
        afficher.setItems(datam);
      }
        
        
        
        
    }
    
    
    
    
    
    
}
