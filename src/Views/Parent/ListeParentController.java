package Views.Parent;

import Entities.Parents;
import Entities.Parents;
import Entities.Reclamation;
import IServices.IserviceParent;
import IServices.IserviceReclamation;
import IServices.ServiceParent;
import IServices.ServiceReclamation;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

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
    private TableColumn<Parents,String> Col_nbenf;
    @FXML
    private TableColumn<Parents,String> col_nbenf;

    @FXML
    private TextField txtsearch;

    @FXML
    private Button srbtn;





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
        for (Parents p : lr ) {
            p.setNbenfant(sr.nbrenfant(p.getId()));

        }

        data.addAll(lr);

        srbtn.setOnAction(event->{

            HandleSearchButton(event);
        });


        //if()


        col_nom.setCellValueFactory(new PropertyValueFactory<Parents,String>("nom"));
        col_id.setCellValueFactory(new PropertyValueFactory<Parents,String>("id"));
        col_numtel.setCellValueFactory(new PropertyValueFactory<Parents,String>("numtel"));
        col_prenom.setCellValueFactory(new PropertyValueFactory<Parents,String>("prenom"));
        col_adresse.setCellValueFactory(new PropertyValueFactory<Parents, String>("adresse"));
        col_nbenf.setCellValueFactory(new PropertyValueFactory<Parents,String>("description"));
        col_sexe.setCellValueFactory(new PropertyValueFactory<Parents,String>("Sexe"));
        col_nbenf.setCellValueFactory(new PropertyValueFactory<Parents,String>("nbenfant"));
        //col_regler.setCellValueFactory(new PropertyValueFactory<>("fixe"));
        // col_supp.setCellValueFactory(new PropertyValueFactory<>("supprimer"));
        //fixeCol();
     deleteCol();


        TbP.setItems(data);
    }




    private void deleteCol(){

        Callback<TableColumn<Parents,Button>, TableCell<Parents,Button>> cellfactory=(param)->{

            final TableCell<Parents,Button> cell=new TableCell<Parents,Button>(){


                @Override
                public void updateItem(Button item, boolean empty){
                    super.updateItem(item,empty);
                    if(empty){
                        setGraphic(null);
                        setText(null);
                    }else{
                        final Button delbtn=new Button("Delete");
                        delbtn.setOnAction(event->{
                            Parents r=getTableView().getItems().get(getIndex());
                            HandleDeleteButton(event,r.getId());
                        });

                        setGraphic(delbtn);
                        setText(null);
                    }
                }
            };

            return cell;
        };
        col_supp.setCellFactory(cellfactory);

    }

    @FXML
    public void HandleDeleteButton(ActionEvent event, int id){
        try{
            IserviceParent sr=new ServiceParent();

            int n=sr.delete(id);

            data.clear();
            this.initialize(null,null);
        }catch (Exception e){


        }


    }



    @FXML
   public void  HandleSearchButton(ActionEvent event){

        FilteredList<Parents> ls = new FilteredList<>(data,b->true);
        String search = txtsearch.getText();
        ls.setPredicate(x->x.getNom().compareToIgnoreCase(search)==0||x.getNom().contains(search)||
                x.getPrenom().compareToIgnoreCase(search)==0||x.getPrenom().contains(search)||
                x.getSexe().compareToIgnoreCase(search)==0||x.getSexe().contains(search)||
                x.getAdresse().compareToIgnoreCase(search)==0||x.getAdresse().contains(search)||
                x.getNumtel().compareToIgnoreCase(search)==0||x.getNumtel().contains(search) );
      //  data.clear();
       // data.addAll(ls);
        TbP.setItems(ls);



    }






}
