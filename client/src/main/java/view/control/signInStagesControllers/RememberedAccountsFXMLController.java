package view.control.signInStagesControllers;

import accountsJAXB.AccountType;
import com.chat.common.StatusEnum;
import com.chat.common.User;
import controller.implementations.Controller;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.control.HomeViewController;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author FARES-LAP
 */
public class RememberedAccountsFXMLController implements Initializable {

    @FXML
    private VBox vBox;

    Stage stage;
    private Controller controller;

    public RememberedAccountsFXMLController() {

    }

    public RememberedAccountsFXMLController(Stage stage, Controller controller) {
        this.stage = stage;
        this.controller = controller;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AccountHBox newAccountHBox = new AccountHBox(null);
        newAccountHBox.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
            try {
                SignInPhoneController signInPhoneController = new SignInPhoneController(stage, controller);
                FXMLLoader loader = new FXMLLoader();
                loader.setController(signInPhoneController);
                Parent root = loader.load(getClass().getResource("/fxml/signInStagesFXMLs/SignInPhone.fxml").openStream());
                Scene scene = new Scene(root, 400, 600);
                scene.getStylesheets().add(getClass().getResource("/styles/application.css").toExternalForm());
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(RememberedAccountsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        vBox.getChildren().add(newAccountHBox);

        new Thread(() -> {
            List<AccountType> accounts = controller.loadAccounts();
            accounts.forEach((account) -> {
                User user = controller.validatePhone(account.getAccountId());
                if (account.isSaved()) {
                    user.setStatusEnum(StatusEnum.ONLINE);
                    controller.updateUser(user);
                    controller.notifyStatusChange(user);
                    HomeViewController homeViewController = new HomeViewController(stage, controller, user);
                    FXMLLoader loader = new FXMLLoader();
                    loader.setController(homeViewController);
                    Platform.runLater(() -> {
                        try {
                            Parent root = loader.load(getClass().getResource("/fxml/mainStageFXMLs/LogedInView.fxml").openStream());
                            Scene scene = new Scene(root, 600, 600);
                            scene.getStylesheets().add(getClass().getResource("/styles/application.css").toExternalForm());
                            stage.setScene(scene);
                            stage.setResizable(true);
                            stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(RememberedAccountsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });
                } else {
                    if (user != null) {
                        AccountHBox accountHBox = new AccountHBox(user);
                        accountHBox.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
                            try {
                                SignInPasswordFXMLController signInPasswordFXMLController = new SignInPasswordFXMLController(stage, controller, user);
                                FXMLLoader loader = new FXMLLoader();
                                loader.setController(signInPasswordFXMLController);
                                Parent root = loader.load(getClass().getResource("/fxml/signInStagesFXMLs/SignInPasswordFXML.fxml").openStream());
                                Scene scene = new Scene(root, 400, 600);
                                scene.getStylesheets().add(getClass().getResource("/styles/application.css").toExternalForm());
                                stage.setScene(scene);
                                stage.show();
                            } catch (IOException ex) {
                                Logger.getLogger(RememberedAccountsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                        Platform.runLater(() -> {
                            vBox.getChildren().add(accountHBox);
                        });
                    }
                }
            });
        }).start();
    }

}
