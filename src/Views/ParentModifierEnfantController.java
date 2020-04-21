/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import Entities.AbonEnf;
import IServices.EnfantService;
import Utils.ConnexionBD;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author FERID
 */
public class ParentModifierEnfantController implements Initializable {

      @FXML
    private AnchorPane root;
    @FXML
    private Button btn_ajt;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private DatePicker dat;
    @FXML
    private ComboBox<String> sex;
    static int ids;
    int suc=0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(ids);
        sex.getItems().addAll("Homme","Femme");
         try{
            Connection con = (Connection) ConnexionBD.getInstance().getCnx();
            String res="SELECT en.nom,en.prenom,en.datenaiss,en.sexe FROM enfant en WHERE en.id="+ids ;
          
            Statement statement = con.createStatement();
          
            ResultSet rs =  statement.executeQuery(res);
            while(rs.next()){
                 AbonEnf p = new AbonEnf();
                 p.setNom(rs.getString("nom"));
                 p.setPrenom(rs.getString("prenom"));
                  p.setDatenaiss(rs.getDate("datenaiss"));
                  p.setSexe(rs.getString("sexe"));
                 
                 LocalDate f=p.getDatenaiss().toLocalDate();
                  
                 nom.setText(p.getNom());
                 prenom.setText(p.getPrenom());
                 sex.setValue(p.getSexe());
                 dat.setValue(f);
               
                
                
            }
        } catch (SQLException ex) {
           
         }
        
      
    }    
    
    
     public static void getid(int id){
      
         ids=id;
        
    }

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
        
        LocalDate lt = LocalDate.now();
         
       LocalDate f =dat.getValue();
       
         if (!(nom.getText().equals("")) && !(prenom.getText().equals("")) && !(dat.getValue()==null) && !(sex.getValue()==null)  ){
             if(f.compareTo(lt)>0){
                 
                  Alert ale= new Alert(Alert.AlertType.INFORMATION);
          ale.setTitle("INFORMATION");
          ale.setHeaderText("Date non valide");
          ale.showAndWait();
             }
             else{
                 
             
        EnfantService en = new EnfantService();
        suc= en.modifierParent(ids, nom.getText(), prenom.getText(), dat.getValue().toString(), sex.getSelectionModel().getSelectedItem());
          if (suc>0){
          Alert ale= new Alert(Alert.AlertType.INFORMATION);
          ale.setTitle("INFORMATION");
          ale.setHeaderText("modification faite !");
          ale.showAndWait();     
           
       
    }}}
         else {
             
             if ((nom.getText().equals("")) && (prenom.getText().equals("")) && (dat.getValue()==null) && (sex.getValue()==null)  ){
                 Alert ale= new Alert(Alert.AlertType.INFORMATION);
          ale.setTitle("INFORMATION");
          ale.setHeaderText("Veuillez remplir les champs");
          ale.showAndWait();
                 
             }
             else{
                  if (nom.getText().equals("")){
             Alert ale= new Alert(Alert.AlertType.INFORMATION);
          ale.setTitle("INFORMATION");
          ale.setHeaderText("Veuillez indiquer le nom");
          ale.showAndWait();}
          else {
                if (prenom.getText().equals("")){
             Alert ale= new Alert(Alert.AlertType.INFORMATION);
          ale.setTitle("INFORMATION");
          ale.setHeaderText("Veuillez indiquer le prennom");
          ale.showAndWait();
         }
                else {
                    if ((dat.getValue()==null)){
             Alert ale= new Alert(Alert.AlertType.INFORMATION);
          ale.setTitle("INFORMATION");
          ale.setHeaderText("Veuillez indiquer la date");
          ale.showAndWait();
         }
                    else{
                        if ((sex.getValue()==null)){
             Alert ale= new Alert(Alert.AlertType.INFORMATION);
          ale.setTitle("INFORMATION");
          ale.setHeaderText("Veuillez indiquer le sexe");
          ale.showAndWait();
         }
                    }
                }
                  }
         
        
        
         
             }
             
         
         }
             
             
             
             
            
    
          if(event.getSource() == btn_ajt){
    AnchorPane pane = FXMLLoader.load(getClass().getResource("ParentAfficherEnf.fxml"));
            root.getChildren().setAll(pane);
    
    }}
    

   
    
}
