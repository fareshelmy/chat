package view.control.registerStagesControllers;

import com.chat.common.RegisteredByEnum;
import com.chat.common.User;
import com.chat.utils.FieldValidationUtil;
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

import javafx.scene.control.TextField;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import view.control.HomeViewController;
import view.control.signInStagesControllers.SignInPhoneController;

public class CreatePasswordController implements Initializable {

    @FXML
    private BorderPane borderPane;
    @FXML
    private Rectangle rectangle;
    @FXML
    private Label passwordInfoLBL;

    @FXML
    private PasswordField passwordTXF;

    @FXML
    private Button backBTN;
    @FXML
    private Button NextBTN;
    private Stage stage;
    private String password;
    private User user;
    private Controller controller;

    CreatePasswordController(Stage stage, User user, Controller controller) {
        this.stage = stage;
        this.user = user;
        this.controller = controller;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        NextBTN.setOnAction(event -> {
            goNext();
        });

        passwordTXF.setOnAction((event) -> {
            goNext();
        });

        backBTN.setOnAction((event) -> {
            goBack();
        });

    }

    private void goNext() {
        password = passwordTXF.getText();
        if (!password.equals("") && FieldValidationUtil.validatePassword(password)) {
            user.setPassword(password);
            if (user.getRegisteredByEnum().equals(RegisteredByEnum.ADMIN)) {
                controller.updateUser(user);

                HomeViewController homeViewController = new HomeViewController(stage, controller, user);
                FXMLLoader loader = new FXMLLoader();
                loader.setController(homeViewController);
                Parent root;
                try {
                    root = loader.load(getClass().getResource("/fxml/mainStageFXMLs/LogedInView.fxml").openStream());
                    Scene scene = new Scene(root, 600, 600);
                    scene.getStylesheets().add(getClass().getResource("/styles/application.css").toExternalForm());
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(AddDetailsController.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {

                CreateNameController createAccountController = new CreateNameController(stage, user, controller);
                FXMLLoader loader = new FXMLLoader();
                loader.setController(createAccountController);
                Parent root = null;
                try {
                    root = loader.load(getClass().getResource("/fxml/registerStagesFXMLs/CreateAccountSecond.fxml").openStream());
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                Scene scene = new Scene(root, 400, 600);

                scene.getStylesheets().add(getClass().getResource("/styles/application.css").toExternalForm());
                stage.setScene(scene);
                stage.show();
            }
        } else {
            passwordInfoLBL.setText("Password must contain at least 8 characters and at least one uppercase letter, one lowercase letter, and one number");
            passwordInfoLBL.setTextFill(Color.RED);
        }
    }

    private void goBack() {
        try {
            CreatePhoneController createAccountController = new CreatePhoneController(stage, controller);
            FXMLLoader loader = new FXMLLoader();
            loader.setController(createAccountController);
            Parent root = loader.load(getClass().getResource("/fxml/registerStagesFXMLs/CreateAccount.fxml").openStream());
            Scene scene = new Scene(root, 400, 600);
            scene.getStylesheets().add(getClass().getResource("/styles/application.css").toExternalForm());
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {

        }
    }
}
