/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Enfant;

import com.teknikindustries.bulksms.SMS;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author FERID
 */
public class MsgController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private Button envo;
    @FXML
    private TextArea ms;
    @FXML
    private TextField num;
    int j=0;
    String tt;
    char [] car;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void envoyer(ActionEvent event) {
       
          
        
      
     
        if (num.getText().matches("[0-9]*")){
             if (!(ms.getText().equals(""))){
                 
             
        SMS s = new SMS();
        
        s.SendSMS("ferid", "Feridferid1",ms.getText(),num.getText() ,"https://bulksms.vsms.net/eapi/submission/send_sms/2/2.0");
         Alert ale= new Alert(Alert.AlertType.INFORMATION);
          ale.setTitle("INFORMATION");
          ale.setHeaderText("Message envoyé");
          ale.showAndWait();
    }
             else{
                  Alert ale= new Alert(Alert.AlertType.INFORMATION);
          ale.setTitle("INFORMATION");
          ale.setHeaderText("Le champs doit comporter un message");
          ale.showAndWait();
             } 
        
        }
        else{
           
           Alert ale= new Alert(Alert.AlertType.INFORMATION);
          ale.setTitle("INFORMATION");
          ale.setHeaderText("Le champs doit comporter des nombres");
          ale.showAndWait();
        }
      
    
    }
    
     public void setLabelText(String tel){
        

        num.setText(tel);
        

    }
    
}
