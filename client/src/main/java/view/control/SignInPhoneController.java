package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

public class SignInPhoneController implements Initializable {
	@FXML
	private TextField phoneTXF;
	@FXML
	private Label createLBL;
	Stage stage;

	public SignInPhoneController(Stage stage) {
		this.stage = stage;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		createLBL.setOnMouseClicked(value -> {
			CreateAccountController createAccountController = new CreateAccountController(stage);
			FXMLLoader loader = new FXMLLoader();
			loader.setController(createAccountController);
			Parent root = null;
			try {
				root = loader.load(getClass().getResource("CreateAccount.fxml").openStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Scene scene = new Scene(root, 600, 400);

			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
		});

	}

}
