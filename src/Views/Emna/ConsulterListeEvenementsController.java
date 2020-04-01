/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Emna;


import Entities.Evenement;
import Views.ConsulterActiviteController;

import Entities.Activite;
import Utils.ConnexionBD;
import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
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
 * @author Emna
 */
public class ConsulterListeEvenementsController implements Initializable {
    
    
    @FXML
    private Button leAdd_btn;
    @FXML 
    private AnchorPane root;
    @FXML
    private TableView<Evenement> TV_le;
    @FXML 
    private TableColumn<Evenement, String> col_titre;
    @FXML
    private TableColumn<Evenement, String> col_cat;
    @FXML
    private TableColumn<Evenement, Date> col_date;
    public ObservableList<Activite> data = FXCollections.observableArrayList();
    @FXML
    private Button mod_btn;
    @FXML
    private Button aff_btn;
    
    public ObservableList<String> nom = FXCollections.observableArrayList();
       
        HashMap<String, Integer> map;
    /**
     * Initializes the controller class.
     */
   
       
      private void Afficher(){
        

        
        data.clear();
        try{
            Connection con = (Connection) ConnexionBD.getInstance().getCnx();
            String res="SELECT titre,categorie, date FROM Evenement" ;
          
            Statement statement = con.createStatement();
          
            ResultSet rs =  statement.executeQuery(res);
            while(rs.next()){
                 Evenement e = new Evenement();
                 e.setTitre(rs.getString("titre"));
                 e.setCategorie(rs.getLibelle("categorie"));
                 e.setDate(rs.getDate("date"));
                
                data.add(e);
            }
        } catch (SQLException ex) {
             Logger.getLogger(ConsulterActiviteController.class.getName()).log(Level.SEVERE, null, ex);
         }
        
        titre.setCellValueFactory(new PropertyValueFactory<Evenement,String>("titre"));
        categorie.setCellValueFactory(new PropertyValueFactory<Evenement,String>("categorie"));
        date.setCellValueFactory(new PropertyValueFactory<Evenement,Date>("date"));
        table.setItems(data);
   
    }
      
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    private void redirect(ActionEvent event) throws IOException {
        if(event.getSource() == leAdd_btn){
            AnchorPane pane = FXMLLoader.load(getClass().getResource("AjoutEvenement.fxml"));
            root.getChildren().setAll(pane);
        }
    }

    @FXML
    private void redirect2(ActionEvent event) throws IOException {
        
          if(event.getSource() == mod_btn){
            AnchorPane pane = FXMLLoader.load(getClass().getResource("ModifierEvenement.fxml"));
            root.getChildren().setAll(pane);
    }
    
}

   
     
        
        
    }    
    
