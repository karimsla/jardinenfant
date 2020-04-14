/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.JardinEnfant;
import Entities.Payment;
import Utils.ConnexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class CrudPaymentImpl implements CrudGeneric<Payment>{
      Connection connexion =null; 

    public CrudPaymentImpl() throws SQLException {
        connexion=ConnexionBD.getInstance().getCnx();
    }
    
 
   
    @Override
    public Payment findById(int id) {
 Payment payment = null;
        String query="SELECT * FROM paiement WHERE id='"+id+"'";
        try{
            Statement statement= connexion.createStatement();
            ResultSet resultSet= statement.executeQuery(query);
            resultSet.next();
                payment=new Payment(
                        resultSet.getDate("date"),
                        resultSet.getDouble("Montant"),
                        resultSet.getInt("jardin_id" )
                
                
                );
            } catch (SQLException ex) {
            Logger.getLogger(CrudJardinEnfantImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return payment;
    }    

         

             
    @Override
    public int create(Payment type) {
        String query = "INSERT INTO paiement"+
                "(date, Montant)"+
                "VALUES(?,?)";
        int inserted =0;
        try{
             PreparedStatement statement= connexion.prepareStatement(query);
          
          statement.setDate( 1,new java.sql.Date(type.getDate().getTime()));
          statement.setDouble(2, type.getMontant());
          //statement.setInt(3, type.getProv());
          inserted=statement.executeUpdate();
         
        } catch (SQLException ex) {
            Logger.getLogger(CrudJardinEnfantImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inserted;
   
        }

    @Override
    public int update(Payment type) {
               int update=0;
        String query="UPDATE paiement SET"+
                
                "date=?"+
                "Montant=?"+
                "jardin_id=?";
        try {
            
          PreparedStatement statement= connexion.prepareStatement(query);
                    statement.setDate( 1,new java.sql.Date(type.getDate().getTime()));

          statement.setInt(3, type.getJardinEnfants().getId());
          statement.setDouble(2, type.getMontant());
          
          update=statement.executeUpdate();
         
        } catch (SQLException ex) { 
              Logger.getLogger(CrudPaymentImpl.class.getName()).log(Level.SEVERE, null, ex);
          } 
        return update;
    }

    @Override
    public int delet(int id) {
        String query="DELETE  from paiement WHERE id='"+id+"'";
        int rowDeleted=0;
        try {
            PreparedStatement statement= connexion.prepareStatement(query);
          rowDeleted=statement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(CrudJardinEnfantImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowDeleted;
    }

    @Override
    public List<Payment> findAll() {
        List<Payment> allpayment = new ArrayList<>();
        String query="select * from jardin";
        try{
            Statement statement= connexion.createStatement();
            ResultSet resultSet= statement.executeQuery(query);
            while(resultSet.next()){
                allpayment.add(new Payment(
                        resultSet.getDate("date"),
                        resultSet.getDouble("Montant"),
                        resultSet.getInt("jardin_id" )
                
                ));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CrudJardinEnfantImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allpayment;
    }
    public  List<JardinEnfant> getJardinById(int b) throws SQLException{
        List<Payment> allpayment = new ArrayList<>();
        List<JardinEnfant> allJardin = new ArrayList<>();
        allpayment=findAll();
        CrudJardinEnfantImpl jes = new CrudJardinEnfantImpl();
        for(int i =0 ; i<allpayment.size();i++){
            JardinEnfant j=null;
        j= jes.findById(allpayment.get(i).getJardinEnfants().getId());
        allJardin.add(j);
        }
        return allJardin;
    }
    
}
