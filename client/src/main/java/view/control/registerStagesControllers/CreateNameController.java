package view.control.registerStagesControllers;

import view.control.registerStagesControllers.AddDetailsController;
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

import javafx.scene.control.TextField;

import javafx.scene.control.Label;

import javafx.scene.layout.FlowPane;

import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;

import javafx.scene.layout.BorderPane;

public class CreateNameController implements Initializable {

    @FXML
    private BorderPane borderPane;
    @FXML
    private Rectangle rectangle;
    @FXML
    private StackPane stackPane;
    @FXML
    private FlowPane flowPane;
    @FXML
    private Label infoLabel;
    @FXML
    private TextField firstNameTXF;
    @FXML
    private Label requiredLabel;
    @FXML
    private TextField lastNameTXF;
    @FXML
    private Button backBTN;
    @FXML
    private Button nextBTN;
    private Stage stage;
    private String firstName;
    private String lastName;
    private User user;
    private Controller controller;

    CreateNameController(Stage stage, User user, Controller controller) {
        this.stage = stage;
        this.user = user;
        this.controller = controller;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nextBTN.setOnAction(event -> {
            moveToDetailsController();
        });

        firstNameTXF.setOnAction((event) -> {
            moveToDetailsController();
        });

        lastNameTXF.setOnAction((event) -> {
            moveToDetailsController();
        });

        backBTN.setOnAction((event) -> {
            goBack();
        });
    }

    private void moveToDetailsController() {
        if (!firstNameTXF.getText().equals("") && !lastNameTXF.getText().equals("")) {
            firstName = firstNameTXF.getText();
            lastName = lastNameTXF.getText();
            user.setFirstName(firstName);
            user.setLastName(lastName);

            AddDetailsController addDetailsController = new AddDetailsController(stage, user, controller);
            FXMLLoader loader = new FXMLLoader();
            loader.setController(addDetailsController);
            Parent root = null;
            try {
                root = loader.load(getClass().getResource("/fxml/registerStagesFXMLs/AddDetails.fxml").openStream());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Scene scene = new Scene(root, 600, 600);

            scene.getStylesheets().add(getClass().getResource("/styles/application.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        } else {
            requiredLabel.setVisible(true);
        }
    }

    private void goBack() {
        try {
            CreatePasswordController createPasswordController = new CreatePasswordController(stage, user, controller);
            FXMLLoader loader = new FXMLLoader();
            loader.setController(createPasswordController);
            Parent root = loader.load(getClass().getResource("/fxml/registerStagesFXMLs/CreatePassword.fxml").openStream());
            Scene scene = new Scene(root, 400, 600);
            scene.getStylesheets().add(getClass().getResource("/styles/application.css").toExternalForm());
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {

        }
    }
}
