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

import IServices.IserviceUser;
import IServices.ServiceUser;
import Utils.ConnexionBD;
import java.io.File;
import java.io.IOException;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.time.LocalDate;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
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

import static jardin.enfant.JardinEnfant.authenticated;
import static javafx.scene.input.KeyCode.A;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;


/**
 * FXML Controller class
 *
 * @author Emna
 */
public class ConsulterListeEvenementsController implements Initializable {

    @FXML
    private TextField txt_tit,txt_titre,txt_lib,txt_libellecat,image_area;
    @FXML
    private TextArea txt_des,txt_desc;
    @FXML
    private DatePicker  date_pickAdd,date_ev;
    @FXML
    private ComboBox<Categorie> comb_cat,comb_catM;
    @FXML
    private Rectangle rec_im;
    @FXML
    private Button leAdd_btn,Add_bt,aff_bt,modif_bt,supp_bt,Add_Ima,stat_btn;
    @FXML
    private AnchorPane Anchorpane_ajout,root,add_cat;
    @FXML
    private Label src;
    @FXML
    private ListView<Evenement> Lv_event;
    EvenementService es;
    private final ObservableList<Evenement> dataC =FXCollections.observableArrayList() ;

    @FXML
    private Pane pane_detEvent,pane_stat;
    @FXML
    private TableView<Evenement> tb_aff;
    @FXML
    private TableColumn<Evenement, String> titre;
    @FXML
    private TableColumn<?, ?> categorie;
    @FXML
    private ListView listview;
    @FXML
    private ListView<Categorie> lv_cat;
    private final ObservableList<Categorie> data =FXCollections.observableArrayList() ;
    CategorieService cs;
    @FXML

    private final ObservableList<Categorie> categories =FXCollections.observableArrayList() ;

    private final ObservableList<String> categorieE =FXCollections.observableArrayList() ;


    @FXML
    private ImageView imageview;
    @FXML
    private ImageView imageviewaff;
    private Image image;
    private File file;
    private FileChooser fileChooser;




    /**
     * Initializes the controller class.
     */




    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        listeCat(comb_cat,categorieE);
        getDataCat(categories);
        getData(data);
        Listeners();



    }
    public void getData(ObservableList data)
    {data.clear();
        es=new EvenementService();
        List<Evenement> ls=es.afficherAll();
        data.addAll(ls);

        Lv_event.setItems(data);
    }


    public void getDataC(ObservableList data)
    {          data.clear();
        cs=new CategorieService();
        Evenement e=Lv_event.getSelectionModel().getSelectedItem();
        List<Categorie> ls=cs.afficherEvenementCategorie(e.getId());
        data.addAll(ls);


        Lv_event.setItems(data);
    }

    public void getDataCat(ObservableList data)
    {data.clear();
        cs=new CategorieService();
        List<Categorie> ls=cs.afficherAll();
        System.out.println(ls);
        data.addAll(ls);

        lv_cat.setItems(data);
    }

    public void Listeners(){
        Lv_event.setOnMouseClicked((event) -> {
            pane_detEvent.setVisible(true);
            Anchorpane_ajout.setVisible(false);
            add_cat.setVisible(false);

            Evenement  e=new Evenement();
            e=Lv_event.getSelectionModel().getSelectedItem();
            txt_titre.setText(e.getTitre());
            txt_desc.setText(e.getDescription());

            Date d= Date.valueOf(e.getDate());
            System.out.println(e.getDate());

            LocalDate l= d.toLocalDate();

            date_ev.setValue(l);

            e.setDate(Date.valueOf(date_ev.getValue()).toString());



            File f=new File(e.getImage());
            image = new Image(f.toURI().toString());
            imageview.setImage(image);

            listeCat(comb_catM,categorieE);
            getDataCat(categories);
            comb_catM.setValue(e.getCategorie());
        });
    }
    public void listeCat(ComboBox cb,ObservableList data)
    { data.clear();
        cs=new CategorieService();
        List<Categorie> ls=cs.afficherAll();
        System.out.println(ls);
        data.addAll(ls);

        cb.setItems(data);
    }

    @FXML
    public void activer()
    {
        Anchorpane_ajout.setVisible(true);
        pane_detEvent.setVisible(false);
        add_cat.setVisible(false);

    }


    public void activate()
    {
        add_cat.setVisible(true);
        pane_detEvent.setVisible(false);
        Anchorpane_ajout.setVisible(false);

    }

    @FXML
    public void ajouter()
    { EvenementService es=new EvenementService();
        Date d=Date.valueOf(date_pickAdd.getValue());
        Evenement e=new Evenement(txt_tit.getText(),d.toString(),txt_des.getText(),image_area.getText().toString());
        e.setCategorie(comb_cat.getSelectionModel().getSelectedItem());
        IserviceUser su=new ServiceUser();
        int idjar=su.jardinid(authenticated.getId());
        es.Ajouter(e,idjar);
        txt_tit.clear();
        txt_des.clear();
        data.clear();
        getData(data);

    }

    @FXML
    public void ajouterCat()
    {
        CategorieService cs= new CategorieService();

        Categorie c=new Categorie(txt_libellecat.getText());
        cs.ajouter(c);
        txt_libellecat.clear();

        getDataCat(categories);


    }


    public void modifier()
    {
        EvenementService es=new EvenementService();

        Evenement e=Lv_event.getSelectionModel().getSelectedItem();
        e.setTitre(txt_titre.getText());
        e.setDescription(txt_desc.getText());

        Date d= Date.valueOf(e.getDate());
        System.out.println(e.getDate());

        LocalDate f= d.toLocalDate();

        date_ev.setValue(f);

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

    public void supprimerCat()
    {
        CategorieService cs=new CategorieService();
        Categorie c= lv_cat.getSelectionModel().getSelectedItem();
        cs.supprimerCategorie(c.getId());
        data.clear();
        getDataCat(categories);
    }

    @FXML
    private void stat() throws IOException
    {Stage primaryStage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Views/Emna/Statistiques.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();


    }
    @FXML
    public void ButtonAction(ActionEvent event)
    {
        FileChooser fc= new FileChooser();
        File seletedFile= fc.showOpenDialog(null);

        if(seletedFile !=null)
        {
            listview.getItems().add(seletedFile.getName());

        }

        else {

            System.out.println("file is not valid! ");
        }

    }



    @FXML
    private void uploadImage(ActionEvent event) throws IOException {

        fileChooser = new FileChooser();
        Stage stage = (Stage) Add_Ima.getScene().getWindow();
        file = fileChooser.showOpenDialog(stage);
        image_area.setText(file.getName());
        File f = new File("src\\" + image_area.getText().toString());

        if (file != null) {
            image_area.setText(file.getAbsolutePath());

            image = new Image(file.toURI().toString());

            imageview.setImage(image);

        }
    }



    private boolean saisie(String titre, String libelle)
    {


        if (txt_tit.getText().matches("[a-z]*"))

        {  JOptionPane.showMessageDialog(null, "Veuillez Vérifier le nom!");
            return false;
        }

        return true;
    }


}
    
