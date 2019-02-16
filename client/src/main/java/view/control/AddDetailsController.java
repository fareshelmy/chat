package application;

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

	Stage stage;

	public AddDetailsController(Stage stage) {
		this.stage = stage;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		countryCBX.getItems().removeAll(countryCBX.getItems());
		countryCBX.getItems().addAll("Egypt", "USA", "UK");
		countryCBX.getSelectionModel().select("USA");

	}

}
