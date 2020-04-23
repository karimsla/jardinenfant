/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Jardin;

import Entities.Jardin;
import Entities.Paiement;
import IServices.*;
import Utils.ConnexionBD;
import Views.Reclamation.FixeController;
import com.teknikindustries.bulksms.SMS;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Random;
import java.util.ResourceBundle;

import static jardin.enfant.JardinEnfant.authenticated;


/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class PaymentController implements Initializable {

    @FXML
    private Label montant;
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
    ObservableList<Paiement> listu  = FXCollections.observableArrayList();


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
    }

    @FXML
    private void payerHandler(ActionEvent event) throws SQLException {
        CrudPayment crud = new PaiementCrud();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date date = new Date(System.currentTimeMillis());
        Alert alert = new Alert(Alert.AlertType.WARNING);
        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);

        Paiement payment = new Paiement();
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

            HandleButton(event);
            //IserviceUser su=new ServiceUser();


            //payment.setJardin(su.jardinid(authenticated.getId()));


            // payment.setProv(JardinEnfant);

        }



    }

    public void HandleButton(ActionEvent event){
        IserviceUser su=new ServiceUser();
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/Jardin/VerifyCode.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            VerifyCodeController fc=fxmlLoader.getController();
            String SALTCHARS = "1234567890";
            StringBuilder salt = new StringBuilder();
            Random rnd = new Random();
            while (salt.length() < 6) { // length of the random string.
                int index = (int) (rnd.nextFloat() * SALTCHARS.length());
                salt.append(SALTCHARS.charAt(index));
            }
            String saltStr = salt.toString();
            //hidden id

            fc.setLabelText(su.jardinid(authenticated.getId()),saltStr);

            System.out.printf(saltStr);
            envoyer(saltStr);


            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    @FXML
    private void annulerHandler(ActionEvent event) {
        expire.getEditor().clear();

        carte.clear();
        code.clear();


    }


    public void envoyer(String msg) throws SQLException {
        IserviceUser su=new ServiceUser();
        CrudJardinEnfant sj=new CrudJardinEnfantImpl();

        int j=su.jardinid(authenticated.getId());
        Jardin jar=sj.findById(j);
        String numtel=jar.getNumtel();

        if (numtel.matches("[0-9]*")){
            if (!(numtel).equals("")){


                SMS s = new SMS();
                System.out.printf(numtel+"/"+msg);

                s.SendSMS("dorraa", "Dorra123",msg,"216"+numtel,"https://bulksms.vsms.net/eapi/submission/send_sms/2/2.0");

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

}