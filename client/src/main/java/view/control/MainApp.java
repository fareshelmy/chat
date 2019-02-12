package view.control;

import controller.implementations.Controller;
import view.view.MainView;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    private final Controller controller;

    public MainApp() {
        controller = new Controller();
    }

    @Override
    public void start(Stage stage) throws Exception {
        MainView root = new MainView(controller);

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");

        stage.setTitle("JavaFX and Maven");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        new MainApp();
        launch(args);
    }

}
