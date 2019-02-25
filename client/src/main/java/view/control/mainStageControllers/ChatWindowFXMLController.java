///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package view.control.mainStageControllers;
//
//import com.chat.common.StatusEnum;
//import view.control.HomeViewController;
//import com.chat.common.entities.Message;
//import com.chat.common.User;
//import view.view.MyHBox;
//import java.net.URL;
//import java.util.ResourceBundle;
//import java.util.UUID;
//import javafx.application.Platform;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.VBox;
//
///**
// *
// * @author FARES-LAP
// */
//public class ChatWindowFXMLController implements Initializable {
//
//    @FXML
//    private VBox msgBox;
//
//    @FXML
//    private TextField msgWriter;
//
//    @FXML
//    private Button sendButton;
//
//    private final HomeViewController controller;
//    private final User user;
//    private final UUID sessionID;
//
//    public ChatWindowFXMLController(HomeViewController controller, User user, UUID uuid) {
//
//        this.controller = controller;
//        this.user = user;
//        sessionID = uuid;
//    }
//
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//        sendButton.setOnAction((event) -> {
//            sendMessage();
//        });
//        msgBox.getChildren().add(new MyHBox());
//    }
//
//    public void displayMessage(Message message) {
//
//        Platform.runLater(() -> {
//            HBox hBox = new HBox();
//            User senderUser = message.getUser();
//            Label nameLabel = new Label(senderUser.getFirstName()+ " : ");
//            Label messageLabel = new Label(message.getMessage());
//            hBox.getChildren().add(nameLabel);
//            hBox.getChildren().add(messageLabel);
//            msgBox.getChildren().add(hBox);
//        });
//
//    }
//
//    public void sendMessage() {
//        Message message = new Message(user, msgWriter.getText());
//        controller.sendMessageToSession(sessionID, message);
//    }
//
////to notify this user's contacts of status change
//    public void notifyStatusChange(){
//        controller.notifyStatusChange(user);
//    }
//}


package view.control.mainStageControllers;

import view.control.HomeViewController;
import com.chat.common.entities.Message;
import com.chat.common.User;
import java.io.IOException;
import view.view.MyHBox;
import java.util.UUID;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author FARES-LAP
 */
public class ChatWindowFXMLController extends BorderPane {

    @FXML
    private VBox msgBox;

    @FXML
    private TextField msgWriter;

    @FXML
    private Button sendButton;

    private final HomeViewController controller;
    private final User user;
    private final UUID sessionID;

    public ChatWindowFXMLController(HomeViewController controller, User user, UUID uuid) {

        this.controller = controller;
        this.user = user;
        sessionID = uuid;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/mainStageFXMLs/ChatWindowFXML.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.setClassLoader(getClass().getClassLoader());
        try {
            loader.load();

        } catch (IOException ex) {
            System.out.println("Error in loading ChatWindowFXML.fxml");
            ex.printStackTrace();
        }
        sendButton.setOnAction((event) -> {
            sendMessage();
        });
        msgBox.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                sendMessage();
            }
        });
        msgBox.getChildren().add(new MyHBox());

    }

//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//
//    }
    public void displayMessage(Message message) {

        Platform.runLater(() -> {
            HBox hBox = new HBox();
            User senderUser = message.getUser();
            Label nameLabel = new Label(senderUser.getFirstName() + " : ");
            Label messageLabel = new Label(message.getMessage());
            hBox.getChildren().add(nameLabel);
            hBox.getChildren().add(messageLabel);
            msgBox.getChildren().add(hBox);
        });

    }

    public void sendMessage() {
        Message message = new Message(user, msgWriter.getText());
        controller.sendMessageToSession(sessionID, message);
    }

//to notify this user's contacts of status change
    public void notifyStatusChange() {
        controller.notifyStatusChange(user);
    }
}