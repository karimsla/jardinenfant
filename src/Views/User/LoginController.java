package Views.User;

import IServices.IserviceUser;
import IServices.ServiceUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.net.URL;
import java.security.MessageDigest;
import java.util.ResourceBundle;
import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXMLLoader;;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;

import static jardin.enfant.JardinEnfant.authenticated;


public class LoginController implements Initializable {

    @FXML
    private Label lblErrors;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtPassword;

    @FXML
    private Button btnSignin;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

       btnSignin.setOnAction(event->{
           try {
               handleButtonAction(event);
           } catch (IOException e) {
               e.printStackTrace();
           }
       });
    }

    @FXML
    public void handleButtonAction(ActionEvent event) throws IOException {

        if (event.getSource() == btnSignin) {
            //login here
            if (logIn().equals("Success")) {
                try {

                    //add you loading or delays - ;-)
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    //stage.setMaximized(true);
                    stage.close();
                    if(authenticated.getRole().contains("ADMIN")){
                        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/Views/FXML.fxml")));
                        stage.setScene(scene);
                        stage.show();
                    }else if(authenticated.getRole().contains("RESPONSABLE")){
                        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/Views/Jardin/Responsable.fxml")));
                        stage.setScene(scene);
                        stage.show();
                    } else if(authenticated.getRole().contains("PARENT")){
                        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/Views/Parent.fxml")));
                        stage.setScene(scene);
                        stage.show();
                    }else {
                        JOptionPane.showConfirmDialog(null,"non autorisé","Attention",JOptionPane.YES_NO_OPTION);
                    }




                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }

            }
        }

    }


    private String logIn() throws IOException {

        IserviceUser su = new ServiceUser();

        String status = "Success";
        String email = txtUsername.getText();
        String password = txtPassword.getText();
        if (email.isEmpty() || password.isEmpty()) {
            setLblError(Color.TOMATO, "Empty credentials");
            status = "Error";
        } else {
            //query


            if (su.Login(email,password).equals("Error")) {
                setLblError(Color.TOMATO, "Enter Correct Email/Password");
                status = "Error";
            } else {
                setLblError(Color.GREEN, "Login Successful..Redirecting..");
                status="Success";

            }

            return status;
        }

      return status;
    }
    private void setLblError (Color color, String text){
        lblErrors.setTextFill(color);
        lblErrors.setText(text);
        System.out.println(text);
    }

}