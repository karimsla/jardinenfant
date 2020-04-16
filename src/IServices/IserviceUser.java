package IServices;

import Entities.User;

import java.io.IOException;

public interface IserviceUser extends CrudGeneric<User> {

    public String Login(String username,String password) throws IOException;
    int jardinid(int respid);
}
