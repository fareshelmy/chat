package view.control.mainStageControllers;

import com.chat.common.User;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import view.control.HomeViewController;

/**
 * FXML Controller class
 *
 * @author rokaya
 */
public final class HeaderController extends AnchorPane {

    @FXML
    private ImageView userImage;

    @FXML
    private Label userName;

    @FXML
    private ToggleButton chatBotButton;

    @FXML
    private MenuBar optionsMenuBar;

    @FXML
    private Menu optionsMenu;

    @FXML
    private MenuItem editSelection;

    @FXML
    private MenuItem groupChatSelection;

    @FXML
    private MenuItem AddFrindSelection;

    @FXML
    private MenuItem signOutSelection;

    @FXML
    private MenuItem closeSelection;

    private HomeViewController controller;
    private User user;

    public HeaderController(HomeViewController controller, User user) {
        this.controller = controller;
        this.user = user;
        FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("/fxml/mainStageFXMLs/Header.fxml"));
        fXMLLoader.setRoot(this);
        fXMLLoader.setController(this);
        fXMLLoader.setClassLoader(getClass().getClassLoader());

        try {
            fXMLLoader.load();
        } catch (IOException ex) {
            System.err.println("error in loading Header.fxml");
        }
        userImage.setImage(new Image(new ByteArrayInputStream(user.getPic())));
        userName.setText(user.getFirstName());
        chatBotButton.setOnAction((event) -> {
            controller.enableChatBot();
//            String reguest = "Hi there";
//        String response = controller.sendMessageToBot(reguest);
//        System.out.println("Mr.happy: " + response);
//        controller.sendMessageToSession(sessionID, response);
        });
        editSelection.setOnAction((event) -> {
                controller.editProfile();
                System.out.println("ajuhu");
        });
    }

}
