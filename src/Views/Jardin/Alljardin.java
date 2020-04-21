package Views.Jardin;

import Entities.AdmEnf;
import Entities.Jardin;
import Entities.Reclamation;
import IServices.CrudJardinEnfantImpl;
import IServices.EnfantService;
import Utils.ConnexionBD;
import Views.Enfant.AdminEnfantController;
import Views.Enfant.ParentAjAboController;
import Views.Reclamation.FixeController;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Alljardin implements Initializable {

    ObservableList<Jardin> list = FXCollections.observableArrayList();

    @FXML
    private TextField recherche;

    @FXML
    private TableView<Jardin> jardin;
    @FXML
    private MenuButton fwith;
    @FXML
    private MenuItem bnom;
    @FXML
    private MenuItem badresse;
    @FXML
    private MenuItem btarif;
    @FXML
    private MenuItem btel;
    @FXML
    private MenuItem betat;
    @FXML
    private MenuItem edit;
    @FXML
    private MenuItem delet;
    @FXML
    private TableColumn<Jardin, String> id;
    @FXML
    private TableColumn<Jardin, Double> tarif;
    @FXML
    private TableColumn<Jardin, String> description;
    @FXML
    private TableColumn<Jardin, String> adresse;
    @FXML
    private TableColumn<Jardin, String> numtel;
    @FXML
    private TableColumn<Jardin, Button> abon;
    @FXML
    private TableColumn<Jardin, String> colnom;
    @FXML
    private TableColumn<AdmEnf, String> enfnom;
    @FXML
    private TableColumn<AdmEnf, String> enfprenom;
    @FXML
    private TableColumn<AdmEnf, String> enfparent;
    Connection connection=null;

    public Alljardin() {
        connection= ConnexionBD.getInstance().getCnx();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        initCol();
        try {
            loadData();
            // TODO

            jardin.setOnMouseClicked((event) -> {
                ObservableList<AdmEnf> data = FXCollections.observableArrayList();
                Jardin selectedOne = jardin.getSelectionModel().getSelectedItem();

                int  id=selectedOne.getId();
                try{
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/Enfant/ParentAjAbo.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    ParentAjAboController fc=fxmlLoader.getController();
                    //hidden id
                    fc.Setjardin(id);

                    Stage stage = new Stage();
                    stage.setScene(new Scene(root1));
                    stage.show();
                }catch (Exception e){
                    System.out.println(e);
                }









            });

        } catch (SQLException ex) {
            Logger.getLogger(RechercheJardinEnfantController2.class.getName()).log(Level.SEVERE, null, ex);
        }





        // TODO

    }
    private void loadData() throws SQLException {
        List<Jardin> prov = new ArrayList<>();
        list.clear();
        CrudJardinEnfantImpl esi =new CrudJardinEnfantImpl();
        prov=(List<Jardin>) esi.findAll();

        list.addAll(prov);
        jardin.setItems(list);

    }
    private Stage getStage() {
        return (Stage) jardin.getScene().getWindow();
    }

    ///pour faire l'inisialisation des attribue
    private void initCol() {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tarif.setCellValueFactory(new PropertyValueFactory<>("Tarif"));
        description.setCellValueFactory(new PropertyValueFactory<>("Description"));
        adresse.setCellValueFactory(new PropertyValueFactory<>("Adresse"));
        numtel.setCellValueFactory(new PropertyValueFactory<>("numtel"));

        colnom.setCellValueFactory(new PropertyValueFactory<>("name"));





    }




    @FXML
    private void fnNom(ActionEvent event) throws SQLException {
        List<Jardin> prov = new ArrayList<>();
        list.clear();
        CrudJardinEnfantImpl esi =new CrudJardinEnfantImpl();
        prov=(List<Jardin>) esi.findByNom(recherche.getText());
        list.addAll(prov);
        jardin.setItems(list);

    }






    @FXML
    private void fnAdresse(ActionEvent event) throws SQLException {
        List<Jardin> prov = new ArrayList<>();
        list.clear();
        CrudJardinEnfantImpl esi =new CrudJardinEnfantImpl();
        prov=(List<Jardin>) esi.findByAdresse(recherche.getText());
        list.addAll(prov);
        jardin.setItems(list);
    }

    @FXML
    private void fnTarif(ActionEvent event) throws SQLException {
        List<Jardin> prov = new ArrayList<>();
        list.clear();
        CrudJardinEnfantImpl esi =new CrudJardinEnfantImpl();
        prov=(List<Jardin>) esi.findByTarif(Double.parseDouble(recherche.getText()));
        list.addAll(prov);
        jardin.setItems(list);
    }

    @FXML
    private void fnNum(ActionEvent event) throws SQLException {
        List<Jardin> prov = new ArrayList<>();
        list.clear();
        CrudJardinEnfantImpl esi =new CrudJardinEnfantImpl();
        prov=(List<Jardin>) esi.findByNum(recherche.getText());
        list.addAll(prov);
        jardin.setItems(list);


    }

    @FXML
    private void fnEtat(ActionEvent event) throws SQLException {

        List<Jardin> prov = new ArrayList<>();
        list.clear();
        CrudJardinEnfantImpl esi =new CrudJardinEnfantImpl();
        prov=(List<Jardin>) esi.findByEtat(recherche.getText());
        list.addAll(prov);
        jardin.setItems(list);
    }


}
