/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Dorra Kerrou
 */
public class FXMLController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private Button act_btn;
    @FXML
    private Button club_btn;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void redirect(ActionEvent event) throws IOException {
        
         if(event.getSource() == act_btn){
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/views/ConsulterActivite.fxml"));
            root.getChildren().setAll(pane);
        }
    }

    
    @FXML
      private void Club(ActionEvent event) throws IOException {
        
         if(event.getSource() == club_btn){
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/viewsClub/ConsulterClub.fxml"));
            root.getChildren().setAll(pane);
        }
    }
   
    
  
    
}
