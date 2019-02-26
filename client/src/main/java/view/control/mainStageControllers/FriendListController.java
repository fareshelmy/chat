/////*
//// * To change this license header, choose License Headers in Project Properties.
//// * To change this template file, choose Tools | Templates
//// * and open the template in the editor.
//// */
////package view.control.mainStageControllers;
////
////
////import com.chat.common.User;
////import view.control.HomeViewController;
////import java.net.URL;
////
////import java.util.List;
////import java.util.ResourceBundle;
////import javafx.fxml.FXML;
////import javafx.fxml.Initializable;
////import javafx.scene.control.Label;
////import javafx.scene.image.Image;
////import javafx.scene.image.ImageView;
////import javafx.scene.input.MouseButton;
////import javafx.scene.layout.HBox;
////import javafx.scene.layout.StackPane;
////import javafx.scene.layout.VBox;
////
/////**
//// * FXML Controller class
//// *
//// * @author rokaya
//// */
////public class FriendListController implements Initializable {
////
////    @FXML
////    private StackPane ListContainer;
////
////    private final HomeViewController homeViewController;
////
////    private final List<User> frindList;
////
////    public FriendListController(List<User> frindList, HomeViewController viewController) {
////        this.homeViewController = viewController;
////        this.frindList = frindList;
////    }
////
////    /**
////     * Initializes the controller class.
////     *
////     * @param url
////     * @param rb
////     */
////    @Override
////    public void initialize(URL url, ResourceBundle rb) {
////
////        if (!frindList.isEmpty()) {
////            ListContainer.getChildren().clear();
////            VBox vBox = new VBox();
////            ListContainer.getChildren().add(vBox);
////            frindList.forEach((friend) -> {
////                vBox.getChildren().add(setFreindBox(friend));
////            });
////        }
////    }
////
////    private HBox setFreindBox(User friend) {
////
////        HBox friendBox = new HBox();
////        friendBox.setOnMouseClicked((event) -> {
////            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
////                homeViewController.openSessionRequest(friend);
////            }
////        });
////        ImageView imageView = new ImageView(new Image(getClass().getResource("/images/default-profile.png").toString()));
////        imageView.setFitHeight(30);
////        imageView.setFitWidth(30);
////        friendBox.getChildren().addAll(imageView, new Label("friend.getName()"));
////        return friendBox;
////    }
////}
//
//
//package view.control.mainStageControllers;
//
//import com.chat.common.User;
//import java.io.IOException;
//import view.control.HomeViewController;
//
//import java.util.List;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.geometry.Insets;
//import javafx.scene.control.Label;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.input.MouseButton;
//import javafx.scene.layout.Background;
//import javafx.scene.layout.BackgroundFill;
//import javafx.scene.layout.CornerRadii;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.StackPane;
//import javafx.scene.layout.VBox;
//import javafx.scene.paint.Color;
//
///**
// * FXML Controller class
// *
// * @author rokaya
// */
//public class FriendListController extends StackPane {
//
//    @FXML
//    private StackPane ListContainer;
//    @FXML
//    private VBox frindListBox;
//    
//    private VBox vBox ;
//
//    private final HomeViewController homeViewController;
//
//    private final List<User> frindList;
//
//    public FriendListController(HomeViewController viewController) {
//        this.homeViewController = viewController;
//        this.frindList = viewController.getFriendList();
//        this.vBox=null;
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/mainStageFXMLs/FriendListFXML.fxml"));
//        loader.setRoot(this);
//        loader.setController(this);
//        loader.setClassLoader(getClass().getClassLoader());
//        try {
//            loader.load();
//        } catch (IOException ex) {
//            System.err.println("error in loading FriendListFXML.fxml");
//            ex.printStackTrace();
//        }
//        if (!frindList.isEmpty()) {
//            System.out.println(frindListBox.getChildren()); 
//            ListContainer.getChildren().clear();
//            vBox = new VBox();
//            vBox.setStyle(frindListBox.getStyle());
//            ListContainer.getChildren().add(vBox);
//            frindList.forEach((friend) -> {
//                vBox.getChildren().add(setFreindBox(friend));
//            });
//        }
//    }
//    
//    private HBox setFreindBox(User friend) {
//
//        HBox friendBox = new HBox();
//        friendBox.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
//        friendBox.getStyleClass().add("frind-Box");
//        friendBox.setOnMouseClicked((event) -> {
//            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
//                homeViewController.openSessionRequest(friend);
//            }
//        });
//        ImageView imageView = new ImageView(new Image(getClass().getResource("/images/default-profile.png").toString()));
//        imageView.setFitHeight(30);
//        imageView.setFitWidth(30);
//        friendBox.getChildren().addAll(imageView, new Label(friend.getFirstName()));
//        return friendBox;
//    }
//}

package view.control.mainStageControllers;

import com.chat.common.User;
import java.io.IOException;
import view.control.HomeViewController;

import java.util.List;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author rokaya
 */
public class FriendListController extends StackPane {

    @FXML
    private StackPane ListContainer;
    @FXML
    private VBox frindListBox;
    
    private VBox vBox ;

    private final HomeViewController homeViewController;

    private final List<User> frindList;

    public FriendListController(HomeViewController viewController) {
        this.homeViewController = viewController;
        this.frindList = viewController.getFriendList();
        this.vBox=null;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/mainStageFXMLs/FriendListFXML.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.setClassLoader(getClass().getClassLoader());
        try {
            loader.load();
        } catch (IOException ex) {
            System.err.println("error in loading FriendListFXML.fxml");
        }
        if (!frindList.isEmpty()) {
            ListContainer.getChildren().clear();
            vBox = new VBox();
            vBox.setStyle(frindListBox.getStyle());
            ListContainer.getChildren().add(vBox);
            frindList.forEach((friend) -> {
                vBox.getChildren().add(setFreindBox(friend));
            });
        }
    }
    
    private HBox setFreindBox(User friend) {

        HBox friendBox = new HBox();
        friendBox.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        friendBox.getStyleClass().add("frind-Box");
        friendBox.setOnMouseClicked((event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                homeViewController.openSessionRequest(friend);
            }
        });
        ImageView imageView = new ImageView(new Image(getClass().getResource("/images/default-profile.png").toString()));
        imageView.setFitHeight(30);
        imageView.setFitWidth(30);
        friendBox.getChildren().addAll(imageView, new Label(friend.getFirstName()));
        return friendBox;
    }
}