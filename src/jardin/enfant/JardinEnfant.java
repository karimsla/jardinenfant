/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jardin.enfant;

import Entities.Chauffeur;
import Entities.User;
import IServices.ChauffeurService;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author karim
 */
public class JardinEnfant extends Application {
    private double xOffset;
    private double yOffset;
    public static User authenticated=new User();
    public void events(Stage stage,Parent scene)
    {
        scene.setOnMousePressed(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {

                xOffset = event.getX();
                yOffset = event.getY();
            }        });

        scene.setOnMouseDragged(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                stage.setX(event.getScreenX()- xOffset);
                stage.setY(event.getScreenY()- yOffset);
            }        });
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/Responsable.fxml"));
        events(primaryStage,root);

        Scene scene = new Scene(root);
        //primaryStage.initStyle(StageStyle.UNDECORATED);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        launch(args);

    }

}
