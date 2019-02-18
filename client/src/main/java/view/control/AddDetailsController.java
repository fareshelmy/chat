package view.control;

import com.chat.common.User;
import controller.implementations.Controller;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.layout.FlowPane;

import javafx.scene.control.ComboBox;

import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;

import javafx.scene.layout.BorderPane;

import javafx.scene.control.DatePicker;

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

    private User user;
    private String country;
    private String birthDate;

    Stage stage;
    private Controller controller;
    public AddDetailsController(Stage stage) {
        controller = new Controller();
        this.stage = stage;
    }

    AddDetailsController(Stage stage, User user) {
        this.user = user;
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        nextBTN.setOnAction(event -> {

            country = countryCBX.getValue();
            birthDate = birthdatePocker.getValue().toString();
            user.setCountry(country);
            user.setDateOfBirth(birthDate);
            
            controller.persistUser(user);
            

        });

        countryCBX.getItems().removeAll(countryCBX.getItems());
        countryCBX.getItems().addAll("Egypt", "USA", "UK");
        countryCBX.getSelectionModel().select("USA");

    }

}
