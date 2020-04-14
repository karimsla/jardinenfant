package Views.Parent;

import Entities.Parents;
import Entities.Parents;
import IServices.IserviceParent;
import IServices.ServiceParent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.text.TabableView;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ListeParentController  implements Initializable {

    @FXML
    private TableView<Parents> TbP;
    @FXML
    private TableColumn<Parents,String> col_id;
    @FXML
    private TableColumn<Parents,String> col_nom;
    @FXML
    private TableColumn<Parents,String> col_prenom;
    @FXML
    private TableColumn<Parents,String> col_numtel;
    @FXML
    private TableColumn<Parents,String> col_adresse;
    @FXML
    private TableColumn<Parents,String> col_sexe;
    @FXML
    private TableColumn<Parents,String> col_nbenf;



    @FXML
    private TableColumn<Parents, Button> col_supp;

    public ObservableList<Parents> data = FXCollections.observableArrayList();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            initTable();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public void initTable() throws SQLException {
        IserviceParent sr=new ServiceParent();
        List<Parents> lr;
        lr=sr.findAll();

        data.addAll(lr);

        col_nom.setCellValueFactory(new PropertyValueFactory<Parents,String>("nom"));
        col_id.setCellValueFactory(new PropertyValueFactory<Parents,String>("id"));
        col_numtel.setCellValueFactory(new PropertyValueFactory<Parents,String>("numtel"));
        col_prenom.setCellValueFactory(new PropertyValueFactory<Parents,String>("prenom"));
        col_adresse.setCellValueFactory(new PropertyValueFactory<Parents, String>("adresse"));
        col_nbenf.setCellValueFactory(new PropertyValueFactory<Parents,String>("description"));
        //col_regler.setCellValueFactory(new PropertyValueFactory<>("fixe"));
        // col_supp.setCellValueFactory(new PropertyValueFactory<>("supprimer"));
        //fixeCol();
       // deleteCol();


        TbP.setItems(data);
    }
}
