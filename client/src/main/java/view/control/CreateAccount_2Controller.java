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

import javafx.scene.layout.FlowPane;

import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;

import javafx.scene.layout.BorderPane;

public class CreateAccount_2Controller implements Initializable {
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
	Stage stage;
        private String firstName;
        private String lastName;
        private User user;

	public CreateAccount_2Controller(Stage stage) {
		this.stage = stage;
	}

    CreateAccount_2Controller(Stage stage, User user) {
        this.user = user;
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		nextBTN.setOnAction(event -> {
                    firstName = firstNameTXF.getText();
                    lastName = lastNameTXF.getText();
                    user.setFirstName(firstName);
                    user.setLastName(lastName);
                    
			AddDetailsController addDetailsController = new AddDetailsController(stage,user);
			FXMLLoader loader = new FXMLLoader();
			loader.setController(addDetailsController);
			Parent root = null;
			try {
				root = loader.load(getClass().getResource("AddDetails.fxml").openStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Scene scene = new Scene(root, 600, 600);

			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
		});

	}

}
