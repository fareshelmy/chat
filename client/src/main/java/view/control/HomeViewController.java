//package view.control;
//
//import sessionJAXB.ChatSessionType;
//import sessionJAXB.MessageType;
//import sessionJAXB.ObjectFactory;
//import com.chat.common.ClientInterface;
//import com.chat.common.StatusEnum;
//import com.chat.common.User;
//import com.chat.common.entities.Message;
//import controller.implementations.Controller;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.math.BigInteger;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.ResourceBundle;
//import java.util.UUID;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javafx.application.Platform;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//import javafx.scene.Parent;
//import javafx.scene.control.Tab;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.StackPane;
//import view.control.mainStageControllers.ChatWindowFXMLController;
//import view.control.mainStageControllers.FriendListController;
//import java.util.List;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.stage.Stage;
//import javax.xml.bind.JAXBContext;
//import javax.xml.bind.JAXBElement;
//import javax.xml.bind.JAXBException;
//import javax.xml.bind.Marshaller;
//import javax.xml.bind.Unmarshaller;
//import javax.xml.datatype.DatatypeConfigurationException;
//import javax.xml.datatype.DatatypeConstants;
//import javax.xml.datatype.DatatypeFactory;
//import javax.xml.datatype.XMLGregorianCalendar;
//
//public class HomeViewController implements Initializable {
//
//    @FXML
//    private HBox header;
//
//    @FXML
//    private Tab cntTab;/////contacts Tab
//
//    @FXML
//    private Tab ntfTab;/////notification tab
//
//    @FXML
//    private StackPane chatArea;
//
//    Button updateBTN = new Button();
//
//    /////////////////////////////Controllers/////////////////////
//    /////////////////////////////Chiled/////////////////////////
//    FriendListController friendListController;
//    Map<UUID, ChatWindowFXMLController> sessions;
//    ////////////////////////////Parent////////////////////////////
//    Controller controller;
//    //////////////////////////////////My user///////////////////////////
//    User user;
//    //////////////////////////////all opened sessions/////////////////////
//    private Stage stage;
//    List<MessageType> messagesList;
//
//    public HomeViewController(Stage stage, Controller controller, User user) {
//        this.stage = stage;
//        this.controller = controller;
//        this.sessions = new HashMap<>();
//        this.user = user;
//        controller.setHomeViewController(this);
//        controller.registerUser(user);
//        messagesList = new ArrayList<>();
//
//    }
//
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        // Add friend List to Contacts List
//
//        /////////////////////code regarding XMLAPI//////////////////////////////
////        MessageType message = new MessageType();
////        message.setSender("Fares");
////        message.setBackgroundcolor("red");
////        message.setBody("Haiii");
////        DatatypeFactory datatypeFactory = null;
////        try {
////            datatypeFactory = DatatypeFactory.newInstance();
////        } catch (DatatypeConfigurationException ex) {
////            Logger.getLogger(HomeViewController.class.getName()).log(Level.SEVERE, null, ex);
////        }
////        XMLGregorianCalendar date = datatypeFactory.newXMLGregorianCalendar(2019, 02, 12, 13, 34,
////                DatatypeConstants.FIELD_UNDEFINED, DatatypeConstants.FIELD_UNDEFINED, DatatypeConstants.FIELD_UNDEFINED);
////        message.setDate(date);
////        message.setBold(true);
////        message.setItalic(false);
////        message.setUnderline(true);
////        message.setFontcolor("black");
////        message.setFontstyle("serif");
////        message.setTimestamp(date);
////        message.setFontsize(BigInteger.valueOf(13));
////        messagesList.add(message);
////        saveChatSession(messagesList);
//        /////////////////////code regarding XMLAPI//////////////////////////////
//        
//        ///////////////////// code regarding accounts JAXB ////////////////////////
//        
//        stage.setOnCloseRequest((event) -> {
//            controller.saveAccount(user);
//            System.exit(0);
//        });
//        ///////////////////// code regarding accounts JAXB ////////////////////////
//
//        friendListController = new FriendListController(controller.getFriendList(user), this);
//        FXMLLoader loader = new FXMLLoader();
//        loader.setController(friendListController);
//        Parent frindListBox = null;
//        try {
//            frindListBox = loader.load(getClass().getResource("/fxml/mainStageFXMLs/FriendListFXML.fxml").openStream());
//            cntTab.setContent(frindListBox);
//        } catch (IOException ex) {
//            System.err.println("error in loading FriendListFXML.fxml");
//            ex.printStackTrace();
//        }
//
//        updateBTN.setOnAction((event) -> {
//
//            try {
//                EditProfileController profileController = new EditProfileController(user, controller);
//                FXMLLoader updateloader = new FXMLLoader();
//                updateloader.setController(profileController);
//                Parent root = updateloader.load(getClass().getResource("/fxml/other/EditProfile.fxml").openStream());
//                Scene scene = new Scene(root);
//                stage.setScene(scene);
//                stage.show();
//            } catch (IOException ex) {
//                Logger.getLogger(HomeViewController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        });
//
//    }
//
//    public void openSessionResponse(UUID sessionID) {
//
//        ChatWindowFXMLController chatWindowController = new ChatWindowFXMLController(this, user, sessionID);
//        sessions.put(sessionID, chatWindowController);
//        FXMLLoader loader = new FXMLLoader();
//        loader.setController(chatWindowController);
//
//        Parent chatWindow;
//        try {
//            chatWindow = loader.load(getClass().getResource("/fxml/mainStageFXMLs/ChatWindowFXML.fxml").openStream());
//            Platform.runLater(() -> {
//                chatArea.getChildren().clear();
//                chatArea.getChildren().add(chatWindow);
//            });
//        } catch (IOException ex) {
//            Logger.getLogger(FriendListController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
//
//    public void openSessionRequest(User friend) {
//        openSessionResponse(controller.createSession(friend));
//    }
//
//    public void sendMessageToSession(UUID sessionID, Message message) {
//        controller.sendMessageToSession(sessionID, message);
//    }
//
//    public void displayMessageOnSession(UUID id, Message message) {
//        sessions.get(id).displayMessage(message);
//    }
//
//    public void saveChatSession(List<MessageType> messagesList) {
//        controller.saveChatSession(messagesList);
//    }
//
//    public void loadChatSession() {
//        messagesList.clear();
//        messagesList = controller.loadChatSession();
//    }
//
//    public void notifyStatusChange(User user) {
//        controller.notifyStatusChange(user);
//    }
//
//    //to be replaced with a notification
//    public void receiveStatusChange(User user) {
//        System.out.println(user.getStatusEnum());
//    }
//}
package view.control;

