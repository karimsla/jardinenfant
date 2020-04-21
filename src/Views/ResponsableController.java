/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import java.net.URL;
import java.util.ResourceBundle;
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
public class ResponsableController implements Initializable {
@FXML
AnchorPane body;
    @FXML
    private Pane chauffeurs,club,messages,evenements,abonnements,tuteurs;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        load(club,"Club/ConsulterClub.fxml");
        load(chauffeurs,"Chauffeurs/Consulter.fxml");
        load(abonnements,"Enfant/ConsulterEnfant.fxml");
        load(evenements,"Emna/ConsulterListeEvenements.fxml");
        load(messages,"Messages/MessagesList.fxml");
        load(tuteurs,"Tuteur/Tuteur.fxml");
        
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

}         
    });
}
}
