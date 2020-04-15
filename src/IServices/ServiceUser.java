package IServices;

import Entities.User;
import Utils.ConnexionBD;
import jardin.enfant.JardinEnfant;
import javafx.scene.paint.Color;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.*;




public class ServiceUser implements IserviceUser {

    Connection con =null;

    public ServiceUser(){

        con= ConnexionBD.getInstance().getCnx();

    }




    @Override
    public String Login(String username, String password) {
        String salt=getsalt(username);

        if(salt.equals("Error")){
            return "Error";
        }else {




        try{
            String sql = "SELECT * FROM fos_user Where (email ='"+username+"' or username='"+username+"')  and password ='"+encrypt(password,salt)+"'";
        Statement statement=con.createStatement();

        ResultSet rs=statement.executeQuery(sql);
        rs.next();
        User connected=new User(rs.getInt("id"),
                 rs.getString("username"),
                rs.getString("email"),
                rs.getString("discr"),
                rs.getString("roles"));

            JardinEnfant.authenticated=username;

            return "Success";





    } catch (SQLException ex) {
        Logger.getLogger(ServiceParent.class.getName()).log(Level.SEVERE, null, ex);
        return "Error";
        }

    }





    }


    public String encrypt(String password,String salt){
        String generatedPassword = null;
        try {





            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] hash = md.digest(password.getBytes("UTF-8"));

            md.update(salt.getBytes(StandardCharsets.UTF_8));
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < hash.length; i++) {
                sb.append(Integer.toString((hash[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();

        }
        catch (NoSuchAlgorithmException | UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        return generatedPassword;





    }



    public String getsalt(String username){


        String sql = "SELECT salt FROM fos_user Where (email ='"+username+"' or username='"+username+"') ";
        try{
            Statement statement=con.createStatement();

            ResultSet rs=statement.executeQuery(sql);
            rs.next();
            return rs.getString(1);





        } catch (SQLException ex) {
            Logger.getLogger(ServiceParent.class.getName()).log(Level.SEVERE, null, ex);
            return "Error";

        }
    }
}
