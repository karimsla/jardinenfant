/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.Evenement;
import Utils.ConnexionBD;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static sun.misc.MessageUtils.where;

/**
 *
 * @author Emna
 */
public class EvenementService {
      private Connection cnx;

      public void Ajouter(Evenement e){
      
          try{
             
            Connection con = (Connection) ConnexionBD.getInstance().getCnx();
            String res="Insert into Evenement(titre,date,description,image) values ('"+e.getTitre()+"','"+e.getDate()+"','"+e.getDescription()+"','"+e.getImage()+"')";
            PreparedStatement pre = con.prepareStatement(res);
           
            pre.executeUpdate();
            
            
        }catch(SQLException ex){
            System.out.println(ex);
        }
      }
    
        public void ModifierEvenement(Evenement e){
            
         try{
cnx=(Connection)ConnexionBD.getInstance().getCnx();
              String res ="Update Evenement Set titre='"+e.getTitre()+"',description='"+e.getDescription()+"',date='"+e.getDate()+"',image='"+e.getImage()+"'"
              + "where id="+e.getId();
         
           Statement st=cnx.createStatement();
             st.executeUpdate(res);
             System.out.println("Evenement modifié!");
           
            
          }catch(SQLException ex){
              
          Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }    
           
     } 
          
        
        
             public void supprimerEvenement(int id)
   {
       try {
           Connection cnx = (Connection) ConnexionBD.getInstance().getCnx();

           String res="Delete from Evenement where id="+id;
           Statement st=cnx.createStatement();
           st.executeUpdate(res);
           System.out.println("Suppression réussie!");
       } catch (SQLException ex) {
           Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
            
          
     public List<Evenement> afficherAll(){
           List<Evenement> le=new ArrayList<Evenement>();
           String req="Select * from Evenement";
       try {
           cnx=(Connection)ConnexionBD.getInstance().getCnx();
           Statement statement= cnx.createStatement();
           ResultSet rs=statement.executeQuery(req);
           
           while (rs.next())
           {Evenement e=new Evenement(rs.getString("titre"),rs.getDate("date").toString(),rs.getString("description"),rs.getString("image"));
        System.out.println(e);
           e.setId(rs.getInt(1));
           le.add(e);
           }
       } catch (Exception ex) {
           System.out.println(ex);
       }
       return le;
   }       
             
 
     
     
     
    public Evenement find(int id)
    {
         String req="Select * from evenement where id="+id;
       Evenement e=new Evenement();
         try {
           Statement statement=cnx.createStatement();
           ResultSet rs=statement.executeQuery(req);
       
           rs.next();
           e=new Evenement(rs.getString(1),rs.getString(3),rs.getDate(2).toString(),rs.getString(4));
       } catch (SQLException ex) {
           Logger.getLogger(ChauffeurService.class.getName()).log(Level.SEVERE, null, ex);
       }
       return e;
       
    }
}
     
     
     
             
    

