/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import Entities.Activite;
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
public class ConsulterActiviteController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
     @FXML
    private TableView<Activite> tableAct;
    
    @FXML 
    private TableColumn<Activite, String> activite;
    @FXML
    private TableColumn<Activite, String> description;
    @FXML
    private TableColumn<Activite, Date> date;
    
    public ObservableList<Activite> data = FXCollections.observableArrayList();
    @FXML
    private Button btn;
    @FXML
    private AnchorPane root;
    @FXML
    private Button modifier_btn;
    
    
    
     @FXML
    private void Afficher(){

        try{
            Connection con = (Connection) ConnexionBD.getInstance().getCnx();
            String res="SELECT typeact,detailles, date FROM `activite" ;
          
            Statement statement = con.createStatement();
          
            ResultSet rs =  statement.executeQuery(res);
            while(rs.next()){
                 Activite p = new Activite();
                 p.setTypeact(rs.getString("typeact"));
                 p.setDetailles(rs.getString("detailles"));
                 p.setDate(rs.getDate("date"));
                
                data.add(p);
            }
        } catch (SQLException ex) {
             Logger.getLogger(ConsulterActiviteController.class.getName()).log(Level.SEVERE, null, ex);
         }
        
        activite.setCellValueFactory(new PropertyValueFactory<Activite,String>("typeact"));
        description.setCellValueFactory(new PropertyValueFactory<Activite,String>("detailles"));
        date.setCellValueFactory(new PropertyValueFactory<Activite,Date>("date"));
        tableAct.setItems(data);
        
        
        
        
       
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void redirect(ActionEvent event) throws IOException {
        if(event.getSource() == btn){
            AnchorPane pane = FXMLLoader.load(getClass().getResource("AjoutActivite.fxml"));
            root.getChildren().setAll(pane);
        }
    }

    @FXML
    private void redirect2(ActionEvent event) throws IOException {
        
          if(event.getSource() == modifier_btn){
            AnchorPane pane = FXMLLoader.load(getClass().getResource("ModifyActivite.fxml"));
            root.getChildren().setAll(pane);
    }
    
}
}