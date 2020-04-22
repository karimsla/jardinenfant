/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.Abonnement;
import Entities.AdmEnf;
import Entities.Enfant;
import Entities.Jardin;
import Utils.ConnexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author FERID
 */
public class EnfantService {


    public void ajouterEnfant(int pid,String nom,String prenom,String date,String sexe)
    {


        try {
            Connection cnx = ConnexionBD.getInstance().getCnx();
            String res="Insert into enfant (parent_id,nom,prenom,datenaiss,sexe) values ('"+pid+"','"+nom+"','"+prenom+"','"+date+"','"+sexe+"')";
            Statement statement=cnx.createStatement();
            statement.executeUpdate(res);
            System.out.println("ajout réussie!");
        } catch (SQLException ex) {
            System.out.println(ex); }
    }



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


    public List<AdmEnf> findall(){
        List<AdmEnf> data=new ArrayList<AdmEnf>() ;

     try{
        Connection con = (Connection) ConnexionBD.getInstance().getCnx();
        String res="SELECT en.nom,en.prenom,en.datenaiss,en.sexe,en.id,ab.nom,ab.prenom FROM enfant en,parent AS ab WHERE en.parent_id=ab.id " ;

        Statement statement = con.createStatement();
        //
        ResultSet rs =  statement.executeQuery(res);
        while(rs.next()){
            AdmEnf p = new AdmEnf();
            p.setNom(rs.getString("nom"));
            p.setPrenom(rs.getString("prenom"));
            p.setDate(rs.getDate("datenaiss"));
            p.setSexe(rs.getString("sexe"));
            p.setJardin(rs.getString("ab.nom")+" "+rs.getString("ab.prenom"));
            p.setId(rs.getInt("id"));




            data.add(p);
        }
    } catch (SQLException ex) {

    }
     return data;
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

    public void ajouterAbonnement(int jid,int jenf,String date,String type,String etat,String montant)
    {


        try {
            Connection cnx = ConnexionBD.getInstance().getCnx();
            String res="Insert into abonnement (jardin_id,enfant_id,date,type,etat,montant) values ('"+jid+"','"+jenf+"','"+date+"','"+type+"','"+etat+"','"+montant+"')";
            Statement statement=cnx.createStatement();
            statement.executeUpdate(res);

        } catch (SQLException ex) {
            System.out.println(ex); }
    }
    public static int modifierParent(int pid,String nom,String prenom,String date,String sexe){
        int ac=0;
        try{
            Connection con = ConnexionBD.getInstance().getCnx();
            String res="Update enfant SET nom=?,prenom=?,datenaiss=?,sexe=?  WHERE id="+pid;
            PreparedStatement pre = con.prepareStatement(res);
            pre.setString(1,nom);
            pre.setString(2,prenom);
            pre.setString(3,date);
            pre.setString(4,sexe);

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
