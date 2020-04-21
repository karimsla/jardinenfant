package Views.Remarques;

import Entities.Remarque;
import IServices.IserviceRemarque;
import IServices.ServiceRemarque;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static jardin.enfant.JardinEnfant.authenticated;

public class MesRemarques implements Initializable {



        @FXML
        private TableView<Remarque> TV_RE;

        @FXML
        private TableColumn<Remarque, String> colenf;

        @FXML
        private TableColumn<Remarque, String> coltut;

        @FXML
        private TableColumn<Remarque, String> coldet;
        @FXML
        private TableColumn<Remarque, Date> coldate;

        @FXML
        private TextField searchfield;

        @FXML
        private Button searchbtn;

        public ObservableList<Remarque> data = FXCollections.observableArrayList();


        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {

            try {
                init();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public void init() throws SQLException {
            IserviceRemarque sr=new ServiceRemarque();
            List<Remarque> lr=new ArrayList<Remarque>();
            int id=authenticated.getId();
            lr=sr.Mesremarques(id);

            data.addAll(lr);


            searchbtn.setOnAction(e->{
                HandleSearchbtn();
            });


            colenf.setCellValueFactory(new PropertyValueFactory<Remarque,String>("enfant"));
            coltut.setCellValueFactory(new PropertyValueFactory<Remarque,String>("nomtut"));
            coldet.setCellValueFactory(new PropertyValueFactory<Remarque,String>("description"));

            coldate.setCellValueFactory(new PropertyValueFactory<Remarque, Date>("date"));




            TV_RE.setItems(data);

        }

        private void HandleSearchbtn(){
            FilteredList<Remarque> ls = new FilteredList<>(data, b->true);
            String search = searchfield.getText();
            ls.setPredicate(x->x.getEnfant().compareToIgnoreCase(search)==0||x.getEnfant().contains(search)||
                    x.getDescription().compareToIgnoreCase(search)==0||x.getDescription().contains(search)||
                    x.getNomtut().compareToIgnoreCase(search)==0||x.getNomtut().contains(search) );
            //  data.clear();
            // data.addAll(ls);
            TV_RE.setItems(ls);

        }


    }


