package Views.Reclamation;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Entities.Evenement;
import Entities.Reclamation;
import IServices.EvenementService;
import IServices.IserviceReclamation;
import IServices.ServiceReclamation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ListeReclamationController implements Initializable {

		@FXML
		private TableView<Reclamation> TV_le;
	 	@FXML
	    private TableColumn<Reclamation, String> col_nom;

	    @FXML
	    private TableColumn<Reclamation, String> col_mail;

	    @FXML
	    private TableColumn<Reclamation, String> col_numtel;

	    @FXML
	    private TableColumn<Reclamation, String> col_titre;

	    @FXML
	    private TableColumn<Reclamation, Date> col_date;

	    @FXML
	    private TableColumn<Reclamation, String> col_desc;

	    @FXML
	    private TableColumn<Reclamation, Button> col_regler;

	    @FXML
	    private TableColumn<Reclamation, Button> col_supp;
	
	    public ObservableList<Reclamation> data = FXCollections.observableArrayList();
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		 try {
			initTable();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void initTable() throws SQLException {
		
	        IserviceReclamation sr=new ServiceReclamation();
	        List<Reclamation> lr=new ArrayList<Reclamation>();
	      lr=sr.findAll();
	      lr.stream().forEach(x->System.out.println(x.toString()));
	      data.addAll(lr);
	          
	      	col_nom.setCellValueFactory(new PropertyValueFactory<Reclamation,String>("nom"));
	      	col_mail.setCellValueFactory(new PropertyValueFactory<Reclamation,String>("mail"));
	      	col_numtel.setCellValueFactory(new PropertyValueFactory<Reclamation,String>("numtel"));
	      	col_titre.setCellValueFactory(new PropertyValueFactory<Reclamation,String>("titre"));
	        col_date.setCellValueFactory(new PropertyValueFactory<Reclamation,Date>("date"));
	    	col_desc.setCellValueFactory(new PropertyValueFactory<Reclamation,String>("description"));
	      //	col_regler.setCellValueFactory(new PropertyValueFactory<>("regler"));
	      //	col_supp.setCellValueFactory(new PropertyValueFactory<>("supp"));
	     
	        TV_le.setItems(data);
	}

}
