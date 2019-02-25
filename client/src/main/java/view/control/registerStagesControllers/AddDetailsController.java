package view.control.registerStagesControllers;

import com.chat.common.GenderEnum;
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
import view.control.HomeViewController;

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

    private Stage stage;
    private Controller controller;

    AddDetailsController(Stage stage, User user, Controller controller) {
        this.controller = controller;
        this.stage = stage;
        this.user = user;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        countryCBX.getItems().removeAll(countryCBX.getItems());
        countryCBX.getItems().addAll("Egypt", "USA", "UK");
        countryCBX.getSelectionModel().select("Egypt");
        nextBTN.setOnAction(event -> {
            goNext();
        });
        backBTN.setOnAction((event) -> {
            goBack();
        });

    }

    private void goNext() {
        if (birthdatePocker.getValue() != null) {
            country = countryCBX.getValue();
            birthDate = birthdatePocker.getValue().toString();
            System.out.println(birthDate);
            user.setCountry(country);
            user.setDateOfBirth(birthDate);
            AddDetailsSecondController addDetailsSecondController = new AddDetailsSecondController(stage, user, controller);
            FXMLLoader loader = new FXMLLoader();
            loader.setController(addDetailsSecondController);
            Parent root;
            try {
                root = loader.load(getClass().getResource("/fxml/registerStagesFXMLs/AddDetailsSecond.fxml").openStream());
                Scene scene = new Scene(root, 600, 600);
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

    private void goBack() {
        try {
            CreateNameController accountSecondController = new CreateNameController(stage, user , controller);
            FXMLLoader loader = new FXMLLoader();
            loader.setController(accountSecondController);
            Parent root = loader.load(getClass().getResource("/fxml/registerStagesFXMLs/CreateAccountSecond.fxml").openStream());
            Scene scene = new Scene(root, 400, 600);
            scene.getStylesheets().add(getClass().getResource("/styles/application.css").toExternalForm());
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {

        }
    }

}
