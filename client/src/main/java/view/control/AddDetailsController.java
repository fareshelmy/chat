package view.control;

import com.chat.common.RegisteredByEnum;
import com.chat.common.StatusEnum;
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

import javafx.scene.layout.FlowPane;

import javafx.scene.control.ComboBox;

import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;

import javafx.scene.layout.BorderPane;

import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class AddDetailsController implements Initializable {

    @FXML
    private BorderPane borderPane;
    @FXML
    private Rectangle rectangle;
    @FXML
    private StackPane stackPane;
    @FXML
    private FlowPane flowPane;
    @FXML
    private ComboBox<String> countryCBX;
    @FXML
    private DatePicker birthdatePocker;
    @FXML
    private Button backBTN;
    @FXML
    private Button nextBTN;

    @FXML
    private Label infoLabel;

    private User user;
    private String country;
    private String birthDate;

    Stage stage;
    private Controller controller;

    AddDetailsController(Stage stage, User user) {
        controller = new Controller();
        this.stage = stage;
        this.user = user;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        nextBTN.setOnAction(event -> {
            addUser();
        });

        countryCBX.getItems().removeAll(countryCBX.getItems());
        countryCBX.getItems().addAll("Egypt", "USA", "UK");
        countryCBX.getSelectionModel().select("Egypt");

    }

    private void addUser() {
        if (birthdatePocker.getValue() != null) {
            try {
                country = countryCBX.getValue();
                birthDate = birthdatePocker.getValue().toString();
                System.out.println(birthDate);
                user.setCountry(country);
                user.setDateOfBirth(birthDate);
                user.setStatusEnum(StatusEnum.ONLINE);
                user.setRegisteredBy(RegisteredByEnum.USER);
                controller.persistUser(user);
                ChatWindowFXMLController signInPasswordFXMLController = new ChatWindowFXMLController(stage, controller, user);
                FXMLLoader loader = new FXMLLoader();
                loader.setController(signInPasswordFXMLController);
                Parent root = loader.load(getClass().getResource("/fxml/ChatWindowFXML.fxml").openStream());
                Scene scene = new Scene(root, 400, 600);
                scene.getStylesheets().add(getClass().getResource("/styles/application.css").toExternalForm());
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AddDetailsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            infoLabel.setText("Birthday field is required");
            infoLabel.setTextFill(Color.RED);
        }
    }

}
