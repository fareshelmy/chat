package view.control;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author yasmin
 */
public class AddContactsController implements Initializable {

    @FXML
    private AnchorPane mainPane;

    @FXML
    private VBox allContactsVBOX;

    @FXML
    private Button addContactsBTN;

    @FXML
    private Button plusBTN;

    private int contactCounter = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Label msgMainLabel = new Label("please enter a valid registered phone!");
        TextField phoneMainTF = new TextField();
        allContactsVBOX.getChildren().add(new VBox(phoneMainTF, msgMainLabel));
        contactCounter++;

        addContactsBTN.setOnAction((event) -> {

        });

        plusBTN.setOnAction((event) -> {
            contactCounter++;
            if (contactCounter < 5) {
                Label msgLabel = new Label("please enter a valid registered phone!");
                TextField phoneTF = new TextField();
                allContactsVBOX.getChildren().add(new VBox(phoneTF, msgLabel));
            } else {
                new Alert(Alert.AlertType.ERROR, "Only allawed adding 4 contacts at a time!").showAndWait();
            }
        });
    }

}
