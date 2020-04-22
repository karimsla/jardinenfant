/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.Enfant;
import Entities.Evenement;
import Utils.ConnexionBD;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Emna
 */
public class ParticiperService {
    
    public List<Enfant> ConsulterEnfantParent(int id)
    {String res="Select * from enfant where parent_id="+id;
    List<Enfant> cs=new ArrayList<Enfant>(); 
    Connection cnx=ConnexionBD.getInstance().getCnx();   
    try
        {
        Statement statement=cnx.createStatement();
       ResultSet rs= statement.executeQuery(res);
        while(rs.next())
        {
            Enfant en=new Enfant(rs.getInt("id"),rs.getString("nom"),rs.getString("prenom"),rs.getDate("datenaiss"),rs.getString("sexe"));
 System.out.println(rs.getString("nom"));
        cs.add(en);
        }
        }
        catch(SQLException ex)
        {System.out.println(ex);
            
        }
    return cs;
    }
    
    public void participer(Evenement e,Enfant en)
    {
        String res="INSERT INTO participer(enfant_id,evenement_id) values("+en.getId()+","+e.getId()+")";
         try
        {Connection cnx=ConnexionBD.getInstance().getCnx();
        Statement statement=cnx.createStatement();
       statement.executeUpdate(res);
 
        
        }
        catch(SQLException ex)
        {
            System.out.println(ex);
        }
       
    }
    
    
}