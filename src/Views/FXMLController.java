/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Dorra Kerrou
 */
public class FXMLController implements Initializable {

    @FXML
    private Button Ljardin;

    @FXML
    private Button demandes;

    @FXML
    private Button reclams;

    @FXML
    private Button Lpar;

    @FXML
    private Button pay;

    @FXML
    private Pane body;


   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        listener();
    }    

    @FXML
    private void listener()  {

        load(Ljardin,"Accueil.fxml");
        load(demandes,"ConsulterActivite.fxml");
        load(reclams,"Reclamation/ListeReclamation.fxml");
        load(Lpar,"ConsulterEnfant.fxml");
        load(pay,"Reclamation/ListeReclamation.fxml");



    }



    public void load(Button p, String name)
    {
        p.setOnAction((event) -> {
            try {
                body.getChildren().clear();
                FXMLLoader loader=new FXMLLoader(getClass().getResource("/Views/"+name));
                AnchorPane pane =loader.load();
                body.getChildren().setAll(pane);
            }catch(Exception ee)
            {
                ee.getMessage();
            }
        });
    }
    
  
    
}
