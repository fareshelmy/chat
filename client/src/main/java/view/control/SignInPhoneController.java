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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;

public class SignInPhoneController implements Initializable {

    @FXML
    private TextField phoneTXF;
    @FXML
    private Label createLBL;
    @FXML
    private Label invalidLabel;
    @FXML
    private Button nextButton;
    
    Stage stage;
    Controller controller;

    public SignInPhoneController(Stage stage, Controller controller) {
        this.stage = stage;
        this.controller = controller;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        createLBL.setOnMouseClicked(value -> {
            CreateAccountController createAccountController = new CreateAccountController(stage);
            FXMLLoader loader = new FXMLLoader();
            loader.setController(createAccountController);
            Parent root = null;
            try {
                root = loader.load(getClass().getResource("/fxml/CreateAccount.fxml").openStream());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Scene scene = new Scene(root, 400, 600);

            scene.getStylesheets().add(getClass().getResource("/styles/application.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        });

        phoneTXF.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                moveToPasswordValidation();
            }
        });
        
        nextButton.setOnAction((event) -> {
            moveToPasswordValidation();
        });
    }

    private void moveToPasswordValidation() {
        User user = controller.validatePhone(phoneTXF.getText());
        if (user != null) {
            try {
                SignInPasswordFXMLController signInPasswordFXMLController = new SignInPasswordFXMLController(stage, controller, user);
                FXMLLoader loader = new FXMLLoader();
                loader.setController(signInPasswordFXMLController);
                Parent root = loader.load(getClass().getResource("/fxml/SignInPasswordFXML.fxml").openStream());
                Scene scene = new Scene(root, 400, 600);
                scene.getStylesheets().add(getClass().getResource("/styles/application.css").toExternalForm());
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(SignInPhoneController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            invalidLabel.setVisible(true);
        }
    }

}
