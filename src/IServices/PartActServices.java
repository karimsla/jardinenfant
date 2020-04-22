package IServices;

import Entities.Activite;
import Entities.PartActivite;
import Utils.ConnexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PartActServices {


    public static int  ajouter(PartActivite a){
        int ac = 0 ;
        try{

            Connection con = ConnexionBD.getInstance().getCnx();
            String res="Insert into part_activite(enfant_id,activite_id,date) values (?,?,?)";
            PreparedStatement pre = con.prepareStatement(res);
            pre.setInt(1,a.getEnfant().getId());
            pre.setInt(2,a.getActivite().getId());
            pre.setString(3,a.getDate());

            ac= pre.executeUpdate();


        }catch(SQLException ex){
            System.out.println(ex);
        }

        return ac ;




    }

}
