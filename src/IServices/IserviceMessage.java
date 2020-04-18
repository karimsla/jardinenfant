package IServices;

import Entities.Jardin;
import Entities.Messages;
import Entities.Parents;

import java.sql.SQLException;
import java.util.List;

public interface IserviceMessage  {


    //get parent who contacted the jardin list id jardin as param
    public List<Messages> getallmess(int id) throws SQLException;


    //get the messages for a parent and jardin id parent and jardin as params
    public List<Messages> getmessages(int id,int idjardin) throws SQLException;

    //add a message i think it work for both
    public void addmess(String msg,int idparent,int idjardin, int idsender);

    //get jardin list for a parent id parent as param
    public  List<Jardin> minemess(int id) throws SQLException;
}