import com.chat.common.RegisteredByEnum;
import com.chat.common.StatusEnum;
import com.chat.common.User;
import com.chat.common.entities.Message;
import com.github.plushaze.traynotification.notification.Notification;
import com.github.plushaze.traynotification.notification.Notifications;
import com.github.plushaze.traynotification.notification.TrayNotification;
import controller.implementations.Controller;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import sessionJAXB.ChatSessionType;
import sessionJAXB.MessageType;
import sessionJAXB.ObjectFactory;
import view.control.mainStageControllers.HeaderController;

public class HomeViewController implements Initializable {

    @FXML
    private AnchorPane myRoot;

    @FXML
    private AnchorPane header;

    @FXML
    private TabPane myTabPane;

    @FXML
    private Tab cntTab;

    @FXML
    private Tab ntfTab;

    @FXML
    private StackPane chatArea;

    Button updateBTN = new Button();

    /////////////////////////////Controllers/////////////////////
    /////////////////////////////Chiled/////////////////////////
    FriendListController friendListController;
    HeaderController headerController;
    Map<UUID, ChatWindowFXMLController> sessions;
    ////////////////////////////Parent////////////////////////////
    Controller controller;
    //////////////////////////////////My user///////////////////////////
    User user;
    //////////////////////////////all opened sessions/////////////////////
    private Stage stage;
    List<MessageType> messagesList;
    private boolean chatBotEnabled;
    private boolean flag;

