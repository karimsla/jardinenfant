/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Enfant;

import Entities.AbonEnf;
import Entities.AdmEnf;
import IServices.EnfantService;
import Utils.ConnexionBD;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Component;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;

import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JFileChooser;

/**
 * FXML Controller class
 *
 * @author FERID
 */
public class AdminEnfantController implements Initializable {

  
     
    @FXML
    private AnchorPane root;
    @FXML
    private TableView<AdmEnf> enfcons;
    @FXML
    private TableColumn<AdmEnf, String> nom;
    @FXML
    private TableColumn<AdmEnf, String> prenom;
    @FXML
    private TableColumn<AdmEnf, Date> date;
    @FXML
    private TableColumn<AdmEnf, String> sexe;
    @FXML
    private TableColumn<AdmEnf, String> parent;
    public ObservableList<AdmEnf> data = FXCollections.observableArrayList();
    public ObservableList<AdmEnf> recherc = FXCollections.observableArrayList();
    @FXML
    private Button btn_supp;
    String id="";
    int as=0;
    @FXML
    private TextField txt_r;
    @FXML
    private ComboBox<String> cmb_r;
    @FXML
    private Button btn_pdf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmb_r.getItems().addAll("nom","prenom","parent");
        
        
        
         try{
            Connection con = (Connection) ConnexionBD.getInstance().getCnx();
            String res="SELECT en.nom,en.prenom,en.datenaiss,en.sexe,en.id,ab.nom,ab.prenom FROM enfant en,parent AS ab WHERE en.parent_id=ab.id " ;
          
            Statement statement = con.createStatement();
          //
            ResultSet rs =  statement.executeQuery(res);
            while(rs.next()){
                 AdmEnf p = new AdmEnf();
                 p.setNom(rs.getString("nom"));
                 p.setPrenom(rs.getString("prenom"));
                  p.setDate(rs.getDate("datenaiss"));
                  p.setSexe(rs.getString("sexe"));
                  p.setJardin(rs.getString("ab.nom")+" "+rs.getString("ab.prenom"));
                  p.setId(rs.getInt("id"));
                  
            
               
                
                data.add(p);
            }
        } catch (SQLException ex) {
           
         }
        
        nom.setCellValueFactory(new PropertyValueFactory<AdmEnf,String>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<AdmEnf,String>("prenom"));
        date.setCellValueFactory(new PropertyValueFactory<AdmEnf,Date>("date"));
         sexe.setCellValueFactory(new PropertyValueFactory<AdmEnf,String>("sexe"));
         parent.setCellValueFactory(new PropertyValueFactory<AdmEnf,String>("jardin"));
        
       
        enfcons.setItems(data);
        
