/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.control;

import com.chat.common.User;
import controller.implementations.Controller;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

/**
 *
 * @author FARES-LAP
 */
public class SignInPasswordFXMLController implements Initializable {

    @FXML
    private PasswordField passwordField;
    @FXML
    private Label wrongPasswordLabel;

    @FXML
    private Button signInButton;

    private final Controller controller;
    private final Stage stage;
    private final User user;

    public SignInPasswordFXMLController(Stage stage, Controller controller, User user) {
        this.stage = stage;
        this.controller = controller;
        this.user = user;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        passwordField.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                moveToMainWindow();
            }
        });

        signInButton.setOnAction((event) -> {
            moveToMainWindow();
        });
    }

    private void moveToMainWindow() {
        if (passwordField.getText().equals(user.getPassword())) {
            try {
                ChatWindowFXMLController signInPasswordFXMLController = new ChatWindowFXMLController(stage, controller, user);
                FXMLLoader loader = new FXMLLoader();
                loader.setController(signInPasswordFXMLController);
                Parent root = loader.load(getClass().getResource("/fxml/ChatWindowFXML.fxml").openStream());
                Scene scene = new Scene(root, 400, 600);
                scene.getStylesheets().add(getClass().getResource("/styles/application.css").toExternalForm());
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(SignInPasswordFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            wrongPasswordLabel.setVisible(true);
        }
    }
}