    public HomeViewController(Stage stage, Controller controller, User user) {
        this.stage = stage;
        this.controller = controller;
        this.sessions = new HashMap<>();
        this.user = user;
        controller.setHomeViewController(this);
        controller.registerUser(user);
        messagesList = new ArrayList<>();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String title = "Welcome " + user.getFirstName() + " " + user.getLastName() + " !";
        Notification notification = Notifications.SUCCESS;

        TrayNotification notifyMessage = new TrayNotification(title, "Have a nice day ^_^", notification);
        notifyMessage.showAndDismiss(Duration.seconds(5));

        AudioClip plonkSound = new AudioClip(getClass().getResource("/sounds/msg.mp3").toString());
        plonkSound.play();
        // Add friend List to Contacts List

        /////////////////////code regarding XMLAPI//////////////////////////////
        MessageType message = new MessageType();
        message.setSender("Fares");
        message.setBackgroundcolor("red");
        message.setBody("Hello");
        DatatypeFactory datatypeFactory = null;
        try {
            datatypeFactory = DatatypeFactory.newInstance();
        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(HomeViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        XMLGregorianCalendar date = datatypeFactory.newXMLGregorianCalendar(2019, 02, 12, 13, 34,
                DatatypeConstants.FIELD_UNDEFINED, DatatypeConstants.FIELD_UNDEFINED, DatatypeConstants.FIELD_UNDEFINED);
        message.setDate(date);
        message.setBold(true);
        message.setItalic(false);
        message.setUnderline(true);
        message.setFontcolor("black");
        message.setFontstyle("serif");
        message.setTimestamp(date);
        message.setFontsize(BigInteger.valueOf(13));
        messagesList.add(message);
        controller.saveChatSession(messagesList);
        ///////////////////code regarding XMLAPI//////////////////////////////
//        FXMLLoader loader = new FXMLLoader();
//        loader.setController(friendListController);
//        Parent frindListBox = null;
//        try {
//            frindListBox = loader.load(getClass().getResource("/fxml/mainStageFXMLs/FriendListFXML.fxml").openStream());
//            cntTab.setContent(frindListBox);
//        } catch (IOException ex) {
//            System.err.println("error in loading FriendListFXML.fxml");
//            ex.printStackTrace();
//        }
        friendListController = new FriendListController(this);
        cntTab.setContent(friendListController);

        headerController = new HeaderController(this, user);

        header.getChildren().addAll(headerController.getChildren());

        updateBTN.setOnAction((event) -> {

            try {
                EditProfileController profileController = new EditProfileController(user, controller);
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
        stage.setOnCloseRequest((event) -> {
            controller.saveAccount(user, false);
            user.setStatusEnum(StatusEnum.OFFLINE);
            controller.notifyStatusChange(user);
            controller.updateUser(user);
            System.exit(0);
        });
    }

    public void openSessionResponse(UUID sessionID) {

        ChatWindowFXMLController chatWindowController = new ChatWindowFXMLController(this, user, sessionID);
        sessions.put(sessionID, chatWindowController);

        Platform.runLater(() -> {
            chatArea.getChildren().clear();
            chatArea.getChildren().add(chatWindowController);
        });
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

    public List<User> getFriendList() {
        return controller.getFriendList(user);
    }

    public void notifyStatusChange(User user) {
        controller.notifyStatusChange(user);
    }

    //to be replaced with a notification
    public void receiveStatusChange(User user) {
        System.out.println(user.getStatusEnum());
    }

    public boolean enableChatBot(boolean chatBotEnabled) {
        this.chatBotEnabled = chatBotEnabled;
        return this.chatBotEnabled;
    }

    public void editProfile() {
        try {
            EditProfileController profileController = new EditProfileController(user, controller);
            FXMLLoader loader = new FXMLLoader();
            loader.setController(profileController);
            Parent root = loader.load(getClass().getResource("/fxml/other/EditProfile.fxml").openStream());

            Scene scene = new Scene(root);
            Stage stage1 = new Stage();
            stage1.setScene(scene);
            stage1.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(HomeViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void enableChatBot() {
        flag = true;
    }
}
