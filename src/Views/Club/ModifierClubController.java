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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Dorra Kerrou
 */
public class ModifierClubController implements Initializable {

    @FXML
    private TextArea descr_text;
    @FXML
    private TextField club_text;
    @FXML
    private ImageView imageview;
    @FXML
    private Button Modifier;

    /**
     * Initializes the controller class.
     */
    public ObservableList data = FXCollections.observableArrayList();

    private Image image;

    private File file;
    private FileChooser fileChooser;
    @FXML
    private Label id;
    @FXML
    private TextField image_label;
    @FXML
    private AnchorPane root;
    @FXML
    private Button image_btn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        File f;
        f = new File(image_label.getText());
        image = new Image(f.toURI().toString());

        imageview.setImage(image);

    }

    @FXML
    private void Modifier(ActionEvent event) throws IOException {

        if (!club_text.getText().equals("")) {
            if (!descr_text.getText().equals("")) {

                String name = club_text.getText();
                String descp = descr_text.getText();
                String images = image_label.getText();
                String ide = id.getText();

                Integer id_club = Integer.parseInt(ide);
                Club A = new Club();

                A.setId(id_club);
                A.setName(name);
                A.setDescription(descp);
                A.setPhoto(images);

                int AS;
                AS = ClubServices.modifier(A);
                if (AS > 0) {
                    JOptionPane.showMessageDialog(null, "Modification est faite");
                
                AnchorPane pane = FXMLLoader.load(getClass().getResource("ConsulterClub.fxml"));
                root.getChildren().setAll(pane);

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("la modification a échoué");
                alert.showAndWait();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.NONE);
            alert.setTitle("un champ est vide");
            alert.setContentText("LA DESCRIPTION WEEY ");
            alert.initStyle(StageStyle.UTILITY);
            alert.showAndWait();
        }
    }

    
        else {
                Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("un champ est vide");
        alert.setContentText("LE TITRE :) :)  ");
        alert.showAndWait();
    }
}

@FXML
        private void Annuler(ActionEvent event) throws IOException {
        
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ConsulterClub.fxml"));
            root.getChildren().setAll(pane);
    }

    @FXML
        private void UploadImage(ActionEvent event) {
        
        fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        Stage stage = (Stage) image_btn.getScene().getWindow();
        file = fileChooser.showOpenDialog(stage);
        image_label.setText(file.getName());
        File f = new File("src\\" + image_label.getText().toString());

        if (file != null) {
            image_label.setText(file.getAbsolutePath());

            image = new Image(file.toURI().toString());

            imageview.setImage(image);

        }
    }

    
    public void GetData(int idc,String nom, String Description, String url){


        club_text.setText(nom);
        descr_text.setText(Description);
        image_label.setText(url);
        id.setText(String.valueOf(idc));
        imageview.setImage(new Image((new File(url)).toURI().toString()));
        
        
    
        
    }
}
