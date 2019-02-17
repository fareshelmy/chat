/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.control;

import com.chat.common.User;
import controller.implementations.Controller;
import view.view.MyHBox;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author FARES-LAP
 */
public class ChatWindowFXMLController implements Initializable {

    @FXML
    private VBox vBox;

    private final Stage stage;
    private final Controller controller;
    private final User user;

    ChatWindowFXMLController(Stage stage, Controller controller, User user) {
        this.stage = stage;
        this.controller = controller;
        this.user = user;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stage.setMinWidth(1000);
        stage.setMinHeight(600);

        vBox.getChildren().add(new MyHBox());
    }

}
