/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.Abonnement;
import Entities.Jardin;
import Utils.ConnexionBD;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author FERID
 */
public class EnfantService {
    
    
    public static int modifier(Integer id){
      int ac = 0;
       String et="accepté";
        try{
             Connection con = ConnexionBD.getInstance().getCnx();
            String res="Update abonnement SET etat=? WHERE id="+id;
            PreparedStatement pre = con.prepareStatement(res); 
            pre.setString(1,et);
            
            ac= pre.executeUpdate();
        }catch(SQLException ex){
            System.out.println(ex);
        }
        catch(Exception es)
        {
            System.err.println(es);
        }
      return ac;
  }
    
    
    
     public static int supprimer(Integer id){
      int ac = 0;
      
        try{
             Connection con = ConnexionBD.getInstance().getCnx();
            String res="DELETE FROM abonnement  WHERE id="+id;
            PreparedStatement pre = con.prepareStatement(res); 
           
            ac= pre.executeUpdate();
            
        }catch(SQLException ex){
            System.out.println(ex);
        }
        catch(Exception es)
        {
            System.err.println(es);
        }
      return ac;
  }
     
     
     
     public static int supprimerAdm(Integer id){
      int ac = 0;
      
        try{
             Connection con = ConnexionBD.getInstance().getCnx();
            String res="DELETE FROM enfant  WHERE id="+id;
            PreparedStatement pre = con.prepareStatement(res); 
           
            ac= pre.executeUpdate();
            
        }catch(SQLException ex){
            System.out.println(ex);
        }
        catch(Exception es)
        {
            System.err.println(es);
        }
      return ac;
  }
     
     
     
     
      public static int modifiertype(Integer id,String ty){
      int ac = 0;
       
        try{
             Connection con = ConnexionBD.getInstance().getCnx();
            String res="Update abonnement SET type=? WHERE id="+id;
            PreparedStatement pre = con.prepareStatement(res); 
            pre.setString(1,ty);
            
            ac= pre.executeUpdate();
        }catch(SQLException ex){
            System.out.println(ex);
        }
        catch(Exception es)
        {
            System.err.println(es);
        }
      return ac;
  }
      
      public void ajouterAbonnement(int jid,int jenf,String date,String type,String etat,String montant)
   {
       
      
        try {
            Connection cnx = ConnexionBD.getInstance().getCnx();
           String res="Insert into abonnement (jardin_id,enfant_id,date,type,etat,montant) values ('"+jid+"','"+jenf+"','"+date+"','"+type+"','"+etat+"','"+montant+"')";
            Statement statement=cnx.createStatement();
           statement.executeUpdate(res);
           System.out.println("ajout réussie!");
       } catch (SQLException ex) {
      System.out.println(ex); }
   }
      
      public void ajouterEnfant(int pid,String nom,String prenom,String date,String sexe)
   {
       
      
        try {
            Connection cnx = ConnexionBD.getInstance().getCnx();
           String res="Insert into enfant (parent_id,nom,prenom,datenaiss,sexe) values ('"+pid+"','"+nom+"','"+prenom+"','"+date+"','"+sexe+"')";
            Statement statement=cnx.createStatement();
           statement.executeUpdate(res);
           System.out.println("ajout réussie!");
       } catch (SQLException ex) {
      System.out.println(ex); }
   }
      
      
    
}
