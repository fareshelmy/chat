package view.control;

import controller.implementations.Controller;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    private final Controller controller;

    public MainApp() {
        controller = new Controller();
    }

    @Override
    public void start(Stage stage) throws Exception {
        AddContactsController addContactsController = new AddContactsController();
        FXMLLoader loader = new FXMLLoader();
        loader.setController(addContactsController);
        Parent root = loader.load(getClass().getResource("/fxml/AddContactsFXML.fxml").openStream());

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/styles/AddContactsCSS.css").toString());

        stage.setScene(scene);
        stage.show();
        
//        MainView root = new MainView(controller);
//
//        Scene scene = new Scene(root);
//        scene.getStylesheets().add("/styles/Styles.css");
//
//        stage.setTitle("JavaFX and Maven");
//        stage.setScene(scene);
//        stage.show();
    }

    public static void main(String[] args) {
        new MainApp();
        launch(args);
    }

}
