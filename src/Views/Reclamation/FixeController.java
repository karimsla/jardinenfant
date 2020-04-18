package Views.Reclamation;

import Entities.Reclamation;
import IServices.IserviceReclamation;
import IServices.ServiceReclamation;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.mail.*;
import javax.mail.internet.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

public class FixeController implements Initializable  {
    @FXML
    private AnchorPane root;
    @FXML
    private TextField msg;
    @FXML
    private Button btn_fermer;
    @FXML
    private Button btn_envoyer;
    @FXML
    private TextField lbltxt;





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       btn_envoyer.setOnAction(e->{
           try {
               sendmail();
           } catch (SQLException ex) {
               ex.printStackTrace();
           }
       });
      btn_fermer.setOnAction(eve->{
          Stage stage = (Stage) btn_fermer.getScene().getWindow();
          // do what you have to do
          stage.close();
      });


    }


    public void setLabelText(int id){
        String a=String.valueOf(id);

        lbltxt.setText(a);
        lbltxt.setVisible(false);
        //testing
    }

    public void sendmail() throws SQLException {
       IserviceReclamation r= new ServiceReclamation();

       Reclamation rec=r.findbyId(Integer.parseInt(lbltxt.getText()));
       String dest=rec.getMail();

        final String username = "trizouni1@gmail.com";
        final String password = "tmdpbiphihxcgyqy";

        //temporary password


        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("JardinEnfant@esprit.tn"));
            message.setRecipient(Message.RecipientType.TO,new InternetAddress(dest));
            message.setSubject("Réponse de votre réclamation sur la plateforme KinderGaren Guide");
            message.setText(msg.getText());

            Transport.send(message);

            r.regelrReclamation(rec);
            System.out.println("Done");
            Stage stage = (Stage) btn_fermer.getScene().getWindow();
            // do what you have to do
            stage.close();

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}



