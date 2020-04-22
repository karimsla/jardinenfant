/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Chauffeurs;

import Entities.Chauffeur;
import Entities.Evenement;
import Entities.Trajet;
import IServices.ChauffeurService;
import IServices.EvenementService;
import IServices.IserviceUser;
import IServices.ServiceUser;
import IServices.TrajetService;
import jardin.enfant.JardinEnfant;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Sami
 */
public class ConsulterController implements Initializable {
    @FXML
    private TextField search, nom, cin, tel, adresse, nom_a, cin_a, tel_a;
    @FXML
    private ListView<Chauffeur> listview;
    @FXML
    private AnchorPane info;
    @FXML
    private Label msg;
    ChauffeurService cs;
    private final ObservableList<Chauffeur> data = FXCollections.observableArrayList();
    @FXML
    private TableView<Trajet> tv;
    @FXML
    private TableColumn<Trajet, String> col_a;
    @FXML
    private TableColumn<Trajet, String> col_h;
    public ObservableList<Trajet> trajets = FXCollections.observableArrayList();
    @FXML
    private Pane ajout;
    @FXML
    private ComboBox hh, mm, sexe, sexe_a;

    @FXML
    private Button mapp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        getData(data);
        Listeners();
        for (int i = 7; i <= 18; i++)
            hh.getItems().add(i + "");
        for (int i = 0; i <= 59; i++)
            mm.getItems().add(i + "");
        ObservableList<String> obs = FXCollections.observableArrayList();
        obs.add("Homme");
        obs.add("Femme");
        sexe_a.setItems(obs);
        sexe.setItems(obs);

    }    
    
     public void getData(ObservableList data) 
     {IserviceUser  js= new ServiceUser();
                cs=new ChauffeurService();
  List<Chauffeur> ls=cs.afficherAll(js.jardinid(JardinEnfant.authenticated.getId()));
                data.addAll(ls);
listview.setItems(data);
}
 
     
 @FXML
 public void chercher()
 { IserviceUser  js= new ServiceUser();
     String ch=search.getText();
      cs=new ChauffeurService();
      data.clear();
  List<Chauffeur> ls=cs.afficherAll(js.jardinid(JardinEnfant.authenticated.getId())).stream().filter(p->p.getNom().toUpperCase().contains(ch.toUpperCase())).collect(Collectors.toList());
  data.addAll(ls);
listview.setItems(data);
       
 }
     
     
     
     
     public void getTable(Chauffeur c)
     {
            trajets.clear();
         TrajetService e=new TrajetService();
          List<Trajet> ls=new ArrayList<Trajet>();
      ls=e.afficherTrajetChauffeur(c.getId());
          trajets.addAll(ls);
          
        col_a.setCellValueFactory(new PropertyValueFactory<Trajet,String>("adresse"));
        col_h.setCellValueFactory(new PropertyValueFactory<Trajet,String>("heure"));
        tv.setItems(trajets);
    }


    public void Listeners() {

        TrajetService ts = new TrajetService();
        listview.setOnMouseClicked((event) -> {
            info.setVisible(true);
            msg.setVisible(false);
            ajout.setVisible(false);
            Chauffeur c = new Chauffeur();
            c = listview.getSelectionModel().getSelectedItem();
            setField(nom, c.getNom());
            setField(cin, c.getCin());

            sexe.setValue(c.getSexe());
            setField(tel, c.getTel());
            getTable(c);

        });
        tv.setOnMouseClicked(((event) -> {
            int dialogResult;
            if (event.getButton() == MouseButton.SECONDARY) {
                dialogResult = JOptionPane.showConfirmDialog(null, "Voulez vous vraiment supprimer ce chauffeur?", "Attention", JOptionPane.YES_NO_OPTION);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    Trajet c = tv.getSelectionModel().getSelectedItem();
                    ts.supprimerTrajet(c.getId());
                    Chauffeur ch = listview.getSelectionModel().getSelectedItem();
                    getTable(ch);

                }
            }
        }));

    }

    public void setField(TextField tf, String value) {
        tf.setText(value);
    }

    @FXML
    public void activer() {
        ajout.setVisible(true);
        info.setVisible(false);
        msg.setVisible(false);
    }

    @FXML
    public void ajouter() {
        ChauffeurService cs = new ChauffeurService();
        if (saisie(cin_a.getText(), nom_a.getText(), tel_a.getText())) {
            Chauffeur c = new Chauffeur(0, cin_a.getText(), nom_a.getText(), tel_a.getText(), sexe_a.getSelectionModel().getSelectedItem().toString(), 3);
            cs.ajouterChauffeur(c);
            data.clear();
            getData(data);
        }
    }

    @FXML
    public void modifier() {
        ChauffeurService cs = new ChauffeurService();
        if (saisie(cin.getText(), nom.getText(), tel.getText())) {
            Chauffeur c = listview.getSelectionModel().getSelectedItem();
            c.setNom(nom.getText());
            c.setCin(cin.getText());
            c.setSexe(sexe.getSelectionModel().getSelectedItem().toString());
            c.setTel(tel.getText());
            cs.modifierChauffeur(c);
        }
    }


    @FXML
    public void supprimer() {
        ChauffeurService cs = new ChauffeurService();
        int dialogResult = JOptionPane.showConfirmDialog(null, "Voulez vous vraiment supprimer ce chauffeur?", "Attention", JOptionPane.YES_NO_OPTION);
        if (dialogResult == JOptionPane.YES_OPTION) {
            Chauffeur c = listview.getSelectionModel().getSelectedItem();
            cs.supprimerChauffeur(c.getId());
            data.clear();
            getData(data);
            info.setVisible(false);
        }
    }

    @FXML
    public void affecter() {
        TrajetService ts = new TrajetService();
        Chauffeur c = listview.getSelectionModel().getSelectedItem();
        String heure = hh.getSelectionModel().getSelectedItem().toString() + "H" + mm.getSelectionModel().getSelectedItem().toString();
        Trajet t = new Trajet(0, adresse.getText(), heure);
        ts.ajouterTrajet(t, c.getId());
        adresse.clear();

    }


    private boolean Check(String cin, char a, char b, boolean space) {
        int i = 0;
        while (i < cin.length()) {
            if (!space) {
                if (cin.toUpperCase().charAt(i) > b || cin.toUpperCase().charAt(i) < a)
                    return false;
            } else {
                if (cin.charAt(i) != ' ' && (cin.toUpperCase().charAt(i) > b || cin.toUpperCase().charAt(i) < a))
                    return false;
            }
            i++;
        }
        return true;
    }

    private boolean saisie(String cin, String nom, String tel) {
        if (cin.length() != 8 || !Check(cin, '0', '9', false)) {
            JOptionPane.showMessageDialog(null, "Veuillez Vérifier le cin!");
            return false;
        }
        if (nom.length() == 0 || !Check(nom, 'A', 'Z', true)) {
            JOptionPane.showMessageDialog(null, "Veuillez Vérifier le nom!");
            return false;
        }
        if (tel.length() != 8 || !Check(tel, '0', '9', false)) {
            JOptionPane.showMessageDialog(null, "Veuillez Vérifier le numéro de téléphone!");
            return false;
        }
        return true;
    }


    @FXML
    public void map() {

        try {

            Parent   root = FXMLLoader.load(getClass().getResource("/Views/Chauffeurs/ConsulterMap.fxml"));
            Scene scene = new Scene(root);
            Stage primaryStage = new Stage();
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

}
