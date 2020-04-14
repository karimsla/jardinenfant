/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dorra Kerrou
 */
public class WebViewController implements Initializable {

    @FXML
    private WebView webView;
    @FXML
    private Button Culturel;
    @FXML
    private Button Bricolage;
    @FXML
    private Button Dessin;
    @FXML
    private Button Lecture;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         

        // Create the WebEngine
        final WebEngine web = webView.getEngine();
        String urlweb="http://google.com";

        // LOad the Start-Page
        web.load(urlweb);
       
    }    

    @FXML
    private void ct(ActionEvent event) {
        
          // Create the WebEngine
        final WebEngine web = webView.getEngine();
        String urlweb="https://facebook.com";

        // LOad the Start-Page
        web.load(urlweb);
    }

    @FXML
    private void br(ActionEvent event) {
          // Create the WebEngine
        final WebEngine web = webView.getEngine();
        String urlweb="https://youtube.com";

        // LOad the Start-Page
        web.load(urlweb);
    }

    @FXML
    private void ds(ActionEvent event) {
          // Create the WebEngine
        final WebEngine web = webView.getEngine();
        String urlweb="https://bershka.com";

        // LOad the Start-Page
        web.load(urlweb);
    }

    @FXML
    private void lec(ActionEvent event) {
          // Create the WebEngine
        final WebEngine web = webView.getEngine();
        String urlweb="https://www.netflix.com/";

        // LOad the Start-Page
        web.load(urlweb);
    }
    
}
