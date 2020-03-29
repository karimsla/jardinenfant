/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.Activite;
import Utils.ConnexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Dorra Kerrou
 */
public class ActiviteServices {
    
  public void ajouter(Activite a){
      
          try{
             
            Connection con = (Connection) ConnexionBD.getInstance().getCnx();
            String res="Insert into Activite(typeact,detailles,date) values (?,?,?)";
            PreparedStatement pre = con.prepareStatement(res);
            pre.setString(1,a.getTypeact());
            pre.setString(2,a.getDetailles());
            pre.setDate(3,a.getDate());
            pre.executeUpdate();
            
            
        }catch(SQLException ex){
            System.out.println(ex);
        }

      
  }
    
    
}
