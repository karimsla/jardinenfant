/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Entities.Activite;
import Entities.Club;
import IServices.ActiviteServices;
import Utils.ConnexionBD;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.HashMap;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Dorra Kerrou
 */
public class AjoutActiviteController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private TextField act_text;
    @FXML
    private TextArea desc_text;
    @FXML
    private DatePicker date_text;
    @FXML
    private ComboBox<String> club_box;
    @FXML
    private Button annuler_btn;
    @FXML
    private Button ajout_btn;
    
     public ObservableList<String> data = FXCollections.observableArrayList();
     
             HashMap<String, Integer> map;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
        map = new HashMap<>();
        LoadData();

       for(int i=0; i<data.size(); i++){ 
            club_box.setValue((String)data.get(i));
           
        }
        club_box.setItems(data);
    }    

    @FXML
    private void Annuler(ActionEvent event) throws IOException {
        
        if(event.getSource() == annuler_btn){
            AnchorPane pane = FXMLLoader.load(getClass().getResource("ConsulterActivite.fxml"));
            root.getChildren().setAll(pane);}
    }

    @FXML
    private void ADD(ActionEvent event) {
        
        String club = club_box.getSelectionModel().getSelectedItem();
        int id = map.get(club); 
        
        LocalDate dateA = date_text.getValue();
        String activite = act_text.getText();
        String description = desc_text.getText();
        String date = dateA.toString();
        
        Club c = new Club();
        c.setId(id);
        Activite A = new Activite();
        A.setDate(date);
        A.setTypeact(activite);
        A.setDetailles(description);
        A.setClub(c);
        ActiviteServices AS = new ActiviteServices();
       int a =  AS.ajouter(A);
        if(a>0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ajout est fait");
            alert.setHeaderText("Ajout réussi ");
            alert.showAndWait();
            
        }else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("n'est pas fait");
            alert.showAndWait();
        }
    }
    
    
     private void LoadData(){
     
         try{
            
            String res="SELECT id,Name FROM Club" ;
          Connection con = (Connection) ConnexionBD.getInstance().getCnx();
            Statement statement = con.prepareStatement(res);
            ResultSet rs =  statement.executeQuery(res);
            while(rs.next()){
                map.put(rs.getString("Name"),rs.getInt("id"));
                data.add(rs.getString("Name"));
              
                
                
            }
            
        } catch (SQLException ex) {
             System.err.println(ex);;
         }
         
    }
}
