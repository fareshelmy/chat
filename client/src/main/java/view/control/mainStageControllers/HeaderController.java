package view.control.mainStageControllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import view.control.HomeViewController;

/**
 * FXML Controller class
 *
 * @author rokaya
 */
public final class HeaderController extends AnchorPane{

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
    public HeaderController(HomeViewController controller) {
        this.controller=controller;
        FXMLLoader fXMLLoader=new FXMLLoader(getClass().getResource("/fxml/mainStageFXMLs/Header.fxml"));
        fXMLLoader.setRoot(this);
        fXMLLoader.setController(this);
        fXMLLoader.setClassLoader(getClass().getClassLoader());
        try {
            fXMLLoader.load();
        } catch (IOException ex) {
            System.err.println("error in loading Header.fxml");
        }
        
    }

}