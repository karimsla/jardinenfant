package IServices;

import Entities.Messages;
import Entities.Parents;

import java.sql.SQLException;
import java.util.List;

public interface IserviceMessage  {

    public List<Messages> getallmess(int id) throws SQLException;
    public List<Messages> getmessages(int id) throws SQLException;
}

