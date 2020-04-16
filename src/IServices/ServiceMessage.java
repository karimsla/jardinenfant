package IServices;

import Entities.Jardin;
import Entities.Messages;
import Entities.Parents;
import Entities.User;
import Utils.ConnexionBD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServiceMessage implements IserviceMessage {

    Connection con = null;
    public ServiceMessage(){
        con = ConnexionBD.getInstance().getCnx();

    }

    public List<Messages> getallmess(int id) throws SQLException {
        CrudJardinEnfant sj=new CrudJardinEnfantImpl();
        IserviceParent sp=new ServiceParent();
        IserviceUser su=new ServiceUser();

        String req="SELECT * from messages as m LEFT JOIN parent as p on p.id=m.parent_id where m.date in(select MAX(l.date) from messages l Group by l.parent_id) AND  m.jardin_id="+id+"   ORDER BY m.date DESC";

        con = ConnexionBD.getInstance().getCnx();
        List<Messages> allmes=new ArrayList<Messages>() ;


        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(req);
            while(rs.next()){

                Messages m=new Messages(rs.getInt("id"),
                rs.getDate("date"),
                rs.getString("msg"),
                sj.findById(rs.getInt("jardin_id")),
                sp.findById(rs.getInt("parent_id")),
                su.findById(rs.getInt("user_id"))


                );

                allmes.add(m);
            }




        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allmes;


    }


    public List<Messages> getmessages(int id) throws SQLException {
        CrudJardinEnfant sj=new CrudJardinEnfantImpl();
        IserviceParent sp=new ServiceParent();
        IserviceUser su=new ServiceUser();
        String req="SELECT m.* from parent as p , messages as m where m.parent_id=p.id AND m.parent_id="+id;
        con = ConnexionBD.getInstance().getCnx();
        List<Messages> allmes=new ArrayList<Messages>() ;


        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(req);
            while(rs.next()){

                Messages m=new Messages(rs.getInt("id"),
                        rs.getDate("date"),
                        rs.getString("msg"),
                        sj.findById(rs.getInt("jardin_id")),
                        sp.findById(rs.getInt("parent_id")),
                        su.findById(rs.getInt("user_id"))


                );

                allmes.add(m);
            }




        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allmes;



    }


}
