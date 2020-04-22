package Views.Activity;
import Entities.Activite;
import Utils.ConnexionBD;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
public class AfficherclubParentController implements Initializable {

    @FXML
    private Label nom_text;
    @FXML
    private Label descr_text;
    @FXML
    private ImageView imageview;
    @FXML
    private Button TakeAct;
    @FXML
    private Button Retour;
    @FXML
    private AnchorPane root;
    @FXML
    private Label id_text;



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void VoirLesActivites(ActionEvent event) throws IOException {


        AfficherActiParentController.ids = Integer.parseInt(id_text.getText().toString()) ;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/AfficherActiParent.fxml"));

        Parent root = (Parent) loader.load();


        //AfficherActiParentController mc = loader.getController();


        //mc.GetData(id_text.getText());
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();



    }

    @FXML
    private void Retour(ActionEvent event) {
    }

    public void GetData(String nom, String Description, String url, String id){
        nom_text.setText(nom);
        descr_text.setText(Description);
        //image_label.setText(url);
        imageview.setImage(new Image((new File(url)).toURI().toString()));
        id_text.setText(id);




    }
}
