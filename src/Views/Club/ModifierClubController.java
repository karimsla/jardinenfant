/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Club;

import Entities.Club;
import IServices.ClubServices;
import Utils.ConnexionBD;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    private ComboBox<String> combo;
    @FXML
    private Button club;
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
      
        //LoadData();

       /* for (int i = 0; i < data.size(); i++) {
            combo.setValue((String) data.get(i));
        }
        combo.setItems(data);
        */
        
         File f;
        f = new File(image_label.getText());
        image = new Image(f.toURI().toString());

        imageview.setImage(image);
        
        

    }

   /* private void LoadData() {

        try {

            String res = "SELECT Name FROM  Club ";
            Connection con = (Connection) ConnexionBD.getInstance().getCnx();
            Statement statement = con.prepareStatement(res);
            ResultSet rs = statement.executeQuery(res);
            while (rs.next()) {
                data.add(rs.getString("Name"));

            }

        } catch (SQLException ex) {
            System.err.println(ex);
        }

    }
    */
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
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("modification est faite");
            alert.setHeaderText("INFO");
            alert.showAndWait();
            AnchorPane pane = FXMLLoader.load(getClass().getResource("ConsulterClub.fxml"));
            root.getChildren().setAll(pane);

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("nop");
            alert.showAndWait();
        }
        
        } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("A FIELD IS MISSING");
                    alert.setContentText("LA DESCRIPTION WEEY ");
                    alert.initStyle(StageStyle.UTILITY);
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("A FIELD IS MISSING");
                alert.setContentText("LE TITRE :) :)  ");
                alert.showAndWait();
            }
    }

    @FXML
    private void Trouver(ActionEvent event) {
/*
        String AID = combo.getSelectionModel().getSelectedItem();

        Entities.Club c = ClubServices.findClub(AID);

        club_text.setText(c.getName());
        descr_text.setText(c.getDescription());
        
        image_label.setText(c.getPhoto());

        String ids = String.valueOf(c.getId());
        id.setText(ids);

        File f;
        f = new File(c.getPhoto());
        image = new Image(f.toURI().toString());

        imageview.setImage(image);
*/

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

    
    public void GetData(String nom, String Description, String photo){
        club_text.setText(nom);
        descr_text.setText(Description);
        image_label.setText(photo);
        
    
        
    }
}
