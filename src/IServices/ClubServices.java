/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.Club;
import Utils.ConnexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

}
