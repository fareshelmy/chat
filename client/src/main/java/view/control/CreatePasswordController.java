package view.control;

import com.chat.common.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
    Stage stage;
    private String password;
    private User user;

    CreatePasswordController(Stage stage, User user) {
        this.stage = stage;
        this.user = user;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        NextBTN.setOnAction(event -> {
            moveToCreateAccountController();
        });

        passwordTXF.setOnAction((event) -> {
            moveToCreateAccountController();
        });
    }

    private void moveToCreateAccountController() {
        if (!passwordTXF.getText().equals("")) {
            password = passwordTXF.getText();
            user.setPassword(password);
            CreateAccount_2Controller createAccountController = new CreateAccount_2Controller(stage, user);
            FXMLLoader loader = new FXMLLoader();
            loader.setController(createAccountController);
            Parent root = null;
            try {
                root = loader.load(getClass().getResource("/fxml/CreateAccount_2.fxml").openStream());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Scene scene = new Scene(root, 400, 600);

            scene.getStylesheets().add(getClass().getResource("/styles/application.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        } else {
            passwordInfoLBL.setText("Password must contain at least 8 characters and at least one uppercase letter, one lowercase letter, and one number");
            passwordInfoLBL.setTextFill(Color.RED);
        }
    }

}
