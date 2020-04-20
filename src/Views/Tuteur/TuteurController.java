/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Tuteur;

import Entities.Chauffeur;
import Entities.Trajet;
import Entities.Tuteur;
import IServices.ChauffeurService;
import IServices.TrajetService;
import IServices.TuteurService;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Sami
 */
public class TuteurController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    private final ObservableList<Tuteur> data =FXCollections.observableArrayList() ;
     @FXML
    private TableView<Tuteur> tv;
    @FXML 
    private TableColumn<Trajet, String> nom_col,prenom_col,sexe_col;
    @FXML 
  private TextField search,nom,prenom,nom_a,prenom_a,username_a,email_a;
    @FXML 
    private ComboBox sexe,sexe_a;
      
    @FXML
private Pane info_a,info;
    @FXML
    private Button ajout,modif,supp;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        getTable();
        Listeners();
        ObservableList<String> obs=FXCollections.observableArrayList();
        obs.add("Homme");
        obs.add("Femme");
        sexe_a.setItems(obs);
       sexe.setItems(obs);
    }    
    
    
       public void Listeners(){
      TuteurService ts=new TuteurService();
        tv.setOnMouseClicked((event) -> {
            info.setVisible(true);
            info_a.setVisible(false);
            ajout.setVisible(false);
       Tuteur  c=new Tuteur();
        c=tv.getSelectionModel().getSelectedItem();
        setField(nom,c.getNom());
        setField(prenom,c.getPrenom());
        sexe.setValue(c.getSexe());
           
        });
       
    }
    
 
     @FXML
 public void search()
 {
     String ch=search.getText();
      TuteurService tut=new TuteurService();
      data.clear();
  List<Tuteur> ls=tut.afficherAll().stream().filter(p->p.getNom().toUpperCase().contains(ch.toUpperCase())).collect(Collectors.toList());
  data.addAll(ls);
tv.setItems(data);
           
 }
       public void setField(TextField tf,String value)
    {tf.setText(value);
    }
    
     public void getTable()
     {
            data.clear();
         TuteurService e=new TuteurService();
          List<Tuteur> ls=new ArrayList<Tuteur>();
      ls=e.afficherAll();
          data.addAll(ls);
          
        nom_col.setCellValueFactory(new PropertyValueFactory<Trajet,String>("nom"));
        prenom_col.setCellValueFactory(new PropertyValueFactory<Trajet,String>("prenom"));
        sexe_col.setCellValueFactory(new PropertyValueFactory<Trajet,String>("sexe"));
        tv.setItems(data);
     }

     @FXML
     public void activer()
     {
         info_a.setVisible(true);
         info.setVisible(false);

         
     }
    @FXML
    public void ajouter()
    {TuteurService s=new TuteurService();
   if(saisie(nom_a.getText(),prenom_a.getText())&&email_a.getText().contains("@"))
   {Tuteur c=new Tuteur(0, nom_a.getText(), prenom_a.getText(), sexe_a.getSelectionModel().getSelectedItem().toString(),username_a.getText(),email_a.getText(),"T","ROLE_TUTEUR");
    boolean t=s.ajouterTuteur(c);
        if(t==false)
            JOptionPane.showConfirmDialog(null,"Ajout echoué! veuillez vérifier les données saisie","Attention!",JOptionPane.OK_OPTION);
         else
        {
            JOptionPane.showConfirmDialog(null,"Ajout terminé!","Succés",JOptionPane.OK_OPTION);
            nom_a.clear();prenom_a.clear();
        email_a.clear();username_a.clear();
    data.clear();
    getTable();
        }
   }
   
   }
    
    @FXML
    public void modifier()
    {
     TuteurService cs=new TuteurService();
      if(saisie(nom.getText(),prenom.getText()))
      {
    Tuteur c=tv.getSelectionModel().getSelectedItem();
   c.setNom(nom.getText());
   c.setPrenom(prenom.getText());
   c.setSexe(sexe.getSelectionModel().getSelectedItem().toString());
   cs.modifierTuteur(c);
    getTable();
      } 
    
    }
    
    
    @FXML
    public void supprimer()
    {
        TuteurService cs=new TuteurService();
      int dialogResult = JOptionPane.showConfirmDialog (null, "Voulez vous vraiment supprimer ce chauffeur?","Attention",JOptionPane.YES_NO_OPTION);
 if(dialogResult == JOptionPane.YES_OPTION){
Tuteur c= tv.getSelectionModel().getSelectedItem();
       cs.supprimerTuteur(c.getId());
       data.clear();
       getTable();
         info.setVisible(false); 
 }
}
    
      private boolean Check(String ch,char a,char b,boolean space)
    {int i=0;
        while (i<ch.length())
        {if(!space)
        {   if (ch.toUpperCase().charAt(i)>b||ch.toUpperCase().charAt(i)<a)
            return false;
        }
        else
        {   if( ch.charAt(i)!=' ' && (ch.toUpperCase().charAt(i)>b || ch.toUpperCase().charAt(i)<a))
            return false;
        } 
        i++;   
        }
        return true;
    }
    private boolean saisie(String nom,String prenom)
    {
        if (nom.length()==0 || !Check(nom,'A','Z',true))
        {  JOptionPane.showMessageDialog(null, "Veuillez Vérifier le nom!");
            return false;
        }
        if (prenom.length()==0 || !Check(prenom,'A','Z',true))
        {  JOptionPane.showMessageDialog(null, "Veuillez Vérifier le prenom!");
            return false;
        }
       
        return true;
    }
}