        enfcons.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() > 1) {
                onEdit();
            }
        });
        
        
        
       
    }
    
     public void onEdit() {
        // check the table's selected item and get selected item
        if (enfcons.getSelectionModel().getSelectedItem() != null) {
            AdmEnf selectedOne = enfcons.getSelectionModel().getSelectedItem();
            id=Integer.toString(selectedOne.getId());
            
           
        }
    }
        

    @FXML
    private void supprimer(ActionEvent event) {
       
         EnfantService ens= new EnfantService();
         try{
        int mos=Integer.parseInt(id);
          as= ens.supprimerAdm(mos);
         }
         catch (Exception e){}
        
      
       if (as>0){
          Alert ale= new Alert(Alert.AlertType.INFORMATION);
          ale.setTitle("INFORMATION");
          ale.setHeaderText("suppression faite !");
          ale.showAndWait();
          
          
          data.clear();
          try{
            Connection con = (Connection) ConnexionBD.getInstance().getCnx();
            String res="SELECT en.nom,en.prenom,en.datenaiss,en.sexe,en.id,ab.nom,ab.prenom FROM enfant en,parent AS ab WHERE en.parent_id=ab.id " ;
          
            Statement statement = con.createStatement();
          
            ResultSet rs =  statement.executeQuery(res);
            while(rs.next()){
                 AdmEnf p = new AdmEnf();
                 p.setNom(rs.getString("nom"));
                 p.setPrenom(rs.getString("prenom"));
                  p.setDate(rs.getDate("datenaiss"));
                  p.setSexe(rs.getString("sexe"));
                  p.setJardin(rs.getString("ab.nom")+" "+rs.getString("ab.prenom"));
                  p.setId(rs.getInt("id"));
                  
            
               
                
                data.add(p);
            }
        } catch (SQLException ex) {
           
         }
        
        nom.setCellValueFactory(new PropertyValueFactory<AdmEnf,String>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<AdmEnf,String>("prenom"));
        date.setCellValueFactory(new PropertyValueFactory<AdmEnf,Date>("date"));
         sexe.setCellValueFactory(new PropertyValueFactory<AdmEnf,String>("sexe"));
         parent.setCellValueFactory(new PropertyValueFactory<AdmEnf,String>("jardin"));
        
       
        enfcons.setItems(data);
          
      }
        
       
        else{
            Alert ale= new Alert(Alert.AlertType.ERROR);
          ale.setTitle("INFORMATION");
          ale.setHeaderText("Selectionnez une personne !");
          ale.showAndWait();
       
       
       }
        
        
        
        
          
    }

    @FXML
    private void rechercher(KeyEvent event) {
         recherc.clear();
            if(txt_r.getText().equals(null)){
                Alert ale= new Alert(Alert.AlertType.ERROR);
          ale.setTitle("INFORMATION");
          ale.setHeaderText("veuillez écrire quelque chose !");
          ale.showAndWait();
            }
            else{
                if (cmb_r.getSelectionModel().getSelectedItem()==null){
                    Alert ale= new Alert(Alert.AlertType.ERROR);
          ale.setTitle("INFORMATION");
          ale.setHeaderText("veuillez choisir un mode !");
          ale.showAndWait();
          
                    
                }
                else {
           for (int i=0;i<data.size();i++){
               if (cmb_r.getSelectionModel().getSelectedItem().equals("nom")){
            if (data.get(i).getNom().contains(txt_r.getText())){
                
                recherc.add(data.get(i));
               
                
            }}
                if (cmb_r.getSelectionModel().getSelectedItem().equals("prenom")){
            if (data.get(i).getPrenom().contains(txt_r.getText())){
                recherc.add(data.get(i));
               
                
            }}
           
                 if (cmb_r.getSelectionModel().getSelectedItem().equals("parent")){
            if (data.get(i).getJardin().contains(txt_r.getText())){
                recherc.add(data.get(i));
               
            }
            }}
               
        
         
        
        nom.setCellValueFactory(new PropertyValueFactory<AdmEnf,String>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<AdmEnf,String>("prenom"));
        date.setCellValueFactory(new PropertyValueFactory<AdmEnf,Date>("datenaiss"));
        sexe.setCellValueFactory(new PropertyValueFactory<AdmEnf,String>("sexe"));
         parent.setCellValueFactory(new PropertyValueFactory<AdmEnf,String>("jardin"));
       
        enfcons.setItems(recherc);
                }
            }
        
    }

    @FXML
    private void generate(ActionEvent event) {
     String path="";
        
       
      
       
        
        
        try {
            
          path="C:\\Users\\Karim\\Documents\\NetBeansProjects\\";
            
        
        
        Document doc=new Document();
            PdfWriter.getInstance(doc, new FileOutputStream(path+"enfant.pdf"));
                    doc.open();
                    Paragraph para = new Paragraph("LISTE DES ENFANTS:");
                    doc.add(para);
                    Paragraph paras = new Paragraph(" ");
                    doc.add(paras);
                    Paragraph parass = new Paragraph(" ");
                    doc.add(parass);
                    
                  PdfPTable th1=new PdfPTable(5);
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
                    for (int i=0;i<data.size();i++){
                     String na=data.get(i).getNom();
                     String pr=data.get(i).getPrenom();
                      String sx=data.get(i).getSexe();
                      String pa=data.get(i).getJardin();
                      Format formatter = new SimpleDateFormat("yyyy-MM-dd");
                      String s = formatter.format(data.get(i).getDate());
                      
                      
                     
                     th1.addCell(na);
                    th1.addCell(pr);
                    th1.addCell(sx);
                    th1.addCell(pa);
                    th1.addCell(s);
                    
                    
                    
                   
                    }
                  
                    doc.add(th1);
                   
            doc.close();
            
            
                    } catch (FileNotFoundException ex) {
            Logger.getLogger(AdminEnfantController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(AdminEnfantController.class.getName()).log(Level.SEVERE, null, ex);
        
        
        }
        
        
    }
    }

   

  
        
        
    
    
    
    
    

