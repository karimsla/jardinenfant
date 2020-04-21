/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Emna;

import Entities.StatModel;
import IServices.EvenementService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Emna
 */
public class StatistiquesController implements Initializable {

  
    
       @FXML
    private Pane pane_stat;
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      LoadData();
    } 
    

private void LoadData()
{ pane_stat.getChildren().clear();

      EvenementService es=new EvenementService();
List<StatModel> ls= es.getStat();
    ObservableList<PieChart.Data> list=FXCollections.observableArrayList();
for(int i=0;i<ls.size();i++)
{
    list.add(new PieChart.Data(ls.get(i).getNom()+" - "+ls.get(i).getNbPart(), ls.get(i).getNbPart()));
}  
PieChart stat= new PieChart(list);
    stat.setTitle("Statistiques des événements");
    
    pane_stat.getChildren().add(stat);

    
    
}
    
      
    }
    

