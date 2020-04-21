package IServices;


import Entities.Remarque;
import Utils.ConnexionBD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static jardin.enfant.JardinEnfant.authenticated;

public class ServiceRemarque implements IserviceRemarque {


    Connection connexion =null;
    List<Remarque> remars=new ArrayList<Remarque>();



    public ServiceRemarque() throws SQLException {

        connexion= ConnexionBD.getInstance().getCnx();
        String req="Select r.*, t.nom as tutnom,t.prenom as tutprenom, e.nom as enfantnom,e.prenom as enfantprenom from enfant as e  JOIN abonnement as a on a.enfant_id=e.id  JOIN remarque as r on r.abonnement_id=a.id  JOIN tuteur as t on t.id=r.tuteur_id";
        try {
            Statement statement=connexion.createStatement();
            ResultSet rs=statement.executeQuery(req);
            while (rs.next())
            {

                Remarque c=new Remarque(

                        rs.getInt("id"),

                        rs.getString("description"),

                        rs.getDate("date"),
                        rs.getString("tutnom")+" "+rs.getString("tutnom") ,
                        rs.getString("enfantnom")+" "+rs.getString("enfantprenom"));



                remars.add(c);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }





    @Override
    public Remarque findById(int id) {
        return null;
    }

    @Override
    public int create(Remarque type) {
        return 0;
    }

    @Override
    public int update(Remarque type) {
        return 0;
    }

    @Override
    public int delet(Remarque type) {
        return 0;
    }

    @Override
    public List<Remarque> findAll() {
        IserviceUser su=new ServiceUser();
        int idjar=su.jardinid(authenticated.getId());
        String req="Select r.*, t.nom as tutnom,t.prenom as tutprenom, e.nom as enfantnom,e.prenom as enfantprenom from enfant as e  JOIN abonnement as a on a.enfant_id=e.id  " +
                "JOIN remarque as r on r.abonnement_id=a.id  JOIN tuteur as t on t.id=r.tuteur_id where a.jardin_id="+idjar;
        try {
            Statement statement=connexion.createStatement();
            ResultSet rs=statement.executeQuery(req);
            while (rs.next())
            {

                Remarque c=new Remarque(

                        rs.getInt("id"),

                        rs.getString("description"),

                        rs.getDate("date"),
                        rs.getString("tutnom")+" "+rs.getString("tutnom") ,
                        rs.getString("enfantnom")+" "+rs.getString("enfantprenom"));



                remars.add(c);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return remars;
    }

    @Override
    public List<Remarque> Mesremarques(int id) {
        String req="Select r.*, t.nom as tutnom,t.prenom as tutprenom, e.nom as enfantnom,e.prenom as enfantprenom from enfant as e  JOIN abonnement as a on a.enfant_id=e.id " +
                " JOIN remarque as r on r.abonnement_id=a.id  JOIN tuteur as t on t.id=r.tuteur_id where e.parent_id="+id;
        try {
            Statement statement=connexion.createStatement();
            ResultSet rs=statement.executeQuery(req);
            while (rs.next())
            {

                Remarque c=new Remarque(

                        rs.getInt("id"),

                        rs.getString("description"),

                        rs.getDate("date"),
                        rs.getString("tutnom")+" "+rs.getString("tutnom") ,
                        rs.getString("enfantnom")+" "+rs.getString("enfantprenom"));



                remars.add(c);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return remars;
    }
}
