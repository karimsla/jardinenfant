/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.Activite;
import Entities.Club;
import Utils.ConnexionBD;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static jardin.enfant.JardinEnfant.authenticated;

/**
 *
 * @author Dorra Kerrou
 */
public class ClubServices {

   
    private FileInputStream fis ; 
    
    
     public static int  ajouter(Club a){
      int ac = 0 ;
          try{
              IserviceUser su =new ServiceUser();
             int id=su.jardinid(authenticated.getId());
             Connection con = ConnexionBD.getInstance().getCnx();
            String res="Insert into Club(Name,Description,photo,jardin_id) values (?,?,?,?)";
            PreparedStatement pre = con.prepareStatement(res);
            pre.setString(1,a.getName());
            pre.setString(2,a.getDescription());
                      
            pre.setString(3,a.getPhoto());
              pre.setInt(4,id);
            
            ac= pre.executeUpdate();
            
            
        }catch(SQLException ex){
            System.out.println(ex);
        }
          
          return ac ; 
          
          

      
  }
     
     
      public static int modifier(Club c){
      
      int ac = 0;
      
        try{
             
             Connection con = ConnexionBD.getInstance().getCnx();
            String res="Update Club SET Name=? ,Description=?, photo=? WHERE id=?";
            PreparedStatement pre = con.prepareStatement(res); 
             pre.setString(1,c.getName());
            pre.setString(2,c.getDescription());
            pre.setString(3,c.getPhoto());
            pre.setInt(4,c.getId());
           

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
    
        
          public static Club findClub(String nom ){
      
              Club A = new Club();
      
    
       try{
        Connection con = ConnexionBD.getInstance().getCnx();
        
         String res="Select * from Club where Name Like '%"+nom+"%' "  ;
           PreparedStatement stat = con.prepareStatement(res);
             ResultSet rs =  stat.executeQuery(res);
            
             if(rs.next()){
                
              
               
                 
                 A.setName(rs.getString("Name"));
                 A.setDescription(rs.getString("Description"));
                 A.setPhoto(rs.getString("photo"));
                 A.setId(rs.getInt("id"));
               
              
             }
        }catch(SQLException ex){
            System.out.println(ex);
        }
      
      
      
      return A;
  }
}
