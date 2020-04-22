/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Enfant;

import Entities.AbonEnf;
import IServices.EnfantService;
import Utils.ConnexionBD;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;

import static jardin.enfant.JardinEnfant.authenticated;

/**
 * FXML Controller class
 *
 * @author FERID
 */
public class ParentAjAboController implements Initializable {


    @FXML
    private TextField idjardin;

    @FXML
    private ComboBox<AbonEnf> enf;
    @FXML
    private ComboBox<String> type;
    @FXML
    private Button btn_ajt;
    @FXML
    private TextField montant;
    public ObservableList<AbonEnf> data = FXCollections.observableArrayList();
    int ids=6;
    int i;
    AbonEnf s;
    int tarif;
    String tx="";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        type.getItems().addAll("bus","normal");
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




                enf.getItems().addAll(p);


                data.add(p);
            }
        } catch (SQLException ex) {

        }

        try{
            Connection cons = (Connection) ConnexionBD.getInstance().getCnx();
            String res="SELECT Tarif FROM jardin " ;

            Statement statement = cons.createStatement();

            ResultSet rs =  statement.executeQuery(res);
            while(rs.next()){
                tarif=rs.getInt("Tarif");










            }
        } catch (SQLException ex) {

        }


        // TODO
    }    

    @FXML
    private void ajouter(ActionEvent event) {

        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        String text = df.format(date);


        if((enf.getSelectionModel().getSelectedItem()==null)){
            Alert ale= new Alert(Alert.AlertType.ERROR);
            ale.setTitle("INFORMATION");
            ale.setHeaderText("Selectionnez un enfant");
            ale.showAndWait();

        }

        if ((type.getSelectionModel().getSelectedItem()==null)){
            Alert ale= new Alert(Alert.AlertType.ERROR);
            ale.setTitle("INFORMATION");
            ale.setHeaderText("Selectionnez un type");
            ale.showAndWait();
        }
        EnfantService en = new EnfantService();
        en.ajouterAbonnement(Integer.parseInt(idjardin.getText()), i, text,type.getSelectionModel().getSelectedItem(), "attente", tx);

        Alert ale= new Alert(Alert.AlertType.ERROR);
        ale.setTitle("INFORMATION");
        ale.setHeaderText("ajout réussie!");
        ale.showAndWait();
    }


    public void Setjardin(int id){
        idjardin.setText(String.valueOf(id));
    }


    @FXML
    private void recupenf(ActionEvent event) {
        i=enf.getSelectionModel().getSelectedItem().getId();

    }

    @FXML
    private void mod(ActionEvent event) {
        if(type.getSelectionModel().getSelectedItem().equals("bus")){
            tx=String.valueOf(tarif+50);
            montant.setText(tx);
        }
        if(type.getSelectionModel().getSelectedItem().equals("normal")){
            tx=String.valueOf(tarif);
            montant.setText(tx);
        }
    }
    
}
