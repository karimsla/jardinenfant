/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.Activite;
import Entities.Club;
import Utils.ConnexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dorra Kerrou
 */
public class ClubServices {
    
    
     public static int  ajouter(Club a){
      int ac = 0 ;
          try{
             
             Connection con = ConnexionBD.getInstance().getCnx();
            String res="Insert into Club(Name,Description) values (?,?)";
            PreparedStatement pre = con.prepareStatement(res);
            pre.setString(1,a.getName());
            pre.setString(2,a.getDescription());
            
            ac= pre.executeUpdate();
            
            
        }catch(SQLException ex){
            System.out.println(ex);
        }
          
          return ac ; 
          
          

      
  }
     
     
      public static int modifier(String nom, String desc, Integer id){
      
      int ac = 0;
      
        try{
             
             Connection con = ConnexionBD.getInstance().getCnx();
            String res="Update Club SET Name=? ,Description=? WHERE id="+id;
            PreparedStatement pre = con.prepareStatement(res); 
            pre.setString(1,nom);
            pre.setString(2,desc);
           

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
      
      
        public static int Delete(Integer id ){
        int d = 0 ;
        try {
          
            
            Connection con = ConnexionBD.getInstance().getCnx();
            
            String res="DELETE FROM Club Where id="+id ;
            
            PreparedStatement stat = con.prepareStatement(res);
         
           d =  stat.executeUpdate(res);
            
            
            
           
        } catch (SQLException ex) {
            Logger.getLogger(ActiviteServices.class.getName()).log(Level.SEVERE, null, ex);
        }
         
            return d ;
  }
    
}
