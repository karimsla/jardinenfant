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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;



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
	




	    @FXML
		public void HandleButton(ActionEvent event,int id){
	    	try{
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/Reclamation/Fixe.fxml"));
				Parent root1 = (Parent) fxmlLoader.load();
				FixeController fc=fxmlLoader.getController();
				fc.setLabelText(id);

				Stage stage = new Stage();
				stage.setScene(new Scene(root1));
				stage.show();
			}catch (Exception e){
				System.out.println(e);
			}
		}
	@FXML
	public void HandleDeleteButton(ActionEvent event,int id){
		try{
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/Reclamation/Fixe.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root1));
			stage.show();
		}catch (Exception e){

		}
	}


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

	      data.addAll(lr);
	          
	      	col_nom.setCellValueFactory(new PropertyValueFactory<Reclamation,String>("nom"));
	      	col_mail.setCellValueFactory(new PropertyValueFactory<Reclamation,String>("mail"));
	      	col_numtel.setCellValueFactory(new PropertyValueFactory<Reclamation,String>("numtel"));
	      	col_titre.setCellValueFactory(new PropertyValueFactory<Reclamation,String>("titre"));
	        col_date.setCellValueFactory(new PropertyValueFactory<Reclamation,Date>("date"));
	    	col_desc.setCellValueFactory(new PropertyValueFactory<Reclamation,String>("description"));
			//col_regler.setCellValueFactory(new PropertyValueFactory<>("fixe"));
	        //col_supp.setCellValueFactory(new PropertyValueFactory<>("supprimer"));
	     	fixeCol();
	     	deleteCol();


	        TV_le.setItems(data);
	}

	private void fixeCol(){

		Callback<TableColumn<Reclamation,Button>, TableCell<Reclamation,Button>> cellfactory=(param)->{

			final TableCell<Reclamation,Button> cell=new TableCell<Reclamation,Button>(){


				@Override
				public void updateItem(Button item, boolean empty){
					super.updateItem(item,empty);
					if(empty){
						setGraphic(null);
						setText(null);
					}else{
						final Button fixebtn=new Button("Fixer");
						fixebtn.setOnAction(event->{
							Reclamation r=getTableView().getItems().get(getIndex());

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

		Callback<TableColumn<Reclamation,Button>, TableCell<Reclamation,Button>> cellfactory=(param)->{

			final TableCell<Reclamation,Button> cell=new TableCell<Reclamation,Button>(){


				@Override
				public void updateItem(Button item, boolean empty){
					super.updateItem(item,empty);
					if(empty){
						setGraphic(null);
						setText(null);
					}else{
						final Button fixebtn=new Button("Delete");
						fixebtn.setOnAction(event->{
							Reclamation r=getTableView().getItems().get(getIndex());
							HandleButton(event,r.getId());
						});

						setGraphic(fixebtn);
						setText(null);
					}
				}
			};

			return cell;
		};
		col_supp.setCellFactory(cellfactory);

	}

}
