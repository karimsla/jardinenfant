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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import com.teknikindustries.bulksms.SMS;
import java.net.URL;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



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
    @FXML
    private TextField mod;
    @FXML
    private Button modifier;
    String id="";
    String res="";
    String te=""; 
   @FXML
    private Button btn_sms;
    @FXML
    private TableColumn<AbonEnf, String> tel;
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
            String res="SELECT en.nom,en.prenom,en.datenaiss,ab.etat,ab.type,ab.date,ab.id,pa.numtel FROM enfant en, abonnement AS ab,parent AS pa WHERE en.id=ab.enfant_id AND en.parent_id=pa.id" ;
          
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
                  p.setNumtel(rs.getString("numtel"));
                  
                 
               
                
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
        tel.setCellValueFactory(new PropertyValueFactory<AbonEnf,String>("numtel"));
       
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
            te=selectedOne.getNumtel();
            mod.setText(selectedOne.getType());
            mod.getText();
            
           
        }
    }
      
      

    @FXML
    private void redirect(ActionEvent event) throws IOException {
        if(event.getSource() == ajou){
            AnchorPane pane = FXMLLoader.load(getClass().getResource("AjouterEnfant.fxml"));
            root.getChildren().setAll(pane);
        }
    }

    @FXML
    private void modify(ActionEvent event) {
        int ms=Integer.parseInt(id);
        res=mod.getText();
        EnfantService en= new EnfantService();
        if ((!mod.getText().equals("bus"))&&((!mod.getText().equals("normal")))){
             Alert ale= new Alert(Alert.AlertType.INFORMATION);
          ale.setTitle("INFORMATION");
          ale.setHeaderText("Le champs doit comporter bus ou normal");
          ale.showAndWait();
        }
        else{
       int al= en.modifiertype(ms,res);
         if (al>0){
          Alert ale= new Alert(Alert.AlertType.INFORMATION);
          ale.setTitle("INFORMATION");
          ale.setHeaderText("Modification faite !");
          ale.showAndWait();
          data.clear();
           try{
            Connection con = (Connection) ConnexionBD.getInstance().getCnx();
            String res="SELECT en.nom,en.prenom,en.datenaiss,ab.etat,ab.type,ab.date,ab.id,pa.numtel FROM enfant en, abonnement AS ab,parent AS pa WHERE en.id=ab.enfant_id AND en.parent_id=pa.id" ;
          
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
                  p.setNumtel(rs.getString("numtel"));
                  
                 
               
                
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
        tel.setCellValueFactory(new PropertyValueFactory<AbonEnf,String>("numtel"));
       
        afficher.setItems(data);
      }
      else {
           Alert aler= new Alert(Alert.AlertType.ERROR);
            aler.setTitle("erreur !");
            aler.setHeaderText("Erreur");
            aler.showAndWait();
          
      }}
    }

    @FXML
    private void sms(ActionEvent event) {
        
        try{
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/Msg.fxml"));
				Parent root1 = (Parent) fxmlLoader.load();
				MsgController fc=fxmlLoader.getController();
				//hidden id
				fc.setLabelText(te);

				        Stage stage = new Stage();
				stage.setScene(new Scene(root1));
				stage.show();
			}catch (Exception e){
				System.out.println(e);
			}
        
       
	}
        
        
    

}