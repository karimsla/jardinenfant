package IServices;

import Entities.Jardin;
import Entities.Messages;
import Entities.Parents;
import Entities.User;
import Utils.ConnexionBD;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

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


    public List<Messages> getmessages(int id,int idjardin) throws SQLException {
        CrudJardinEnfant sj=new CrudJardinEnfantImpl();
        IserviceParent sp=new ServiceParent();
        IserviceUser su=new ServiceUser();
        String req="SELECT m.* from  messages as m where m.parent_id="+id+" AND m.jardin_id="+idjardin;
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

    @Override
    public void addmess(String msg, int idparent, int idjardin, int idsender) {

        try{
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            String sqldate=dtf.format(now);

            Connection con = ConnexionBD.getInstance().getCnx();
            String res="Insert into messages(date,msg,jardin_id,parent_id,user_id) values ('"+
                    sqldate+"','"+
                    msg+"',"+
                    idjardin+","+
                    idparent+","+
                    idsender+")";
            PreparedStatement pre = con.prepareStatement(res);


             pre.executeUpdate();


        }catch(SQLException ex){
            System.out.println(ex);
        }


    }

    @Override
    public List<Jardin> minemess(int id) throws SQLException {
        String req="SELECT j.* from jardin as j LEFT JOIN abonnement as a on a.jardin_id=j.id LEFT JOIN enfant as e on e.id=a.enfant_id where e.parent_id="+id;
        CrudJardinEnfant sj=new CrudJardinEnfantImpl();
        IserviceParent sp=new ServiceParent();
        IserviceUser su=new ServiceUser();

        con = ConnexionBD.getInstance().getCnx();
        List<Jardin> alljar=new ArrayList<Jardin>() ;


        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(req);
            while(rs.next()){

                Jardin m=new Jardin(rs.getInt("id"),
                        rs.getString("Name"),
                        rs.getString("Description"),
                        rs.getString("numtel"),
                        (float)rs.getDouble("tarif"),
                        rs.getString("Adresse"),
                        rs.getString("Etat") );

                alljar.add(m);
            }




        } catch (SQLException e) {
            e.printStackTrace();
        }

        return alljar;
    }


}
