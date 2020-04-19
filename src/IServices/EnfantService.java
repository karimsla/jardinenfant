/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Utils.ConnexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
    
}
