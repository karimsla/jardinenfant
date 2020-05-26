package Views.Jardin;

import Entities.Paiement;
import IServices.CrudPayment;
import IServices.IserviceUser;
import IServices.PaiementCrud;
import IServices.ServiceUser;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class VerifyCodeController implements Initializable{
    @FXML
    private TextField code;

    @FXML
    private Button sbmit;

    @FXML
    private TextField idjardin;
    @FXML
    private TextField codeval;
    @FXML
    private Label lbltxt;
    int id;
    public int nbr;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
     /*   int x= crud.create(payment);
        if(x!=0){
            alert1.setTitle("success");
            alert1.setContentText("payement effectuer avec succees" );
            alert1.show();
        }
        else{
            alert.setTitle("error");
            alert.setContentText("payment failed" );
            alert.show();
        }*/


        sbmit.setOnAction(e->{
            System.out.println(codeval.getText());
            if(code.getText().equals(codeval.getText())){
                //add to database else reject it
                try {
                    SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    IserviceUser su=new ServiceUser();


                    //payment.setJardin(su.jardinid(authenticated.getId()));
                    Date date = new Date(System.currentTimeMillis());
                    Paiement payment=new Paiement();
                    PaiementCrud crud = new PaiementCrud();
                    payment.setDate(date);
                    int cb= crud.findclub();
                    int ev = crud.findevent();
                    float mont= cb*5+ev*6+100;

                    payment.setMontant(mont);
                    payment.setJardin(Integer.parseInt(idjardin.getText()));
                    int x= crud.create(payment);
                    lbltxt.setText("Paiement effectué avec succué");
                    lbltxt.setVisible(true);

                    // do what you have to do

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }else {
                lbltxt.setText("code incorrect réessayer");
                lbltxt.setVisible(true);

            }
        });

    }




    public void setLabelText(int id,String code){
        String a=String.valueOf(id);

        idjardin.setText(a);
        idjardin.setVisible(false);
        codeval.setText(code);
        codeval.setVisible(false);
        //testing
    }

}
