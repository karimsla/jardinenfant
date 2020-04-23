/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.Evenement;
import Entities.StatModel;
import Utils.ConnexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Emna
 */
public class EvenementService {
    private Connection cnx;

    public void Ajouter(Evenement e, int id) {

        try {

            Connection con = ConnexionBD.getInstance().getCnx();
            String res = "Insert into Evenement(titre,date,description,image,categorie_id,jardin_id)" +
                    " values (?,?,?,?,?,?)";

            PreparedStatement pre = con.prepareStatement(res);
            pre.setString(1,e.getTitre());
            pre.setString(2,e.getDate());

            pre.setString(3,e.getDescription());
            pre.setString(4,e.getImage());
            pre.setInt(5,e.getCategorie().getId());
            pre.setInt(6,id);
            pre.executeUpdate();


        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void ModifierEvenement(Evenement e) {

        try {
            cnx = ConnexionBD.getInstance().getCnx();
            String res = "Update Evenement Set titre='" + e.getTitre() + "',description='" + e.getDescription() + "',date='" + e.getDate() + "'"
                    + "where id=" + e.getId();

            Statement st = cnx.createStatement();
            st.executeUpdate(res);


        } catch (SQLException ex) {

            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    public void supprimerEvenement(int id) {
        try {
            Connection cnx = ConnexionBD.getInstance().getCnx();

            String res = "Delete from Evenement where id=" + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(res);

        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public List<Evenement> afficherAll() {
        CategorieService cs = new CategorieService();
        List<Evenement> le = new ArrayList<Evenement>();
        String req = "Select * from Evenement";
        try {
            cnx = ConnexionBD.getInstance().getCnx();
            Statement statement = cnx.createStatement();
            ResultSet rs = statement.executeQuery(req);

            while (rs.next()) {
                Evenement e = new Evenement(rs.getString("titre"), rs.getDate("date").toString(), rs.getString("description"), rs.getString("image"));
                e.setId(rs.getInt(1));
                e.setCategorie(cs.find(rs.getInt("categorie_id")));
                le.add(e);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return le;
    }

    public List<Evenement> findmine(int id) {
        CategorieService cs = new CategorieService();
        List<Evenement> le = new ArrayList<Evenement>();
        String req = "Select * from Evenement where jardin_id=" + id;
        try {
            cnx = ConnexionBD.getInstance().getCnx();
            Statement statement = cnx.createStatement();
            ResultSet rs = statement.executeQuery(req);

            while (rs.next()) {
                Evenement e = new Evenement(rs.getString("titre"), rs.getDate("date").toString(), rs.getString("description"), rs.getString("image"));
                e.setId(rs.getInt(1));
                e.setCategorie(cs.find(rs.getInt("categorie_id")));

                le.add(e);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return le;
    }


    public Evenement find(int id) {
        String req = "Select * from evenement where id=" + id;
        Evenement e = new Evenement();
        try {
            Statement statement = cnx.createStatement();
            ResultSet rs = statement.executeQuery(req);

            rs.next();
            e = new Evenement(rs.getString("titre"), rs.getDate("date").toString(), rs.getString("description"), rs.getString("image"));
        } catch (SQLException ex) {
            Logger.getLogger(ChauffeurService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return e;

    }

    public int Count(int id){
        int c=0;
        String req = "Select Count(*) FROM participer where evenement_id="+id;
        try {
            Statement statement = cnx.createStatement();
            ResultSet rs = statement.executeQuery(req);

           rs.next();
           c=rs.getInt(1);


        } catch (SQLException ex) {
            Logger.getLogger(ChauffeurService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;

    }


    public List<StatModel> getStat() {
        cnx = ConnexionBD.getInstance().getCnx();
        List<StatModel> ls = new ArrayList();
        String req = "Select titre ,id FROM evenement ";
        Evenement e = new Evenement();
        try {
            Statement statement = cnx.createStatement();
            ResultSet rs = statement.executeQuery(req);
            String titre;
            int c=0;
            while (rs.next()) {
               // e = find(rs.getInt(2));
               c=Count(rs.getInt(2));


              StatModel statM = new StatModel(rs.getString(1),c );
                ls.add(statM);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ChauffeurService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ls;

    }
}
     
     
     
             
    

