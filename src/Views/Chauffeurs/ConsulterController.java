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
import IServices.TrajetService;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Sami
 */
public class ConsulterController implements Initializable {
@FXML
private TextField nom,cin,sexe,tel,adresse,heure,nom_a,cin_a,sexe_a,tel_a; 
    @FXML
private ListView<Chauffeur> listview;
    @FXML
private AnchorPane info;
    @FXML
private Label msg;
   ChauffeurService cs;
private final ObservableList<Chauffeur> data =FXCollections.observableArrayList() ;
  @FXML
    private TableView<Trajet> tv;
    @FXML 
    private TableColumn<Trajet, String> col_a;
    @FXML
    private TableColumn<Trajet, String> col_h;
    public ObservableList<Trajet> trajets = FXCollections.observableArrayList();
    @FXML
    private Pane ajout;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        getData(data);
        Listeners();
        
        
    }    
    
     public void getData(ObservableList data) 
     {
                cs=new ChauffeurService();
  List<Chauffeur> ls=cs.afficherAll();
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
    public void Listeners(){
        listview.setOnMouseClicked((event) -> {
            info.setVisible(true);
            msg.setVisible(false);
            ajout.setVisible(false);
       Chauffeur  c=new Chauffeur();
        c=listview.getSelectionModel().getSelectedItem();
        setField(nom,c.getNom());
        setField(cin,c.getCin());
        setField(sexe,c.getSexe());
        setField(tel,c.getTel());
        getTable(c);
        
        });
    }
    
    public void setField(TextField tf,String value)
    {tf.setText(value);
        
    }
     @FXML
     public void activer()
     {
         ajout.setVisible(true);
         info.setVisible(false);
         msg.setVisible(false);
     }
    @FXML
    public void ajouter()
    {ChauffeurService cs=new ChauffeurService();
    Chauffeur c=new Chauffeur(0, cin_a.getText(), nom_a.getText(), tel_a.getText(), sexe_a.getText(), 0);
cs.ajouterChauffeur(c);
data.clear();
getData(data);
    }
    @FXML
    public void modifier()
    {
ChauffeurService cs=new ChauffeurService();

    Chauffeur c=listview.getSelectionModel().getSelectedItem();
    c.setNom(nom.getText());
   c.setCin(cin.getText());
   c.setSexe(sexe.getText());
   c.setTel(tel.getText());
   cs.modifierChauffeur(c);
        
    }
    
    @FXML
    public void supprimer()
    {
        ChauffeurService cs=new ChauffeurService();
       Chauffeur c= listview.getSelectionModel().getSelectedItem();
       cs.supprimerChauffeur(c.getId());
       data.clear();
       getData(data);
    }
    @FXML
    public void affecter()
    {
        TrajetService ts=new TrajetService();
        Chauffeur c=listview.getSelectionModel().getSelectedItem();
        Trajet t=new Trajet(0,adresse.getText(),heure.getText());
        ts.ajouterTrajet(t, c.getId());
adresse.clear();
heure.clear();
    }
}
