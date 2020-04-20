/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.Jardin;
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
public class CrudJardinEnfantImpl implements CrudJardinEnfant {
    
    Connection connexion =null; 

    public CrudJardinEnfantImpl() throws SQLException {
        connexion= ConnexionBD.getInstance().getCnx();
    }
    
 

    @Override
    public List<Jardin> findByNom(String nom) {
   List<Jardin> allJardin = new ArrayList<>();
       
        try{
         

                
                String req="SELECT * FROM jardin WHERE Name LIKE '%"+nom+"%'";
            Statement stm=connexion.createStatement();
            ResultSet rs=stm.executeQuery(req);
                
                



           while(rs.next()){
              Jardin j= new Jardin(
                      rs.getInt("id"),
                   rs.getString("Name"),
                   rs.getString("Description"),
                       rs.getString("numtel"),
                      (float) rs.getDouble("Tarif"),
                     rs.getString("Adresse"),

                      rs.getString("etat"));
  allJardin.add(j);
                
           }
          
         
            
        } 
        catch (SQLException ex) {
            Logger.getLogger(CrudJardinEnfantImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allJardin;
    }
        

    @Override
    public List<Jardin> findByNum(String numTel) {

        List<Jardin> allJardin = new ArrayList<>();
        String query = "select * from jardin where numtem LIKE '%" + numTel + "%'";

        try {
            Statement statement = connexion.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                 /*int id, String name, String description,
			String numtel, float tarif, String adresse, String etat*/
                allJardin.add(new Jardin(
                        rs.getInt("id"),
                        rs.getString("Name"),
                        rs.getString("Description"),
                        rs.getString("numtel"),
                        (float) rs.getDouble("Tarif"),
                        rs.getString("Adresse"),

                        rs.getString("etat")));

            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudJardinEnfantImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  allJardin;
    }


        @Override
    public List<Jardin> findByEtat(String etat) {

            List<Jardin> allJardin = new ArrayList<>();
        String query="select * from jardin where Etat LIKE'%"+etat+"%'";
        try{
            Statement statement= connexion.createStatement();
            ResultSet rs= statement.executeQuery(query);
            while(rs.next()){
                allJardin.add(new Jardin(
                        rs.getInt("id"),
                        rs.getString("Name"),
                        rs.getString("Description"),
                        rs.getString("numtel"),
                        (float) rs.getDouble("Tarif"),
                        rs.getString("Adresse"),

                        rs.getString("etat"))
                
                );
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CrudJardinEnfantImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allJardin;
    }
    @Override
    public List<Jardin> findByTarif(Double tarif) {
     List<Jardin> allJardin = new ArrayList<>();
        String query="select * from jardin where Tarif='"+tarif+"'";
        try{
            Statement statement= connexion.createStatement();
            ResultSet rs= statement.executeQuery(query);
            while(rs.next()){
                allJardin.add(new Jardin(
                        rs.getInt("id"),
                        rs.getString("Name"),
                        rs.getString("Description"),
                        rs.getString("numtel"),
                        (float) rs.getDouble("Tarif"),
                        rs.getString("Adresse"),

                        rs.getString("etat"))
                
                );
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CrudJardinEnfantImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allJardin;
    }

    @Override
    public List<Jardin> findByAdresse(String adresse) {
     List<Jardin> allJardin = new ArrayList<>();
        String query="select * from jardin where Adresse LIKE '%"+adresse+"%'";
        try{
            Statement statement= connexion.createStatement();
            ResultSet rs= statement.executeQuery(query);
            while(rs.next()){
                allJardin.add(new Jardin(
                        rs.getInt("id"),
                        rs.getString("Name"),
                        rs.getString("Description"),
                        rs.getString("numtel"),
                        (float) rs.getDouble("Tarif"),
                        rs.getString("Adresse"),

                        rs.getString("etat"))
                
                );
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CrudJardinEnfantImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allJardin;
    }

    @Override
    public Jardin findById(int id) {
 Jardin Jardin = null;
        String query="select * from jardin where id='"+id+"'";
        try{
            Statement statement= connexion.createStatement();
            ResultSet rs= statement.executeQuery(query);
            rs.next();
                Jardin=new Jardin(
                        rs.getInt("id"),
                        rs.getString("Name"),
                        rs.getString("Description"),
                        rs.getString("numtel"),
                        (float) rs.getDouble("Tarif"),
                        rs.getString("Adresse"),

                        rs.getString("etat"));
            } catch (SQLException ex) {
            Logger.getLogger(CrudJardinEnfantImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Jardin;
    }    

    @Override
    public int create(Jardin type) {
        String query = "INSERT INTO jardin"+
                "(Name,Description, numtel, Adresse, Etat,  Tarif)"+
                "VALUES(?,?,?,?,?,?)";
        int inserted =0;
        try{
             PreparedStatement statement= connexion.prepareStatement(query);
          statement.setString(1, type.getName());
          statement.setString(2, type.getDescription());
          statement.setString(3, type.getNumtel());
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
    public int update(Jardin type) {
        int update=0;
        String query="UPDATE jardin SET"+
                "Name=?"+
                "Description=?"+
                "numtel=?"+
                "Adresse=?"+
                "Etat=?"+
                "Tarif=?";
        try {
            
          PreparedStatement statement= connexion.prepareStatement(query);
          statement.setString(1, type.getName());
          statement.setString(2, type.getDescription());
          statement.setString(3, type.getNumtel());
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
    public int delet(Jardin jardin) {
        String query="DELETE FROM jardin WHERE id="+jardin.getId();
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
    public List<Jardin> findAll() {
        List<Jardin> allJardin = new ArrayList<>();
        String query="select * from jardin";
        try{
            Statement statement= connexion.createStatement();
            ResultSet rs= statement.executeQuery(query);
            while(rs.next()){
                allJardin.add(new Jardin(
                        rs.getInt("id"),
                        rs.getString("Name"),
                        rs.getString("Description"),
                        rs.getString("numtel"),
                        (float) rs.getDouble("Tarif"),
                        rs.getString("Adresse"),

                        rs.getString("etat"))
                
                );
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CrudJardinEnfantImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allJardin;
    }

    
}
