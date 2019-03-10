//

package view.control.mainStageControllers;

import com.chat.common.GenderEnum;
import com.chat.common.RegisteredByEnum;
import com.chat.common.StatusEnum;
import com.chat.common.User;
import controller.implementations.Controller;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
//import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utility.ChatUtility;

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

    private ArrayList<Label> phoneLabels;
    private ArrayList<TextField> phoneTextFields;
    private User user;
    private Controller controller;
    private Stage stage;

    
    public AddContactsController(User user, Controller controller, Stage stage) {
        this.stage = stage;
        this.controller = controller;
        this.stage = stage;
        phoneLabels = new ArrayList<>();
        phoneTextFields = new ArrayList<>();
        this.user = user;

        user = new User("1111111111", "9", "9", "9", "9", new byte[1000], GenderEnum.FEMALE, "Egypt", new Date(), "9", StatusEnum.BUSY, RegisteredByEnum.USER);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Label msgMainLabel = new Label("");
        phoneLabels.add(msgMainLabel);

        TextField phoneMainTF = new TextField();
        phoneTextFields.add(phoneMainTF);
        allContactsVBOX.getChildren().add(new VBox(phoneMainTF, msgMainLabel));
        contactCounter++;

        plusBTN.setOnAction((event) -> {
            contactCounter++;
            if (contactCounter <= 5) {
                Label msgLabel = new Label("");
                phoneLabels.add(msgLabel);

                TextField phoneTF = new TextField();
                phoneTextFields.add(phoneTF);
                allContactsVBOX.getChildren().add(new VBox(phoneTF, msgLabel));
            } else {
                new Alert(Alert.AlertType.WARNING, "Only 5 contacts at a time allowed to add!").showAndWait();
            }
        });

        addContactsBTN.setOnAction((event) -> {
            for (int count = 0; count < phoneTextFields.size(); count++) {
                phoneLabels.get(count).setText("");
            }
            for (int count = 0; count < phoneTextFields.size(); count++) {
                String phoneNum = phoneTextFields.get(count).getText();

                if (ChatUtility.validatePhone(phoneNum)) {
                    User newUser = new User();
                    newUser.setPhone(phoneNum);
                    boolean userFound = controller.addContact(user, newUser);
                    System.out.println(userFound);

                    if (userFound == false) {
                        phoneLabels.get(count).setText("Phone not registered in our system :(");
                    } else {
                        List<User> frienList = controller.getFriendList(user);
                        boolean isFriend = false;
                        for (int i = 0; i < frienList.size(); i++) {
                            if (newUser.getPhone().equals(frienList.get(i))) {
                                phoneLabels.get(count).setText("You are already friends!");
                                isFriend = true;
                            }
                        }
                        if (isFriend == false) {
                            phoneLabels.get(count).setText("User added successfully ^_^");
                        }
                        System.out.println(isFriend);
                    }

                } else {
                    phoneLabels.get(count).setText("Please enter a valid phone number!");
                }
            }

        });
    }

}