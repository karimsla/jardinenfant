package Views.Enfant;
import IServices.EnfantService;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import static jardin.enfant.JardinEnfant.authenticated;


public class ParentAjouterEnfant implements Initializable {


    @FXML
    private AnchorPane root;
    @FXML
    private Button btn_ajt;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private DatePicker dat;
    @FXML
    private ComboBox<String> sex;
    int ids=6;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        sex.getItems().addAll("Homme","Femme");


    }




    @FXML
    private void ajouter(ActionEvent event) {

        LocalDate lt = LocalDate.now();

        LocalDate f =dat.getValue();

        if (!(nom.getText().equals("")) && !(prenom.getText().equals("")) && !(dat.getValue()==null) && !(sex.getValue()==null)  ){
            if(f.compareTo(lt)>0){

                Alert ale= new Alert(Alert.AlertType.INFORMATION);
                ale.setTitle("INFORMATION");
                ale.setHeaderText("Date non valide");
                ale.showAndWait();
            }
            else{


                EnfantService en = new EnfantService();
                en.ajouterEnfant(authenticated.getId(), nom.getText(), prenom.getText(), dat.getValue().toString(), sex.getSelectionModel().getSelectedItem());
                Alert ale= new Alert(Alert.AlertType.CONFIRMATION);
                ale.setTitle("INFORMATION");
                ale.setHeaderText("Ajout réussi");
                ale.showAndWait();
            }}
        else {

            if ((nom.getText().equals("")) && (prenom.getText().equals("")) && (dat.getValue()==null) && (sex.getValue()==null)  ){
                Alert ale= new Alert(Alert.AlertType.INFORMATION);
                ale.setTitle("INFORMATION");
                ale.setHeaderText("Veuillez remplir les champs");
                ale.showAndWait();

            }
            else{
                if (nom.getText().equals("")){
                    Alert ale= new Alert(Alert.AlertType.INFORMATION);
                    ale.setTitle("INFORMATION");
                    ale.setHeaderText("Veuillez indiquer le nom");
                    ale.showAndWait();}
                else {
                    if (prenom.getText().equals("")){
                        Alert ale= new Alert(Alert.AlertType.INFORMATION);
                        ale.setTitle("INFORMATION");
                        ale.setHeaderText("Veuillez indiquer le prennom");
                        ale.showAndWait();
                    }
                    else {
                        if ((dat.getValue()==null)){
                            Alert ale= new Alert(Alert.AlertType.INFORMATION);
                            ale.setTitle("INFORMATION");
                            ale.setHeaderText("Veuillez indiquer la date");
                            ale.showAndWait();
                        }
                        else{
                            if ((sex.getValue()==null)){
                                Alert ale= new Alert(Alert.AlertType.INFORMATION);
                                ale.setTitle("INFORMATION");
                                ale.setHeaderText("Veuillez indiquer le sexe");
                                ale.showAndWait();
                            }
                        }
                    }
                }




            }


        }









    }

}
