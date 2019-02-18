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

import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;

public class CreatePasswordController implements Initializable {

    @FXML
    private BorderPane borderPane;
    @FXML
    private Rectangle rectangle;
    @FXML
    private Label passwordInfoLBL;
    @FXML
    private Label passwordINFOWarning;
    @FXML
    private TextField passwordTXF;
    @FXML
    private Label licenceLBL;
    @FXML
    private Button backBTN;
    @FXML
    private Button NextBTN;
    Stage stage;
    private String password;
    private User user;

    public CreatePasswordController(Stage stage) {
        this.stage = stage;
    }

    CreatePasswordController(Stage stage, User user) {
        this.user = user;
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        NextBTN.setOnAction(event -> {
            password = passwordTXF.getText();
            user.setPassword(password);
            CreateAccount_2Controller createAccountController = new CreateAccount_2Controller(stage,user);
            FXMLLoader loader = new FXMLLoader();
            loader.setController(createAccountController);
            Parent root = null;
            try {
                root = loader.load(getClass().getResource("/fxml/CreateAccount_2.fxml").openStream());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Scene scene = new Scene(root, 600, 400);

            scene.getStylesheets().add(getClass().getResource("/styles/application.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        });

    }

}
