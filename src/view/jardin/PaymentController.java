/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.jardin;

import Entities.JardinEnfant;
import Entities.Payment;
import IServices.CrudJardinEnfantImpl;
import IServices.CrudPaymentImpl;
import Utils.ConnexionBD;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class PaymentController implements Initializable {

     @FXML
    private TextField carte;
       @FXML
    private DatePicker expire;
     @FXML
    private TextField code;
    @FXML
    private Button valider;
    @FXML
    private Button annuler;
             Connection connection=null;
  
    
    public PaymentController() {
        connection=ConnexionBD.getInstance().getCnx();
    }
              ObservableList<Payment> listu  = FXCollections.observableArrayList();


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void payerHandler(ActionEvent event) throws SQLException {
        CrudPaymentImpl crud = new CrudPaymentImpl();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
          
Date date = new Date(System.currentTimeMillis());
        Alert alert = new Alert(Alert.AlertType.WARNING);
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);

        Payment payment = new Payment();
        if(carte.getText().length()!=13){
            alert.setTitle("error");
                        alert.setContentText("verifier champs num de carte" );
                        alert.show();
        }
        else if (
               code.getText().length()!=3){
            alert.setTitle("error");
                        alert.setContentText("verifier champs mot de passe " );
                        alert.show();
        }
        else if (
        expire.getValue().compareTo(LocalDate.now())<0)
        {
        alert.setTitle("error");
                        alert.setContentText("verifier champs date ! date invalide " );
                        alert.show();
        }
        else
        {
           payment.setMontant(250.0);
           payment.setDate(date);
           
          // payment.setProv(JardinEnfant);
           int x= crud.create(payment);
           if(x!=0){
             alert1.setTitle("success");
                        alert1.setContentText("payement effectuer avec succees" );
                        alert1.show();
           }
           else{
               alert.setTitle("error");
                        alert.setContentText("payment failed" );
                        alert.show();
           }
        }
            
    
        
    }

    @FXML
    private void annulerHandler(ActionEvent event) {
        expire.getEditor().clear();
  
        carte.clear();
        code.clear();
     
        
    }
    
}
