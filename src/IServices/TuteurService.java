/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.Tuteur;
import Entities.TuteurJson;
import Utils.ConnexionBD;
import static jardin.enfant.JardinEnfant.authenticated;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import jardin.enfant.JardinEnfant;
import org.json.JSONException;

/**
 *
 * @author Sami
 */
public class TuteurService {
    private Connection cnx;
    public TuteurService()
    {
        cnx=ConnexionBD.getInstance().getCnx();
    }

    public boolean ajouterTuteur(Tuteur p)
    {
        try {

            String s=   add(p);
            if(s.equals("Success"))
                return true;
        }
        catch (Exception ex) {
            System.out.println(ex); }
        return false;
    }


    public List<Tuteur> afficherAll(){
        List<Tuteur> lp=new ArrayList<Tuteur>();
        String req="Select * from Tuteur";
        try {
            Statement statement=cnx.createStatement();
            ResultSet rs=statement.executeQuery(req);
            while (rs.next())
            {
                Tuteur t=new Tuteur(rs.getInt(1),rs.getString("nom"),rs.getString("prenom"),rs.getString("sexe"));
                lp.add(t);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TuteurService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lp;
    }



    public void supprimerTuteur(int id)
    {
        try {
            String res="Delete from Tuteur where id="+id;

            String res2="Delete from fos_user where id="+id;
            Statement statement=cnx.createStatement();
            statement.executeUpdate(res);
            statement=cnx.createStatement();
            statement.executeUpdate(res2);

            System.out.println("Suppression réussie!");
        } catch (SQLException ex) {
            Logger.getLogger(TuteurService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modifierTuteur(Tuteur p)
    {
        try {
            String res="Update Tuteur Set nom='"+p.getNom()+"',prenom='"+p.getPrenom()+"',sexe='"+p.getSexe()+"'"
                    + "where id="+p.getId();
            Statement statement=cnx.createStatement();
            statement.executeUpdate(res);
            System.out.println("Modification réussie!" );
        } catch (SQLException ex)
        {
            Logger.getLogger(TuteurService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public Tuteur find(int id)
    {
        String req="Select * from Tuteur where id="+id;
        Tuteur c=new Tuteur();
        try {
            Statement statement=cnx.createStatement();
            ResultSet rs=statement.executeQuery(req);

            rs.next();
            c=new Tuteur(rs.getInt(1),rs.getString("nom"),rs.getString("prenom"),rs.getString("sexe"));

        } catch (SQLException ex) {
            Logger.getLogger(TuteurService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;

    }




    private static String streamToString(InputStream inputStream) {
        String text = new Scanner(inputStream, "UTF-8").useDelimiter("\\Z").next();
        return text;
    }

    public static String readJsonFromUrl(String urlparm) throws IOException, JSONException {
        String json="";
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

    public String add(Tuteur t) throws IOException {
        Connection con =ConnexionBD.getInstance().getCnx();
        IserviceUser us=new ServiceUser();
        String json = readJsonFromUrl("http://127.0.0.1:8000/Api/addtutor/"+us.jardinid(JardinEnfant.authenticated.getId())+"/" + t.getEmail() +"/" + t.getUsername()+"/" + t.getUsername()+"/" + t.getNom()+"/" + t.getPrenom()+"/" + t.getSexe());

        if (json.compareTo("\"done\"") == 0) {
            System.out.println("done");

            return "Success";
        }
        return "Error";


    }
}
