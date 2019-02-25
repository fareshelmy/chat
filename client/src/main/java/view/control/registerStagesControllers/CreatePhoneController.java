package view.control.registerStagesControllers;

import com.chat.common.GenderEnum;
import com.chat.common.RegisteredByEnum;
import com.chat.common.StatusEnum;
import com.chat.common.User;
import com.chat.utils.FieldValidationUtil;
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

import javafx.scene.layout.FlowPane;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;

import javafx.scene.layout.BorderPane;
import view.control.signInStagesControllers.RememberedAccountsFXMLController;
import view.control.signInStagesControllers.SignInPhoneController;

public class CreatePhoneController implements Initializable {

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
    private Controller controller;

    public CreatePhoneController(Stage stage, Controller controller) {
        this.stage = stage;
        this.controller = controller;
        user = new User();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        nextBTN.setOnAction(event -> {
            phoneRegisterationHandlling();
        });

        phoneNumber.setOnAction((event) -> {
            phoneRegisterationHandlling();
        });

        countryKeyCode.getItems().removeAll(countryKeyCode.getItems());
        countryKeyCode.getItems().addAll("+20", "+30", "+15");
        countryKeyCode.getSelectionModel().select("+30");

        backBTN.setOnAction((event) -> {
            goBack();
        });
    }

    private void phoneRegisterationHandlling() {
        phone = phoneNumber.getText();

        if (!phone.equals("") && FieldValidationUtil.validatePhone(phone)) {
            user = controller.validatePhone(phone);

            if (user != null) {
                System.out.println(user.getRegisteredByEnum());
                System.out.println(RegisteredByEnum.USER);
                if (RegisteredByEnum.valueOf(user.getRegisteredByEnum()).equals(RegisteredByEnum.USER)) {
                    System.out.println("By user");
                    requiredLabel.setText("Phone already registered!");
                    requiredLabel.setVisible(true);

                } else {
                    System.out.println("By admin");
                    moveToPasswordController();
                }
            } else {
                moveToPasswordController();
            }

        } else {
            System.out.println("null");

            requiredLabel.setVisible(true);
        }
    }

    private void goBack() {
        try {
            SignInPhoneController signInPhoneController = new SignInPhoneController(stage, controller);
            FXMLLoader loader = new FXMLLoader();
            loader.setController(signInPhoneController);
            Parent root = loader.load(getClass().getResource("/fxml/signInStagesFXMLs/SignInPhone.fxml").openStream());
            Scene scene = new Scene(root, 400, 600);
            scene.getStylesheets().add(getClass().getResource("/styles/application.css").toExternalForm());
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {

        }
    }

    private void phoneValidation() {

    }

    private void moveToPasswordController() {
        CreatePasswordController createPasswordController = new CreatePasswordController(stage, user, controller);
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
    }
}
