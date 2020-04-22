/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Emna;

import Entities.Enfant;
import Entities.Evenement;
import IServices.EvenementService;
import IServices.ParticiperService;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Emna
 */

public class PaticiperController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
 @FXML
    private ListView<Evenement> listview;
      EvenementService es;
     private final ObservableList<Evenement> data =FXCollections.observableArrayList() ;

     private final ObservableList<Enfant> data_comb =FXCollections.observableArrayList() ;

    @FXML
    private ComboBox<Enfant> combo_enfant;

    @FXML
    private Button bt_part;

    @FXML
    private ImageView imageview;

    @FXML
    private TextArea txt_area;

    @FXML
    private TextField txt_filed;   
   @FXML
   private Pane det;
    private Image image;
    private ParticiperService ps;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ps=new ParticiperService();

                getData(data);
                Listeners();
                

    }    
    
    
       public void getData(ObservableList data) 
     {data.clear();
                es=new EvenementService();
                 List<Evenement> ls=es.afficherAll();
                data.addAll(ls);
                
        listview.setItems(data);
     }
    
              public void Listeners(){
            listview.setOnMouseClicked((event) -> {
            det.setVisible(true);
           

       Evenement  e=new Evenement();
        e=listview.getSelectionModel().getSelectedItem();
        txt_area.setText(e.getDescription());
        txt_filed.setText(e.getDate().toString());
         File f=new File(e.getImage());
        image = new Image(f.toURI().toString());

        imageview.setImage(image);
        data_comb.clear();
        System.out.println("im here");
        
        ps=new ParticiperService();
        List<Enfant> ls=ps.ConsulterEnfantParent(1);
        
        data_comb.addAll(ls);
        
        combo_enfant.setItems(data_comb);
        
        });  
        
    
    
    
    
              } 
              
              @FXML
              public void part()
              {
                  ParticiperService ps=new ParticiperService();
                 Evenement e=listview.getSelectionModel().getSelectedItem();
                 Enfant en=combo_enfant.getSelectionModel().getSelectedItem();
                  ps.participer(e, en);
                  
              }
}
