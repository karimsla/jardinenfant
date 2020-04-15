/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import com.google.gson.*;
import com.google.gson.reflect.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Dorra Kerrou
 */
public class WeatherController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label temperature;
    @FXML
    private Label humidity;
    @FXML
    private Label speed;

    public static Map<String, Object> JsonToMap(String str) {

        Map<String, Object> map = new Gson().fromJson(
                str, new TypeToken<HashMap<String, Object>>() {
                }.getType());

        return map;

    }
    @FXML
    private Label Country;
    @FXML
    private Label main;

    private Image image;
    @FXML
    private ImageView imageview;
    @FXML
    private Label feels;
    @FXML
    private Label max;
    @FXML
    private Label min;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
        
        
       
        String API_Key = "b834cf5d3ea7686a84b07cde283ba7a0";
        String LOCATION = "Moscow";
        String urlString = "http://api.openweathermap.org/data/2.5/weather?q=" + LOCATION + "&appid=" + API_Key
                + "&units-Metric";
        try {
            StringBuilder result = new StringBuilder();
            URL url1 = new URL(urlString);
            URLConnection conn = url1.openConnection();
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();
            System.out.println(result);

            Map<String, Object> respMap = JsonToMap(result.toString());
            Map<String, Object> mainMap = JsonToMap(respMap.get("main").toString());
            Map<String, Object> windMap = JsonToMap(respMap.get("wind").toString());

            String t = String.valueOf(mainMap.get("temp"));
            temperature.setText(t);

            String h = String.valueOf(mainMap.get("humidity"));
            humidity.setText(h);

            String s = String.valueOf(windMap.get("speed"));
            speed.setText(s);

            String m = String.valueOf(mainMap.get("weather.description"));
            main.setText(m);
            
            String f = String.valueOf(mainMap.get("feels_like"));
            feels.setText(f);

            
            String ma = String.valueOf(mainMap.get("temp_max"));
            max.setText(ma);
            
            String mi = String.valueOf(mainMap.get("temp_min"));
            min.setText(mi);
            
            System.out.println("Current Temperature: " + mainMap.get("temp"));
            System.out.println("Current Humidity : " + mainMap.get("humidity"));
            System.out.println("Wind Speeds : " + windMap.get("speed"));
            System.out.println("main : " + respMap.get("weather"));
            
            
            System.out.println("feels like max  : " + mainMap.get("temp_max"));
            System.out.println("feels like  min: " + mainMap.get("temp_min"));
 

        } catch (MalformedURLException ex) {
            Logger.getLogger(WeatherController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(WeatherController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
