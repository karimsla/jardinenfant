/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Emna;


import Entities.Evenement;

import Entities.Categorie;
import IServices.CategorieService;

import IServices.EvenementService;

import Utils.ConnexionBD;
import java.io.File;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Emna
 */
public class ConsulterListeEvenementsController implements Initializable {
    
    @FXML
    private TextField txt_tit,txt_titre,txt_lib; 
     @FXML
    private TextArea txt_des,txt_desc;
     @FXML
    private DatePicker  date_pickAdd,date_ev;
     @FXML
    private ComboBox<String> comb_cat;
   @FXML
   private Rectangle rec_im;
     @FXML
    private Button leAdd_btn,Add_bt,aff_bt,modif_bt,supp_bt,Add_Ima;   
    @FXML
    private AnchorPane Anchorpane_ajout,root;
    @FXML
    private ListView<Categorie> list_cat;
    private final ObservableList<Categorie> data =FXCollections.observableArrayList() ;
      CategorieService cs;
    private final ObservableList<Categorie> categories =FXCollections.observableArrayList() ;
    @FXML 
    private Label src;
     @FXML
    private ListView<Evenement> Lv_event;
      EvenementService es;
     private final ObservableList<Evenement> dataC =FXCollections.observableArrayList() ;
    @FXML
    private Pane pane_detEvent;
    @FXML
    private TableView<Evenement> tb_aff;
    @FXML
    private TableColumn<Evenement, String> titre;
    @FXML
    private TableColumn<?, ?> categorie;
   
     
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
                es=new EvenementService();
                 List<Evenement> ls=es.afficherAll();
                data.addAll(ls);
                
        Lv_event.setItems(data);
     }
        
        
           public void getDataC(ObservableList data) 
     {data.clear();
                cs=new CategorieService();
                Evenement e=Lv_event.getSelectionModel().getSelectedItem();
             List<Categorie> ls=cs.afficherEvenementCategorie(e.getId());
                data.addAll(ls);
                
             
      list_cat.setItems(data);
}
           
           
           public void Listeners(){
            Lv_event.setOnMouseClicked((event) -> {
            pane_detEvent.setVisible(true);
            Anchorpane_ajout.setVisible(false);
       Evenement  e=new Evenement();
        e=Lv_event.getSelectionModel().getSelectedItem();
       txt_titre.setText(e.getTitre());
        txt_desc.setText(e.getDescription());

      getDataC(categories);        
        });
    }
        @FXML   
        public void activer()
     {
         Anchorpane_ajout.setVisible(true);
         pane_detEvent.setVisible(false);
     }
        
    @FXML
    public void ajouter()
    { EvenementService es=new EvenementService();
    Date d=Date.valueOf(date_pickAdd.getValue());
    Evenement e=new Evenement(txt_titre.getText(),d.toString(),txt_des.getText()," ");
    es.Ajouter(e);
    data.clear();
    getData(data);
    }
 
    public void modifier()
    {
    EvenementService es=new EvenementService();

    Evenement e=Lv_event.getSelectionModel().getSelectedItem();
    e.setTitre(txt_titre.getText());
    e.setDescription(txt_desc.getText());
   e.setDate(Date.valueOf(date_ev.getValue()).toString());
   
   es.ModifierEvenement(e);
  
    }
    
    public void supprimer()
    {
        EvenementService es=new EvenementService();
        Evenement e= Lv_event.getSelectionModel().getSelectedItem();
       es.supprimerEvenement(e.getId());
       data.clear();
       getData(data);
    }
    public void affecter()
    {
        CategorieService cs=new CategorieService();
        Evenement e=Lv_event.getSelectionModel().getSelectedItem();
        Categorie c=new Categorie(0,txt_lib.getText());
        cs.ajouter(c, e.getId());
        txt_lib.clear();
    } 
    //add picture code     
     public void addpicture()
   {try{
       FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Open Resource File");
    Stage stage=(Stage)Add_Ima.getScene().getWindow();  
    File file=fileChooser.showOpenDialog(stage);
    src.setText(file.getName());
    File f=new File("src\\"+src.getText().toString());
    rec_im.setFill(new ImagePattern(new Image(f.toURI().toString())));
   }catch(Exception e)
   {
     System.out.println(e.getMessage());  
   }}
    
    
    public boolean checkTitre(String titre)
     {int i=0;
         while(i<titre.length())
         {if (((titre.toUpperCase().charAt(i)<'A')||(titre.toUpperCase().charAt(i)>'Z'))&&(titre.toUpperCase().charAt(i)!=' '))
         {             return false;
         }
         i++;}
         return true;
     }
public boolean checkDate(String date)
     {int i=0;
         while(i<date.length())
         {if (((date.toUpperCase().charAt(i)<'0')||(date.toUpperCase().charAt(i)>'9'))&&(date.charAt(i)!='/'))
         {             return false;
         }
         i++;}
         return true;
     }
      
    
    }    
    
