
package IServices;

import Entities.Jardin;

import Entities.Paiement;
import Entities.Paiement;
import Entities.Responsable;
import Utils.ConnexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static jardin.enfant.JardinEnfant.authenticated;

/**
     *
     * @author ASUS
     */
    public class PaiementCrud implements CrudPayment {
        Connection connexion =null;
        int idj=0;

        public PaiementCrud() throws SQLException {
            connexion= ConnexionBD.getInstance().getCnx();
        }

        public int findclub()
        {
            try{

                String rese="SELECT jardin_id AS idj FROM  responsable AS ab WHERE ab.id="+authenticated.getId() ;

                Statement statement = connexion.createStatement();

                ResultSet rse =  statement.executeQuery(rese);
                while(rse.next()){
                     idj=(rse.getInt("idj"));

                }}
            catch(SQLException e){

            }

int clu=0;

            try{

                String nbr= "SELECT COUNT(*) as idj FROM club WHERE jardin_id="+idj;
                Statement statement = connexion.createStatement();
            ResultSet rse = statement.executeQuery(nbr);
            while(rse.next()){
                clu=(rse.getInt("idj"));

            }}
            catch(SQLException e){

    }
return clu;
        }


    public int findevent()
    {
        try{

            String rese="SELECT jardin_id AS idj FROM  responsable AS ab WHERE ab.id="+authenticated.getId() ;

            Statement statement = connexion.createStatement();

            ResultSet rse =  statement.executeQuery(rese);
            while(rse.next()){
                idj=(rse.getInt("idj"));

            }}
        catch(SQLException e){

        }

        int evnt=0;

        try{

            String nbr= "SELECT COUNT(*) as idj FROM evenement WHERE jardin_id="+idj;
            Statement statement = connexion.createStatement();
            ResultSet rse = statement.executeQuery(nbr);
            while(rse.next()){
                evnt=(rse.getInt("idj"));

            }}
        catch(SQLException e){

        }
        return evnt;
    }


        @Override
        public Paiement findById(int id) {
            Paiement Paiement = null;
            String query="SELECT * FROM paiement WHERE id='"+id+"'";
            try{
                Statement statement= connexion.createStatement();
                ResultSet resultSet= statement.executeQuery(query);
                resultSet.next();
                Paiement=new Paiement(
                        resultSet.getDate("date"),
                        (float)resultSet.getDouble("Montant"),
                        resultSet.getInt("jardin_id" )


                );
            } catch (SQLException ex) {
                Logger.getLogger(CrudJardinEnfantImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return Paiement;
        }



        @Override
        public int delet(Paiement type) {
            return 0;
        }


        @Override
        public int create(Paiement type) {
            String query = "INSERT INTO paiement"+
                    "(date, Montant,jardin_id)"+
                    "VALUES(?,?,?)";
            int inserted =0;
            try{
                PreparedStatement statement= connexion.prepareStatement(query);

                statement.setDate( 1,new Date(type.getDate().getTime()));
                statement.setDouble(2, type.getMontant());
                statement.setInt(3, type.getJardin());
                inserted=statement.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(CrudJardinEnfantImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return inserted;

        }

        @Override
        public int update(Paiement type) {
            int update=0;
            String query="UPDATE paiement SET"+

                    "date=?"+
                    "Montant=?"+
                    "jardin_id=?";
            try {

                PreparedStatement statement= connexion.prepareStatement(query);
                statement.setDate( 1,new Date(type.getDate().getTime()));

                statement.setInt(3, type.getJardin());
                statement.setDouble(2, type.getMontant());

                update=statement.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(PaiementCrud.class.getName()).log(Level.SEVERE, null, ex);
            }
            return update;
        }

        //@Override
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
        public List<Paiement> findAll() {
            List<Paiement> allPaiement = new ArrayList<>();
            String query="select p.*, j.Name from paiement as p,jardin as j where j.id=p.jardin_id ";
            try{
                Statement statement= connexion.createStatement();
                ResultSet resultSet= statement.executeQuery(query);
                while(resultSet.next()){
                    allPaiement.add(new Paiement(
                            resultSet.getDate("date"),
                            (float) resultSet.getDouble("Montant"),
                            resultSet.getString("Name" )

                    ));
                }

            } catch (SQLException ex) {
                Logger.getLogger(CrudJardinEnfantImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return allPaiement;
        }


        @Override
        public Collection<Paiement> findByMontant(Double montant) {

            List<Paiement> allPaiement = new ArrayList<>();
            String query="select * from paiement where Montant="+montant;
            try{
                Statement statement= connexion.createStatement();
                ResultSet resultSet= statement.executeQuery(query);
                while(resultSet.next()){
                    allPaiement.add(new Paiement(
                            resultSet.getDate("date"),
                            (float) resultSet.getDouble("Montant"),
                            resultSet.getInt("jardin_id" )

                    ));
                }

            } catch (SQLException ex) {
                Logger.getLogger(CrudJardinEnfantImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return allPaiement;
        }
    }

