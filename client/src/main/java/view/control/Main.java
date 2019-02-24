package view.control;

import com.chat.common.GenderEnum;
import com.chat.common.RegisteredByEnum;
import com.chat.common.StatusEnum;
import com.chat.common.User;
import controller.implementations.Controller;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import view.control.signInStagesControllers.RememberedAccountsFXMLController;

public class Main extends Application {

    private Controller controller;

    public Main() {
        controller = new Controller();
    }

    @Override
    public void start(Stage primaryStage) {

        try {
//            EditProfileController profileController = new EditProfileController(new User("0100", "Yasmin", "Ayman",
//                    "0000", "yyy", new byte[10], GenderEnum.FEMALE, "Egypt", "hello", "hello there", StatusEnum.BUSY, RegisteredByEnum.USER));
//            FXMLLoader loader = new FXMLLoader();
//            loader.setController(profileController);
//            Parent root = loader.load(getClass().getResource("EditProfile.fxml").openStream());
//
//            Scene scene = new Scene(root);
//
//            primaryStage.setScene(scene);
//            primaryStage.show();

            RememberedAccountsFXMLController rememberedAccountsFXMLController = new RememberedAccountsFXMLController(primaryStage, controller);
            FXMLLoader loader = new FXMLLoader();
            loader.setController(rememberedAccountsFXMLController);
            Parent root = loader.load(getClass().getResource("/fxml/signInStagesFXMLs/RememberedAccountsFXML.fxml").openStream());
            Scene scene = new Scene(root, 400, 600);
            scene.getStylesheets().add(getClass().getResource("/styles/application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();

            primaryStage.setOnCloseRequest((event) -> {
                System.exit(0);
            });
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        new Main();
        launch(args);
    }
}
