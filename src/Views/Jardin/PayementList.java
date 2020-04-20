package Views.Jardin;

import Entities.Paiement;
import Entities.Reclamation;
import IServices.CrudPayment;
import IServices.IserviceUser;
import IServices.PaiementCrud;
import IServices.ServiceUser;
import Utils.ConnexionBD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static jardin.enfant.JardinEnfant.authenticated;

public class PayementList implements Initializable {

    @FXML
    private TableView<Paiement> tabpay;
    @FXML
    private TableColumn<Paiement, String> coljar;
    @FXML
    private TableColumn<Paiement, Date> coldate;
    @FXML
    private TableColumn<Paiement, Float> colmont;
    @FXML
    private TableColumn<Paiement, String> id;

    Connection connection=null;


    public PayementList() {
        connection= ConnexionBD.getInstance().getCnx();
    }

    ObservableList<Paiement> data  = FXCollections.observableArrayList();


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        try {
            inittab();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void inittab() throws SQLException {

        CrudPayment sp=new PaiementCrud();
        List<Paiement> lr=new ArrayList<Paiement>();
        lr=sp.findAll();

        data.addAll(lr);
        coljar.setCellValueFactory(new PropertyValueFactory<Paiement,String>("jard"));
        colmont.setCellValueFactory(new PropertyValueFactory<Paiement,Float>("montant"));

        coldate.setCellValueFactory(new PropertyValueFactory<Paiement,Date>("date"));
        id.setCellValueFactory(new PropertyValueFactory<Paiement,String>("id"));
        //col_regler.setCellValueFactory(new PropertyValueFactory<>("fixe"));
        //col_supp.setCellValueFactory(new PropertyValueFactory<>("supprimer"));




        tabpay.setItems(data);
    }



}
