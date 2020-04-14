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
    private TextField client;
    @FXML
    private TextField mail;
    @FXML
    private TextField code;
    @FXML
    private TextField carte;
    @FXML
    private TextField tel;
    @FXML
    private DatePicker expire;
    @FXML
    private Slider montant;
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
        if(client.getText().isEmpty()||carte.getText().length()!=13||!mail.getText().contains("@")||
                tel.getText().length()!=8||code.getText().length()!=3){
            alert.setTitle("error");
                        alert.setContentText("verifier champs" );
                        alert.show();
        }
        else
        {
           payment.setMontant(250.0);
           payment.setDate(date);
           //payment.setProv(jardin.getId());
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
        tel.clear();
        client.clear();
        carte.clear();
        code.clear();
        mail.clear();
        
    }
    
}
