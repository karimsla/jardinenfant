package IServices;

import Entities.Parents;
import Entities.User;
import Utils.ConnexionBD;


import java.io.*;
import java.net.HttpURLConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;
import java.lang.*;
import org.json.JSONException;
import org.json.JSONObject;
import java.net.URL;

import static jardin.enfant.JardinEnfant.authenticated;


public class ServiceUser implements IserviceUser {

    Connection con = null;

    static InputStream is = null;
    static JSONObject jObj = null;
    static String json = "";

    public ServiceUser() {

        con = ConnexionBD.getInstance().getCnx();

    }




    private static String streamToString(InputStream inputStream) {
        String text = new Scanner(inputStream, "UTF-8").useDelimiter("\\Z").next();
        return text;
    }

    public static String readJsonFromUrl(String urlparm) throws IOException, JSONException {

        try {
            URL url = new URL(urlparm);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("charset", "utf-8");
            connection.connect();
            InputStream inStream = connection.getInputStream();

            json = streamToString(inStream); // input stream to string
        } catch (IOException ex) {
            ex.printStackTrace();
        }


            return json;

    }


    @Override
    public String Login(String username, String password) throws IOException {

        String json = readJsonFromUrl("http://localhost:8000/Api/login/" + username + "/" + password);

        if (json.compareTo("\"Success\"") == 0) {
            con = ConnexionBD.getInstance().getCnx();
            String req = "Select * from fos_user where username='" + username+"'";

            try {
                Statement statement = con.createStatement();
                ResultSet rs = statement.executeQuery(req);
                rs.next();

                authenticated.setId(rs.getInt("id"));
                authenticated.setEmail(rs.getString("email"));
                authenticated.setUsername(rs.getString("username"));
                authenticated.setRole(rs.getString("roles"));
                authenticated.setType(rs.getString("discr"));


            } catch (SQLException e) {
                e.printStackTrace();
            }

            return "Success";
        }


        return "Error";


    }

    @Override
    public int jardinid(int respid) {

        con = ConnexionBD.getInstance().getCnx();
        String req = "Select jardin_id from responsable where id=" + respid;
        int id=0;

        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(req);
            rs.next();

            id=rs.getInt(1);


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return id;
    }

       @Override
    public String nompar() {
        con = ConnexionBD.getInstance().getCnx();
        String req = "Select p.nom,p.Prenom from parent as p where id=" + authenticated.getId();
        int id=0;


        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(req);
            rs.next();

            return rs.getString("nom")+" "+rs.getString("prenom");


        } catch (SQLException e) {
            e.printStackTrace();
        }




        return null;
    }



    @Override
    public String nomresp() {
        con = ConnexionBD.getInstance().getCnx();
        String req = "Select Name from jardin where id="+jardinid(authenticated.getId());
        int id=0;


        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(req);
            rs.next();

            return rs.getString("Name");


        } catch (SQLException e) {
            e.printStackTrace();
        }




        return null;
    }



    @Override
    public User findById(int id) {
        con = ConnexionBD.getInstance().getCnx();
        User us=new User();
        String req = "Select * from fos_user where id='" + id+"'";

        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(req);
            rs.next();

            us.setId(rs.getInt("id"));
            us.setEmail(rs.getString("email"));
            us.setUsername(rs.getString("username"));
            us.setRole(rs.getString("roles"));
            us.setType(rs.getString("discr"));


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return us;
    }

    @Override
    public int create(User type) {
        return 0;
    }

    @Override
    public int update(User type) {
        return 0;
    }

    @Override
    public int delet(User type) {
        return 0;
    }

    @Override
    public List<User> findAll() {
        return null;
    }
}
