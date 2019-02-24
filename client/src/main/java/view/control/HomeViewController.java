package view.control;

import com.chat.common.ClientInterface;
import com.chat.common.User;
import com.chat.common.entities.Message;
import controller.implementations.Controller;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import view.control.mainStageControllers.ChatWindowFXMLController;
import view.control.mainStageControllers.FriendListController;
import java.util.List;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HomeViewController implements Initializable {

    @FXML
    private HBox header;

    @FXML
    private Tab cntTab;/////contacts Tab

    @FXML
    private Tab ntfTab;/////notification tab

    @FXML
    private StackPane chatArea;

    Button updateBTN = new Button();

    /////////////////////////////Controllers/////////////////////
    /////////////////////////////Chiled/////////////////////////
    FriendListController friendListController;
    Map<UUID, ChatWindowFXMLController> sessions;
    ////////////////////////////Parent////////////////////////////
    Controller controller;
    //////////////////////////////////My user///////////////////////////
    User user;
    //////////////////////////////all opened sessions/////////////////////
    private Stage stage;

    public HomeViewController(Stage stage, Controller controller, User user) {
        this.stage = stage;
        this.controller = controller;
        this.sessions = new HashMap<>();
        this.user = user;
        controller.setHomeViewController(this);
        controller.registerUser(user);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Add friend List to Contacts List

        friendListController = new FriendListController(controller.getFriendList(user), this);
        FXMLLoader loader = new FXMLLoader();
        loader.setController(friendListController);
        Parent frindListBox = null;
        try {
            frindListBox = loader.load(getClass().getResource("/fxml/mainStageFXMLs/FriendListFXML.fxml").openStream());
            cntTab.setContent(frindListBox);
        } catch (IOException ex) {
            System.err.println("error in loading FriendListFXML.fxml");
            ex.printStackTrace();
        }

        updateBTN.setOnAction((event) -> {

            try {
                EditProfileController profileController = new EditProfileController(user);
                FXMLLoader updateloader = new FXMLLoader();
                updateloader.setController(profileController);
                Parent root = updateloader.load(getClass().getResource("/fxml/other/EditProfile.fxml").openStream());
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(HomeViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }

    public void openSessionResponse(UUID sessionID) {

        ChatWindowFXMLController chatWindowController = new ChatWindowFXMLController(this, user, sessionID);
        sessions.put(sessionID, chatWindowController);
        FXMLLoader loader = new FXMLLoader();
        loader.setController(chatWindowController);

        Parent chatWindow;
        try {
            chatWindow = loader.load(getClass().getResource("/fxml/mainStageFXMLs/ChatWindowFXML.fxml").openStream());
            Platform.runLater(() -> {
                chatArea.getChildren().clear();
                chatArea.getChildren().add(chatWindow);
            });
        } catch (IOException ex) {
            Logger.getLogger(FriendListController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void openSessionRequest(User friend) {
        openSessionResponse(controller.createSession(friend));
    }

    public void sendMessageToSession(UUID sessionID, Message message) {
        controller.sendMessageToSession(sessionID, message);
    }

    public void displayMessageOnSession(UUID id, Message message) {
        sessions.get(id).displayMessage(message);
    }

}
