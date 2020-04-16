package IServices;

import Utils.ConnexionBD;


import java.io.*;
import java.net.HttpURLConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
}
