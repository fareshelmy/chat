package view.control;

import sessionJAXB.MessageType;
import controller.implementations.Controller;
import java.io.IOException;
import java.util.List;
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
