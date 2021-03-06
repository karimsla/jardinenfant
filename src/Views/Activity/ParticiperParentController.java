package Views.Activity;

import Entities.*;
import IServices.ActiviteServices;
import IServices.PartActServices;
import Utils.ConnexionBD;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;

import static jardin.enfant.JardinEnfant.authenticated;

/**
 * FXML Controller class
 *
 * @author Dorra Kerrou
 */
public class ParticiperParentController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private TextField nom_act;
    @FXML
    private TextArea desc_act;
    @FXML
    private DatePicker date_act;
    @FXML
    private ComboBox<AbonEnf> combo_enfant;
    @FXML
    private Button Participer;

    public ObservableList<String> data = FXCollections.observableArrayList();
    int ids = 8;


    HashMap<String, Integer> map;
    @FXML
    private Label id_act;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        map = new HashMap<>();
        LoadEnfant();
        try{
            Connection con = (Connection) ConnexionBD.getInstance().getCnx();
            String res="SELECT en.nom,en.prenom,en.id FROM enfant en,parent AS pa WHERE en.parent_id=pa.id AND pa.id="+authenticated.getId() ;

            Statement statement = con.createStatement();

            ResultSet rs =  statement.executeQuery(res);
            while(rs.next()){
                AbonEnf p = new AbonEnf();
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString("prenom"));
                p.setId(rs.getInt("id"));




                combo_enfant.getItems().addAll(p);


            }
        } catch (SQLException ex) {

        }


    }

    @FXML
    private void ADD(ActionEvent event) throws IOException {




        AbonEnf e = combo_enfant.getSelectionModel().getSelectedItem();

        PartActServices p1 = new PartActServices();
       boolean v = p1.verifier(e.getId(),Integer.parseInt(id_act.getText()));

        if ( v ){

           // int id = map.get(nom);

            LocalDate dateA = date_act.getValue();
            String activite = nom_act.getText();

            String date = dateA.toString();



            Activite A = new Activite();


            A.setId(Integer.parseInt(id_act.getText()));


            PartActivite p = new PartActivite();
            p.setE(e);
            p.setActivite(A);
            p.setDate(date);

            PartActServices AS = new PartActServices();
            int a = AS.ajouter(p);
            if (a > 0) {
                JOptionPane.showMessageDialog(null, "La participation est enregistré !!");


            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.showAndWait();
            }
        }else {
            JOptionPane.showMessageDialog(null, "Ton fils participe déjà à cette activité");
            AnchorPane pane = FXMLLoader.load(getClass().getResource("AfficherActiParent.fxml"));
            root.getChildren().setAll(pane);
        }

    }

    public void GetData(String nom, String Description, LocalDate date, String id) {
        nom_act.setText(nom);
        desc_act.setText(Description);
        date_act.setValue(date);
        id_act.setText(id);

    }

    public void LoadEnfant() {

        try {

            String res = "SELECT en.nom , en.id FROM enfant en,parent AS pa WHERE en.parent_id=pa.id AND pa.id=" + ids;
            Connection con = (Connection) ConnexionBD.getInstance().getCnx();
            Statement statement = con.createStatement();

            ResultSet rs = statement.executeQuery(res);
            while (rs.next()) {

                data.add(rs.getString("nom"));
                map.put(rs.getString("nom"), rs.getInt("id"));
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

}