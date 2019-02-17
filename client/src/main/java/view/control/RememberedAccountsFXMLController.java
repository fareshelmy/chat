package view.control;

import com.chat.common.GenderEnum;
import com.chat.common.RegisteredByEnum;
import com.chat.common.StatusEnum;
import com.chat.common.User;
import controller.implementations.Controller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author FARES-LAP
 */
public class RememberedAccountsFXMLController implements Initializable {

    @FXML
    private ImageView personImageView;

    @FXML
    private ImageView plusImageView;

    @FXML
    private HBox newContactHBox;

    @FXML
    private HBox savedContactHBox;

    Stage stage;
    private Controller controller;

    public RememberedAccountsFXMLController() {

    }

    RememberedAccountsFXMLController(Stage stage, Controller controller) {
        this.stage = stage;
        this.controller = controller;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        personImageView.setImage(new Image(getClass().getResource("/images/person.jpg").toString()));
        plusImageView.setImage(new Image(getClass().getResource("/images/plus.png").toString()));

        newContactHBox.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
            try {
                SignInPhoneController signInPhoneController = new SignInPhoneController(stage, controller);
                FXMLLoader loader = new FXMLLoader();
                loader.setController(signInPhoneController);
                Parent root = loader.load(getClass().getResource("/fxml/SignInPhone.fxml").openStream());
                Scene scene = new Scene(root, 400, 600);
                scene.getStylesheets().add(getClass().getResource("/styles/application.css").toExternalForm());
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(RememberedAccountsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        savedContactHBox.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
            try {
                User user = controller.validatePhone("1234");
                SignInPasswordFXMLController signInPasswordFXMLController = new SignInPasswordFXMLController(stage, controller,user);             FXMLLoader loader = new FXMLLoader();
                loader.setController(signInPasswordFXMLController);
                Parent root = loader.load(getClass().getResource("/fxml/SignInPasswordFXML.fxml").openStream());
                Scene scene = new Scene(root, 400, 600);
                scene.getStylesheets().add(getClass().getResource("/styles/application.css").toExternalForm());
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(RememberedAccountsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        );
    }

}
