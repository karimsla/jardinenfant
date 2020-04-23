/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Jardin;

import Entities.AdmEnf;
import Entities.Chauffeur;
import Entities.Enfant;
import Entities.Jardin;
import IServices.CrudJardinEnfantImpl;
import IServices.EnfantService;
import IServices.TrajetService;
import Utils.ConnexionBD;
import Views.Enfant.AdminEnfantController;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class RechercheJardinEnfantController2 implements Initializable {
    ObservableList<Jardin> list = FXCollections.observableArrayList();

    @FXML
    private TextField recherche;
    @FXML
    private Button pdf;

    @FXML
    private TableView<AdmEnf> enftb;
    @FXML
    private TableView<Jardin> jardin;
    @FXML
    private MenuButton fwith;
    @FXML
    private MenuItem bnom;
    @FXML
    private MenuItem badresse;
    @FXML
    private MenuItem btarif;
    @FXML
    private MenuItem btel;
    @FXML
    private MenuItem betat;
    @FXML
    private MenuItem edit;
    @FXML
    private MenuItem delet;
     @FXML
    private TableColumn<Jardin, String> id;
    @FXML
    private TableColumn<Jardin, Double> tarif;
    @FXML
    private TableColumn<Jardin, String> description;
    @FXML
    private TableColumn<Jardin, String> adresse;
    @FXML
    private TableColumn<Jardin, String> numtel;
    @FXML
    private TableColumn<Jardin, String> etat;
    @FXML
    private TableColumn<Jardin, String> colnom;
    @FXML
    private TableColumn<AdmEnf, String> enfnom;
    @FXML
    private TableColumn<AdmEnf, String> enfprenom;
    @FXML
    private TableColumn<AdmEnf, String> enfparent;
     Connection connection=null;
    
    public RechercheJardinEnfantController2() {
        connection= ConnexionBD.getInstance().getCnx();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        initCol();
        try {
            loadData();
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(RechercheJardinEnfantController2.class.getName()).log(Level.SEVERE, null, ex);
        }

            jardin.setOnMouseClicked((event) -> {
                ObservableList<AdmEnf> data = FXCollections.observableArrayList();
                Jardin selectedOne = jardin.getSelectionModel().getSelectedItem();

                int  id=selectedOne.getId();
try{

                    String res="SELECT en.nom,en.prenom,p.nom as pnom ,p.Prenom as ppnom FROM parent AS p ,enfant en JOIN abonnement ab ON en.id=ab.enfant_id  WHERE  p.id=en.parent_id AND ab.jardin_id="+id ;

                    Statement statement = connection.createStatement();
                    //
                    ResultSet rs =  statement.executeQuery(res);
                    while(rs.next()){
                        AdmEnf p = new AdmEnf();
                        p.setNom(rs.getString("nom"));
                        p.setPrenom(rs.getString("prenom"));

                        p.setJardin(rs.getString("pnom")+" "+rs.getString("ppnom"));





                        data.add(p);
                    }
                } catch (SQLException ex) {

                }
                enfnom.setCellValueFactory(new PropertyValueFactory<AdmEnf,String>("nom"));
                enfprenom.setCellValueFactory(new PropertyValueFactory<AdmEnf,String>("prenom"));
                enfparent.setCellValueFactory(new PropertyValueFactory<AdmEnf,String>("jardin"));


                enftb.setItems(data);
                enftb.setVisible(true);









            });


        pdf.setOnAction(eve ->{
            generate(eve);


        } );



            // TODO

    }    
  private void loadData() throws SQLException {
        List<Jardin> prov = new ArrayList<>();
        list.clear();
        CrudJardinEnfantImpl esi =new CrudJardinEnfantImpl();
        prov=(List<Jardin>) esi.findAll();

        list.addAll(prov);
        jardin.setItems(list);
        
    }
   private Stage getStage() {
        return (Stage) jardin.getScene().getWindow();
    }

    ///pour faire l'inisialisation des attribue 
    private void initCol() {
         id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tarif.setCellValueFactory(new PropertyValueFactory<>("Tarif"));
        description.setCellValueFactory(new PropertyValueFactory<>("Description"));
        adresse.setCellValueFactory(new PropertyValueFactory<>("Adresse"));
        numtel.setCellValueFactory(new PropertyValueFactory<>("numtel"));
        etat.setCellValueFactory(new PropertyValueFactory<>("Etat"));
        colnom.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        
        
        
    }
    @FXML
    private void fnNom(ActionEvent event) throws SQLException {
         List<Jardin> prov = new ArrayList<>();
        list.clear();
        CrudJardinEnfantImpl esi =new CrudJardinEnfantImpl();
        prov=(List<Jardin>) esi.findByNom(recherche.getText());
       list.addAll(prov);
        jardin.setItems(list);
        
    }






    @FXML
    private void fnAdresse(ActionEvent event) throws SQLException {
          List<Jardin> prov = new ArrayList<>();
        list.clear();
        CrudJardinEnfantImpl esi =new CrudJardinEnfantImpl();
        prov=(List<Jardin>) esi.findByAdresse(recherche.getText());
       list.addAll(prov);
        jardin.setItems(list);
    }

    @FXML
    private void fnTarif(ActionEvent event) throws SQLException {
          List<Jardin> prov = new ArrayList<>();
        list.clear();
        CrudJardinEnfantImpl esi =new CrudJardinEnfantImpl();
        prov=(List<Jardin>) esi.findByTarif(Double.parseDouble(recherche.getText()));
       list.addAll(prov);
        jardin.setItems(list);
    }

    @FXML
    private void fnNum(ActionEvent event) throws SQLException {
          List<Jardin> prov = new ArrayList<>();
        list.clear();
        CrudJardinEnfantImpl esi =new CrudJardinEnfantImpl();
        prov=(List<Jardin>) esi.findByNum(recherche.getText());
        list.addAll(prov);
        jardin.setItems(list);
        
        
    }

    @FXML
    private void fnEtat(ActionEvent event) throws SQLException {
        
          List<Jardin> prov = new ArrayList<>();
        list.clear();
        CrudJardinEnfantImpl esi =new CrudJardinEnfantImpl();
        prov=(List<Jardin>) esi.findByEtat(recherche.getText());
        list.addAll(prov);
        jardin.setItems(list);
    }

    @FXML
    private void handlerEdit(ActionEvent event) throws SQLException, IOException {
        
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        CrudJardinEnfantImpl esi =new CrudJardinEnfantImpl();
         Jardin selectedForEdit = jardin.getSelectionModel().getSelectedItem();
        if (selectedForEdit == null) {
                        alert.setTitle("error");
                        alert.setContentText("No Space selected ,Please select a Space for edit" );
                        alert.show();
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("/Views/Jardin/EditJardin.fxml"));
            Parent parent = loader.load();
            EditJardinController controller = (EditJardinController) loader.getController();
            controller.inflateUI(selectedForEdit);
           esi.delet(selectedForEdit);
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("edit Space");
            stage.setScene(new Scene(parent));
            stage.show();

           
        } catch (IOException ex) {
            ex.printStackTrace();
    }
    }
    
 

    @FXML
    private void handlerDelet(ActionEvent event) throws SQLException {
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        CrudJardinEnfantImpl esi =new CrudJardinEnfantImpl();
        Jardin selectedForDeletion = jardin.getSelectionModel().getSelectedItem();
        if (selectedForDeletion == null) {
                        alert.setTitle("error");
                        alert.setContentText("No Jardin selected ,Please select a Space for edit" );
                        alert.show();
            return;
        }
        else if (selectedForDeletion != null)
            {
        alert.setTitle("Deleting jardin");
        alert.setContentText("Are you sure want to delete the Space " + selectedForDeletion.getName() + " ?");
        Optional<ButtonType> answer = alert.showAndWait();
        if (answer.get() == ButtonType.OK) {
            int result = esi.delet(selectedForDeletion);
            System.out.println(selectedForDeletion.getId());
            if (result!=0) 
             {
                        alert.setTitle("success");
                        alert.setContentText("Space deleted "+ selectedForDeletion.getName() + " was deleted successfully.");
                        alert.show();
                        list.remove(selectedForDeletion);
            }
            else
            {
                        alert.setTitle("echec");
                        alert.setContentText("Failed "+selectedForDeletion.getName() +  " could not be deleted");
                        alert.show();
            }
        }
}
        else 
        {
            alert.setTitle("Cancelled");
                        alert.setContentText("Deletion cancelled "+ selectedForDeletion.getName() + "Deletion process cancelled");
                        alert.show();
        }
            }




    @FXML
    private void generate(ActionEvent event) {
        String path="";



         ObservableList<AdmEnf> ld = FXCollections.observableArrayList();

        EnfantService se=new EnfantService();
        ld.addAll(se.findall());



        try {

            path="C:\\Users\\karim\\Downloads\\";








            Document doc=new Document();
            PdfWriter.getInstance(doc, new FileOutputStream(path+"enfant.pdf"));
            doc.open();



            Rectangle rect= new Rectangle(595,760);


            rect.enableBorderSide(4);
            rect.setBorder(Rectangle.BOX);
            rect.setBorderWidth(4);
            rect.setBorderColor(Color.DARK_GRAY);

            doc.add(rect);

            Image im=Image.getInstance(path+"kgpng.png\\");

            doc.add(im);


            Font reds = new Font(null, 12, Font.NORMAL, Color.MAGENTA);
            Chunk redTexts = new Chunk(" LISTE DES ENFANTS:",reds);
            Paragraph para = new Paragraph(redTexts);
            doc.add(para);


            Paragraph paras = new Paragraph(" ");
            doc.add(paras);
            Paragraph parass = new Paragraph(" ");
            doc.add(parass);

            PdfPTable th1=new PdfPTable(5);
            th1.setWidthPercentage(100);

            PdfPCell c1=new PdfPCell(new Phrase("Nom"));
            th1.addCell(c1);

            PdfPCell c2=new PdfPCell(new Phrase("Prenom"));
            th1.addCell(c2);
            PdfPCell c3=new PdfPCell(new Phrase("Sexe"));
            th1.addCell(c3);
            PdfPCell c4=new PdfPCell(new Phrase("Parent"));
            th1.addCell(c4);

            PdfPCell c5=new PdfPCell(new Phrase("Date Naissance"));
            th1.addCell(c5);
            th1.setHeaderRows(1);
            for (int i=0;i<ld.size();i++){
                String na=ld.get(i).getNom();
                String pr=ld.get(i).getPrenom();
                String sx=ld.get(i).getSexe();
                String pa=ld.get(i).getJardin();
                Format formatter = new SimpleDateFormat("yyyy-MM-dd");
                String s = formatter.format(ld.get(i).getDate());



                th1.addCell(na);
                th1.addCell(pr);
                th1.addCell(sx);
                th1.addCell(pa);
                th1.addCell(s);





            }

            doc.add(th1);


            java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            String text = df.format(date);
            Paragraph paress = new Paragraph(" "
                    + " "
                    + " "
                    + " ");
            doc.add(paress);
            Paragraph paresss = new Paragraph(" ");
            doc.add(paresss);

            doc.close();


        } catch (FileNotFoundException ex) {
            Logger.getLogger(AdminEnfantController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(AdminEnfantController.class.getName()).log(Level.SEVERE, null, ex);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    

    
}
