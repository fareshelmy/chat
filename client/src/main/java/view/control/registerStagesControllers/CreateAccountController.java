package view.control.registerStagesControllers;

import com.chat.common.GenderEnum;
import com.chat.common.RegisteredByEnum;
import com.chat.common.StatusEnum;
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

import javafx.scene.layout.FlowPane;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;

import javafx.scene.layout.BorderPane;

public class CreateAccountController implements Initializable {

    @FXML
    private BorderPane borderPane;
    @FXML
    private Rectangle rectangle;
    @FXML
    private StackPane stackPane;
    @FXML
    private FlowPane flowPane;
    @FXML
    private ComboBox<String> countryKeyCode;
    @FXML
    private TextField phoneNumber;
    @FXML
    private Button backBTN;
    @FXML
    private Button nextBTN;

    @FXML
    private Label requiredLabel;

    Stage stage;
    private User user;
    private String phone;

    public CreateAccountController() {

    }

    public CreateAccountController(Stage stage) {
        this.stage = stage;
        user = new User(null, null, null, null, null, null, null, null, null, null, null, null);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        nextBTN.setOnAction(event -> {
            moveToPasswordController();
        });

        phoneNumber.setOnAction((event) -> {
            moveToPasswordController();
        });

        countryKeyCode.getItems().removeAll(countryKeyCode.getItems());
        countryKeyCode.getItems().addAll("+20", "+30", "+15");
        countryKeyCode.getSelectionModel().select("+30");
    }

    private void moveToPasswordController() {
        if (!phoneNumber.getText().equals("")) {
            phone = phoneNumber.getText();
            user.setPhone(phone);
            CreatePasswordController createPasswordController = new CreatePasswordController(stage, user);
            FXMLLoader loader = new FXMLLoader();
            loader.setController(createPasswordController);
            Parent root = null;
            try {
                root = loader.load(getClass().getResource("/fxml/registerStagesFXMLs/CreatePassword.fxml").openStream());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Scene scene = new Scene(root, 400, 600);

            scene.getStylesheets().add(getClass().getResource("/styles/application.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        } else {
            requiredLabel.setVisible(true);
        }
    }

}
