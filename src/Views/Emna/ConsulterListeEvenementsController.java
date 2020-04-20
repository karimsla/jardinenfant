/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Emna;


import Entities.Evenement;

import IServices.EvenementService;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

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
    public ObservableList<Evenement> data = FXCollections.observableArrayList();
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
        EvenementService e=new EvenementService();
          List<Evenement> ls=new ArrayList<Evenement>();
      ls=e.afficherAll();
      System.out.println(ls.get(0).getTitre());
          data.addAll(ls);
          
        col_titre.setCellValueFactory(new PropertyValueFactory<Evenement,String>("titre"));
        col_cat.setCellValueFactory(new PropertyValueFactory<Evenement,String>("description"));
        col_date.setCellValueFactory(new PropertyValueFactory<Evenement,Date>("date"));
        TV_le.setItems(data);
   
    }
      
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Afficher();
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
    
