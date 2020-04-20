/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.JardinEnfant;
import Utils.ConnexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
;

/**
 *
 * @author ASUS
 */
public class CrudJardinEnfantImpl implements CrudJardinEnfant{
    
    Connection connexion =null; 

    public CrudJardinEnfantImpl() throws SQLException {
        connexion=ConnexionBD.getInstance().getCnx();
    }
    
 

    @Override
    public List<JardinEnfant> findByNom(String nom) {
   List<JardinEnfant> allJardinEnfant = new ArrayList<>();
       
        try{
         

                
                String req="SELECT * FROM jardin WHERE Name='"+nom+"'";
            Statement stm=connexion.createStatement();
            ResultSet rs=stm.executeQuery(req);
                
                
               
           while(rs.next()){
              JardinEnfant j= new JardinEnfant(
                   rs.getString(2),
                   rs.getString("Description"),
                       rs.getString("numtel"),
                     rs.getString("Adresse"),
                     rs.getString("Etat"),
                      rs.getDouble("Tarif"));
  allJardinEnfant.add(j);
                
           }
          
         
            
        } 
        catch (SQLException ex) {
            Logger.getLogger(CrudJardinEnfantImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allJardinEnfant;
    }
        

    @Override
    public List<JardinEnfant> findByNum(String numTel) {
 List<JardinEnfant> allJardinEnfant = new ArrayList<>();
        String query="select * from jardin where numtel='"+numTel+"'";
        try{
            Statement statement= connexion.createStatement();
            ResultSet resultSet= statement.executeQuery(query);
            while(resultSet.next()){
                allJardinEnfant.add(new JardinEnfant(
                        resultSet.getString("Name"),
                        resultSet.getString("Description"),
                        resultSet.getString("numtel"),
                        resultSet.getString("Adresse"),
                        resultSet.getString("Etat"),
                        resultSet.getDouble("Tarif"))
                
                );
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CrudJardinEnfantImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allJardinEnfant;
        }

    @Override
    public List<JardinEnfant> findByEtat(String etat) {
 List<JardinEnfant> allJardinEnfant = new ArrayList<>();
        String query="select * from jardin where Etat='"+etat+"'";
        try{
            Statement statement= connexion.createStatement();
            ResultSet resultSet= statement.executeQuery(query);
            while(resultSet.next()){
                allJardinEnfant.add(new JardinEnfant(
                        resultSet.getString("Name"),
                        resultSet.getString("Description"),
                        resultSet.getString("numtel"),
                        resultSet.getString("Adresse"),
                        resultSet.getString("Etat"),
                        resultSet.getDouble("Tarif"))
                
                );
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CrudJardinEnfantImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allJardinEnfant;
    }
    @Override
    public List<JardinEnfant> findByTarif(Double tarif) {
     List<JardinEnfant> allJardinEnfant = new ArrayList<>();
        String query="select * from jardin where Tarif='"+tarif+"'";
        try{
            Statement statement= connexion.createStatement();
            ResultSet resultSet= statement.executeQuery(query);
            while(resultSet.next()){
                allJardinEnfant.add(new JardinEnfant(
                        resultSet.getString("Name"),
                        resultSet.getString("Description"),
                        resultSet.getString("numtel"),
                        resultSet.getString("Adresse"),
                        resultSet.getString("Etat"),
                        resultSet.getDouble("Tarif"))
                
                );
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CrudJardinEnfantImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allJardinEnfant;
    }

    @Override
    public List<JardinEnfant> findByAdresse(String adresse) {
     List<JardinEnfant> allJardinEnfant = new ArrayList<>();
        String query="select * from jardin where Adresse='"+adresse+"'";
        try{
            Statement statement= connexion.createStatement();
            ResultSet resultSet= statement.executeQuery(query);
            while(resultSet.next()){
                allJardinEnfant.add(new JardinEnfant(
                        resultSet.getString("Name"),
                        resultSet.getString("Description"),
                        resultSet.getString("numtel"),
                        resultSet.getString("Adresse"),
                        resultSet.getString("Etat"),
                        resultSet.getDouble("Tarif"))
                
                );
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CrudJardinEnfantImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allJardinEnfant;
    }

    @Override
    public JardinEnfant findById(int id) {
 JardinEnfant jardinEnfant = null;
        String query="select * from jardin where id='"+id+"'";
        try{
            Statement statement= connexion.createStatement();
            ResultSet resultSet= statement.executeQuery(query);
            resultSet.next();
                jardinEnfant=new JardinEnfant(
                        resultSet.getString("Name"),
                        resultSet.getString("Description"),
                        resultSet.getString("numtel"),
                        resultSet.getString("Adresse"),
                        resultSet.getString("Etat"),
                        resultSet.getDouble("Tarif")
                
                );
            } catch (SQLException ex) {
            Logger.getLogger(CrudJardinEnfantImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jardinEnfant;
    }    

    @Override
    public int create(JardinEnfant type) {
        String query = "INSERT INTO jardin"+
                "(Name,Description, numtel, Adresse, Etat,  Tarif)"+
                "VALUES(?,?,?,?,?,?)";
        int inserted =0;
        try{
             PreparedStatement statement= connexion.prepareStatement(query);
          statement.setString(1, type.getNom());
          statement.setString(2, type.getDescription());
          statement.setString(3, type.getNumTel());
          statement.setString(4, type.getAdresse());
          statement.setString(5, type.getEtat());
          statement.setDouble(6, type.getTarif());
          inserted=statement.executeUpdate();
         
        } catch (SQLException ex) {
            Logger.getLogger(CrudJardinEnfantImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inserted;
    }

    @Override
    public int update(JardinEnfant type) {
        int update=0;
        String query="UPDATE jardin SET"+
                "Name=?"+
                "Description=?"+
                "numtel=?"+
                "Adresse=?"+
                "Etat=?"+
                "Tarif=?"+
               " WHERE id=?" ;
        
        try {
            
          PreparedStatement statement= connexion.prepareStatement(query);
          statement.setString(1, type.getNom());
          statement.setString(2, type.getDescription());
          statement.setString(3, type.getNumTel());
          statement.setString(4, type.getAdresse());
          statement.setString(5, type.getEtat());
          statement.setDouble(6, type.getTarif());
          statement.setInt(7, type.getId());
          update=statement.executeUpdate();
         
        } catch (SQLException ex) {
            Logger.getLogger(CrudJardinEnfantImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return update;
    }

    @Override
    public int delet(int id) {
        String query="DELETE FROM jardin WHERE id="+id;
        int rowDeleted=0;
        try {
            PreparedStatement statement= connexion.prepareStatement(query);
          rowDeleted=statement.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rowDeleted;
    }

    @Override
    public List<JardinEnfant> findAll() {
        List<JardinEnfant> allJardinEnfant = new ArrayList<>();
        String query="select * from jardin";
        try{
            Statement statement= connexion.createStatement();
            ResultSet resultSet= statement.executeQuery(query);
            while(resultSet.next()){
                allJardinEnfant.add(new JardinEnfant(
                        resultSet.getInt("id"),
                        resultSet.getString("Name"),
                        resultSet.getString("Description"),
                        resultSet.getString("numtel"),
                        resultSet.getString("Adresse"),
                        resultSet.getString("Etat"),
                        resultSet.getDouble("Tarif"))
                
                );
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CrudJardinEnfantImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allJardinEnfant;
    }

    
}
