/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.control.signInStagesControllers;

import com.chat.common.StatusEnum;
import com.chat.common.User;
import controller.implementations.Controller;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import view.control.HomeViewController;

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
                user.setStatusEnum(StatusEnum.ONLINE);
                controller.updateUser(user);
                controller.notifyStatusChange(user);
                HomeViewController homeViewController = new HomeViewController(stage, controller, user);
                FXMLLoader loader = new FXMLLoader();
                loader.setController(homeViewController);
                Parent root = loader.load(getClass().getResource("/fxml/mainStageFXMLs/LogedInView.fxml").openStream());
                Scene scene = new Scene(root, 600, 600);
                scene.getStylesheets().add(getClass().getResource("/styles/application.css").toExternalForm());
                stage.setScene(scene);
                stage.setResizable(true);
                stage.show();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            wrongPasswordLabel.setVisible(true);
        }
    }
}
