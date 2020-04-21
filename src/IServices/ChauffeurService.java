/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.Chauffeur;
import Utils.ConnexionBD;
import jardin.enfant.JardinEnfant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sami
 */

public class ChauffeurService {

    private Connection cnx;
    public ChauffeurService()
    {
        cnx=ConnexionBD.getInstance().getCnx();
    }

    public void ajouterChauffeur(Chauffeur p)
    {IserviceUser us=new ServiceUser();

        try {
            String res="Insert into chauffeur (jardin_id,cin,nom,tel,sexe) values ("+us.jardinid(JardinEnfant.authenticated.getId())+",'"+p.getCin()+"','"+p.getNom()+"','"+p.getTel()+"','"+p.getSexe()+"')";
            Statement statement=cnx.createStatement();
            statement.executeUpdate(res);
            System.out.println("ajout réussie!");
        } catch (SQLException ex) {
            System.out.println(ex); }
    }


    public List<Chauffeur> afficherAll(){
        List<Chauffeur> lp=new ArrayList<Chauffeur>();
        String req="Select * from chauffeur";
        try {
            Statement statement=cnx.createStatement();
            ResultSet rs=statement.executeQuery(req);
            while (rs.next())
            {
                Chauffeur c=new Chauffeur(rs.getInt(1),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(2));
                lp.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ChauffeurService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lp;
    }



    public void supprimerChauffeur(int id)
    {
        try {
            String res="Delete from chauffeur where id="+id;
            Statement statement=cnx.createStatement();
            statement.executeUpdate(res);
            System.out.println("Suppression réussie!");
        } catch (SQLException ex) {
            Logger.getLogger(ChauffeurService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modifierChauffeur(Chauffeur p)
    {
        try {
            String res="Update chauffeur Set nom='"+p.getNom()+"',cin='"+p.getCin()+"',sexe='"+p.getSexe()+"',tel='"+p.getTel()+"'"
                    + "where id="+p.getId();
            Statement statement=cnx.createStatement();
            statement.executeUpdate(res);
            System.out.println("Modification réussie!" );
        } catch (SQLException ex)
        {
            Logger.getLogger(ChauffeurService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public Chauffeur find(int id)
    {
        String req="Select * from chauffeur where id="+id;
        Chauffeur c=new Chauffeur();
        try {
            Statement statement=cnx.createStatement();
            ResultSet rs=statement.executeQuery(req);

            rs.next();
            c=new Chauffeur(rs.getInt(1),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(2));

        } catch (SQLException ex) {
            Logger.getLogger(ChauffeurService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;

    }
}
