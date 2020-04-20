/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Club;

import Entities.Club;
import IServices.ClubServices;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Dorra Kerrou
 */
public class AjoutClubController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private Button ajouter_btn;
    @FXML
    private TextArea description_text;
    @FXML
    private TextField club_text;

    private Image image;
    @FXML
    private ImageView imageview;
    @FXML
    private Button image_btn;

    private File file;
    private FileChooser fileChooser;
    @FXML
    private TextField image_area;
    @FXML
    private Pane stage;
    @FXML
    private Button annuler_btn;
 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    

    @FXML
    private void Ajouter(ActionEvent event) {

        if (club_text.getText().matches("[a-zA-Z]*")) {

            if (!club_text.getText().equals("")) {
                if (!description_text.getText().equals("")) {
                    if (!image_area.getText().equals("")) {

                        String club_nom = club_text.getText();
                        String description = description_text.getText();
                        String image = image_area.getText();

                        Club c = new Club();
                        c.setName(club_nom);
                        c.setDescription(description);
                        c.setPhoto(image);

                        ClubServices AS = new ClubServices();
                        int a = AS.ajouter(c);
                        if (a > 0) {
                           JOptionPane.showMessageDialog(null, "Ajout est fait");}

                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("WROONG");
                            alert.setContentText("smthng went wrong ");
                            alert.showAndWait();
                        }

                    
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("un champ est vide");
                    alert.setContentText("LA DESCRIPTION  ");
                    alert.initStyle(StageStyle.UTILITY);
                    alert.showAndWait();
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("un champ est vide");
                alert.setContentText("LE TITRE :) :)  ");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);

            alert.setTitle("les lettres");
            alert.setContentText("LE TITRE :) :)  ");
            alert.showAndWait();
        }
    }

    @FXML
    private void uploadImage(ActionEvent event) throws IOException {

        fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        Stage stage = (Stage) image_btn.getScene().getWindow();
        file = fileChooser.showOpenDialog(stage);
        image_area.setText(file.getName());
        File f = new File("src\\" + image_area.getText().toString());

        if (file != null) {
            image_area.setText(file.getAbsolutePath());

            image = new Image(file.toURI().toString());

            imageview.setImage(image);

        }
    }

    @FXML
    private void ANNULER(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ConsulterClub.fxml"));
        root.getChildren().setAll(pane);
    }

}
