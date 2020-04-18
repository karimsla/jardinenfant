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
		        		   rs.getString("mail"));
		        		 //  rs.getString("etat"));

		           
		           
		           reclams.add(c);
		           }
		     
		       } catch (SQLException ex) {
		           Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
		       }
	    }
	    
	 
	   
	    
	@Override
	public Reclamation findbyId(int id) {
		if(connexion==null) {
		 connexion = ConnexionBD.getInstance().getCnx();
		}
		
		return reclams.stream().filter(x->x.getId()==id).findFirst().get();
	}

	@Override
	public Reclamation findById(int id) {
		if(connexion==null) {
			connexion = ConnexionBD.getInstance().getCnx();
		}

		return reclams.stream().filter(x->x.getId()==id).findFirst().get();
	}

	@Override
	public int create(Reclamation type) {

		String query="INSERT  INto  Reclamation (description,date,titre,nom,numtel,mail,etat) VALUES ("+type.getDescription()+","+type.getDate()+","+type.getTitre()+","+type.getNom()

				+","+type.getNumtel()+","+type.getMail() +","+type.getEtat()+")";
		int flag=0;
		try {
			PreparedStatement statement= connexion.prepareStatement(query);
			flag=statement.executeUpdate();

		} catch (SQLException ex) {
			Logger.getLogger(CrudJardinEnfantImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
		return flag;
	}

	@Override
	public int update(Reclamation type) {

		try{

			if(connexion==null) {
				connexion = ConnexionBD.getInstance().getCnx();
			}

			String res="Update Reclamation set" +"etat=? WHERE id = ?;";
			PreparedStatement pre = connexion.prepareStatement(res);

			pre.setString(1, "fixed");
			pre.setInt(2, type.getId());

			pre.executeUpdate();


		}catch(SQLException ex){
			System.out.println(ex);
		}


	    	return 0;
	}

	@Override
	public int delet(Reclamation type) {

		String query="DELETE  from reclamation WHERE id="+type.getId();
		int rowDeleted=0;
		try {
			PreparedStatement statement= connexion.prepareStatement(query);
			rowDeleted=statement.executeUpdate();

		} catch (SQLException ex) {
			Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
		}
		return rowDeleted;
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
