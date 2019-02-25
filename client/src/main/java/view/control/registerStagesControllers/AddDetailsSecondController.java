/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.control.registerStagesControllers;

import com.chat.common.GenderEnum;
import com.chat.common.RegisteredByEnum;
import com.chat.common.StatusEnum;
import com.chat.common.User;
import com.sun.xml.internal.ws.api.ha.StickyFeature;
import controller.implementations.Controller;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import view.control.HomeViewController;

/**
 * FXML Controller class
 *
 * @author yasmi
 */
public class AddDetailsSecondController implements Initializable {

    @FXML
    private BorderPane borderPane;
    @FXML
    private Rectangle rectangle;
    @FXML
    private StackPane stackPane;
    @FXML
    private FlowPane flowPane;
    @FXML
    private Circle circleImage;
    @FXML
    private TextField emailTXF;
    @FXML
    private ComboBox<String> genderCOMX;
    @FXML
    private Button backBTN;
    @FXML
    private Button nextBTN;

    @FXML
    private TextArea bioTextArea;

    /**
     * Initializes the controller class.
     */
    private Stage stage;
    private User user;
    private Controller controller;
    private File imageFile;
    private Image userImage;
    private Label infoLabel;

    private String country;
    private String birthDate;

    private String email, gender, bio;
    private File profileImage;

    public AddDetailsSecondController(Stage primaryStage, User user, Controller controller) {
        this.stage = primaryStage;
        this.user = user;
        this.controller = controller;

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        genderCOMX.getItems().removeAll(genderCOMX.getItems());
        genderCOMX.getItems().addAll("Male", "Fmale");
        genderCOMX.getSelectionModel().select("Male");

        circleImage.setOnMousePressed(event -> {

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose Image");
            File selectedFile = fileChooser.showOpenDialog(stage);
            if (selectedFile != null) {
                imageFile = new File(selectedFile.getPath());
                userImage = new Image(imageFile.toURI().toString());
                //userImageView.setImage(userImage);
                circleImage.setFill(new ImagePattern(userImage));
            }

        });

        nextBTN.setOnAction((event) -> {
            addUser();
        });

        backBTN.setOnAction((event) -> {
            goBack();
        });
    }

    private void goBack() {
        try {
            AddDetailsController addDetailsController = new AddDetailsController(stage, user, controller);
            FXMLLoader loader = new FXMLLoader();
            loader.setController(addDetailsController);
            Parent root = loader.load(getClass().getResource("/fxml/registerStagesFXMLs/AddDetails.fxml").openStream());
            Scene scene = new Scene(root, 400, 600);
            scene.getStylesheets().add(getClass().getResource("/styles/application.css").toExternalForm());
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {

        }
    }

    private void addUser() {
        if (emailTXF.getText() != null) {
            try {
                email = emailTXF.getText();
                gender = genderCOMX.getValue();
                bio = bioTextArea.getText();
                profileImage = imageFile;
                user.setPic(Files.readAllBytes(profileImage.toPath()));
                
                user.setEmail(email);
                user.setBio(bio);
                if (gender.equals("Male")) {
                    user.setGenderEnum(GenderEnum.MALE);
                } else {
                    user.setGenderEnum(GenderEnum.FEMALE);
                }
                user.setRegisteredBy(RegisteredByEnum.USER);
                user.setStatusEnum(StatusEnum.AVAILABLE);
                controller.persistUser(user);

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

            } catch (IOException ex) {
                Logger.getLogger(AddDetailsSecondController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            infoLabel.setText("Birthday field is required");
            infoLabel.setTextFill(Color.RED);
        }
    }

}
