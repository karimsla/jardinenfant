package Views.Demandes;

import Entities.Jardin;
import Entities.Parents;
import Entities.Reclamation;
import IServices.*;
import Views.Reclamation.FixeController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static jardin.enfant.JardinEnfant.authenticated;

public class Demandes implements Initializable {


    @FXML
    private TextField recherche;

    @FXML
    private TableView<Jardin> lidemandes;

    @FXML
    private TableColumn<Jardin,String> colnom;

    @FXML
    private TableColumn<Jardin,String> adresse;

    @FXML
    private TableColumn<Jardin,String> description;

    @FXML
    private TableColumn<Jardin,String> numtel;

    @FXML
    private TableColumn<Jardin, Button> col_regler;

    @FXML
    private TableColumn<Jardin, Button> col_supp;

    @FXML
    private TableColumn<Jardin, String> id;

    @FXML
    private Button searchbtn;


    public ObservableList<Jardin> data = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            System.out.println(authenticated.getUsername());
            initTable();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }




    public void initTable() throws SQLException {

         CrudJardinEnfant sj=new CrudJardinEnfantImpl();
        List<Jardin> lj=new ArrayList<Jardin>();
        lj=sj.findByEnAttente();

        data.addAll(lj);


        searchbtn.setOnAction(e->{
            HandleSearchbtn();
        });

        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        description.setCellValueFactory(new PropertyValueFactory<>("Description"));
        adresse.setCellValueFactory(new PropertyValueFactory<>("Adresse"));
        numtel.setCellValueFactory(new PropertyValueFactory<>("numtel"));
        colnom.setCellValueFactory(new PropertyValueFactory<>("name"));
        //col_regler.setCellValueFactory(new PropertyValueFactory<>("fixe"));
        //col_supp.setCellValueFactory(new PropertyValueFactory<>("supprimer"));
        fixeCol();
        deleteCol();



        lidemandes.setItems(data);
    }

    private void fixeCol(){

        Callback<TableColumn<Jardin,Button>, TableCell<Jardin,Button>> cellfactory=(param)->{

            final TableCell<Jardin,Button> cell=new TableCell<Jardin,Button>(){


                @Override
                public void updateItem(Button item, boolean empty){
                    super.updateItem(item,empty);
                    if(empty){
                        setGraphic(null);
                        setText(null);
                    }else{
                        final Button fixebtn=new Button("Accepter");
                        fixebtn.setOnAction(event->{
                            Jardin r=getTableView().getItems().get(getIndex());

                            HandleButton(event,r.getId());
                        });

                        setGraphic(fixebtn);
                        setText(null);
                    }
                }
            };

            return cell;
        };
        col_regler.setCellFactory(cellfactory);

    }

    private void deleteCol(){

        Callback<TableColumn<Jardin,Button>, TableCell<Jardin,Button>> cellfactory=(param)->{

            final TableCell<Jardin,Button> cell=new TableCell<Jardin,Button>(){


                @Override
                public void updateItem(Button item, boolean empty){
                    super.updateItem(item,empty);
                    if(empty){
                        setGraphic(null);
                        setText(null);
                    }else{
                        final Button deletebtn=new Button("Delete");
                        deletebtn.setOnAction(event->{
                            Jardin r=getTableView().getItems().get(getIndex());
                            HandleDeleteButton(event,r.getId());
                        });

                        setGraphic(deletebtn);
                        setText(null);
                    }
                }
            };

            return cell;
        };
        col_supp.setCellFactory(cellfactory);

    }

    private void HandleSearchbtn(){
        FilteredList<Jardin> ls = new FilteredList<>(data, b->true);
        String search = recherche.getText();
        ls.setPredicate(x->x.getName().compareToIgnoreCase(search)==0||x.getName().contains(search)||
                x.getAdresse().compareToIgnoreCase(search)==0||x.getAdresse().contains(search)||
                x.getDescription().compareToIgnoreCase(search)==0||x.getDescription().contains(search)||
                x.getNumtel().compareToIgnoreCase(search)==0||x.getNumtel().contains(search));
        //  data.clear();
        // data.addAll(ls);
        lidemandes.setItems(ls);

    }


    @FXML
    public void HandleButton(ActionEvent event,int id){
        try{
            CrudJardinEnfant sj=new CrudJardinEnfantImpl();

            sj.updateStat(id);
            data.clear();
            this.initialize(null,null);
        }catch (Exception e){
            System.out.println(e);
        }
    }
    @FXML
    public void HandleDeleteButton(ActionEvent event,int id){
        try{
            CrudJardinEnfant sj=new CrudJardinEnfantImpl();
            Jardin jar=sj.findById(id);

            int n=sj.delet(jar);

            data.clear();
            this.initialize(null,null);
        }catch (SQLException e){

        }


    }




}
