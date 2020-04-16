/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Club;

import IServices.ClubServices;
import Utils.ConnexionBD;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author Dorra Kerrou
 */
public class AffichageController implements Initializable {

    @FXML
    private Label nom;
    @FXML
    private TextArea description;
    @FXML
    private ComboBox<String> combo;
    public ObservableList data = FXCollections.observableArrayList();
    @FXML
    private Button trouver;
    @FXML
    private ImageView imageview;
    
     private Image image;
    @FXML
    private Button Back;
    @FXML
    private AnchorPane root;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        LoadData();
        for (int i = 0; i < data.size(); i++) {
            combo.setValue((String) data.get(i));
        }
        combo.setItems(data);
    }    
    
    
    
    private void LoadData() {

        try {

            String res = "SELECT Name    FROM Club ";
            Connection con = (Connection) ConnexionBD.getInstance().getCnx();
            Statement statement = con.prepareStatement(res);
            ResultSet rs = statement.executeQuery(res);
            while (rs.next()) {
                data.add(rs.getString("Name"));
            
               
             
            }

        } catch (SQLException ex) {
            System.err.println(ex);;
        }

    }

    @FXML
    private void TrouverPourAfficher(ActionEvent event) {
         String name = combo.getSelectionModel().getSelectedItem();
          Entities.Club A = ClubServices.findClub(name);

        nom.setText(A.getName());
        description.setText(A.getDescription());
        
        
        
    
        File f=new File(A.getPhoto());
        image = new Image(f.toURI().toString());

        imageview.setImage(image);
        
        
    }

    @FXML
    private void Back(ActionEvent event) throws IOException {
        
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ConsulterClub.fxml"));
            root.getChildren().setAll(pane);
    }
      
    
}
