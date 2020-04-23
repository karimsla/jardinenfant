/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Trajets;

import Entities.Chauffeur;
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
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author Sami
 */
public class TrajetsController implements Initializable {

    private final ObservableList<Trajet> trajets = FXCollections.observableArrayList();

    @FXML
    WebView webview;
    String data;
    WebEngine webEngine;
    @FXML
    private TableView<Trajet> tv_trajets;
    @FXML
    private TableColumn<Trajet, String> adresse;
    @FXML
    private TableColumn<Trajet, String> heure;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        webEngine = webview.getEngine();
        data = "this is working";
        final URL urlGoogleMaps = getClass().getResource("/Assets/index.html");

        webEngine.load(urlGoogleMaps.toExternalForm());
        getTable();

        tv_trajets.setOnMouseClicked((event) -> {
            try {
                Trajet t = tv_trajets.getSelectionModel().getSelectedItem();

                point(t);
            } catch (Exception ex) {
                Logger.getLogger(TrajetsController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
    }

    @FXML
    public void update() throws Exception {
        IserviceUser us = new ServiceUser();
        TrajetService ts = new TrajetService();

        List<Trajet> lt = ts.afficherTrajetParent(JardinEnfant.authenticated.getId());
        List<MapModel> mp = new ArrayList<MapModel>();
        Gson gson = new Gson();
        for (Trajet t : lt) {
            String sURL = "https://api.mapbox.com/geocoding/v5/mapbox.places/"+t.getAdresse()+".json?access_token=pk.eyJ1Ijoic2FtaWtyIiwiYSI6ImNrOHRieWk3dDBuaTQzbGxvZDh2ZGJrZjgifQ.ZXvwJ489e09-HnnWfWpWtA&limit=1";
            String json = readUrl(sURL);
            JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
            JsonArray arr = jsonObject.getAsJsonArray("features");
            JsonArray center = arr.get(0).getAsJsonObject().get("center").getAsJsonArray();
            double lat=center.get(0).getAsDouble();
            double lan=center.get(1).getAsDouble();
            webEngine.executeScript("setMarker('" + lan + "','" + lat + "','" + t.getAdresse() + "','" +t.getHeure()  + "')");

        }


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

    public void getTable() {
        trajets.clear();
        TrajetService e = new TrajetService();
        List<Trajet> ls = new ArrayList<Trajet>();
        ls = e.afficherTrajetParent(JardinEnfant.authenticated.getId());
        trajets.addAll(ls);

        adresse.setCellValueFactory(new PropertyValueFactory<Trajet, String>("adresse"));
        heure.setCellValueFactory(new PropertyValueFactory<Trajet, String>("heure"));
        tv_trajets.setItems(trajets);
    }


    public void point(Trajet t) throws Exception {
        String sURL = "https://api.mapbox.com/geocoding/v5/mapbox.places/tunis,manouba.json?access_token=pk.eyJ1Ijoic2FtaWtyIiwiYSI6ImNrOHRieWk3dDBuaTQzbGxvZDh2ZGJrZjgifQ.ZXvwJ489e09-HnnWfWpWtA&limit=1";
        String json = readUrl(sURL);
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        JsonArray arr = jsonObject.getAsJsonArray("features");
        JsonArray center = arr.get(0).getAsJsonObject().get("center").getAsJsonArray();
        double lat = center.get(0).getAsDouble();
        double lan = center.get(1).getAsDouble();
        webEngine.executeScript("moveTo('" + lat + "','" + lan + "')");

    }
}
