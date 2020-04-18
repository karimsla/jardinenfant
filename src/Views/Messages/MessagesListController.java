package Views.Messages;

import Entities.Messages;
import Entities.User;
import IServices.IserviceMessage;
import IServices.IserviceUser;
import IServices.ServiceMessage;
import IServices.ServiceUser;
import com.jfoenix.controls.JFXDrawer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static jardin.enfant.JardinEnfant.authenticated;

public class MessagesListController implements Initializable {


        private double xOffset;
        private double yOffset;

        @FXML
        private AnchorPane rootPane;
        @FXML
        private AnchorPane titleBar;

        @FXML
        private TextArea idparent;
        @FXML
        private AnchorPane detailPane;

        @FXML
        private AnchorPane chatPane;

        @FXML
        private TextArea txtMsg;

        @FXML
        private VBox chatBox;

        @FXML
        private Button btnSend;

        @FXML
        private ScrollPane scrollPane;

        @FXML
        private TextFlow emojiList;

        @FXML
        private Button btnEmoji;
        @FXML
        private JFXDrawer drawerPane;

        @FXML
        private ScrollPane clientListScroll;

        @FXML
        private VBox clientListBox;
        @FXML
        private Button btnClose;


    IserviceMessage sm=new ServiceMessage();
    IserviceUser su=new ServiceUser();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        scrollPane.vvalueProperty().bind(chatBox.heightProperty());
      /*  try {
            chatObserver=new ChatObserverImpl(this);
            controller= ServerConnector.getServerConnector().getController();
            controller.addChatObserver(chatObserver);
//            controller.updateClientList();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }*/

        System.out.println();
        titleBar.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        titleBar.setOnMouseDragged(event -> {
            titleBar.getScene().getWindow().setX(event.getScreenX() - xOffset);
            titleBar.getScene().getWindow().setY(event.getScreenY() - yOffset);
        });

        try {
            List<Messages>allmess=sm.getallmess(su.jardinid(authenticated.getId()));
         /*   for(Messages m: allmess){
                update(m.getSender().getUsername(),m.getMsg());
            }*/
            updateUI(allmess);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }



    public void sendAction() throws SQLException {

        String msg=txtMsg.getText();

        String id=idparent.getText();

        sm.addmess(msg,Integer.parseInt(id),su.jardinid(authenticated.getId()),authenticated.getId());

        txtMsg.clear();
        update(Integer.parseInt(id));


    }

    public void closeAction(){

    }


    public boolean updateUI(List<Messages> messageList)  {
        Platform.runLater(() -> clientListBox.getChildren().clear());
        for(Messages mess : messageList){
           // if(mess.get.equals(authenticated.getUsername())) continue;
//            containerPane.getStyleClass().add("online-user-container");
            HBox container=new HBox() ;
            container.setAlignment(Pos.CENTER_LEFT);
            container.setSpacing(10);
            container.setPrefWidth(clientListBox.getPrefWidth());
            container.setPadding(new Insets(5));
            container.getStyleClass().add("online-user-container");
            Circle img =new Circle(50,50,30);

            try{
                String path= new File("src/Assets/Alexandra-Daddario-color.jpg").toURI().toString();
                img.setFill(new ImagePattern(new Image(path)));

            }catch (Exception ex){
                String path= new File("src/Assets/Alexandra-Daddario-color.jpg").toURI().toString();
                img.setFill(new ImagePattern(new Image(path)));
            }
            container.getChildren().add(img);

            VBox userDetailContainer=new VBox();
            userDetailContainer.setPrefWidth(clientListBox.getPrefWidth()/1.7);
            Label lblUsername=new Label(mess.getParent().getNom()+" "+mess.getParent().getPrenom());
            lblUsername.getStyleClass().add("online-label");
            userDetailContainer.getChildren().add(lblUsername);
            User us=null;
            try {
              //  us=controller.get(client);
                Label lblName=new Label(mess.getMsg());
                lblName.getStyleClass().add("online-label-details");
                userDetailContainer.getChildren().add(lblName);

            }catch (NullPointerException ex){
                System.out.println("user is null");
            }
            container.getChildren().add(userDetailContainer);

            Hyperlink settings = new Hyperlink("...");
            settings.getStyleClass().add("online-settings");
            settings.setTextAlignment(TextAlignment.CENTER);
            settings.setOnAction(e->{
                try {
                    update(mess.getParent().getId());
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            });
            container.getChildren().add(settings);
            System.out.println(container.getChildren().size());
            Platform.runLater(() -> clientListBox.getChildren().add(container));

        }
        return true;
    }

    public boolean update(int id) throws SQLException {
        List<Messages> mmesslist=sm.getmessages(id);

        chatBox.getChildren().clear();
        for(Messages m :mmesslist) {


            Text text = new Text(m.getMsg());

            text.setFill(Color.WHITE);
            text.getStyleClass().add("message");
            TextFlow tempFlow = new TextFlow();
            if (!authenticated.getUsername().equals(m.getSender().getUsername())) {
                Text txtName = new Text(m.getParent().getNom() +" "+m.getParent().getPrenom()+ "\n");
                txtName.getStyleClass().add("txtName");
                tempFlow.getChildren().add(txtName);
            }

            tempFlow.getChildren().add(text);
            tempFlow.setMaxWidth(200);

            TextFlow flow = new TextFlow(tempFlow);

            HBox hbox = new HBox(12);

            Circle img = new Circle(32, 32, 16);
            try {
                //System.out.println(username);
                String path = new File("src/Assets/Alexandra-Daddario-color.jpg").toURI().toString();
                img.setFill(new ImagePattern(new Image(path)));
            } catch (Exception ex) {
                String path = new File("src/Assets/Alexandra-Daddario-color.jpg").toURI().toString();
                img.setFill(new ImagePattern(new Image(path)));
            }

            img.getStyleClass().add("imageView");

            if (!authenticated.getUsername().equals(m.getSender().getUsername())) {

                tempFlow.getStyleClass().add("tempFlowFlipped");
                flow.getStyleClass().add("textFlowFlipped");
                chatBox.setAlignment(Pos.TOP_LEFT);
                hbox.setAlignment(Pos.CENTER_LEFT);
                hbox.getChildren().add(img);
                hbox.getChildren().add(flow);

            } else {
                text.setFill(Color.WHITE);
                tempFlow.getStyleClass().add("tempFlow");
                flow.getStyleClass().add("textFlow");
                hbox.setAlignment(Pos.BOTTOM_RIGHT);
                hbox.getChildren().add(flow);
                hbox.getChildren().add(img);
            }
            hbox.getStyleClass().add("hbox");
            Platform.runLater(() -> chatBox.getChildren().addAll(hbox));
        }

            idparent.setText(String.valueOf(id));


        return true;

    }


}
