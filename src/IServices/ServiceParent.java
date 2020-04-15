package IServices;

import Entities.Parents;
import Entities.Reclamation;
import Utils.ConnexionBD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.Alert;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ServiceParent implements IserviceParent {

    Connection connexion =null;
    List<Parents> pare=new ArrayList<Parents>();



    public ServiceParent() throws SQLException {

        connexion= ConnexionBD.getInstance().getCnx();
        String req="Select *from parent";
        try {
            Statement statement=connexion.createStatement();
            ResultSet rs=statement.executeQuery(req);
            while (rs.next())
            {
                Parents c=new Parents(

                        rs.getInt("id"),

                        rs.getString("nom"),

                        rs.getString("prenom"),
                        rs.getString("numtel"),
                        rs.getString("adresse"),
                        rs.getString("sexe")
                      ) ;


                pare.add(c);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }









    @Override
    public Parents findById(int id) {
        if(connexion==null) {
            connexion = ConnexionBD.getInstance().getCnx();
        }

        return pare.stream().filter(x->x.getId()==id).findFirst().get();
    }

    @Override
    public int create(Parents type) {
        String query="INSERT INTO parent (nom,Prenom,numtel,adresse,sexe) values ("+type.getNom()+","+type.getPrenom()+","+type.getNumtel()
        +","+type.getAdresse()+","+type.getSexe()
                +")";
        int flag=0;
        try {
            PreparedStatement statement= connexion.prepareStatement(query);
            flag=statement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CrudJardinEnfantImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }

    @Override
    public int update(Parents type) {

        return 0;
    }

    @Override
    public int delet(Parents type) {
        String query="DELETE  from parent WHERE id="+type.getId();
        int rowDeleted=0;
        try {
            PreparedStatement statement= connexion.prepareStatement(query);
            rowDeleted=statement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowDeleted;
    }

    @Override
    public int delete(int id) {
        String query="DELETE  from parent WHERE id="+id;
        int rowDeleted=0;
        try {
            PreparedStatement statement= connexion.prepareStatement(query);
            rowDeleted=statement.executeUpdate();

        } catch (SQLException ex) {
           // Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur survenu");
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());

            alert.showAndWait();
        }
        return rowDeleted;
    }

    @Override
    public int nbrenfant(int id) {
        int nb=0;
        String req="SELECT count(*) from enfant where parent_id="+id;

        try {
            Statement statement=connexion.createStatement();
            ResultSet rs=statement.executeQuery(req);
            rs.next();
            nb=rs.getInt(1);

        } catch (SQLException ex) {
            Logger.getLogger(ServiceParent.class.getName()).log(Level.SEVERE, null, ex);

        }

        return nb;
    }

    @Override
    public ObservableList<Parents> search(String search) {
        //String req="select from"
        ObservableList<Parents> data = FXCollections.observableArrayList();



         pare.stream().filter(x->x.getNom().compareToIgnoreCase(search)==0||
                        x.getPrenom().compareToIgnoreCase(search)==0||
                        x.getSexe().compareToIgnoreCase(search)==0||
                        x.getAdresse().compareToIgnoreCase(search)==0||
                        x.getNumtel().compareToIgnoreCase(search)==0  );

         data.addAll(pare);
         return data;



    }


    @Override
    public List<Parents> findAll() {
        if(connexion==null) {
            connexion = ConnexionBD.getInstance().getCnx();
        }
        return pare;
    }
}
