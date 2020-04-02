/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Sami
 */
public class AccueilController implements Initializable {

    @FXML
    private AnchorPane body;
    
    @FXML
    private Pane accueil,chauffeurs,activites,quitter,evenements,abonnements;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 
Listeners();
    }    
    
    public void Listeners()
    {
        load(accueil,"Accueil.fxml");
        load(activites,"ConsulterActivite.fxml");
        load(chauffeurs,"Chauffeurs/Consulter.fxml");
        load(abonnements,"ConsulterEnfant.fxml");
        load(evenements,"Emna/ConsulterListeEvenements.fxml");
        
        quitter.setOnMouseClicked((event) -> {
        Platform.exit();
    });
    }
    
    
    public void load(Pane p,String name)
    {
       p.setOnMouseClicked((event) -> {
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