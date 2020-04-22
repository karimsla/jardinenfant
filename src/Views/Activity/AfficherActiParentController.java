package Views.Activity;


import Entities.Activite;
import Utils.ConnexionBD;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class AfficherActiParentController implements Initializable {


    @FXML
    private AnchorPane root;
    @FXML
    private TableView<Activite> table_act;
    private TableColumn<Activite, String> description;
    private TableColumn<Activite, String> date;
    @FXML
    private Button participer;
    @FXML
    private TextField club_name;


    public ObservableList<Activite> data = FXCollections.observableArrayList();


    HashMap<String, Integer> map;
    @FXML
    private TableColumn<Activite, String> nom_field;
    @FXML
    private TableColumn<Activite, String> description_field;
    @FXML
    private TableColumn<Activite, Date> date_field;

    private String nom_t;
    private String description_t;
    private LocalDate date_t;
    private String id_act;
    @FXML
    private Label id_textt;

    public static int ids ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO


        GetActivite();
        map = new HashMap<>();



        table_act.setOnMouseClicked((MouseEvent event1) -> {

            if (event1.getClickCount() > 1) {

                onEdit();

            }
        });


    }

    private void onEdit() {

        if (table_act.getSelectionModel().getSelectedItem() != null) {
            Activite selectedOne = table_act.getSelectionModel().getSelectedItem();

            nom_t = selectedOne.getTypeact();
            description_t = selectedOne.getDetailles();
            date_t = LocalDate.parse(selectedOne.getDate());
            id_act = Integer.toString(selectedOne.getId());

        }
    }


    private void GetActivite(){


        //ids = Integer.parseInt(id_textt.getText().toString());
        try {
            Connection con = (Connection) ConnexionBD.getInstance().getCnx();
            String res = "SELECT typeact,detailles, date FROM Activite WHERE club_id=" + ids;

            Statement statement = con.createStatement();

            ResultSet rs = statement.executeQuery(res);
            while (rs.next()) {
                Activite p = new Activite();
                p.setTypeact(rs.getString("typeact"));
                p.setDetailles(rs.getString("detailles"));
                p.setDate(rs.getString("date"));

                data.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsulterActiviteController.class.getName()).log(Level.SEVERE, null, ex);
        }

        nom_field.setCellValueFactory(new PropertyValueFactory<Activite,String>("typeact"));
        description_field.setCellValueFactory(new PropertyValueFactory<Activite, String>("detailles"));
        date_field.setCellValueFactory(new PropertyValueFactory<Activite, Date>("date"));
        table_act.setItems(data);
    }


    @FXML
    private void Participer(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ParticiperParent.fxml"));

        Parent root = (Parent) loader.load();

        ParticiperParentController mc = loader.getController();

        mc.GetData(nom_t, description_t, date_t, id_act);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }



    public void GetData( String id){
        id_textt.setText(id);




    }

    @FXML
    private void Recherche(KeyEvent event) {
        data.clear();
        String nom = club_name.getText();

        try {
            Connection con = (Connection) ConnexionBD.getInstance().getCnx();
            String res = "Select * from Activite where typeact Like '%" + nom + "%' and club_id = "+ids;

            Statement statement = con.createStatement();

            ResultSet rs = statement.executeQuery(res);
            while (rs.next()) {
                Activite p = new Activite();
                p.setTypeact(rs.getString("typeact"));
                p.setDetailles(rs.getString("detailles"));
                p.setDate(rs.getString("date"));

                data.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsulterActiviteController.class.getName()).log(Level.SEVERE, null, ex);
        }

        nom_field.setCellValueFactory(new PropertyValueFactory<Activite,String>("typeact"));
        description_field.setCellValueFactory(new PropertyValueFactory<Activite, String>("detailles"));
        date_field.setCellValueFactory(new PropertyValueFactory<Activite, Date>("date"));
        table_act.setItems(data);
    }
}
