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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dorra Kerrou
 */
public class ActiviteServices {
    
    
    private Connection cnx; 

    public ActiviteServices() {
        cnx = ConnexionBD.getInstance().getCnx();
    }
    
    
  public static int  ajouter(Activite a){
      int ac = 0 ;
          try{
             
             Connection con = ConnexionBD.getInstance().getCnx();
            String res="Insert into Activite(typeact,detailles,date,club_id) values (?,?,?,?)";
            PreparedStatement pre = con.prepareStatement(res);
            pre.setString(1,a.getTypeact());
            pre.setString(2,a.getDetailles());
            pre.setString(3,a.getDate());
            pre.setInt(4,a.getClub().getId());
            ac= pre.executeUpdate();
            
            
        }catch(SQLException ex){
            System.out.println(ex);
        }
          
          return ac ; 
          
          

      
  }
  public static int modifier(Activite A){
      
      int ac = 0;
      
        try{
             
             Connection con = ConnexionBD.getInstance().getCnx();
            String res="Update Activite SET typeact=? ,detailles=? ,date=? , club_id=? WHERE typeact=?";
            PreparedStatement pre = con.prepareStatement(res); 
            pre.setString(1,A.getTypeact());
            pre.setString(2,A.getDetailles());
            pre.setString(3,A.getDate());
            pre.setInt(4,A.getClub().getId());
            pre.setString(5,A.getTypeact());
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
    
  public static Activite findActivite(String nom ){
      Activite A = new Activite();
      
    
       try{
        Connection con = ConnexionBD.getInstance().getCnx();
        
         String res="Select * from Activite where typeact Like '%"+nom+"%' "  ;
           PreparedStatement stat = con.prepareStatement(res);
             ResultSet rs =  stat.executeQuery(res);
             Club c = new Club();
             if(rs.next()){
                
              
                 c.setId(rs.getInt("club_id"));
                 A.setTypeact(rs.getString("typeact"));
                 A.setDetailles(rs.getString("detailles"));
                 A.setDate(rs.getString("date"));
                 A.setClub(c);
              
             }
        }catch(SQLException ex){
            System.out.println(ex);
        }
      
      
      
      return A;
  }
  
  public static Club findNomClub(int id ){
      
      Club c1 = new Club();
        try {
            Connection con = ConnexionBD.getInstance().getCnx();
            
            String res="Select Name from Club WHERE id= "+id  ;
            
            PreparedStatement stat = con.prepareStatement(res);
            
            ResultSet rs =  stat.executeQuery(res);
            
            while(rs.next()){
                c1.setName(rs.getString("Name"));
           
                
            }
        } catch (SQLException ex) {
            System.err.println(ex);;
        }
        return c1;
  
      
  }

  
  
  public static int Delete(String nom ){
        int d = 0 ;
        try {
          
            
            Connection con = ConnexionBD.getInstance().getCnx();
            
            String res="DELETE FROM Activite Where typeact Like '%"+nom+"%'" ;
            
            PreparedStatement stat = con.prepareStatement(res);
         
           d =  stat.executeUpdate(res);
            
            
            
           
        } catch (SQLException ex) {
            Logger.getLogger(ActiviteServices.class.getName()).log(Level.SEVERE, null, ex);
        }
         
            return d ;
  }
    
}
