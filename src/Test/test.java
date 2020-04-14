/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Utils.ConnexionBD;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Dorra Kerrou
 */
public class test extends Application{
    public static void main(String[] args) {
        Application.launch(args);
        
    } 

    @Override
    public void start(Stage primaryStage) throws Exception {
       Parent root =  FXMLLoader.load(getClass().getResource("rechercheJardinEnfant2.fxml"));
       Scene scene = new Scene(root);
       primaryStage.setScene(scene);
       primaryStage.show();
        
         
    }

    }
    
   
