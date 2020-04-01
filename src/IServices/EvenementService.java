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

/**
 *
 * @author Emna
 */
public class EvenementService {
    
      public void Ajouter(Evenement e){
      
          try{
             
            Connection con = (Connection) ConnexionBD.getInstance().getCnx();
            String res="Insert into Evenement(id,titre,date,description,image) values (?,?,?,?,?)";
            PreparedStatement pre = con.prepareStatement(res);
            pre.setString(1,e.getTitre());
            pre.setDate(2,e.getDate());
            pre.setString(3,e.getDescription());
            pre.setString(4,e.getImage());
            pre.executeUpdate();
            
            
        }catch(SQLException ex){
            System.out.println(ex);
        }
      }
    
        public void ModifierEvenement(int id_E,String titre_E,String description_E, Date date_E,String image_E){
            
         try{
            Connection cnx = (Connection) ConnexionBD.getInstance().getCnx();

             String query="Update Evenement SET titre='"+titre_E
      
               +"', date="+date_E
               +"'description="+description_E
               +"'image="+image_E
             
             +" WHERE id="+id_E;
           Statement st=cnx.createStatement();
             st=cnx.createStatement();
             st.executeUpdate(query);
             System.out.println("Evenement modifié!");
           
            
          }catch(SQLException e){
              
            System.out.println(e);
        }    
            
     }     
          
        
        
             public void supprimerEvenement(int id)
   {
       try {
           Connection cnx = (Connection) ConnexionBD.getInstance().getCnx();

           String res="Delete from Evenement where id="+id;
           Statement st=cnx.createStatement();
           st =cnx.createStatement();
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
           Connection cnx = (Connection) ConnexionBD.getInstance().getCnx();
           Statement statement= cnx.createStatement();
           ResultSet rs=statement.executeQuery(req);
           while (rs.next())
           {Evenement e=new Evenement(rs.getString(1),rs.getDate(2),rs.getString(3),rs.getString(4));
           le.add(e);
           }
       } catch (SQLException ex) {
           Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
       }
       return le;
   }       
             
             
             
    }

