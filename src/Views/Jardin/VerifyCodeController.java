package Views.Jardin;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
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
            if(code.getText().equals(codeval.getText())){
                //add to database else reject it
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
