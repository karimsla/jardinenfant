/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.Activite;
import Entities.Enfant;
import Entities.PartActivite;
import Utils.ConnexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Dorra Kerrou
 */
public class PartActServices {

    public ObservableList<Enfant> data = FXCollections.observableArrayList();

    public static int ajouter(PartActivite a) {
        int ac = 0;
        try {

            Connection con = ConnexionBD.getInstance().getCnx();
            String res = "Insert into part_activite(enfant_id,activite_id,date) values (?,?,?)";
            PreparedStatement pre = con.prepareStatement(res);
            pre.setInt(1, a.getEnfant().getId());
            pre.setInt(2, a.getActivite().getId());
            pre.setString(3, a.getDate());

            ac = pre.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return ac;

    }

    public Enfant verifier(String nom) {

         Enfant e = new Enfant();
        try {

            Connection con = ConnexionBD.getInstance().getCnx();
            String res = "SELECT * FROM enfant  WHERE nom = '" + nom+"'";
           PreparedStatement stat = con.prepareStatement(res);
            
            ResultSet rs =  stat.executeQuery(res);

             while(rs.next()){
                e.setNom(rs.getString("nom"));}

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return e;

    }

}
