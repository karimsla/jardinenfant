package Views.Chauffeurs;


import Entities.MapModel;
import Entities.Trajet;
import IServices.IserviceUser;
import IServices.ServiceUser;
import IServices.TrajetService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jardin.enfant.JardinEnfant;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;


public class MapController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    WebView webView;
    String data;
    WebEngine webEngine;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        webEngine=webView.getEngine();
        data="this is working";
        final URL urlGoogleMaps = getClass().getResource("/Assets/index.html");

        webEngine.load(urlGoogleMaps.toExternalForm());

    }
    @FXML
    public void update() throws Exception
    {
       IserviceUser us=new ServiceUser(); 
        TrajetService ts=new TrajetService();
        List<Trajet> lt=ts.afficherTrajet(us.jardinid(JardinEnfant.authenticated.getId()));
        List<MapModel> mp=new ArrayList<MapModel>();
        Gson gson=new Gson();
        for(Trajet t : lt)
        { 
            String sURL = "https://api.mapbox.com/geocoding/v5/mapbox.places/"+t.getAdresse()+".json?access_token=pk.eyJ1Ijoic2FtaWtyIiwiYSI6ImNrOHRieWk3dDBuaTQzbGxvZDh2ZGJrZjgifQ.ZXvwJ489e09-HnnWfWpWtA&limit=1";
            String json = readUrl(sURL);
            JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
            JsonArray arr = jsonObject.getAsJsonArray("features");
            JsonArray center = arr.get(0).getAsJsonObject().get("center").getAsJsonArray();
            double lat=center.get(0).getAsDouble();
            double lan=center.get(1).getAsDouble();
            System.out.println(center.get(0).getAsString());
            MapModel m=new MapModel(lat,lan, t.getChauffeur().getNom(), t.getHeure());
            mp.add(m);
        }


        for(int i=0;i<mp.size();i++)
            webEngine.executeScript("setMarker('"+mp.get(i).getLat()+"','"+mp.get(i).getLan()+"','"+mp.get(i).getNom()+"','"+mp.get(i).getHeure()+"')");

    }

    private static String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);

            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
    }
}