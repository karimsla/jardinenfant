package IServices;

import Entities.Activite;
import Entities.Enfant;
import Entities.PartActivite;
import Utils.ConnexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PartActServices {



    public boolean verifier(int  id,int actid) {

        Enfant e = new Enfant();
        try {

            Connection con = ConnexionBD.getInstance().getCnx();
            String res = "SELECT * FROM part_activite  WHERE enfant_id =" + id+" AND activite_id="+actid;
            PreparedStatement stat = con.prepareStatement(res);

            ResultSet rs =  stat.executeQuery(res);

            return !rs.isBeforeFirst();

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return false;

    }




    public static int  ajouter(PartActivite a){
        int ac = 0 ;
        try{

            Connection con = ConnexionBD.getInstance().getCnx();
            String res="Insert into part_activite(enfant_id,activite_id,date) values (?,?,?)";
            PreparedStatement pre = con.prepareStatement(res);
            pre.setInt(1,a.getE().getId());
            pre.setInt(2,a.getActivite().getId());
            pre.setString(3,a.getDate());

            ac= pre.executeUpdate();


        }catch(SQLException ex){
            System.out.println(ex);
        }

        return ac ;




    }

}
