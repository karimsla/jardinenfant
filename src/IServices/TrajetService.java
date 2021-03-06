/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.Chauffeur;
import Entities.Trajet;
import Utils.ConnexionBD;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sami
 */
public class TrajetService {
    
   private Connection cnx;
   
   public TrajetService()
   {
       cnx=ConnexionBD.getInstance().getCnx();
   }
   
   public void ajouterTrajet(Trajet t,int id)
   {
       try {
           String res="Insert into trajet (chauffeur_id,heure,adresse) values ("+id+",'"+t.getHeure()+"','"+t.getAdresse()+"')";
           Statement statement=cnx.createStatement();
           statement.executeUpdate(res);
           System.out.println("ajout réussie!");
       } catch (SQLException ex) {
      System.out.println(ex); }
   }
   
   public List<Trajet> afficherTrajet(int jardinId){
          List<Trajet> lp=new ArrayList<Trajet>();
           ChauffeurService cs=new ChauffeurService();
         String req="Select t.id,chauffeur_id,heure,adresse from trajet t,chauffeur c where chauffeur_id=c.id and jardin_id="+jardinId;
       try {
           Statement statement=cnx.createStatement();
           ResultSet rs=statement.executeQuery(req);
           while (rs.next())
           {
            
           Trajet c=new Trajet(rs.getInt(1),rs.getString("adresse"),rs.getString("heure"));
          c.setChauffeur(cs.find(rs.getInt(2)));
           lp.add(c);
         System.out.println(c);
           }
       } catch (SQLException ex) 
       {
           Logger.getLogger(ChauffeurService.class.getName()).log(Level.SEVERE, null, ex);
       }
       return lp;
   }
   public List<Trajet> afficherTrajetChauffeur(int id){
           List<Trajet> lp=new ArrayList<Trajet>();
           ChauffeurService cs=new ChauffeurService();
           String req="Select * from trajet where chauffeur_id="+id;
       try {
           Statement statement=cnx.createStatement();
           ResultSet rs=statement.executeQuery(req);
           while (rs.next())
           {
           Trajet c=new Trajet(rs.getInt(1),rs.getString("adresse"),rs.getString("heure"));
          c.setChauffeur(cs.find(id));
           lp.add(c);
           }
       } catch (SQLException ex) 
       {
           Logger.getLogger(ChauffeurService.class.getName()).log(Level.SEVERE, null, ex);
       }
       return lp;
   }
    public void supprimerTrajet(int id)
   {
       try {
           String res="Delete from trajet where id="+id;
           Statement statement=cnx.createStatement();
           statement.executeUpdate(res);
           System.out.println("Suppression réussie!");
       } catch (SQLException ex) {
           Logger.getLogger(ChauffeurService.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
   
    public List<Trajet> afficherTrajetParent(int parentId)
    {
        String req="Select * from trajet t,chauffeur c WHERE t.chauffeur_id=c.id and c.jardin_id IN (SELECT abo.jardin_id FROM abonnement abo,enfant en WHERE abo.enfant_id=en.id and en.parent_id="+parentId+")";

  List<Trajet> lp=new ArrayList<Trajet>();
           ChauffeurService cs=new ChauffeurService();
       try {
           Statement statement=cnx.createStatement();
           ResultSet rs=statement.executeQuery(req);
           while (rs.next())
           {
            
           Trajet c=new Trajet(rs.getInt(1),rs.getString("adresse"),rs.getString("heure"));
          c.setChauffeur(cs.find(rs.getInt(2)));
           lp.add(c);
         System.out.println(c);
           }
       } catch (SQLException ex) 
       {
           Logger.getLogger(ChauffeurService.class.getName()).log(Level.SEVERE, null, ex);
       }
       return lp;
    }
    
}
