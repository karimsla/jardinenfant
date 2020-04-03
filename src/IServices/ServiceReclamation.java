package IServices;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import Entities.Chauffeur;
import Entities.Reclamation;
import Utils.ConnexionBD;

public class ServiceReclamation implements IserviceReclamation {
	 Connection connexion =null; 
	   List<Reclamation> reclams=new ArrayList<Reclamation>();
	   
	   
	   
	    public ServiceReclamation() throws SQLException {
	    	
	        connexion=ConnexionBD.getInstance().getCnx();
	 		String req="Select * from Reclamation";
		       try {
		           Statement statement=connexion.createStatement();
		           ResultSet rs=statement.executeQuery(req);
		           while (rs.next())
		           {
		           Reclamation c=new Reclamation(
		        		   
		        		   rs.getInt("id"),
		        		   
		        		   rs.getString("description"),
		        		   
		        		   rs.getDate("date"),
		        		   rs.getString("titre"),
		        		   rs.getString("nom"),
		        		   rs.getString("numtel"),
		        		   rs.getString("mail"),
		        		   rs.getString("etat"));
		           
		           
		           reclams.add(c);
		           }
		     
		       } catch (SQLException ex) {
		           Logger.getLogger(ChauffeurService.class.getName()).log(Level.SEVERE, null, ex);
		       }
	    }
	    
	 
	   
	    
	@Override
	public Optional<Reclamation> findbyId(int id) {
		if(connexion==null) {
		 connexion = ConnexionBD.getInstance().getCnx();
		}
		
		return reclams.stream().filter(x->x.getId()==id).findFirst();
	}

	@Override
	public List<Reclamation> findAll() {
		// TODO Auto-generated method stub
		if(connexion==null) {
			 connexion = ConnexionBD.getInstance().getCnx();
			}
	  
		
		return reclams.stream().sorted(Comparator.
				comparing(Reclamation :: getDate, Comparator.nullsLast(Comparator.reverseOrder())))
				.collect(Collectors.toList());
	}

	@Override
	public List<Reclamation> findnew() {
		if(connexion==null) {
			 connexion = ConnexionBD.getInstance().getCnx();
			}
			
		return reclams.stream().filter(x->x.getEtat().compareTo("fixed")!=0).collect(Collectors.toList());
		
	}

	@Override
	public void regelrReclamation(Reclamation reclam) {
		
		
		reclam.setEtat("fixed");
 try{
             
	 if(connexion==null) {
		 connexion = ConnexionBD.getInstance().getCnx();
		}
	 
            String res="Update Reclamation set" +"etat=? WHERE id = ?;";
            PreparedStatement pre = connexion.prepareStatement(res);
            
            pre.setString(1, reclam.getEtat());
            pre.setInt(2, reclam.getId());

            pre.executeUpdate();
            
            
        }catch(SQLException ex){
            System.out.println(ex);
        }
          
        
          
		
	}

	
	
	
	
	
	
}
